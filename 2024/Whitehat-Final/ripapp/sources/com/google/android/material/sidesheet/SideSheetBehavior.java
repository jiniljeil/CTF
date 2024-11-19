package com.google.android.material.sidesheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.LinkedHashSet;
import java.util.Set;

public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements Sheet<SideSheetCallback> {
    private static final int DEFAULT_ACCESSIBILITY_PANE_TITLE = R.string.side_sheet_accessibility_pane_title;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SideSheet;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int NO_MAX_SIZE = -1;
    static final int SIGNIFICANT_VEL_THRESHOLD = 500;
    private ColorStateList backgroundTint;
    private final Set<SideSheetCallback> callbacks = new LinkedHashSet();
    /* access modifiers changed from: private */
    public int childWidth;
    private int coplanarSiblingViewId = -1;
    private WeakReference<View> coplanarSiblingViewRef;
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
        public boolean tryCaptureView(View view, int i) {
            if (SideSheetBehavior.this.state == 1 || SideSheetBehavior.this.viewRef == null || SideSheetBehavior.this.viewRef.get() != view) {
                return false;
            }
            return true;
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            ViewGroup.MarginLayoutParams marginLayoutParams;
            View coplanarSiblingView = SideSheetBehavior.this.getCoplanarSiblingView();
            if (!(coplanarSiblingView == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) == null)) {
                SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                coplanarSiblingView.setLayoutParams(marginLayoutParams);
            }
            SideSheetBehavior.this.dispatchOnSlide(view, i);
        }

        public void onViewDragStateChanged(int i) {
            if (i == 1 && SideSheetBehavior.this.draggable) {
                SideSheetBehavior.this.setStateInternal(1);
            }
        }

        public void onViewReleased(View view, float f, float f2) {
            int access$500 = SideSheetBehavior.this.calculateTargetStateOnViewReleased(view, f, f2);
            SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
            sideSheetBehavior.startSettling(view, access$500, sideSheetBehavior.shouldSkipSmoothAnimation());
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return MathUtils.clamp(i, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
        }

        public int getViewHorizontalDragRange(View view) {
            return SideSheetBehavior.this.childWidth + SideSheetBehavior.this.getInnerMargin();
        }
    };
    /* access modifiers changed from: private */
    public boolean draggable = true;
    private float elevation;
    private float hideFriction = 0.1f;
    private boolean ignoreEvents;
    private int initialX;
    private int innerMargin;
    private int lastStableState = 5;
    private MaterialShapeDrawable materialShapeDrawable;
    private float maximumVelocity;
    private int parentInnerEdge;
    private int parentWidth;
    private ShapeAppearanceModel shapeAppearanceModel;
    /* access modifiers changed from: private */
    public SheetDelegate sheetDelegate;
    private MaterialSideContainerBackHelper sideContainerBackHelper;
    /* access modifiers changed from: private */
    public int state = 5;
    private final SideSheetBehavior<V>.StateSettlingTracker stateSettlingTracker = new StateSettlingTracker();
    private VelocityTracker velocityTracker;
    /* access modifiers changed from: private */
    public ViewDragHelper viewDragHelper;
    /* access modifiers changed from: private */
    public WeakReference<V> viewRef;

    /* access modifiers changed from: package-private */
    public float getHideThreshold() {
        return 0.5f;
    }

    /* access modifiers changed from: package-private */
    public int getSignificantVelocityThreshold() {
        return SIGNIFICANT_VEL_THRESHOLD;
    }

    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    public SideSheetBehavior() {
    }

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SideSheetBehavior_Layout);
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_backgroundTint)) {
            this.backgroundTint = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.SideSheetBehavior_Layout_backgroundTint);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.shapeAppearanceModel = ShapeAppearanceModel.builder(context, attributeSet, 0, DEF_STYLE_RES).build();
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId)) {
            setCoplanarSiblingViewId(obtainStyledAttributes.getResourceId(R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId, -1));
        }
        createMaterialShapeDrawableIfNeeded(context);
        this.elevation = obtainStyledAttributes.getDimension(R.styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        setDraggable(obtainStyledAttributes.getBoolean(R.styleable.SideSheetBehavior_Layout_behavior_draggable, true));
        obtainStyledAttributes.recycle();
        this.maximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private void setSheetEdge(V v, int i) {
        setSheetEdge(GravityCompat.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) v.getLayoutParams()).gravity, i) == 3 ? 1 : 0);
    }

    private void setSheetEdge(int i) {
        SheetDelegate sheetDelegate2 = this.sheetDelegate;
        if (sheetDelegate2 != null && sheetDelegate2.getSheetEdge() == i) {
            return;
        }
        if (i == 0) {
            this.sheetDelegate = new RightSheetDelegate(this);
            if (this.shapeAppearanceModel != null && !hasRightMargin()) {
                ShapeAppearanceModel.Builder builder = this.shapeAppearanceModel.toBuilder();
                builder.setTopRightCornerSize(0.0f).setBottomRightCornerSize(0.0f);
                updateMaterialShapeDrawable(builder.build());
            }
        } else if (i == 1) {
            this.sheetDelegate = new LeftSheetDelegate(this);
            if (this.shapeAppearanceModel != null && !hasLeftMargin()) {
                ShapeAppearanceModel.Builder builder2 = this.shapeAppearanceModel.toBuilder();
                builder2.setTopLeftCornerSize(0.0f).setBottomLeftCornerSize(0.0f);
                updateMaterialShapeDrawable(builder2.build());
            }
        } else {
            throw new IllegalArgumentException("Invalid sheet edge position value: " + i + ". Must be 0 or 1.");
        }
    }

    private int getGravityFromSheetEdge() {
        SheetDelegate sheetDelegate2 = this.sheetDelegate;
        if (sheetDelegate2 == null || sheetDelegate2.getSheetEdge() == 0) {
            return 5;
        }
        return 3;
    }

    private boolean hasRightMargin() {
        CoordinatorLayout.LayoutParams viewLayoutParams = getViewLayoutParams();
        return viewLayoutParams != null && viewLayoutParams.rightMargin > 0;
    }

    private boolean hasLeftMargin() {
        CoordinatorLayout.LayoutParams viewLayoutParams = getViewLayoutParams();
        return viewLayoutParams != null && viewLayoutParams.leftMargin > 0;
    }

    private CoordinatorLayout.LayoutParams getViewLayoutParams() {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || (view = (View) weakReference.get()) == null || !(view.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return null;
        }
        return (CoordinatorLayout.LayoutParams) view.getLayoutParams();
    }

    private void updateMaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel2) {
        MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel2);
        }
    }

    public void expand() {
        setState(3);
    }

    public void hide() {
        setState(5);
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (SideSheetBehavior<?>) this);
    }

    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        }
        int i = (savedState.state == 1 || savedState.state == 2) ? 5 : savedState.state;
        this.state = i;
        this.lastStableState = i;
    }

    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        v.measure(getChildMeasureSpec(i, coordinatorLayout.getPaddingLeft() + coordinatorLayout.getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, -1, marginLayoutParams.width), getChildMeasureSpec(i3, coordinatorLayout.getPaddingTop() + coordinatorLayout.getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, -1, marginLayoutParams.height));
        return true;
    }

    private int getChildMeasureSpec(int i, int i2, int i3, int i4) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, i2, i4);
        if (i3 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), BasicMeasure.EXACTLY);
        }
        if (size != 0) {
            i3 = Math.min(size, i3);
        }
        return View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.viewRef = new WeakReference<>(v);
            this.sideContainerBackHelper = new MaterialSideContainerBackHelper(v);
            MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
            if (materialShapeDrawable2 != null) {
                ViewCompat.setBackground(v, materialShapeDrawable2);
                MaterialShapeDrawable materialShapeDrawable3 = this.materialShapeDrawable;
                float f = this.elevation;
                if (f == -1.0f) {
                    f = ViewCompat.getElevation(v);
                }
                materialShapeDrawable3.setElevation(f);
            } else {
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    ViewCompat.setBackgroundTintList(v, colorStateList);
                }
            }
            updateSheetVisibility(v);
            updateAccessibilityActions();
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
            ensureAccessibilityPaneTitleIsSet(v);
        }
        setSheetEdge(v, i);
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        int outerEdge = this.sheetDelegate.getOuterEdge(v);
        coordinatorLayout.onLayoutChild(v, i);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(coordinatorLayout);
        this.childWidth = v.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        this.innerMargin = marginLayoutParams != null ? this.sheetDelegate.calculateInnerMargin(marginLayoutParams) : 0;
        ViewCompat.offsetLeftAndRight(v, calculateCurrentOffset(outerEdge, v));
        maybeAssignCoplanarSiblingViewBasedId(coordinatorLayout);
        for (SheetCallback next : this.callbacks) {
            if (next instanceof SideSheetCallback) {
                ((SideSheetCallback) next).onLayout(v);
            }
        }
        return true;
    }

    private void updateSheetVisibility(View view) {
        int i = this.state == 5 ? 4 : 0;
        if (view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }

    private void ensureAccessibilityPaneTitleIsSet(View view) {
        if (ViewCompat.getAccessibilityPaneTitle(view) == null) {
            ViewCompat.setAccessibilityPaneTitle(view, view.getResources().getString(DEFAULT_ACCESSIBILITY_PANE_TITLE));
        }
    }

    private void maybeAssignCoplanarSiblingViewBasedId(CoordinatorLayout coordinatorLayout) {
        int i;
        View findViewById;
        if (this.coplanarSiblingViewRef == null && (i = this.coplanarSiblingViewId) != -1 && (findViewById = coordinatorLayout.findViewById(i)) != null) {
            this.coplanarSiblingViewRef = new WeakReference<>(findViewById);
        }
    }

    /* access modifiers changed from: package-private */
    public int getChildWidth() {
        return this.childWidth;
    }

    /* access modifiers changed from: package-private */
    public int getParentWidth() {
        return this.parentWidth;
    }

    /* access modifiers changed from: package-private */
    public int getParentInnerEdge() {
        return this.parentInnerEdge;
    }

    /* access modifiers changed from: package-private */
    public int getInnerMargin() {
        return this.innerMargin;
    }

    private int calculateCurrentOffset(int i, V v) {
        int i2 = this.state;
        if (i2 == 1 || i2 == 2) {
            return i - this.sheetDelegate.getOuterEdge(v);
        }
        if (i2 == 3) {
            return 0;
        }
        if (i2 == 5) {
            return this.sheetDelegate.getHiddenOffset();
        }
        throw new IllegalStateException("Unexpected value: " + this.state);
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper2;
        if (!shouldInterceptTouchEvent(v)) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            resetVelocity();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.initialX = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.ignoreEvents) {
            this.ignoreEvents = false;
            return false;
        }
        if (this.ignoreEvents || (viewDragHelper2 = this.viewDragHelper) == null || !viewDragHelper2.shouldInterceptTouchEvent(motionEvent)) {
            return false;
        }
        return true;
    }

    private boolean shouldInterceptTouchEvent(V v) {
        return (v.isShown() || ViewCompat.getAccessibilityPaneTitle(v) != null) && this.draggable;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        if (shouldHandleDraggingWithHelper()) {
            this.viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            resetVelocity();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents && isDraggedFarEnough(motionEvent)) {
            this.viewDragHelper.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    private boolean isDraggedFarEnough(MotionEvent motionEvent) {
        if (shouldHandleDraggingWithHelper() && calculateDragDistance((float) this.initialX, motionEvent.getX()) > ((float) this.viewDragHelper.getTouchSlop())) {
            return true;
        }
        return false;
    }

    private float calculateDragDistance(float f, float f2) {
        return Math.abs(f - f2);
    }

    public int getExpandedOffset() {
        return this.sheetDelegate.getExpandedOffset();
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    public void setHideFriction(float f) {
        this.hideFriction = f;
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public void addCallback(SideSheetCallback sideSheetCallback) {
        this.callbacks.add(sideSheetCallback);
    }

    public void removeCallback(SideSheetCallback sideSheetCallback) {
        this.callbacks.remove(sideSheetCallback);
    }

    public void setState(int i) {
        if (i == 1 || i == 2) {
            throw new IllegalArgumentException("STATE_" + (i == 1 ? "DRAGGING" : "SETTLING") + " should not be set externally.");
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            setStateInternal(i);
        } else {
            runAfterLayout((View) this.viewRef.get(), new SideSheetBehavior$$ExternalSyntheticLambda0(this, i));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setState$0$com-google-android-material-sidesheet-SideSheetBehavior  reason: not valid java name */
    public /* synthetic */ void m130lambda$setState$0$comgoogleandroidmaterialsidesheetSideSheetBehavior(int i) {
        View view = (View) this.viewRef.get();
        if (view != null) {
            startSettling(view, i, false);
        }
    }

    private void runAfterLayout(V v, Runnable runnable) {
        if (isLayingOut(v)) {
            v.post(runnable);
        } else {
            runnable.run();
        }
    }

    private boolean isLayingOut(V v) {
        ViewParent parent = v.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(v);
    }

    public int getState() {
        return this.state;
    }

    /* access modifiers changed from: package-private */
    public void setStateInternal(int i) {
        View view;
        if (this.state != i) {
            this.state = i;
            if (i == 3 || i == 5) {
                this.lastStableState = i;
            }
            WeakReference<V> weakReference = this.viewRef;
            if (weakReference != null && (view = (View) weakReference.get()) != null) {
                updateSheetVisibility(view);
                for (SideSheetCallback onStateChanged : this.callbacks) {
                    onStateChanged.onStateChanged(view, i);
                }
                updateAccessibilityActions();
            }
        }
    }

    private void resetVelocity() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.velocityTracker = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldHide(View view, float f) {
        return this.sheetDelegate.shouldHide(view, f);
    }

    private boolean shouldHandleDraggingWithHelper() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    private void createMaterialShapeDrawableIfNeeded(Context context) {
        if (this.shapeAppearanceModel != null) {
            MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.materialShapeDrawable = materialShapeDrawable2;
            materialShapeDrawable2.initializeElevationOverlay(context);
            ColorStateList colorStateList = this.backgroundTint;
            if (colorStateList != null) {
                this.materialShapeDrawable.setFillColor(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16842801, typedValue, true);
            this.materialShapeDrawable.setTint(typedValue.data);
        }
    }

    /* access modifiers changed from: package-private */
    public float getXVelocity() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 == null) {
            return 0.0f;
        }
        velocityTracker2.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getXVelocity();
    }

    /* access modifiers changed from: private */
    public void startSettling(View view, int i, boolean z) {
        if (isSettling(view, i, z)) {
            setStateInternal(2);
            this.stateSettlingTracker.continueSettlingToState(i);
            return;
        }
        setStateInternal(i);
    }

    private boolean isSettling(View view, int i, boolean z) {
        int outerEdgeOffsetForState = getOuterEdgeOffsetForState(i);
        ViewDragHelper viewDragHelper2 = getViewDragHelper();
        return viewDragHelper2 != null && (!z ? viewDragHelper2.smoothSlideViewTo(view, outerEdgeOffsetForState, view.getTop()) : viewDragHelper2.settleCapturedViewAt(outerEdgeOffsetForState, view.getTop()));
    }

    /* access modifiers changed from: package-private */
    public int getOuterEdgeOffsetForState(int i) {
        if (i == 3) {
            return getExpandedOffset();
        }
        if (i == 5) {
            return this.sheetDelegate.getHiddenOffset();
        }
        throw new IllegalArgumentException("Invalid state to get outer edge offset: " + i);
    }

    /* access modifiers changed from: package-private */
    public ViewDragHelper getViewDragHelper() {
        return this.viewDragHelper;
    }

    /* access modifiers changed from: private */
    public int calculateTargetStateOnViewReleased(View view, float f, float f2) {
        if (isExpandingOutwards(f)) {
            return 3;
        }
        if (shouldHide(view, f)) {
            if (!this.sheetDelegate.isSwipeSignificant(f, f2) && !this.sheetDelegate.isReleasedCloseToInnerEdge(view)) {
                return 3;
            }
        } else if (f == 0.0f || !SheetUtils.isSwipeMostlyHorizontal(f, f2)) {
            int left = view.getLeft();
            if (Math.abs(left - getExpandedOffset()) < Math.abs(left - this.sheetDelegate.getHiddenOffset())) {
                return 3;
            }
        }
        return 5;
    }

    private boolean isExpandingOutwards(float f) {
        return this.sheetDelegate.isExpandingOutwards(f);
    }

    /* access modifiers changed from: private */
    public void dispatchOnSlide(View view, int i) {
        if (!this.callbacks.isEmpty()) {
            float calculateSlideOffset = this.sheetDelegate.calculateSlideOffset(i);
            for (SideSheetCallback onSlide : this.callbacks) {
                onSlide.onSlide(view, calculateSlideOffset);
            }
        }
    }

    public void setCoplanarSiblingViewId(int i) {
        this.coplanarSiblingViewId = i;
        clearCoplanarSiblingView();
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (i != -1 && ViewCompat.isLaidOut(view)) {
                view.requestLayout();
            }
        }
    }

    public void setCoplanarSiblingView(View view) {
        this.coplanarSiblingViewId = -1;
        if (view == null) {
            clearCoplanarSiblingView();
            return;
        }
        this.coplanarSiblingViewRef = new WeakReference<>(view);
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            View view2 = (View) weakReference.get();
            if (ViewCompat.isLaidOut(view2)) {
                view2.requestLayout();
            }
        }
    }

    public View getCoplanarSiblingView() {
        WeakReference<View> weakReference = this.coplanarSiblingViewRef;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    private void clearCoplanarSiblingView() {
        WeakReference<View> weakReference = this.coplanarSiblingViewRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.coplanarSiblingViewRef = null;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    public void startBackProgress(BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            materialSideContainerBackHelper.startBackProgress(backEventCompat);
        }
    }

    public void updateBackProgress(BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            materialSideContainerBackHelper.updateBackProgress(backEventCompat, getGravityFromSheetEdge());
            updateCoplanarSiblingBackProgress();
        }
    }

    private void updateCoplanarSiblingBackProgress() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && weakReference.get() != null) {
            View view = (View) this.viewRef.get();
            View coplanarSiblingView = getCoplanarSiblingView();
            if (coplanarSiblingView != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) != null) {
                this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, (int) ((((float) this.childWidth) * view.getScaleX()) + ((float) this.innerMargin)));
                coplanarSiblingView.requestLayout();
            }
        }
    }

    public void handleBackInvoked() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            BackEventCompat onHandleBackInvoked = materialSideContainerBackHelper.onHandleBackInvoked();
            if (onHandleBackInvoked == null || Build.VERSION.SDK_INT < 34) {
                setState(5);
            } else {
                this.sideContainerBackHelper.finishBackProgress(onHandleBackInvoked, getGravityFromSheetEdge(), new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SideSheetBehavior.this.setStateInternal(5);
                        if (SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() != null) {
                            ((View) SideSheetBehavior.this.viewRef.get()).requestLayout();
                        }
                    }
                }, getCoplanarFinishAnimatorUpdateListener());
            }
        }
    }

    private ValueAnimator.AnimatorUpdateListener getCoplanarFinishAnimatorUpdateListener() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        View coplanarSiblingView = getCoplanarSiblingView();
        if (coplanarSiblingView == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) == null) {
            return null;
        }
        return new SideSheetBehavior$$ExternalSyntheticLambda2(this, marginLayoutParams, this.sheetDelegate.getCoplanarSiblingAdjacentMargin(marginLayoutParams), coplanarSiblingView);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCoplanarFinishAnimatorUpdateListener$1$com-google-android-material-sidesheet-SideSheetBehavior  reason: not valid java name */
    public /* synthetic */ void m129lambda$getCoplanarFinishAnimatorUpdateListener$1$comgoogleandroidmaterialsidesheetSideSheetBehavior(ViewGroup.MarginLayoutParams marginLayoutParams, int i, View view, ValueAnimator valueAnimator) {
        this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, AnimationUtils.lerp(i, 0, valueAnimator.getAnimatedFraction()));
        view.requestLayout();
    }

    public void cancelBackProgress() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            materialSideContainerBackHelper.cancelBackProgress();
        }
    }

    /* access modifiers changed from: package-private */
    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    class StateSettlingTracker {
        private final Runnable continueSettlingRunnable = new SideSheetBehavior$StateSettlingTracker$$ExternalSyntheticLambda0(this);
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;

        StateSettlingTracker() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-google-android-material-sidesheet-SideSheetBehavior$StateSettlingTracker  reason: not valid java name */
        public /* synthetic */ void m131lambda$new$0$comgoogleandroidmaterialsidesheetSideSheetBehavior$StateSettlingTracker() {
            this.isContinueSettlingRunnablePosted = false;
            if (SideSheetBehavior.this.viewDragHelper != null && SideSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                continueSettlingToState(this.targetState);
            } else if (SideSheetBehavior.this.state == 2) {
                SideSheetBehavior.this.setStateInternal(this.targetState);
            }
        }

        /* access modifiers changed from: package-private */
        public void continueSettlingToState(int i) {
            if (SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() != null) {
                this.targetState = i;
                if (!this.isContinueSettlingRunnablePosted) {
                    ViewCompat.postOnAnimation((View) SideSheetBehavior.this.viewRef.get(), this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }
    }

    protected static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, SideSheetBehavior<?> sideSheetBehavior) {
            super(parcelable);
            this.state = sideSheetBehavior.state;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    public static <V extends View> SideSheetBehavior<V> from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof SideSheetBehavior) {
                return (SideSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with SideSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private void updateAccessibilityActions() {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, 1048576);
            if (this.state != 5) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            if (this.state != 3) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    private void replaceAccessibilityActionForState(V v, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i) {
        ViewCompat.replaceAccessibilityAction(v, accessibilityActionCompat, (CharSequence) null, createAccessibilityViewCommandForState(i));
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int i) {
        return new SideSheetBehavior$$ExternalSyntheticLambda1(this, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createAccessibilityViewCommandForState$2$com-google-android-material-sidesheet-SideSheetBehavior  reason: not valid java name */
    public /* synthetic */ boolean m128lambda$createAccessibilityViewCommandForState$2$comgoogleandroidmaterialsidesheetSideSheetBehavior(int i, View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        setState(i);
        return true;
    }
}
