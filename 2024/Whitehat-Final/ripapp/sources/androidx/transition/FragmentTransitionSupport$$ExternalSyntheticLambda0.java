package androidx.transition;

import androidx.core.os.CancellationSignal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FragmentTransitionSupport$$ExternalSyntheticLambda0 implements CancellationSignal.OnCancelListener {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ Transition f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ FragmentTransitionSupport$$ExternalSyntheticLambda0(Runnable runnable, Transition transition, Runnable runnable2) {
        this.f$0 = runnable;
        this.f$1 = transition;
        this.f$2 = runnable2;
    }

    public final void onCancel() {
        FragmentTransitionSupport.lambda$setListenerForTransitionEnd$0(this.f$0, this.f$1, this.f$2);
    }
}
