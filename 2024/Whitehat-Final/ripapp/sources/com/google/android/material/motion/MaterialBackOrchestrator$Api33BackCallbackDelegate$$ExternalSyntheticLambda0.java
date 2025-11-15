package com.google.android.material.motion;

import android.window.OnBackInvokedCallback;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MaterialBackOrchestrator$Api33BackCallbackDelegate$$ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final /* synthetic */ MaterialBackHandler f$0;

    public /* synthetic */ MaterialBackOrchestrator$Api33BackCallbackDelegate$$ExternalSyntheticLambda0(MaterialBackHandler materialBackHandler) {
        this.f$0 = materialBackHandler;
    }

    public final void onBackInvoked() {
        this.f$0.handleBackInvoked();
    }
}
