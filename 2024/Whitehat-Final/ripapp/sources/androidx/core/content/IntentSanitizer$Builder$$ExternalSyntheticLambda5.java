package androidx.core.content;

import android.content.ComponentName;
import androidx.core.util.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IntentSanitizer$Builder$$ExternalSyntheticLambda5 implements Predicate {
    public final /* synthetic */ ComponentName f$0;

    public /* synthetic */ IntentSanitizer$Builder$$ExternalSyntheticLambda5(ComponentName componentName) {
        this.f$0 = componentName;
    }

    public final boolean test(Object obj) {
        return this.f$0.equals((ComponentName) obj);
    }
}
