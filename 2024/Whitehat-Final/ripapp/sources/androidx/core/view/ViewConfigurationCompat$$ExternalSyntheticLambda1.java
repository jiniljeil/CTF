package androidx.core.view;

import android.view.ViewConfiguration;
import androidx.core.util.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ViewConfigurationCompat$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ ViewConfiguration f$0;

    public /* synthetic */ ViewConfigurationCompat$$ExternalSyntheticLambda1(ViewConfiguration viewConfiguration) {
        this.f$0 = viewConfiguration;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getScaledMinimumFlingVelocity());
    }
}
