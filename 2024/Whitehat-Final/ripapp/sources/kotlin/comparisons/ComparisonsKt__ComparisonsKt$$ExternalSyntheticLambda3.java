package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda3 implements Comparator {
    public final /* synthetic */ Function1[] f$0;

    public /* synthetic */ ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda3(Function1[] function1Arr) {
        this.f$0 = function1Arr;
    }

    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt__ComparisonsKt.compareBy$lambda$0$ComparisonsKt__ComparisonsKt(this.f$0, obj, obj2);
    }
}
