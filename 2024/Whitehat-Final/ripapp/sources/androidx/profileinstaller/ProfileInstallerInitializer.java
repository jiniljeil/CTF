package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProfileInstallerInitializer implements Initializer<Result> {
    private static final int DELAY_MS = 5000;

    public static class Result {
    }

    public Result create(Context context) {
        delayAfterFirstFrame(context.getApplicationContext());
        return new Result();
    }

    /* access modifiers changed from: package-private */
    public void delayAfterFirstFrame(Context context) {
        Choreographer16Impl.postFrameCallback(new ProfileInstallerInitializer$$ExternalSyntheticLambda1(this, context));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: installAfterDelay */
    public void m56lambda$delayAfterFirstFrame$0$androidxprofileinstallerProfileInstallerInitializer(Context context) {
        Handler handler;
        if (Build.VERSION.SDK_INT >= 28) {
            handler = Handler28Impl.createAsync(Looper.getMainLooper());
        } else {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.postDelayed(new ProfileInstallerInitializer$$ExternalSyntheticLambda0(context), (long) (new Random().nextInt(Math.max(1000, 1)) + DELAY_MS));
    }

    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }

    /* access modifiers changed from: private */
    public static void writeInBackground(Context context) {
        new ThreadPoolExecutor(0, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new ProfileInstallerInitializer$$ExternalSyntheticLambda2(context));
    }

    private static class Choreographer16Impl {
        private Choreographer16Impl() {
        }

        public static void postFrameCallback(Runnable runnable) {
            Choreographer.getInstance().postFrameCallback(new ProfileInstallerInitializer$Choreographer16Impl$$ExternalSyntheticLambda0(runnable));
        }
    }

    private static class Handler28Impl {
        private Handler28Impl() {
        }

        public static Handler createAsync(Looper looper) {
            return Handler.createAsync(looper);
        }
    }
}
