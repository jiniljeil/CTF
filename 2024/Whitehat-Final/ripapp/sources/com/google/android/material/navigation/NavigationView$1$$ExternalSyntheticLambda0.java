package com.google.android.material.navigation;

import com.google.android.material.motion.MaterialBackOrchestrator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NavigationView$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MaterialBackOrchestrator f$0;

    public /* synthetic */ NavigationView$1$$ExternalSyntheticLambda0(MaterialBackOrchestrator materialBackOrchestrator) {
        this.f$0 = materialBackOrchestrator;
    }

    public final void run() {
        this.f$0.startListeningForBackCallbacksWithPriorityOverlay();
    }
}
