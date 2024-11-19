package androidx.lifecycle;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import androidx.lifecycle.ProcessLifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0017J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u000b"}, d2 = {"androidx/lifecycle/ProcessLifecycleOwner$attach$1", "Landroidx/lifecycle/EmptyActivityLifecycleCallbacks;", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityPaused", "onActivityPreCreated", "onActivityStopped", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessLifecycleOwner.kt */
public final class ProcessLifecycleOwner$attach$1 extends EmptyActivityLifecycleCallbacks {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    ProcessLifecycleOwner$attach$1(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ProcessLifecycleOwner.Api29Impl.registerActivityLifecycleCallbacks(activity, new ProcessLifecycleOwner$attach$1$onActivityPreCreated$1(this.this$0));
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Build.VERSION.SDK_INT < 29) {
            ReportFragment.Companion.get(activity).setProcessListener(this.this$0.initializationListener);
        }
    }

    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.this$0.activityPaused$lifecycle_process_release();
    }

    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.this$0.activityStopped$lifecycle_process_release();
    }
}
