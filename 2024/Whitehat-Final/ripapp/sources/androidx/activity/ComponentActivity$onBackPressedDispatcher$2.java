package androidx.activity;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/activity/OnBackPressedDispatcher;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ComponentActivity.kt */
final class ComponentActivity$onBackPressedDispatcher$2 extends Lambda implements Function0<OnBackPressedDispatcher> {
    final /* synthetic */ ComponentActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ComponentActivity$onBackPressedDispatcher$2(ComponentActivity componentActivity) {
        super(0);
        this.this$0 = componentActivity;
    }

    public final OnBackPressedDispatcher invoke() {
        OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher(new ComponentActivity$onBackPressedDispatcher$2$$ExternalSyntheticLambda0(this.this$0));
        ComponentActivity componentActivity = this.this$0;
        if (Build.VERSION.SDK_INT >= 33) {
            if (!Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
                new Handler(Looper.getMainLooper()).post(new ComponentActivity$onBackPressedDispatcher$2$$ExternalSyntheticLambda1(componentActivity, onBackPressedDispatcher));
            } else {
                componentActivity.addObserverForBackInvoker(onBackPressedDispatcher);
            }
        }
        return onBackPressedDispatcher;
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        try {
            ComponentActivity$onBackPressedDispatcher$2.super.onBackPressed();
        } catch (IllegalStateException e) {
            if (!Intrinsics.areEqual((Object) e.getMessage(), (Object) "Can not perform this action after onSaveInstanceState")) {
                throw e;
            }
        } catch (NullPointerException e2) {
            if (!Intrinsics.areEqual((Object) e2.getMessage(), (Object) "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                throw e2;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$2$lambda$1(ComponentActivity componentActivity, OnBackPressedDispatcher onBackPressedDispatcher) {
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        Intrinsics.checkNotNullParameter(onBackPressedDispatcher, "$dispatcher");
        componentActivity.addObserverForBackInvoker(onBackPressedDispatcher);
    }
}
