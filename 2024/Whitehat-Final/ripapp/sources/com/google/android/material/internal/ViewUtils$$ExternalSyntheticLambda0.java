package com.google.android.material.internal;

import android.view.View;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ViewUtils$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ View f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ViewUtils$$ExternalSyntheticLambda0(View view, boolean z) {
        this.f$0 = view;
        this.f$1 = z;
    }

    public final void run() {
        ViewUtils.showKeyboard(this.f$0, this.f$1);
    }
}
