package kotlin.enums;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0010\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "E", "", "invoke", "()[Ljava/lang/Enum;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EnumEntries.kt */
final class EnumEntriesKt$enumEntries$1 extends Lambda implements Function0<E[]> {
    final /* synthetic */ E[] $entries;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EnumEntriesKt$enumEntries$1(E[] eArr) {
        super(0);
        this.$entries = eArr;
    }

    public final E[] invoke() {
        return this.$entries;
    }
}
