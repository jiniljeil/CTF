package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aV\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00072\u0006\u0010\b\u001a\u0002H\u00032\u0017\u0010\t\u001a\u0013\u0012\t\u0012\u0007H\u0004¢\u0006\u0002\b\u000b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0002\u0010\f\u001a^\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00072\u0006\u0010\b\u001a\u0002H\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0017\u0010\t\u001a\u0013\u0012\t\u0012\u0007H\u0004¢\u0006\u0002\b\u000b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"registerForActivityResult", "Landroidx/activity/result/ActivityResultLauncher;", "", "I", "O", "Landroidx/activity/result/ActivityResultCaller;", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "input", "callback", "Lkotlin/Function1;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Landroidx/activity/result/ActivityResultCaller;Landroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroidx/activity/result/ActivityResultLauncher;", "registry", "Landroidx/activity/result/ActivityResultRegistry;", "(Landroidx/activity/result/ActivityResultCaller;Landroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Landroidx/activity/result/ActivityResultRegistry;Lkotlin/jvm/functions/Function1;)Landroidx/activity/result/ActivityResultLauncher;", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ActivityResultCaller.kt */
public final class ActivityResultCallerKt {
    public static final <I, O> ActivityResultLauncher<Unit> registerForActivityResult(ActivityResultCaller activityResultCaller, ActivityResultContract<I, O> activityResultContract, I i, ActivityResultRegistry activityResultRegistry, Function1<O, Unit> function1) {
        Intrinsics.checkNotNullParameter(activityResultCaller, "<this>");
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        Intrinsics.checkNotNullParameter(activityResultRegistry, "registry");
        Intrinsics.checkNotNullParameter(function1, "callback");
        return new ActivityResultCallerLauncher<>(activityResultCaller.registerForActivityResult(activityResultContract, activityResultRegistry, new ActivityResultCallerKt$$ExternalSyntheticLambda0(function1)), activityResultContract, i);
    }

    /* access modifiers changed from: private */
    public static final void registerForActivityResult$lambda$0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$callback");
        function1.invoke(obj);
    }

    public static final <I, O> ActivityResultLauncher<Unit> registerForActivityResult(ActivityResultCaller activityResultCaller, ActivityResultContract<I, O> activityResultContract, I i, Function1<O, Unit> function1) {
        Intrinsics.checkNotNullParameter(activityResultCaller, "<this>");
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        Intrinsics.checkNotNullParameter(function1, "callback");
        return new ActivityResultCallerLauncher<>(activityResultCaller.registerForActivityResult(activityResultContract, new ActivityResultCallerKt$$ExternalSyntheticLambda1(function1)), activityResultContract, i);
    }

    /* access modifiers changed from: private */
    public static final void registerForActivityResult$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$callback");
        function1.invoke(obj);
    }
}
