package androidx.profileinstaller;

import androidx.profileinstaller.ProfileInstaller;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProfileInstaller$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ProfileInstaller.DiagnosticsCallback f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ ProfileInstaller$$ExternalSyntheticLambda0(ProfileInstaller.DiagnosticsCallback diagnosticsCallback, int i, Object obj) {
        this.f$0 = diagnosticsCallback;
        this.f$1 = i;
        this.f$2 = obj;
    }

    public final void run() {
        this.f$0.onResultReceived(this.f$1, this.f$2);
    }
}
