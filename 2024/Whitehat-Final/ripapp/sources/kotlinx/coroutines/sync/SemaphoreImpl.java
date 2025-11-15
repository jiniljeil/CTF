package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.debug.internal.ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0002\u0018\u00002\u00020\u001eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u001d\u0010\r\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0013\u0010\u0012J\u0019\u0010\u0014\u001a\u00020\f*\b\u0012\u0004\u0012\u00020\u00060\nH\u0002¢\u0006\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0017\u001a\u00020\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R \u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00060\u00188\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u001c\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "", "permits", "acquiredPermits", "<init>", "(II)V", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "Lkotlinx/coroutines/CancellableContinuation;", "cont", "", "addAcquireToQueue", "(Lkotlinx/coroutines/CancellableContinuation;)Z", "release", "()V", "tryAcquire", "()Z", "tryResumeNextFromQueue", "tryResumeAcquire", "getAvailablePermits", "()I", "availablePermits", "Lkotlin/Function1;", "", "onCancellationRelease", "Lkotlin/jvm/functions/Function1;", "I", "kotlinx-coroutines-core", "Lkotlinx/coroutines/sync/Semaphore;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: Semaphore.kt */
final class SemaphoreImpl implements Semaphore {
    static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$FU;
    private static final /* synthetic */ AtomicLongFieldUpdater deqIdx$FU;
    private static final /* synthetic */ AtomicLongFieldUpdater enqIdx$FU;
    private static final /* synthetic */ AtomicReferenceFieldUpdater head$FU;
    private static final /* synthetic */ AtomicReferenceFieldUpdater tail$FU;
    volatile /* synthetic */ int _availablePermits;
    private volatile /* synthetic */ long deqIdx = 0;
    private volatile /* synthetic */ long enqIdx = 0;
    private volatile /* synthetic */ Object head;
    /* access modifiers changed from: private */
    public final Function1<Throwable, Unit> onCancellationRelease;
    private final int permits;
    private volatile /* synthetic */ Object tail;

