package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityResultRegistry$$ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final /* synthetic */ ActivityResultRegistry f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ActivityResultCallback f$2;
    public final /* synthetic */ ActivityResultContract f$3;

    public /* synthetic */ ActivityResultRegistry$$ExternalSyntheticLambda0(ActivityResultRegistry activityResultRegistry, String str, ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract) {
        this.f$0 = activityResultRegistry;
        this.f$1 = str;
        this.f$2 = activityResultCallback;
        this.f$3 = activityResultContract;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        ActivityResultRegistry.register$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, lifecycleOwner, event);
    }
}
