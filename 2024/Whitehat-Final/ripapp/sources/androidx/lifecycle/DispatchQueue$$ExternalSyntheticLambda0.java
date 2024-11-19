package androidx.lifecycle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DispatchQueue$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DispatchQueue f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ DispatchQueue$$ExternalSyntheticLambda0(DispatchQueue dispatchQueue, Runnable runnable) {
        this.f$0 = dispatchQueue;
        this.f$1 = runnable;
    }

    public final void run() {
        DispatchQueue.dispatchAndEnqueue$lambda$2$lambda$1(this.f$0, this.f$1);
    }
}
