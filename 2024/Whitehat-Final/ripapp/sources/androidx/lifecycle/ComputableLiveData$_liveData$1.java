package androidx.lifecycle;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"androidx/lifecycle/ComputableLiveData$_liveData$1", "Landroidx/lifecycle/LiveData;", "onActive", "", "lifecycle-livedata_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ComputableLiveData.kt */
public final class ComputableLiveData$_liveData$1 extends LiveData<T> {
    final /* synthetic */ ComputableLiveData<T> this$0;

    ComputableLiveData$_liveData$1(ComputableLiveData<T> computableLiveData) {
        this.this$0 = computableLiveData;
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        this.this$0.getExecutor$lifecycle_livedata_release().execute(this.this$0.refreshRunnable);
    }
}
