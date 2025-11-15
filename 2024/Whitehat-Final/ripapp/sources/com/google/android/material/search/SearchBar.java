package com.google.android.material.search;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;

public class SearchBar extends Toolbar {
    private static final int DEFAULT_SCROLL_FLAGS = 53;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SearchBar;
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    /* access modifiers changed from: private */
    public final AccessibilityManager accessibilityManager;
    private MaterialShapeDrawable backgroundShape;
    private View centerView;
    private final boolean defaultMarginsEnabled;
    private final Drawable defaultNavigationIcon;
    private boolean defaultScrollFlagsEnabled;
    private final boolean forceDefaultNavigationOnClickListener;
    private final boolean layoutInflated;
    private int menuResId;
    private Integer navigationIconTint;
    private Drawable originalNavigationIconBackground;
    private final SearchBarAnimationHelper searchBarAnimationHelper;
    private final TextView textView;
    private final boolean tintNavigationIcon;
    /* access modifiers changed from: private */
    public final AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    public static abstract class OnLoadAnimationCallback {
        public void onAnimationEnd() {
        }

        public void onAnimationStart() {
        }
    }

    private int defaultIfZero(int i, int i2) {
        return i == 0 ? i2 : i;
    }

    public void setSubtitle(CharSequence charSequence) {
    }

    public void setTitle(CharSequence charSequence) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-material-search-SearchBar  reason: not valid java name */
    public /* synthetic */ void m113lambda$new$0$comgoogleandroidmaterialsearchSearchBar(boolean z) {
        setFocusableInTouchMode(z);
    }

