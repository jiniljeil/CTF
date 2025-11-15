package androidx.activity;

import android.content.IntentSender;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ComponentActivity$activityResultRegistry$1 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ IntentSender.SendIntentException f$2;

    public /* synthetic */ ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda1(ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1, int i, IntentSender.SendIntentException sendIntentException) {
        this.f$0 = componentActivity$activityResultRegistry$1;
        this.f$1 = i;
        this.f$2 = sendIntentException;
    }

    public final void run() {
        ComponentActivity$activityResultRegistry$1.onLaunch$lambda$1(this.f$0, this.f$1, this.f$2);
    }
}
