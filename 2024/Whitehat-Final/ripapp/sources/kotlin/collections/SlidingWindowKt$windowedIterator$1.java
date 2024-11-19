package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 2, 2, 3, 3}, l = {34, 40, 49, 55, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator<T> $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator<? extends T> it, boolean z, boolean z2, Continuation<? super SlidingWindowKt$windowedIterator$1> continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    public final Object invoke(SequenceScope<? super List<? extends T>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlidingWindowKt$windowedIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0094, code lost:
        r10.L$0 = r4;
        r10.L$1 = r3;
        r10.L$2 = r2;
        r10.I$0 = r1;
        r10.label = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a5, code lost:
        if (r4.yield(r3, r10) != r0) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a7, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010c, code lost:
        if (r10.$reuseBuffer == false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010e, code lost:
        r11 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0112, code lost:
        r11 = new java.util.ArrayList(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011c, code lost:
        r10.L$0 = r8;
        r10.L$1 = r5;
        r10.L$2 = r1;
        r10.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012b, code lost:
        if (r8.yield(r11, r10) != r0) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x012d, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0142  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0057
            if (r1 == r6) goto L_0x0044
            if (r1 == r5) goto L_0x003f
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            goto L_0x003f
        L_0x0019:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0021:
            java.lang.Object r1 = r10.L$1
            kotlin.collections.RingBuffer r1 = (kotlin.collections.RingBuffer) r1
            java.lang.Object r4 = r10.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0166
        L_0x002e:
            java.lang.Object r1 = r10.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r5 = r10.L$1
            kotlin.collections.RingBuffer r5 = (kotlin.collections.RingBuffer) r5
            java.lang.Object r8 = r10.L$0
            kotlin.sequences.SequenceScope r8 = (kotlin.sequences.SequenceScope) r8
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x012e
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0188
        L_0x0044:
            int r1 = r10.I$0
            java.lang.Object r2 = r10.L$2
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r10.L$1
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            java.lang.Object r4 = r10.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r11)
        L_0x0055:
            r11 = r1
            goto L_0x00a8
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlin.sequences.SequenceScope r11 = (kotlin.sequences.SequenceScope) r11
            int r1 = r10.$size
            r8 = 1024(0x400, float:1.435E-42)
            int r1 = kotlin.ranges.RangesKt.coerceAtMost((int) r1, (int) r8)
            int r8 = r10.$step
            int r9 = r10.$size
            int r8 = r8 - r9
            if (r8 < 0) goto L_0x00e2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r1)
            java.util.Iterator<T> r1 = r10.$iterator
            r3 = 0
            r4 = r11
            r11 = r3
            r3 = r2
            r2 = r1
            r1 = r8
        L_0x007a:
            boolean r8 = r2.hasNext()
            if (r8 == 0) goto L_0x00ba
            java.lang.Object r8 = r2.next()
            if (r11 <= 0) goto L_0x0089
            int r11 = r11 + -1
            goto L_0x007a
        L_0x0089:
            r3.add(r8)
            int r8 = r3.size()
            int r9 = r10.$size
            if (r8 != r9) goto L_0x007a
            r11 = r10
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r10.L$0 = r4
            r10.L$1 = r3
            r10.L$2 = r2
            r10.I$0 = r1
            r10.label = r6
            java.lang.Object r11 = r4.yield(r3, r11)
            if (r11 != r0) goto L_0x0055
            return r0
        L_0x00a8:
            boolean r1 = r10.$reuseBuffer
            if (r1 == 0) goto L_0x00b0
            r3.clear()
            goto L_0x00b8
        L_0x00b0:
            java.util.ArrayList r1 = new java.util.ArrayList
            int r3 = r10.$size
            r1.<init>(r3)
            r3 = r1
        L_0x00b8:
            r1 = r11
            goto L_0x007a
        L_0x00ba:
            r11 = r3
            java.util.Collection r11 = (java.util.Collection) r11
            boolean r11 = r11.isEmpty()
            r11 = r11 ^ r6
            if (r11 == 0) goto L_0x0188
            boolean r11 = r10.$partialWindows
            if (r11 != 0) goto L_0x00d0
            int r11 = r3.size()
            int r1 = r10.$size
            if (r11 != r1) goto L_0x0188
        L_0x00d0:
            r11 = r10
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r10.L$0 = r7
            r10.L$1 = r7
            r10.L$2 = r7
            r10.label = r5
            java.lang.Object r11 = r4.yield(r3, r11)
            if (r11 != r0) goto L_0x0188
            return r0
        L_0x00e2:
            kotlin.collections.RingBuffer r5 = new kotlin.collections.RingBuffer
            r5.<init>(r1)
            java.util.Iterator<T> r1 = r10.$iterator
            r8 = r11
        L_0x00ea:
            boolean r11 = r1.hasNext()
            if (r11 == 0) goto L_0x0134
            java.lang.Object r11 = r1.next()
            r5.add(r11)
            boolean r11 = r5.isFull()
            if (r11 == 0) goto L_0x00ea
            int r11 = r5.size()
            int r9 = r10.$size
            if (r11 >= r9) goto L_0x010a
            kotlin.collections.RingBuffer r5 = r5.expanded(r9)
            goto L_0x00ea
        L_0x010a:
            boolean r11 = r10.$reuseBuffer
            if (r11 == 0) goto L_0x0112
            r11 = r5
            java.util.List r11 = (java.util.List) r11
            goto L_0x011c
        L_0x0112:
            java.util.ArrayList r11 = new java.util.ArrayList
            r9 = r5
            java.util.Collection r9 = (java.util.Collection) r9
            r11.<init>(r9)
            java.util.List r11 = (java.util.List) r11
        L_0x011c:
            r9 = r10
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r10.L$0 = r8
            r10.L$1 = r5
            r10.L$2 = r1
            r10.label = r4
            java.lang.Object r11 = r8.yield(r11, r9)
            if (r11 != r0) goto L_0x012e
            return r0
        L_0x012e:
            int r11 = r10.$step
            r5.removeFirst(r11)
            goto L_0x00ea
        L_0x0134:
            boolean r11 = r10.$partialWindows
            if (r11 == 0) goto L_0x0188
            r1 = r5
            r4 = r8
        L_0x013a:
            int r11 = r1.size()
            int r5 = r10.$step
            if (r11 <= r5) goto L_0x016c
            boolean r11 = r10.$reuseBuffer
            if (r11 == 0) goto L_0x014a
            r11 = r1
            java.util.List r11 = (java.util.List) r11
            goto L_0x0154
        L_0x014a:
            java.util.ArrayList r11 = new java.util.ArrayList
            r5 = r1
            java.util.Collection r5 = (java.util.Collection) r5
            r11.<init>(r5)
            java.util.List r11 = (java.util.List) r11
        L_0x0154:
            r5 = r10
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r10.L$0 = r4
            r10.L$1 = r1
            r10.L$2 = r7
            r10.label = r3
            java.lang.Object r11 = r4.yield(r11, r5)
            if (r11 != r0) goto L_0x0166
            return r0
        L_0x0166:
            int r11 = r10.$step
            r1.removeFirst(r11)
            goto L_0x013a
        L_0x016c:
            r11 = r1
            java.util.Collection r11 = (java.util.Collection) r11
            boolean r11 = r11.isEmpty()
            r11 = r11 ^ r6
            if (r11 == 0) goto L_0x0188
            r11 = r10
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r10.L$0 = r7
            r10.L$1 = r7
            r10.L$2 = r7
            r10.label = r2
            java.lang.Object r11 = r4.yield(r1, r11)
            if (r11 != r0) goto L_0x0188
            return r0
        L_0x0188:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
