package androidx.activity;

import androidx.activity.result.contract.ActivityResultContract;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ComponentActivity$activityResultRegistry$1 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ActivityResultContract.SynchronousResult f$2;

    public /* synthetic */ ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1, int i, ActivityResultContract.SynchronousResult synchronousResult) {
        this.f$0 = componentActivity$activityResultRegistry$1;
        this.f$1 = i;
        this.f$2 = synchronousResult;
    }

    public final void run() {
        ComponentActivity$activityResultRegistry$1.onLaunch$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
