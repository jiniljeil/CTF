package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\b\u0003\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u0006B\u0019\u0012\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\b¢\u0006\u0002\u0010\nJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u001fH\u0002R\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tX\u000e¢\u0006\u0004\n\u0002\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\t8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lkotlin/enums/EnumEntriesList;", "T", "", "Lkotlin/enums/EnumEntries;", "Lkotlin/collections/AbstractList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "entriesProvider", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "_entries", "[Ljava/lang/Enum;", "entries", "getEntries", "()[Ljava/lang/Enum;", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Enum;)Z", "get", "index", "(I)Ljava/lang/Enum;", "indexOf", "(Ljava/lang/Enum;)I", "lastIndexOf", "writeReplace", "", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EnumEntries.kt */
final class EnumEntriesList<T extends Enum<T>> extends AbstractList<T> implements EnumEntries<T>, Serializable {
    private volatile T[] _entries;
    private final Function0<T[]> entriesProvider;

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        return contains((Enum) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        return indexOf((Enum) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        return lastIndexOf((Enum) obj);
    }

    public EnumEntriesList(Function0<T[]> function0) {
        Intrinsics.checkNotNullParameter(function0, "entriesProvider");
        this.entriesProvider = function0;
    }

    private final T[] getEntries() {
        T[] tArr = this._entries;
        if (tArr != null) {
            return tArr;
        }
        T[] tArr2 = (Enum[]) this.entriesProvider.invoke();
        this._entries = tArr2;
        return tArr2;
    }

    public int getSize() {
        return getEntries().length;
    }

    public T get(int i) {
        T[] entries = getEntries();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, entries.length);
        return entries[i];
    }

    public boolean contains(T t) {
        Intrinsics.checkNotNullParameter(t, "element");
        return ((Enum) ArraysKt.getOrNull((T[]) getEntries(), t.ordinal())) == t;
    }

    public int indexOf(T t) {
        Intrinsics.checkNotNullParameter(t, "element");
        int ordinal = t.ordinal();
        if (((Enum) ArraysKt.getOrNull((T[]) getEntries(), ordinal)) == t) {
            return ordinal;
        }
        return -1;
    }

    public int lastIndexOf(T t) {
        Intrinsics.checkNotNullParameter(t, "element");
        return indexOf((Object) t);
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(getEntries());
    }
}
