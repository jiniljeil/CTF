package androidx.fragment.app.strictmode;

import androidx.fragment.app.strictmode.FragmentStrictMode;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FragmentStrictMode$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FragmentStrictMode.Policy f$0;
    public final /* synthetic */ Violation f$1;

    public /* synthetic */ FragmentStrictMode$$ExternalSyntheticLambda0(FragmentStrictMode.Policy policy, Violation violation) {
        this.f$0 = policy;
        this.f$1 = violation;
    }

    public final void run() {
        FragmentStrictMode.m49handlePolicyViolation$lambda0(this.f$0, this.f$1);
    }
}
