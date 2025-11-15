package androidx.profileinstaller;

import android.content.Context;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProfileInstallerInitializer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ ProfileInstallerInitializer$$ExternalSyntheticLambda0(Context context) {
        this.f$0 = context;
    }

    public final void run() {
        ProfileInstallerInitializer.writeInBackground(this.f$0);
    }
}
