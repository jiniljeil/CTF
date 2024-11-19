package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u000e\u0010\u0004\u001a\n \u0005*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "X", "Y", "x", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Transformations.kt */
final class Transformations$map$2 extends Lambda implements Function1<X, Unit> {
    final /* synthetic */ Function<X, Y> $mapFunction;
    final /* synthetic */ MediatorLiveData<Y> $result;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Transformations$map$2(MediatorLiveData<Y> mediatorLiveData, Function<X, Y> function) {
        super(1);
        this.$result = mediatorLiveData;
        this.$mapFunction = function;
    }

    public final void invoke(X x) {
        this.$result.setValue(this.$mapFunction.apply(x));
    }
}
