package androidx.arch.core.executor;

import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ArchTaskExecutor$$ExternalSyntheticLambda1 implements Executor {
    public final void execute(Runnable runnable) {
        ArchTaskExecutor.getInstance().executeOnDiskIO(runnable);
    }
}
