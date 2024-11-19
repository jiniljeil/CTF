package androidx.activity.result;

import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ActivityResultCallerKt$$ExternalSyntheticLambda1 implements ActivityResultCallback {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ ActivityResultCallerKt$$ExternalSyntheticLambda1(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onActivityResult(Object obj) {
        ActivityResultCallerKt.registerForActivityResult$lambda$1(this.f$0, obj);
    }
}
