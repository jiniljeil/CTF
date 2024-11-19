package androidx.activity;

import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "resources", "Landroid/content/res/Resources;", "invoke", "(Landroid/content/res/Resources;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EdgeToEdge.kt */
final class SystemBarStyle$Companion$auto$1 extends Lambda implements Function1<Resources, Boolean> {
    public static final SystemBarStyle$Companion$auto$1 INSTANCE = new SystemBarStyle$Companion$auto$1();

    SystemBarStyle$Companion$auto$1() {
        super(1);
    }

    public final Boolean invoke(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        return Boolean.valueOf((resources.getConfiguration().uiMode & 48) == 32);
    }
}
