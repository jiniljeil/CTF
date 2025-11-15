package androidx.fragment.app.strictmode;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FragmentStrictMode$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Violation f$1;

    public /* synthetic */ FragmentStrictMode$$ExternalSyntheticLambda1(String str, Violation violation) {
        this.f$0 = str;
        this.f$1 = violation;
    }

    public final void run() {
        FragmentStrictMode.m50handlePolicyViolation$lambda1(this.f$0, this.f$1);
    }
}