    public SearchBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SearchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSearchBarStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SearchBar(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            r10 = this;
            int r6 = DEF_STYLE_RES
            android.content.Context r11 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r11, r12, r13, r6)
            r10.<init>(r11, r12, r13)
            r11 = -1
            r10.menuResId = r11
            com.google.android.material.search.SearchBar$$ExternalSyntheticLambda1 r0 = new com.google.android.material.search.SearchBar$$ExternalSyntheticLambda1
            r0.<init>(r10)
            r10.touchExplorationStateChangeListener = r0
            android.content.Context r7 = r10.getContext()
            r10.validateAttributes(r12)
            int r0 = r10.getDefaultNavigationIconResource()
            android.graphics.drawable.Drawable r0 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r7, r0)
            r10.defaultNavigationIcon = r0
            com.google.android.material.search.SearchBarAnimationHelper r0 = new com.google.android.material.search.SearchBarAnimationHelper
            r0.<init>()
            r10.searchBarAnimationHelper = r0
            int[] r2 = com.google.android.material.R.styleable.SearchBar
            r8 = 0
            int[] r5 = new int[r8]
            r0 = r7
            r1 = r12
            r3 = r13
            r4 = r6
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r12 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r7, (android.util.AttributeSet) r12, (int) r13, (int) r6)
            com.google.android.material.shape.ShapeAppearanceModel r2 = r12.build()
            int r12 = com.google.android.material.R.styleable.SearchBar_backgroundTint
            int r3 = r0.getColor(r12, r8)
            int r12 = com.google.android.material.R.styleable.SearchBar_elevation
            r13 = 0
            float r4 = r0.getDimension(r12, r13)
            int r12 = com.google.android.material.R.styleable.SearchBar_defaultMarginsEnabled
            r13 = 1
            boolean r12 = r0.getBoolean(r12, r13)
            r10.defaultMarginsEnabled = r12
            int r12 = com.google.android.material.R.styleable.SearchBar_defaultScrollFlagsEnabled
            boolean r12 = r0.getBoolean(r12, r13)
            r10.defaultScrollFlagsEnabled = r12
            int r12 = com.google.android.material.R.styleable.SearchBar_hideNavigationIcon
            boolean r12 = r0.getBoolean(r12, r8)
            int r1 = com.google.android.material.R.styleable.SearchBar_forceDefaultNavigationOnClickListener
            boolean r1 = r0.getBoolean(r1, r8)
            r10.forceDefaultNavigationOnClickListener = r1
            int r1 = com.google.android.material.R.styleable.SearchBar_tintNavigationIcon
            boolean r1 = r0.getBoolean(r1, r13)
            r10.tintNavigationIcon = r1
            int r1 = com.google.android.material.R.styleable.SearchBar_navigationIconTint
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L_0x0088
            int r1 = com.google.android.material.R.styleable.SearchBar_navigationIconTint
            int r1 = r0.getColor(r1, r11)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r10.navigationIconTint = r1
        L_0x0088:
            int r1 = com.google.android.material.R.styleable.SearchBar_android_textAppearance
            int r11 = r0.getResourceId(r1, r11)
            int r1 = com.google.android.material.R.styleable.SearchBar_android_text
            java.lang.String r1 = r0.getString(r1)
            int r5 = com.google.android.material.R.styleable.SearchBar_android_hint
            java.lang.String r5 = r0.getString(r5)
            int r6 = com.google.android.material.R.styleable.SearchBar_strokeWidth
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r6 = r0.getDimension(r6, r9)
            int r9 = com.google.android.material.R.styleable.SearchBar_strokeColor
            int r8 = r0.getColor(r9, r8)
            r0.recycle()
            if (r12 != 0) goto L_0x00b0
            r10.initNavigationIcon()
        L_0x00b0:
            r10.setClickable(r13)
            r10.setFocusable(r13)
            android.view.LayoutInflater r12 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_search_bar
            r12.inflate(r0, r10)
            r10.layoutInflated = r13
            int r12 = com.google.android.material.R.id.open_search_bar_text_view
            android.view.View r12 = r10.findViewById(r12)
            android.widget.TextView r12 = (android.widget.TextView) r12
            r10.textView = r12
            androidx.core.view.ViewCompat.setElevation(r10, r4)
            r10.initTextView(r11, r1, r5)
            r1 = r10
            r5 = r6
            r6 = r8
            r1.initBackground(r2, r3, r4, r5, r6)
            android.content.Context r11 = r10.getContext()
            java.lang.String r12 = "accessibility"
            java.lang.Object r11 = r11.getSystemService(r12)
            android.view.accessibility.AccessibilityManager r11 = (android.view.accessibility.AccessibilityManager) r11
            r10.accessibilityManager = r11
            r10.setupTouchExplorationStateChangeListener()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.SearchBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void setupTouchExplorationStateChangeListener() {
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null) {
            if (accessibilityManager2.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled()) {
                setFocusableInTouchMode(true);
            }
            addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View view) {
                    AccessibilityManagerCompat.addTouchExplorationStateChangeListener(SearchBar.this.accessibilityManager, SearchBar.this.touchExplorationStateChangeListener);
                }

                public void onViewDetachedFromWindow(View view) {
                    AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(SearchBar.this.accessibilityManager, SearchBar.this.touchExplorationStateChangeListener);
                }
            });
        }
    }

    private void validateAttributes(AttributeSet attributeSet) {
        if (attributeSet != null) {
            if (attributeSet.getAttributeValue(NAMESPACE_APP, "title") != null) {
                throw new UnsupportedOperationException("SearchBar does not support title. Use hint or text instead.");
            } else if (attributeSet.getAttributeValue(NAMESPACE_APP, "subtitle") != null) {
                throw new UnsupportedOperationException("SearchBar does not support subtitle. Use hint or text instead.");
            }
        }
    }

    private void initNavigationIcon() {
        setNavigationIcon(getNavigationIcon() == null ? this.defaultNavigationIcon : getNavigationIcon());
        setNavigationIconDecorative(true);
    }

    private void initTextView(int i, String str, String str2) {
        if (i != -1) {
            TextViewCompat.setTextAppearance(this.textView, i);
        }
        setText((CharSequence) str);
        setHint((CharSequence) str2);
        if (getNavigationIcon() == null) {
            MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) this.textView.getLayoutParams(), getResources().getDimensionPixelSize(R.dimen.m3_searchbar_text_margin_start_no_navigation_icon));
        }
    }

    private void initBackground(ShapeAppearanceModel shapeAppearanceModel, int i, float f, float f2, int i2) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        this.backgroundShape = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(getContext());
        this.backgroundShape.setElevation(f);
        if (f2 >= 0.0f) {
            this.backgroundShape.setStroke(f2, i2);
        }
        int color = MaterialColors.getColor(this, R.attr.colorControlHighlight);
        this.backgroundShape.setFillColor(ColorStateList.valueOf(i));
        ColorStateList valueOf = ColorStateList.valueOf(color);
        MaterialShapeDrawable materialShapeDrawable2 = this.backgroundShape;
        ViewCompat.setBackground(this, new RippleDrawable(valueOf, materialShapeDrawable2, materialShapeDrawable2));
    }

    private ColorStateList getCompatBackgroundColorStateList(int i, int i2) {
        int layer = MaterialColors.layer(i, i2);
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842908}, new int[0]}, new int[]{layer, layer, i});
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.layoutInflated && this.centerView == null && !(view instanceof ActionMenuView)) {
            this.centerView = view;
            view.setAlpha(0.0f);
        }
        super.addView(view, i, layoutParams);
    }

    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeDrawable materialShapeDrawable = this.backgroundShape;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setElevation(f);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getCanonicalName());
        accessibilityNodeInfo.setEditable(isEnabled());
        CharSequence text = getText();
        boolean isEmpty = TextUtils.isEmpty(text);
        if (Build.VERSION.SDK_INT >= 26) {
            accessibilityNodeInfo.setHintText(getHint());
            accessibilityNodeInfo.setShowingHintText(isEmpty);
        }
        if (isEmpty) {
            text = getHint();
        }
        accessibilityNodeInfo.setText(text);
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        if (!this.forceDefaultNavigationOnClickListener) {
            super.setNavigationOnClickListener(onClickListener);
            setNavigationIconDecorative(onClickListener == null);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        super.setNavigationIcon(maybeTintNavigationIcon(drawable));
    }

    private Drawable maybeTintNavigationIcon(Drawable drawable) {
        int i;
        int i2;
        if (!this.tintNavigationIcon || drawable == null) {
            return drawable;
        }
        Integer num = this.navigationIconTint;
        if (num != null) {
            i = num.intValue();
        } else {
            if (drawable == this.defaultNavigationIcon) {
                i2 = R.attr.colorOnSurfaceVariant;
            } else {
                i2 = R.attr.colorOnSurface;
            }
            i = MaterialColors.getColor(this, i2);
        }
        Drawable wrap = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(wrap, i);
        return wrap;
    }

    private void setNavigationIconDecorative(boolean z) {
        Drawable drawable;
        ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this);
        if (navigationIconButton != null) {
            navigationIconButton.setClickable(!z);
            navigationIconButton.setFocusable(!z);
            Drawable background = navigationIconButton.getBackground();
            if (background != null) {
                this.originalNavigationIconBackground = background;
            }
            if (z) {
                drawable = null;
            } else {
                drawable = this.originalNavigationIconBackground;
            }
            navigationIconButton.setBackgroundDrawable(drawable);
            setHandwritingBoundsInsets();
        }
    }

    public void inflateMenu(int i) {
        Menu menu = getMenu();
        boolean z = menu instanceof MenuBuilder;
        if (z) {
            ((MenuBuilder) menu).stopDispatchingItemsChanged();
        }
        super.inflateMenu(i);
        this.menuResId = i;
        if (z) {
            ((MenuBuilder) menu).startDispatchingItemsChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureCenterView(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        layoutCenterView();
        setHandwritingBoundsInsets();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.backgroundShape);
        setDefaultMargins();
        setOrClearDefaultScrollFlags();
    }

    private void setDefaultMargins() {
        if (this.defaultMarginsEnabled && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.m3_searchbar_margin_horizontal);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(getDefaultMarginVerticalResource());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.leftMargin = defaultIfZero(marginLayoutParams.leftMargin, dimensionPixelSize);
            marginLayoutParams.topMargin = defaultIfZero(marginLayoutParams.topMargin, dimensionPixelSize2);
            marginLayoutParams.rightMargin = defaultIfZero(marginLayoutParams.rightMargin, dimensionPixelSize);
            marginLayoutParams.bottomMargin = defaultIfZero(marginLayoutParams.bottomMargin, dimensionPixelSize2);
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultMarginVerticalResource() {
        return R.dimen.m3_searchbar_margin_vertical;
    }

    /* access modifiers changed from: protected */
    public int getDefaultNavigationIconResource() {
        return R.drawable.ic_search_black_24;
    }

    private void setOrClearDefaultScrollFlags() {
        if (getLayoutParams() instanceof AppBarLayout.LayoutParams) {
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) getLayoutParams();
            if (this.defaultScrollFlagsEnabled) {
                if (layoutParams.getScrollFlags() == 0) {
                    layoutParams.setScrollFlags(53);
                }
            } else if (layoutParams.getScrollFlags() == 53) {
                layoutParams.setScrollFlags(0);
            }
        }
    }

    private void measureCenterView(int i, int i2) {
        View view = this.centerView;
        if (view != null) {
            view.measure(i, i2);
        }
    }

    private void layoutCenterView() {
        View view = this.centerView;
        if (view != null) {
            int measuredWidth = view.getMeasuredWidth();
            int measuredWidth2 = (getMeasuredWidth() / 2) - (measuredWidth / 2);
            int i = measuredWidth2 + measuredWidth;
            int measuredHeight = this.centerView.getMeasuredHeight();
            int measuredHeight2 = (getMeasuredHeight() / 2) - (measuredHeight / 2);
            layoutChild(this.centerView, measuredWidth2, measuredHeight2, i, measuredHeight2 + measuredHeight);
        }
    }

    private void layoutChild(View view, int i, int i2, int i3, int i4) {
        if (ViewCompat.getLayoutDirection(this) == 1) {
            view.layout(getMeasuredWidth() - i3, i2, getMeasuredWidth() - i, i4);
        } else {
            view.layout(i, i2, i3, i4);
        }
    }

    private void setHandwritingBoundsInsets() {
        if (Build.VERSION.SDK_INT >= 34) {
            boolean z = true;
            int i = 0;
            if (getLayoutDirection() != 1) {
                z = false;
            }
            ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this);
            int width = (navigationIconButton == null || !navigationIconButton.isClickable()) ? 0 : z ? getWidth() - navigationIconButton.getLeft() : navigationIconButton.getRight();
            ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(this);
            if (actionMenuView != null) {
                i = z ? actionMenuView.getRight() : getWidth() - actionMenuView.getLeft();
            }
            float f = (float) (-(z ? i : width));
            if (!z) {
                width = i;
            }
            setHandwritingBoundsOffsets(f, 0.0f, (float) (-width), 0.0f);
        }
    }

    public View getCenterView() {
        return this.centerView;
    }

    public void setCenterView(View view) {
        View view2 = this.centerView;
        if (view2 != null) {
            removeView(view2);
            this.centerView = null;
        }
        if (view != null) {
            addView(view);
        }
    }

    public TextView getTextView() {
        return this.textView;
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public void setText(CharSequence charSequence) {
        this.textView.setText(charSequence);
    }

    public void setText(int i) {
        this.textView.setText(i);
    }

    public void clearText() {
        this.textView.setText("");
    }

    public CharSequence getHint() {
        return this.textView.getHint();
    }

    public void setHint(CharSequence charSequence) {
        this.textView.setHint(charSequence);
    }

    public void setHint(int i) {
        this.textView.setHint(i);
    }

    public int getStrokeColor() {
        return this.backgroundShape.getStrokeColor().getDefaultColor();
    }

    public void setStrokeColor(int i) {
        if (getStrokeColor() != i) {
            this.backgroundShape.setStrokeColor(ColorStateList.valueOf(i));
        }
    }

    public float getStrokeWidth() {
        return this.backgroundShape.getStrokeWidth();
    }

    public void setStrokeWidth(float f) {
        if (getStrokeWidth() != f) {
            this.backgroundShape.setStrokeWidth(f);
        }
    }

    public float getCornerSize() {
        return this.backgroundShape.getTopLeftCornerResolvedSize();
    }

    public boolean isDefaultScrollFlagsEnabled() {
        return this.defaultScrollFlagsEnabled;
    }

    public void setDefaultScrollFlagsEnabled(boolean z) {
        this.defaultScrollFlagsEnabled = z;
        setOrClearDefaultScrollFlags();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startOnLoadAnimation$1$com-google-android-material-search-SearchBar  reason: not valid java name */
    public /* synthetic */ void m114lambda$startOnLoadAnimation$1$comgoogleandroidmaterialsearchSearchBar() {
        this.searchBarAnimationHelper.startOnLoadAnimation(this);
    }

    public void startOnLoadAnimation() {
        post(new SearchBar$$ExternalSyntheticLambda0(this));
    }

    public void stopOnLoadAnimation() {
        this.searchBarAnimationHelper.stopOnLoadAnimation(this);
    }

    public boolean isOnLoadAnimationFadeInEnabled() {
        return this.searchBarAnimationHelper.isOnLoadAnimationFadeInEnabled();
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.searchBarAnimationHelper.setOnLoadAnimationFadeInEnabled(z);
    }

    public void addOnLoadAnimationCallback(OnLoadAnimationCallback onLoadAnimationCallback) {
        this.searchBarAnimationHelper.addOnLoadAnimationCallback(onLoadAnimationCallback);
    }

    public boolean removeOnLoadAnimationCallback(OnLoadAnimationCallback onLoadAnimationCallback) {
        return this.searchBarAnimationHelper.removeOnLoadAnimationCallback(onLoadAnimationCallback);
    }

    public boolean isExpanding() {
        return this.searchBarAnimationHelper.isExpanding();
    }

    public boolean expand(View view) {
        return expand(view, (AppBarLayout) null);
    }

    public boolean expand(View view, AppBarLayout appBarLayout) {
        return expand(view, appBarLayout, false);
    }

    public boolean expand(View view, AppBarLayout appBarLayout, boolean z) {
        if ((view.getVisibility() == 0 || isExpanding()) && !isCollapsing()) {
            return false;
        }
        this.searchBarAnimationHelper.startExpandAnimation(this, view, appBarLayout, z);
        return true;
    }

    public void addExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        this.searchBarAnimationHelper.addExpandAnimationListener(animatorListenerAdapter);
    }

    public boolean removeExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        return this.searchBarAnimationHelper.removeExpandAnimationListener(animatorListenerAdapter);
    }

    public boolean isCollapsing() {
        return this.searchBarAnimationHelper.isCollapsing();
    }

    public boolean collapse(View view) {
        return collapse(view, (AppBarLayout) null);
    }

    public boolean collapse(View view, AppBarLayout appBarLayout) {
        return collapse(view, appBarLayout, false);
    }

    public boolean collapse(View view, AppBarLayout appBarLayout, boolean z) {
        if ((view.getVisibility() != 0 || isCollapsing()) && !isExpanding()) {
            return false;
        }
        this.searchBarAnimationHelper.startCollapseAnimation(this, view, appBarLayout, z);
        return true;
    }

    public void addCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        this.searchBarAnimationHelper.addCollapseAnimationListener(animatorListenerAdapter);
    }

    public boolean removeCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        return this.searchBarAnimationHelper.removeCollapseAnimationListener(animatorListenerAdapter);
    }

    /* access modifiers changed from: package-private */
    public int getMenuResId() {
        return this.menuResId;
    }

    /* access modifiers changed from: package-private */
    public float getCompatElevation() {
        MaterialShapeDrawable materialShapeDrawable = this.backgroundShape;
        return materialShapeDrawable != null ? materialShapeDrawable.getElevation() : ViewCompat.getElevation(this);
    }

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
        private boolean initialized = false;

        /* access modifiers changed from: protected */
        public boolean shouldHeaderOverlapScrollingChild() {
            return true;
        }

        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            boolean onDependentViewChanged = super.onDependentViewChanged(coordinatorLayout, view, view2);
            if (!this.initialized && (view2 instanceof AppBarLayout)) {
                this.initialized = true;
                setAppBarLayoutTransparent((AppBarLayout) view2);
            }
            return onDependentViewChanged;
        }

        private void setAppBarLayoutTransparent(AppBarLayout appBarLayout) {
            appBarLayout.setBackgroundColor(0);
            appBarLayout.setTargetElevation(0.0f);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        String str;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        CharSequence text = getText();
        if (text == null) {
            str = null;
        } else {
            str = text.toString();
        }
        savedState.text = str;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setText((CharSequence) savedState.text);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String text;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.text = parcel.readString();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.text);
        }
    }
}
