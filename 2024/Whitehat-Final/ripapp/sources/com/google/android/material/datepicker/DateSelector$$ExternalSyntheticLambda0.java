package com.google.android.material.datepicker;

import android.view.View;
import android.widget.EditText;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DateSelector$$ExternalSyntheticLambda0 implements View.OnFocusChangeListener {
    public final /* synthetic */ EditText[] f$0;

    public /* synthetic */ DateSelector$$ExternalSyntheticLambda0(EditText[] editTextArr) {
        this.f$0 = editTextArr;
    }

    public final void onFocusChange(View view, boolean z) {
        DateSelector.lambda$showKeyboardWithAutoHideBehavior$0(this.f$0, view, z);
    }
}
