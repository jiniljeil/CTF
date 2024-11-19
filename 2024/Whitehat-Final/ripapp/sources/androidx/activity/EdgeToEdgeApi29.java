package androidx.activity;

import android.view.View;
import android.view.Window;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0017¨\u0006\u000f"}, d2 = {"Landroidx/activity/EdgeToEdgeApi29;", "Landroidx/activity/EdgeToEdgeApi28;", "()V", "setUp", "", "statusBarStyle", "Landroidx/activity/SystemBarStyle;", "navigationBarStyle", "window", "Landroid/view/Window;", "view", "Landroid/view/View;", "statusBarIsDark", "", "navigationBarIsDark", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EdgeToEdge.kt */
class EdgeToEdgeApi29 extends EdgeToEdgeApi28 {
    public void setUp(SystemBarStyle systemBarStyle, SystemBarStyle systemBarStyle2, Window window, View view, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(systemBarStyle, "statusBarStyle");
        Intrinsics.checkNotNullParameter(systemBarStyle2, "navigationBarStyle");
        Intrinsics.checkNotNullParameter(window, "window");
        Intrinsics.checkNotNullParameter(view, "view");
        boolean z3 = false;
        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.setStatusBarColor(systemBarStyle.getScrimWithEnforcedContrast$activity_release(z));
        window.setNavigationBarColor(systemBarStyle2.getScrimWithEnforcedContrast$activity_release(z2));
        window.setStatusBarContrastEnforced(false);
        if (systemBarStyle2.getNightMode$activity_release() == 0) {
            z3 = true;
        }
        window.setNavigationBarContrastEnforced(z3);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, view);
        windowInsetsControllerCompat.setAppearanceLightStatusBars(!z);
        windowInsetsControllerCompat.setAppearanceLightNavigationBars(true ^ z2);
    }
}
