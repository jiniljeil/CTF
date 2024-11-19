package androidx.activity;

import android.window.OnBackInvokedCallback;
import androidx.activity.OnBackPressedDispatcher;
import kotlin.jvm.functions.Function0;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final /* synthetic */ Function0 f$0;

    public /* synthetic */ OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0(Function0 function0) {
        this.f$0 = function0;
    }

    public final void onBackInvoked() {
        OnBackPressedDispatcher.Api33Impl.createOnBackInvokedCallback$lambda$0(this.f$0);
    }
}
