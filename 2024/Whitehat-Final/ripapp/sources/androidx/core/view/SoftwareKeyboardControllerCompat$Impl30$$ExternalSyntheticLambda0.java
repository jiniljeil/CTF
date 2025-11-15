package androidx.core.view;

import android.view.WindowInsetsController;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0 implements WindowInsetsController.OnControllableInsetsChangedListener {
    public final /* synthetic */ AtomicBoolean f$0;

    public /* synthetic */ SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0(AtomicBoolean atomicBoolean) {
        this.f$0 = atomicBoolean;
    }

    public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i) {
        SoftwareKeyboardControllerCompat.Impl30.lambda$hide$0(this.f$0, windowInsetsController, i);
    }
}
