package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\tH\u0007J\b\u0010\u000b\u001a\u00020\tH\u0007J\b\u0010\f\u001a\u00020\tH\u0007J\b\u0010\r\u001a\u00020\tH\u0007J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0007J\b\u0010\u0012\u001a\u00020\tH\u0007J\b\u0010\u0013\u001a\u00020\tH\u0007J\b\u0010\u0014\u001a\u00020\tH\u0007J\b\u0010\u0015\u001a\u00020\tH\u0007J\b\u0010\u0016\u001a\u00020\tH\u0007J\b\u0010\u0017\u001a\u00020\tH\u0007J\b\u0010\u0018\u001a\u00020\tH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/core/os/BuildCompat;", "", "()V", "AD_SERVICES_EXTENSION_INT", "", "R_EXTENSION_INT", "S_EXTENSION_INT", "T_EXTENSION_INT", "isAtLeastN", "", "isAtLeastNMR1", "isAtLeastO", "isAtLeastOMR1", "isAtLeastP", "isAtLeastPreReleaseCodename", "codename", "", "buildCodename", "isAtLeastQ", "isAtLeastR", "isAtLeastS", "isAtLeastSv2", "isAtLeastT", "isAtLeastU", "isAtLeastV", "Api30Impl", "PrereleaseSdkCheck", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BuildCompat.kt */
public final class BuildCompat {
    public static final int AD_SERVICES_EXTENSION_INT;
    public static final BuildCompat INSTANCE = new BuildCompat();
    public static final int R_EXTENSION_INT = (Build.VERSION.SDK_INT >= 30 ? Api30Impl.INSTANCE.getExtensionVersion(30) : 0);
    public static final int S_EXTENSION_INT = (Build.VERSION.SDK_INT >= 30 ? Api30Impl.INSTANCE.getExtensionVersion(31) : 0);
    public static final int T_EXTENSION_INT = (Build.VERSION.SDK_INT >= 30 ? Api30Impl.INSTANCE.getExtensionVersion(33) : 0);

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/core/os/BuildCompat$PrereleaseSdkCheck;", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Retention(AnnotationRetention.BINARY)
    @java.lang.annotation.Retention(RetentionPolicy.CLASS)
    /* compiled from: BuildCompat.kt */
    public @interface PrereleaseSdkCheck {
    }

    @JvmStatic
    @Deprecated(message = "Android N is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 24`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 24", imports = {}))
    public static final boolean isAtLeastN() {
        return true;
    }

    private BuildCompat() {
    }

    @JvmStatic
    public static final boolean isAtLeastPreReleaseCodename(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "codename");
        Intrinsics.checkNotNullParameter(str2, "buildCodename");
        if (Intrinsics.areEqual((Object) "REL", (Object) str2)) {
            return false;
        }
        String upperCase = str2.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = str.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        if (upperCase.compareTo(upperCase2) >= 0) {
            return true;
        }
        return false;
    }

    @JvmStatic
    @Deprecated(message = "Android N MR1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 25`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 25", imports = {}))
    public static final boolean isAtLeastNMR1() {
        return Build.VERSION.SDK_INT >= 25;
    }

    @JvmStatic
    @Deprecated(message = "Android O is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead use `Build.VERSION.SDK_INT >= 26`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 26", imports = {}))
    public static final boolean isAtLeastO() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @JvmStatic
    @Deprecated(message = "Android O MR1 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 27`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 27", imports = {}))
    public static final boolean isAtLeastOMR1() {
        return Build.VERSION.SDK_INT >= 27;
    }

    @JvmStatic
    @Deprecated(message = "Android P is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 28`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 28", imports = {}))
    public static final boolean isAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    @JvmStatic
    @Deprecated(message = "Android Q is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 29`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 29", imports = {}))
    public static final boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    @JvmStatic
    @Deprecated(message = "Android R is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 30`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 30", imports = {}))
    public static final boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    @JvmStatic
    @Deprecated(message = "Android S is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 31`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 31", imports = {}))
    public static final boolean isAtLeastS() {
        if (Build.VERSION.SDK_INT < 31) {
            if (Build.VERSION.SDK_INT >= 30) {
                String str = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
                if (isAtLeastPreReleaseCodename("S", str)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @JvmStatic
    @Deprecated(message = "Android Sv2 is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 32`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 32", imports = {}))
    public static final boolean isAtLeastSv2() {
        if (Build.VERSION.SDK_INT < 32) {
            if (Build.VERSION.SDK_INT >= 31) {
                String str = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
                if (isAtLeastPreReleaseCodename("Sv2", str)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @JvmStatic
    @Deprecated(message = "Android Tiramisu is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 33`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 33", imports = {}))
    public static final boolean isAtLeastT() {
        if (Build.VERSION.SDK_INT < 33) {
            if (Build.VERSION.SDK_INT >= 32) {
                String str = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
                if (isAtLeastPreReleaseCodename("Tiramisu", str)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @JvmStatic
    @Deprecated(message = "Android UpsideDownCase is a finalized release and this method is no longer necessary. It will be removed in a future release of this library. Instead, use `Build.VERSION.SDK_INT >= 34`.", replaceWith = @ReplaceWith(expression = "android.os.Build.VERSION.SDK_INT >= 34", imports = {}))
    public static final boolean isAtLeastU() {
        if (Build.VERSION.SDK_INT < 34) {
            if (Build.VERSION.SDK_INT >= 33) {
                String str = Build.VERSION.CODENAME;
                Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
                if (isAtLeastPreReleaseCodename("UpsideDownCake", str)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @JvmStatic
    public static final boolean isAtLeastV() {
        if (Build.VERSION.SDK_INT >= 34) {
            String str = Build.VERSION.CODENAME;
            Intrinsics.checkNotNullExpressionValue(str, "CODENAME");
            if (isAtLeastPreReleaseCodename("VanillaIceCream", str)) {
                return true;
            }
        }
        return false;
    }

    static {
        int i = 0;
        if (Build.VERSION.SDK_INT >= 30) {
            i = Api30Impl.INSTANCE.getExtensionVersion(DurationKt.NANOS_IN_MILLIS);
        }
        AD_SERVICES_EXTENSION_INT = i;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"Landroidx/core/os/BuildCompat$Api30Impl;", "", "()V", "getExtensionVersion", "", "extension", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BuildCompat.kt */
    private static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final int getExtensionVersion(int i) {
            return SdkExtensions.getExtensionVersion(i);
        }
    }
}
