package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0019\u001a\u00028\u0000H%¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0016\u0010\u0010\u001a\u00020\u00118\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00118\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0013¨\u0006\u001d"}, d2 = {"Landroidx/lifecycle/ComputableLiveData;", "T", "", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "_liveData", "Landroidx/lifecycle/LiveData;", "computing", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getComputing$lifecycle_livedata_release", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "getExecutor$lifecycle_livedata_release", "()Ljava/util/concurrent/Executor;", "invalid", "getInvalid$lifecycle_livedata_release", "invalidationRunnable", "Ljava/lang/Runnable;", "getInvalidationRunnable$lifecycle_livedata_release$annotations", "()V", "liveData", "getLiveData", "()Landroidx/lifecycle/LiveData;", "refreshRunnable", "getRefreshRunnable$lifecycle_livedata_release$annotations", "compute", "()Ljava/lang/Object;", "invalidate", "", "lifecycle-livedata_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ComputableLiveData.kt */
public abstract class ComputableLiveData<T> {
    private final LiveData<T> _liveData;
    private final AtomicBoolean computing;
    private final Executor executor;
    private final AtomicBoolean invalid;
    public final Runnable invalidationRunnable;
    private final LiveData<T> liveData;
    public final Runnable refreshRunnable;

    public ComputableLiveData() {
        this((Executor) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ void getInvalidationRunnable$lifecycle_livedata_release$annotations() {
    }

    public static /* synthetic */ void getRefreshRunnable$lifecycle_livedata_release$annotations() {
    }

    /* access modifiers changed from: protected */
    public abstract T compute();

    public ComputableLiveData(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.executor = executor2;
        LiveData<T> computableLiveData$_liveData$1 = new ComputableLiveData$_liveData$1(this);
        this._liveData = computableLiveData$_liveData$1;
        this.liveData = computableLiveData$_liveData$1;
        this.invalid = new AtomicBoolean(true);
        this.computing = new AtomicBoolean(false);
        this.refreshRunnable = new ComputableLiveData$$ExternalSyntheticLambda0(this);
        this.invalidationRunnable = new ComputableLiveData$$ExternalSyntheticLambda1(this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ComputableLiveData(java.util.concurrent.Executor r1, int r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            r2 = r2 & 1
            if (r2 == 0) goto L_0x000d
            java.util.concurrent.Executor r1 = androidx.arch.core.executor.ArchTaskExecutor.getIOThreadExecutor()
            java.lang.String r2 = "getIOThreadExecutor()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
        L_0x000d:
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.ComputableLiveData.<init>(java.util.concurrent.Executor, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Executor getExecutor$lifecycle_livedata_release() {
        return this.executor;
    }

    public LiveData<T> getLiveData() {
        return this.liveData;
    }

    public final AtomicBoolean getInvalid$lifecycle_livedata_release() {
        return this.invalid;
    }

    public final AtomicBoolean getComputing$lifecycle_livedata_release() {
        return this.computing;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void refreshRunnable$lambda$0(androidx.lifecycle.ComputableLiveData r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
        L_0x0005:
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.computing
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r1, r2)
            if (r0 == 0) goto L_0x0036
            r0 = 0
            r3 = r1
        L_0x0011:
            java.util.concurrent.atomic.AtomicBoolean r4 = r5.invalid     // Catch:{ all -> 0x002f }
            boolean r4 = r4.compareAndSet(r2, r1)     // Catch:{ all -> 0x002f }
            if (r4 == 0) goto L_0x001f
            java.lang.Object r0 = r5.compute()     // Catch:{ all -> 0x002f }
            r3 = r2
            goto L_0x0011
        L_0x001f:
            if (r3 == 0) goto L_0x0028
            androidx.lifecycle.LiveData r2 = r5.getLiveData()     // Catch:{ all -> 0x002f }
            r2.postValue(r0)     // Catch:{ all -> 0x002f }
        L_0x0028:
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.computing
            r0.set(r1)
            r1 = r3
            goto L_0x0036
        L_0x002f:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicBoolean r5 = r5.computing
            r5.set(r1)
            throw r0
        L_0x0036:
            if (r1 == 0) goto L_0x0040
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.invalid
            boolean r0 = r0.get()
            if (r0 != 0) goto L_0x0005
        L_0x0040:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.ComputableLiveData.refreshRunnable$lambda$0(androidx.lifecycle.ComputableLiveData):void");
    }

    /* access modifiers changed from: private */
    public static final void invalidationRunnable$lambda$1(ComputableLiveData computableLiveData) {
        Intrinsics.checkNotNullParameter(computableLiveData, "this$0");
        boolean hasActiveObservers = computableLiveData.getLiveData().hasActiveObservers();
        if (computableLiveData.invalid.compareAndSet(false, true) && hasActiveObservers) {
            computableLiveData.executor.execute(computableLiveData.refreshRunnable);
        }
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.invalidationRunnable);
    }
}
