package com.google.android.material.datepicker;

import android.view.View;
import com.google.android.material.internal.ViewUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DateSelector$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ View f$0;

    public /* synthetic */ DateSelector$$ExternalSyntheticLambda1(View view) {
        this.f$0 = view;
    }

    public final void run() {
        ViewUtils.requestFocusAndShowKeyboard(this.f$0, false);
    }
}
