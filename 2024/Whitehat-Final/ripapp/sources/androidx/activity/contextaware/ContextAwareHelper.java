package androidx.activity.contextaware;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/activity/contextaware/ContextAwareHelper;", "", "()V", "context", "Landroid/content/Context;", "listeners", "", "Landroidx/activity/contextaware/OnContextAvailableListener;", "addOnContextAvailableListener", "", "listener", "clearAvailableContext", "dispatchOnContextAvailable", "peekAvailableContext", "removeOnContextAvailableListener", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ContextAwareHelper.kt */
public final class ContextAwareHelper {
    private volatile Context context;
    private final Set<OnContextAvailableListener> listeners = new CopyOnWriteArraySet();

    public final Context peekAvailableContext() {
        return this.context;
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener, "listener");
        Context context2 = this.context;
        if (context2 != null) {
            onContextAvailableListener.onContextAvailable(context2);
        }
        this.listeners.add(onContextAvailableListener);
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener, "listener");
        this.listeners.remove(onContextAvailableListener);
    }

    public final void dispatchOnContextAvailable(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        for (OnContextAvailableListener onContextAvailable : this.listeners) {
            onContextAvailable.onContextAvailable(context2);
        }
    }

    public final void clearAvailableContext() {
        this.context = null;
    }
}
