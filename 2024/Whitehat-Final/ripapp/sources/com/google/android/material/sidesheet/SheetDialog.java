package com.google.android.material.sidesheet;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.sidesheet.SheetCallback;

abstract class SheetDialog<C extends SheetCallback> extends AppCompatDialog {
    private static final int COORDINATOR_LAYOUT_ID = R.id.coordinator;
    private static final int TOUCH_OUTSIDE_ID = R.id.touch_outside;
    private MaterialBackOrchestrator backOrchestrator;
    private Sheet<C> behavior;
    boolean cancelable = true;
    private boolean canceledOnTouchOutside = true;
    private boolean canceledOnTouchOutsideSet;
    private FrameLayout container;
    boolean dismissWithAnimation;
    private FrameLayout sheet;

    /* access modifiers changed from: package-private */
    public abstract void addSheetCancelOnHideCallback(Sheet<C> sheet2);

    /* access modifiers changed from: package-private */
    public abstract Sheet<C> getBehaviorFromSheet(FrameLayout frameLayout);

    /* access modifiers changed from: package-private */
    public abstract int getDialogId();

    /* access modifiers changed from: package-private */
    public abstract int getLayoutResId();

    /* access modifiers changed from: package-private */
    public abstract int getStateOnStart();

    SheetDialog(Context context, int i, int i2, int i3) {
        super(context, getThemeResId(context, i, i2, i3));
        supportRequestWindowFeature(1);
    }

    public void setContentView(int i) {
        super.setContentView(wrapInSheet(i, (View) null, (ViewGroup.LayoutParams) null));
    }

