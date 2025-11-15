package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlinx.coroutines.Job;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LifecycleController$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final /* synthetic */ LifecycleController f$0;
    public final /* synthetic */ Job f$1;

    public /* synthetic */ LifecycleController$$ExternalSyntheticLambda0(LifecycleController lifecycleController, Job job) {
        this.f$0 = lifecycleController;
        this.f$1 = job;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        LifecycleController.observer$lambda$0(this.f$0, this.f$1, lifecycleOwner, event);
    }
}
