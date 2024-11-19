package androidx.core.app;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.PendingIntentCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PendingIntentCompat$GatedCallback$$ExternalSyntheticLambda0 implements PendingIntent.OnFinished {
    public final /* synthetic */ PendingIntentCompat.GatedCallback f$0;

    public /* synthetic */ PendingIntentCompat$GatedCallback$$ExternalSyntheticLambda0(PendingIntentCompat.GatedCallback gatedCallback) {
        this.f$0 = gatedCallback;
    }

    public final void onSendFinished(PendingIntent pendingIntent, Intent intent, int i, String str, Bundle bundle) {
        this.f$0.onSendFinished(pendingIntent, intent, i, str, bundle);
    }
}
