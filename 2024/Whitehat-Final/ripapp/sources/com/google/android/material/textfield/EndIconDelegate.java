package com.google.android.material.textfield;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;

abstract class EndIconDelegate {
    final Context context;
    final CheckableImageButton endIconView;
    final EndCompoundLayout endLayout;
    final TextInputLayout textInputLayout;

    /* access modifiers changed from: package-private */
    public void afterEditTextChanged(Editable editable) {
    }

    /* access modifiers changed from: package-private */
    public void beforeEditTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: package-private */
    public int getIconContentDescriptionResId() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getIconDrawableResId() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public View.OnClickListener getOnIconClickListener() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public AccessibilityManagerCompat.TouchExplorationStateChangeListener getTouchExplorationStateChangeListener() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isBoxBackgroundModeSupported(int i) {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconActivable() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconActivated() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconCheckable() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isIconChecked() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onEditTextAttached(EditText editText) {
    }

    /* access modifiers changed from: package-private */
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    /* access modifiers changed from: package-private */
    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
    }

    /* access modifiers changed from: package-private */
    public void onSuffixVisibilityChanged(boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void setUp() {
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTintIconOnError() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void tearDown() {
    }

    EndIconDelegate(EndCompoundLayout endCompoundLayout) {
        this.textInputLayout = endCompoundLayout.textInputLayout;
        this.endLayout = endCompoundLayout;
        this.context = endCompoundLayout.getContext();
        this.endIconView = endCompoundLayout.getEndIconView();
    }

    /* access modifiers changed from: package-private */
    public final void refreshIconState() {
        this.endLayout.refreshIconState(false);
    }
}
