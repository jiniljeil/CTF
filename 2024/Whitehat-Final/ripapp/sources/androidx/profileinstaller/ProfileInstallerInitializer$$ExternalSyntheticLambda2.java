package androidx.profileinstaller;

import android.content.Context;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProfileInstallerInitializer$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ ProfileInstallerInitializer$$ExternalSyntheticLambda2(Context context) {
        this.f$0 = context;
    }

    public final void run() {
        ProfileInstaller.writeProfile(this.f$0);
    }
}