    public void setContentView(View view) {
        super.setContentView(wrapInSheet(0, view, (ViewGroup.LayoutParams) null));
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(wrapInSheet(0, view, layoutParams));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setStatusBarColor(0);
            window.addFlags(Integer.MIN_VALUE);
            window.setLayout(-1, -1);
        }
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
        if (this.cancelable != z) {
            this.cancelable = z;
        }
        if (getWindow() != null) {
            updateListeningForBackCallbacks();
        }
    }

    private void updateListeningForBackCallbacks() {
        MaterialBackOrchestrator materialBackOrchestrator = this.backOrchestrator;
        if (materialBackOrchestrator != null) {
            if (this.cancelable) {
                materialBackOrchestrator.startListeningForBackCallbacks();
            } else {
                materialBackOrchestrator.stopListeningForBackCallbacks();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Sheet<C> sheet2 = this.behavior;
        if (sheet2 != null && sheet2.getState() == 5) {
            this.behavior.setState(getStateOnStart());
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        maybeUpdateWindowAnimationsBasedOnLayoutDirection();
        updateListeningForBackCallbacks();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MaterialBackOrchestrator materialBackOrchestrator = this.backOrchestrator;
        if (materialBackOrchestrator != null) {
            materialBackOrchestrator.stopListeningForBackCallbacks();
        }
    }

    public void cancel() {
        Sheet behavior2 = getBehavior();
        if (!this.dismissWithAnimation || behavior2.getState() == 5) {
            super.cancel();
        } else {
            behavior2.setState(5);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        if (z && !this.cancelable) {
            this.cancelable = true;
        }
        this.canceledOnTouchOutside = z;
        this.canceledOnTouchOutsideSet = true;
    }

    public void setDismissWithSheetAnimationEnabled(boolean z) {
        this.dismissWithAnimation = z;
    }

    public boolean isDismissWithSheetAnimationEnabled() {
        return this.dismissWithAnimation;
    }

    private void ensureContainerAndBehavior() {
        if (this.container == null) {
            FrameLayout frameLayout = (FrameLayout) View.inflate(getContext(), getLayoutResId(), (ViewGroup) null);
            this.container = frameLayout;
            FrameLayout frameLayout2 = (FrameLayout) frameLayout.findViewById(getDialogId());
            this.sheet = frameLayout2;
            Sheet<C> behaviorFromSheet = getBehaviorFromSheet(frameLayout2);
            this.behavior = behaviorFromSheet;
            addSheetCancelOnHideCallback(behaviorFromSheet);
            this.backOrchestrator = new MaterialBackOrchestrator(this.behavior, this.sheet);
        }
    }

    private FrameLayout getContainer() {
        if (this.container == null) {
            ensureContainerAndBehavior();
        }
        return this.container;
    }

    private FrameLayout getSheet() {
        if (this.sheet == null) {
            ensureContainerAndBehavior();
        }
        return this.sheet;
    }

    /* access modifiers changed from: package-private */
    public Sheet<C> getBehavior() {
        if (this.behavior == null) {
            ensureContainerAndBehavior();
        }
        return this.behavior;
    }

    private View wrapInSheet(int i, View view, ViewGroup.LayoutParams layoutParams) {
        ensureContainerAndBehavior();
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getContainer().findViewById(COORDINATOR_LAYOUT_ID);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, coordinatorLayout, false);
        }
        FrameLayout sheet2 = getSheet();
        sheet2.removeAllViews();
        if (layoutParams == null) {
            sheet2.addView(view);
        } else {
            sheet2.addView(view, layoutParams);
        }
        coordinatorLayout.findViewById(TOUCH_OUTSIDE_ID).setOnClickListener(new SheetDialog$$ExternalSyntheticLambda0(this));
        ViewCompat.setAccessibilityDelegate(getSheet(), new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (SheetDialog.this.cancelable) {
                    accessibilityNodeInfoCompat.addAction(1048576);
                    accessibilityNodeInfoCompat.setDismissable(true);
                    return;
                }
                accessibilityNodeInfoCompat.setDismissable(false);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 1048576 || !SheetDialog.this.cancelable) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                SheetDialog.this.cancel();
                return true;
            }
        });
        return this.container;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$wrapInSheet$0$com-google-android-material-sidesheet-SheetDialog  reason: not valid java name */
    public /* synthetic */ void m127lambda$wrapInSheet$0$comgoogleandroidmaterialsidesheetSheetDialog(View view) {
        if (this.cancelable && isShowing() && shouldWindowCloseOnTouchOutside()) {
            cancel();
        }
    }

    public void setSheetEdge(int i) {
        FrameLayout frameLayout = this.sheet;
        if (frameLayout == null) {
            throw new IllegalStateException("Sheet view reference is null; sheet edge cannot be changed if the sheet view is null.");
        } else if (!ViewCompat.isLaidOut(frameLayout)) {
            ViewGroup.LayoutParams layoutParams = this.sheet.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                ((CoordinatorLayout.LayoutParams) layoutParams).gravity = i;
                maybeUpdateWindowAnimationsBasedOnLayoutDirection();
            }
        } else {
            throw new IllegalStateException("Sheet view has been laid out; sheet edge cannot be changed once the sheet has been laid out.");
        }
    }

    private void maybeUpdateWindowAnimationsBasedOnLayoutDirection() {
        FrameLayout frameLayout;
        int i;
        Window window = getWindow();
        if (window != null && (frameLayout = this.sheet) != null && (frameLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            if (GravityCompat.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) this.sheet.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this.sheet)) == 3) {
                i = R.style.Animation_Material3_SideSheetDialog_Left;
            } else {
                i = R.style.Animation_Material3_SideSheetDialog_Right;
            }
            window.setWindowAnimations(i);
        }
    }

    private boolean shouldWindowCloseOnTouchOutside() {
        if (!this.canceledOnTouchOutsideSet) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{16843611});
            this.canceledOnTouchOutside = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
            this.canceledOnTouchOutsideSet = true;
        }
        return this.canceledOnTouchOutside;
    }

    private static int getThemeResId(Context context, int i, int i2, int i3) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(i2, typedValue, true) ? typedValue.resourceId : i3;
    }
}
