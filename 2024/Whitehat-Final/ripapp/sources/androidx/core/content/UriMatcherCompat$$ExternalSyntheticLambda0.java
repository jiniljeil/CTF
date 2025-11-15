package androidx.core.content;

import android.content.UriMatcher;
import android.net.Uri;
import androidx.core.util.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UriMatcherCompat$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ UriMatcher f$0;

    public /* synthetic */ UriMatcherCompat$$ExternalSyntheticLambda0(UriMatcher uriMatcher) {
        this.f$0 = uriMatcher;
    }

    public final boolean test(Object obj) {
        return UriMatcherCompat.lambda$asPredicate$0(this.f$0, (Uri) obj);
    }
}
