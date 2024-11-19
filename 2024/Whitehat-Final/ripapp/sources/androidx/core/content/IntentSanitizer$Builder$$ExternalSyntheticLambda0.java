package androidx.core.content;

import android.net.Uri;
import androidx.core.util.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IntentSanitizer$Builder$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ IntentSanitizer$Builder$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final boolean test(Object obj) {
        return this.f$0.equals(((Uri) obj).getAuthority());
    }
}
