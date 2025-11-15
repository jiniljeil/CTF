package androidx.emoji2.text.flatbuffer;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Utf8Old$$ExternalSyntheticThreadLocal1 extends ThreadLocal {
    public final /* synthetic */ Supplier initialValueSupplier;

    public /* synthetic */ Utf8Old$$ExternalSyntheticThreadLocal1(Supplier supplier) {
        this.initialValueSupplier = supplier;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Object initialValue() {
        return this.initialValueSupplier.get();
    }
}
