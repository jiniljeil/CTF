package androidx.lifecycle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessLifecycleOwner$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ProcessLifecycleOwner f$0;

    public /* synthetic */ ProcessLifecycleOwner$$ExternalSyntheticLambda0(ProcessLifecycleOwner processLifecycleOwner) {
        this.f$0 = processLifecycleOwner;
    }

    public final void run() {
        ProcessLifecycleOwner.delayedPauseRunnable$lambda$0(this.f$0);
    }
}
