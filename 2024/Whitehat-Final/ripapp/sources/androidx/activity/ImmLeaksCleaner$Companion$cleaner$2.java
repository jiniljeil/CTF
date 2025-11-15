package androidx.activity;

import android.view.inputmethod.InputMethodManager;
import androidx.activity.ImmLeaksCleaner;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/activity/ImmLeaksCleaner$Cleaner;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImmLeaksCleaner.kt */
final class ImmLeaksCleaner$Companion$cleaner$2 extends Lambda implements Function0<ImmLeaksCleaner.Cleaner> {
    public static final ImmLeaksCleaner$Companion$cleaner$2 INSTANCE = new ImmLeaksCleaner$Companion$cleaner$2();

    ImmLeaksCleaner$Companion$cleaner$2() {
        super(0);
    }

    public final ImmLeaksCleaner.Cleaner invoke() {
        Class<InputMethodManager> cls = InputMethodManager.class;
        try {
            Field declaredField = cls.getDeclaredField("mServedView");
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField("mNextServedView");
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mH");
            declaredField3.setAccessible(true);
            Intrinsics.checkNotNullExpressionValue(declaredField3, "hField");
            Intrinsics.checkNotNullExpressionValue(declaredField, "servedViewField");
            Intrinsics.checkNotNullExpressionValue(declaredField2, "nextServedViewField");
            return new ImmLeaksCleaner.ValidCleaner(declaredField3, declaredField, declaredField2);
        } catch (NoSuchFieldException unused) {
            return ImmLeaksCleaner.FailedInitialization.INSTANCE;
        }
    }
}
