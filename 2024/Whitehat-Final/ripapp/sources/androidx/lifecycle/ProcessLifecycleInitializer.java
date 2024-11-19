package androidx.lifecycle;

import android.content.Context;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\t0\bH\u0016¨\u0006\n"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleInitializer;", "Landroidx/startup/Initializer;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "create", "context", "Landroid/content/Context;", "dependencies", "", "Ljava/lang/Class;", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessLifecycleInitializer.kt */
public final class ProcessLifecycleInitializer implements Initializer<LifecycleOwner> {
    public LifecycleOwner create(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AppInitializer instance = AppInitializer.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(context)");
        if (instance.isEagerlyInitialized(getClass())) {
            LifecycleDispatcher.init(context);
            ProcessLifecycleOwner.Companion.init$lifecycle_process_release(context);
            return ProcessLifecycleOwner.Companion.get();
        }
        throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml".toString());
    }

    public List<Class<? extends Initializer<?>>> dependencies() {
        return CollectionsKt.emptyList();
    }
}