    static {
        Class<SemaphoreImpl> cls = SemaphoreImpl.class;
        head$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "head");
        deqIdx$FU = AtomicLongFieldUpdater.newUpdater(cls, "deqIdx");
        tail$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "tail");
        enqIdx$FU = AtomicLongFieldUpdater.newUpdater(cls, "enqIdx");
        _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_availablePermits");
    }

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        if (i <= 0) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i).toString());
        } else if (i2 < 0 || i2 > i) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i).toString());
        } else {
            SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0, (SemaphoreSegment) null, 2);
            this.head = semaphoreSegment;
            this.tail = semaphoreSegment;
            this._availablePermits = i - i2;
            this.onCancellationRelease = new SemaphoreImpl$onCancellationRelease$1(this);
        }
    }

    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    public Object acquire(Continuation<? super Unit> continuation) {
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object acquireSlowPath = acquireSlowPath(continuation);
        return acquireSlowPath == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? acquireSlowPath : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cancellableContinuation) {
        Object obj;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail;
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        long access$getSEGMENT_SIZE$p = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        loop0:
        while (true) {
            Segment segment = semaphoreSegment;
            while (true) {
                if (segment.getId() >= access$getSEGMENT_SIZE$p && !segment.getRemoved()) {
                    obj = SegmentOrClosed.m1725constructorimpl(segment);
                    break;
                }
                Object access$getNextOrClosed = segment.getNextOrClosed();
                if (access$getNextOrClosed == ConcurrentLinkedListKt.CLOSED) {
                    obj = SegmentOrClosed.m1725constructorimpl(ConcurrentLinkedListKt.CLOSED);
                    break;
                }
                Segment segment2 = (Segment) ((ConcurrentLinkedListNode) access$getNextOrClosed);
                if (segment2 == null) {
                    segment2 = SemaphoreKt.createSegment(segment.getId() + 1, (SemaphoreSegment) segment);
                    if (segment.trySetNext(segment2)) {
                        if (segment.getRemoved()) {
                            segment.remove();
                        }
                    }
                }
                segment = segment2;
            }
            if (SegmentOrClosed.m1730isClosedimpl(obj)) {
                break;
            }
            Segment r6 = SegmentOrClosed.m1728getSegmentimpl(obj);
            while (true) {
                Segment segment3 = (Segment) this.tail;
                if (segment3.getId() >= r6.getId()) {
                    break loop0;
                } else if (r6.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(tail$FU, this, segment3, r6)) {
                        if (segment3.decPointers$kotlinx_coroutines_core()) {
                            segment3.remove();
                        }
                    } else if (r6.decPointers$kotlinx_coroutines_core()) {
                        r6.remove();
                    }
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1728getSegmentimpl(obj);
        int access$getSEGMENT_SIZE$p2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        if (ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.acquirers, access$getSEGMENT_SIZE$p2, (Object) null, cancellableContinuation)) {
            cancellableContinuation.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler(semaphoreSegment2, access$getSEGMENT_SIZE$p2));
            return true;
        }
        if (ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.acquirers, access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT, SemaphoreKt.TAKEN)) {
            cancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
            return true;
        } else if (!DebugKt.getASSERTIONS_ENABLED() || semaphoreSegment2.acquirers.get(access$getSEGMENT_SIZE$p2) == SemaphoreKt.BROKEN) {
            return false;
        } else {
            throw new AssertionError();
        }
    }

    private final boolean tryResumeNextFromQueue() {
        Object obj;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.head;
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        long access$getSEGMENT_SIZE$p = andIncrement / ((long) SemaphoreKt.SEGMENT_SIZE);
        loop0:
        while (true) {
            Segment segment = semaphoreSegment;
            while (true) {
                if (segment.getId() >= access$getSEGMENT_SIZE$p && !segment.getRemoved()) {
                    obj = SegmentOrClosed.m1725constructorimpl(segment);
                    break;
                }
                Object access$getNextOrClosed = segment.getNextOrClosed();
                if (access$getNextOrClosed == ConcurrentLinkedListKt.CLOSED) {
                    obj = SegmentOrClosed.m1725constructorimpl(ConcurrentLinkedListKt.CLOSED);
                    break;
                }
                Segment segment2 = (Segment) ((ConcurrentLinkedListNode) access$getNextOrClosed);
                if (segment2 == null) {
                    segment2 = SemaphoreKt.createSegment(segment.getId() + 1, (SemaphoreSegment) segment);
                    if (segment.trySetNext(segment2)) {
                        if (segment.getRemoved()) {
                            segment.remove();
                        }
                    }
                }
                segment = segment2;
            }
            if (SegmentOrClosed.m1730isClosedimpl(obj)) {
                break;
            }
            Segment r6 = SegmentOrClosed.m1728getSegmentimpl(obj);
            while (true) {
                Segment segment3 = (Segment) this.head;
                if (segment3.getId() >= r6.getId()) {
                    break loop0;
                } else if (r6.tryIncPointers$kotlinx_coroutines_core()) {
                    if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(head$FU, this, segment3, r6)) {
                        if (segment3.decPointers$kotlinx_coroutines_core()) {
                            segment3.remove();
                        }
                    } else if (r6.decPointers$kotlinx_coroutines_core()) {
                        r6.remove();
                    }
                }
            }
        }
        SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1728getSegmentimpl(obj);
        semaphoreSegment2.cleanPrev();
        if (semaphoreSegment2.getId() > access$getSEGMENT_SIZE$p) {
            return false;
        }
        int access$getSEGMENT_SIZE$p2 = (int) (andIncrement % ((long) SemaphoreKt.SEGMENT_SIZE));
        Object andSet = semaphoreSegment2.acquirers.getAndSet(access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT);
        if (andSet == null) {
            int access$getMAX_SPIN_CYCLES$p = SemaphoreKt.MAX_SPIN_CYCLES;
            for (int i = 0; i < access$getMAX_SPIN_CYCLES$p; i++) {
                if (semaphoreSegment2.acquirers.get(access$getSEGMENT_SIZE$p2) == SemaphoreKt.TAKEN) {
                    return true;
                }
            }
            return !ConcurrentWeakMap$Core$$ExternalSyntheticBackportWithForwarding0.m(semaphoreSegment2.acquirers, access$getSEGMENT_SIZE$p2, SemaphoreKt.PERMIT, SemaphoreKt.BROKEN);
        } else if (andSet == SemaphoreKt.CANCELLED) {
            return false;
        } else {
            return tryResumeAcquire((CancellableContinuation) andSet);
        }
    }

    private final boolean tryResumeAcquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        Object tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, (Object) null, this.onCancellationRelease);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }

    public boolean tryAcquire() {
        int i;
        do {
            i = this._availablePermits;
            if (i <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, i, i - 1));
        return true;
    }

    /* access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl<? super Unit> orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        CancellableContinuation cancellableContinuation = orCreateCancellableContinuation;
        while (true) {
            if (!addAcquireToQueue(cancellableContinuation)) {
                if (_availablePermits$FU.getAndDecrement(this) > 0) {
                    cancellableContinuation.resume(Unit.INSTANCE, this.onCancellationRelease);
                    break;
                }
            } else {
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    public void release() {
        while (true) {
            int i = this._availablePermits;
            if (i < this.permits) {
                if (_availablePermits$FU.compareAndSet(this, i, i + 1) && (i >= 0 || tryResumeNextFromQueue())) {
                    return;
                }
            } else {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
        }
    }
}
