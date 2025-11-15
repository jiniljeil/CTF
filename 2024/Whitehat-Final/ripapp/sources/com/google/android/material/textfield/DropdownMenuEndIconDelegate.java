package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;

class DropdownMenuEndIconDelegate extends EndIconDelegate {
    private static final int DEFAULT_ANIMATION_FADE_IN_DURATION = 67;
    private static final int DEFAULT_ANIMATION_FADE_OUT_DURATION = 50;
    private static final boolean IS_LOLLIPOP = true;
    private AccessibilityManager accessibilityManager;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private AutoCompleteTextView autoCompleteTextView;
    private long dropdownPopupActivatedAt = Long.MAX_VALUE;
    private boolean dropdownPopupDirty;
    private boolean editTextHasFocus;
    /* access modifiers changed from: private */
    public ValueAnimator fadeInAnim;
    private ValueAnimator fadeOutAnim;
    private boolean isEndIconChecked;
    private final View.OnFocusChangeListener onEditTextFocusChangeListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda4(this);
    private final View.OnClickListener onIconClickListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda3(this);
    private final AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener = new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda5(this);

    /* access modifiers changed from: package-private */
    public boolean isBoxBackgroundModeSupported(int i) {
        return i != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconActivable() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconCheckable() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTintIconOnError() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ void m140lambda$new$0$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate(View view) {
        showHideDropdown();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ void m141lambda$new$1$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate(View view, boolean z) {
        this.editTextHasFocus = z;
        refreshIconState();
        if (!z) {
            setEndIconChecked(false);
            this.dropdownPopupDirty = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ void m142lambda$new$2$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate(boolean z) {
        AutoCompleteTextView autoCompleteTextView2 = this.autoCompleteTextView;
        if (autoCompleteTextView2 != null && !EditTextUtils.isEditable(autoCompleteTextView2)) {
            ViewCompat.setImportantForAccessibility(this.endIconView, z ? 2 : 1);
        }
    }

    DropdownMenuEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 67);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 50);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(endCompoundLayout.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
    }

    /* access modifiers changed from: package-private */
    public void setUp() {
        initAnimators();
        this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
    }

    /* access modifiers changed from: package-private */
    public void tearDown() {
        AutoCompleteTextView autoCompleteTextView2 = this.autoCompleteTextView;
        if (autoCompleteTextView2 != null) {
            autoCompleteTextView2.setOnTouchListener((View.OnTouchListener) null);
            if (IS_LOLLIPOP) {
                this.autoCompleteTextView.setOnDismissListener((AutoCompleteTextView.OnDismissListener) null);
            }
        }
    }

    public AccessibilityManagerCompat.TouchExplorationStateChangeListener getTouchExplorationStateChangeListener() {
        return this.touchExplorationStateChangeListener;
    }

    /* access modifiers changed from: package-private */
    public int getIconDrawableResId() {
        return IS_LOLLIPOP ? R.drawable.mtrl_dropdown_arrow : R.drawable.mtrl_ic_arrow_drop_down;
    }

    /* access modifiers changed from: package-private */
    public int getIconContentDescriptionResId() {
        return R.string.exposed_dropdown_menu_content_description;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconChecked() {
        return this.isEndIconChecked;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconActivated() {
        return this.editTextHasFocus;
    }

    /* access modifiers changed from: package-private */
    public View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    public void onEditTextAttached(EditText editText) {
        this.autoCompleteTextView = castAutoCompleteTextViewOrThrow(editText);
        setUpDropdownShowHideBehavior();
        this.textInputLayout.setErrorIconDrawable((Drawable) null);
        if (!EditTextUtils.isEditable(editText) && this.accessibilityManager.isTouchExplorationEnabled()) {
            ViewCompat.setImportantForAccessibility(this.endIconView, 2);
        }
        this.textInputLayout.setEndIconVisible(true);
    }

    public void afterEditTextChanged(Editable editable) {
        if (this.accessibilityManager.isTouchExplorationEnabled() && EditTextUtils.isEditable(this.autoCompleteTextView) && !this.endIconView.hasFocus()) {
            this.autoCompleteTextView.dismissDropDown();
        }
        this.autoCompleteTextView.post(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$afterEditTextChanged$3$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ void m138lambda$afterEditTextChanged$3$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate() {
        boolean isPopupShowing = this.autoCompleteTextView.isPopupShowing();
        setEndIconChecked(isPopupShowing);
        this.dropdownPopupDirty = isPopupShowing;
    }

    /* access modifiers changed from: package-private */
    public View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onEditTextFocusChangeListener;
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!EditTextUtils.isEditable(this.autoCompleteTextView)) {
            accessibilityNodeInfoCompat.setClassName(Spinner.class.getName());
        }
        if (accessibilityNodeInfoCompat.isShowingHintText()) {
            accessibilityNodeInfoCompat.setHintText((CharSequence) null);
        }
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        if (this.accessibilityManager.isEnabled() && !EditTextUtils.isEditable(this.autoCompleteTextView)) {
            boolean z = (accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 8) && this.isEndIconChecked && !this.autoCompleteTextView.isPopupShowing();
            if (accessibilityEvent.getEventType() == 1 || z) {
                showHideDropdown();
                updateDropdownPopupDirty();
            }
        }
    }

    private void showHideDropdown() {
        if (this.autoCompleteTextView != null) {
            if (isDropdownPopupActive()) {
                this.dropdownPopupDirty = false;
            }
            if (!this.dropdownPopupDirty) {
                if (IS_LOLLIPOP) {
                    setEndIconChecked(!this.isEndIconChecked);
                } else {
                    this.isEndIconChecked = !this.isEndIconChecked;
                    refreshIconState();
                }
                if (this.isEndIconChecked) {
                    this.autoCompleteTextView.requestFocus();
                    this.autoCompleteTextView.showDropDown();
                    return;
                }
                this.autoCompleteTextView.dismissDropDown();
                return;
            }
            this.dropdownPopupDirty = false;
        }
    }

    private void setUpDropdownShowHideBehavior() {
        this.autoCompleteTextView.setOnTouchListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(this));
        if (IS_LOLLIPOP) {
            this.autoCompleteTextView.setOnDismissListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda2(this));
        }
        this.autoCompleteTextView.setThreshold(0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpDropdownShowHideBehavior$4$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ boolean m143lambda$setUpDropdownShowHideBehavior$4$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (isDropdownPopupActive()) {
                this.dropdownPopupDirty = false;
            }
            showHideDropdown();
            updateDropdownPopupDirty();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpDropdownShowHideBehavior$5$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ void m144lambda$setUpDropdownShowHideBehavior$5$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate() {
        updateDropdownPopupDirty();
        setEndIconChecked(false);
    }

    private boolean isDropdownPopupActive() {
        long currentTimeMillis = System.currentTimeMillis() - this.dropdownPopupActivatedAt;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }

    private static AutoCompleteTextView castAutoCompleteTextViewOrThrow(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    private void updateDropdownPopupDirty() {
        this.dropdownPopupDirty = true;
        this.dropdownPopupActivatedAt = System.currentTimeMillis();
    }

    private void setEndIconChecked(boolean z) {
        if (this.isEndIconChecked != z) {
            this.isEndIconChecked = z;
            this.fadeInAnim.cancel();
            this.fadeOutAnim.start();
        }
    }

    private void initAnimators() {
        this.fadeInAnim = getAlphaAnimator(this.animationFadeInDuration, 0.0f, 1.0f);
        ValueAnimator alphaAnimator = getAlphaAnimator(this.animationFadeOutDuration, 1.0f, 0.0f);
        this.fadeOutAnim = alphaAnimator;
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                DropdownMenuEndIconDelegate.this.refreshIconState();
                DropdownMenuEndIconDelegate.this.fadeInAnim.start();
            }
        });
    }

    private ValueAnimator getAlphaAnimator(int i, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.animationFadeInterpolator);
        ofFloat.setDuration((long) i);
        ofFloat.addUpdateListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda0(this));
        return ofFloat;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAlphaAnimator$6$com-google-android-material-textfield-DropdownMenuEndIconDelegate  reason: not valid java name */
    public /* synthetic */ void m139lambda$getAlphaAnimator$6$comgoogleandroidmaterialtextfieldDropdownMenuEndIconDelegate(ValueAnimator valueAnimator) {
        this.endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
