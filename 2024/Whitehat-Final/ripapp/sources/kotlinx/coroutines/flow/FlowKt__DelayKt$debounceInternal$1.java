package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {222, 355}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "timeoutMillis", "downstream", "values", "lastValue", "timeoutMillis"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$debounceInternal$1 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<T> $this_debounceInternal;
    final /* synthetic */ Function1<T, Long> $timeoutMillisSelector;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1(Function1<? super T, Long> function1, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$debounceInternal$1> continuation) {
        super(3, continuation);
        this.$timeoutMillisSelector = function1;
        this.$this_debounceInternal = flow;
    }

    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation);
        flowKt__DelayKt$debounceInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$debounceInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$debounceInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(14:8|27|28|31|(8:39|40|41|(1:43)|44|47|(1:49)|(1:51))(2:37|38)|33|39|40|41|(0)|44|47|(0)|(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0113, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0114, code lost:
        r10.handleBuilderException(r15);
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f8 A[Catch:{ all -> 0x0113 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0126 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 2
            r3 = 1
            r4 = 0
            r6 = 0
            if (r1 == 0) goto L_0x0045
            if (r1 == r3) goto L_0x0030
            if (r1 != r2) goto L_0x0028
            java.lang.Object r1 = r14.L$3
            kotlin.jvm.internal.Ref$LongRef r1 = (kotlin.jvm.internal.Ref.LongRef) r1
            java.lang.Object r1 = r14.L$2
            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref.ObjectRef) r1
            java.lang.Object r7 = r14.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r14.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r15)
        L_0x0024:
            r9 = r8
            r8 = r7
            r7 = r1
            goto L_0x006a
        L_0x0028:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0030:
            java.lang.Object r1 = r14.L$3
            kotlin.jvm.internal.Ref$LongRef r1 = (kotlin.jvm.internal.Ref.LongRef) r1
            java.lang.Object r7 = r14.L$2
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r14.L$1
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            java.lang.Object r9 = r14.L$0
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00b5
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r7 = r15
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            java.lang.Object r15 = r14.L$1
            kotlinx.coroutines.flow.FlowCollector r15 = (kotlinx.coroutines.flow.FlowCollector) r15
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$values$1 r1 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$values$1
            kotlinx.coroutines.flow.Flow<T> r8 = r14.$this_debounceInternal
            r1.<init>(r8, r6)
            r10 = r1
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = 3
            r12 = 0
            r8 = 0
            r9 = 0
            kotlinx.coroutines.channels.ReceiveChannel r1 = kotlinx.coroutines.channels.ProduceKt.produce$default(r7, r8, r9, r10, r11, r12)
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            r9 = r15
            r8 = r1
        L_0x006a:
            T r15 = r7.element
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.DONE
            if (r15 == r1) goto L_0x0127
            kotlin.jvm.internal.Ref$LongRef r1 = new kotlin.jvm.internal.Ref$LongRef
            r1.<init>()
            T r15 = r7.element
            if (r15 == 0) goto L_0x00b7
            kotlin.jvm.functions.Function1<T, java.lang.Long> r15 = r14.$timeoutMillisSelector
            kotlinx.coroutines.internal.Symbol r10 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            T r11 = r7.element
            if (r11 != r10) goto L_0x0082
            r11 = r6
        L_0x0082:
            java.lang.Object r15 = r15.invoke(r11)
            java.lang.Number r15 = (java.lang.Number) r15
            long r10 = r15.longValue()
            r1.element = r10
            long r10 = r1.element
            int r15 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r15 < 0) goto L_0x00bc
            long r10 = r1.element
            int r15 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r15 != 0) goto L_0x00b7
            kotlinx.coroutines.internal.Symbol r15 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            T r10 = r7.element
            if (r10 != r15) goto L_0x00a1
            r10 = r6
        L_0x00a1:
            r15 = r14
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            r14.L$0 = r9
            r14.L$1 = r8
            r14.L$2 = r7
            r14.L$3 = r1
            r14.label = r3
            java.lang.Object r15 = r9.emit(r10, r15)
            if (r15 != r0) goto L_0x00b5
            return r0
        L_0x00b5:
            r7.element = r6
        L_0x00b7:
            r15 = r1
            r1 = r7
            r7 = r8
            r8 = r9
            goto L_0x00c8
        L_0x00bc:
            java.lang.IllegalArgumentException r15 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Debounce timeout should not be negative"
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        L_0x00c8:
            boolean r9 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            if (r9 == 0) goto L_0x00df
            T r9 = r1.element
            if (r9 == 0) goto L_0x00df
            long r9 = r15.element
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 <= 0) goto L_0x00d9
            goto L_0x00df
        L_0x00d9:
            java.lang.AssertionError r15 = new java.lang.AssertionError
            r15.<init>()
            throw r15
        L_0x00df:
            r14.L$0 = r8
            r14.L$1 = r7
            r14.L$2 = r1
            r14.L$3 = r15
            r14.label = r2
            r9 = r14
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            kotlinx.coroutines.selects.SelectBuilderImpl r10 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r10.<init>(r9)
            r11 = r10
            kotlinx.coroutines.selects.SelectBuilder r11 = (kotlinx.coroutines.selects.SelectBuilder) r11     // Catch:{ all -> 0x0113 }
            T r12 = r1.element     // Catch:{ all -> 0x0113 }
            if (r12 == 0) goto L_0x0104
            long r12 = r15.element     // Catch:{ all -> 0x0113 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$1 r15 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$1     // Catch:{ all -> 0x0113 }
            r15.<init>(r8, r1, r6)     // Catch:{ all -> 0x0113 }
            kotlin.jvm.functions.Function1 r15 = (kotlin.jvm.functions.Function1) r15     // Catch:{ all -> 0x0113 }
            r11.onTimeout(r12, r15)     // Catch:{ all -> 0x0113 }
        L_0x0104:
            kotlinx.coroutines.selects.SelectClause1 r15 = r7.getOnReceiveCatching()     // Catch:{ all -> 0x0113 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2 r12 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2     // Catch:{ all -> 0x0113 }
            r12.<init>(r1, r8, r6)     // Catch:{ all -> 0x0113 }
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12     // Catch:{ all -> 0x0113 }
            r11.invoke(r15, r12)     // Catch:{ all -> 0x0113 }
            goto L_0x0117
        L_0x0113:
            r15 = move-exception
            r10.handleBuilderException(r15)
        L_0x0117:
            java.lang.Object r15 = r10.getResult()
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r15 != r10) goto L_0x0124
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r9)
        L_0x0124:
            if (r15 != r0) goto L_0x0024
            return r0
        L_0x0127:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
