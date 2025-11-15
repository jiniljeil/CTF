package androidx.profileinstaller;

import android.view.Choreographer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProfileInstallerInitializer$Choreographer16Impl$$ExternalSyntheticLambda0 implements Choreographer.FrameCallback {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ ProfileInstallerInitializer$Choreographer16Impl$$ExternalSyntheticLambda0(Runnable runnable) {
        this.f$0 = runnable;
    }

    public final void doFrame(long j) {
        this.f$0.run();
    }
}
