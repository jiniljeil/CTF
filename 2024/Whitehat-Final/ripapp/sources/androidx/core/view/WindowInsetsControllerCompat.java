package androidx.core.view;

import android.os.Build;
import android.os.CancellationSignal;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.accessibility.AccessibilityEventCompat;

public final class WindowInsetsControllerCompat {
    public static final int BEHAVIOR_DEFAULT = 1;
    @Deprecated
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;
    @Deprecated
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
    private final Impl mImpl;

    public interface OnControllableInsetsChangedListener {
        void onControllableInsetsChanged(WindowInsetsControllerCompat windowInsetsControllerCompat, int i);
    }

    @Deprecated
    private WindowInsetsControllerCompat(WindowInsetsController windowInsetsController) {
        this.mImpl = new Impl30(windowInsetsController, this, new SoftwareKeyboardControllerCompat(windowInsetsController));
    }

    public WindowInsetsControllerCompat(Window window, View view) {
        SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat = new SoftwareKeyboardControllerCompat(view);
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(window, this, softwareKeyboardControllerCompat);
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.mImpl = new Impl26(window, softwareKeyboardControllerCompat);
        } else {
            this.mImpl = new Impl23(window, softwareKeyboardControllerCompat);
        }
    }

    @Deprecated
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(WindowInsetsController windowInsetsController) {
        return new WindowInsetsControllerCompat(windowInsetsController);
    }

    public void show(int i) {
        this.mImpl.show(i);
    }

    public void hide(int i) {
        this.mImpl.hide(i);
    }

    public boolean isAppearanceLightStatusBars() {
        return this.mImpl.isAppearanceLightStatusBars();
    }

    public void setAppearanceLightStatusBars(boolean z) {
        this.mImpl.setAppearanceLightStatusBars(z);
    }

    public boolean isAppearanceLightNavigationBars() {
        return this.mImpl.isAppearanceLightNavigationBars();
    }

    public void setAppearanceLightNavigationBars(boolean z) {
        this.mImpl.setAppearanceLightNavigationBars(z);
    }

    public void controlWindowInsetsAnimation(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        this.mImpl.controlWindowInsetsAnimation(i, j, interpolator, cancellationSignal, windowInsetsAnimationControlListenerCompat);
    }

    public void setSystemBarsBehavior(int i) {
        this.mImpl.setSystemBarsBehavior(i);
    }

    public int getSystemBarsBehavior() {
        return this.mImpl.getSystemBarsBehavior();
    }

    public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.mImpl.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
    }

    public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.mImpl.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
    }

    private static class Impl {
        /* access modifiers changed from: package-private */
        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        /* access modifiers changed from: package-private */
        public void controlWindowInsetsAnimation(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        /* access modifiers changed from: package-private */
        public int getSystemBarsBehavior() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public void hide(int i) {
        }

        public boolean isAppearanceLightNavigationBars() {
            return false;
        }

        public boolean isAppearanceLightStatusBars() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        public void setAppearanceLightNavigationBars(boolean z) {
        }

        public void setAppearanceLightStatusBars(boolean z) {
        }

        /* access modifiers changed from: package-private */
        public void setSystemBarsBehavior(int i) {
        }

        /* access modifiers changed from: package-private */
        public void show(int i) {
        }

        Impl() {
        }
    }

    private static class Impl20 extends Impl {
        private final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected final Window mWindow;

        /* access modifiers changed from: package-private */
        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        /* access modifiers changed from: package-private */
        public void controlWindowInsetsAnimation(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        /* access modifiers changed from: package-private */
        public int getSystemBarsBehavior() {
            return 0;
        }

        /* access modifiers changed from: package-private */
        public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        Impl20(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.mWindow = window;
            this.mSoftwareKeyboardControllerCompat = softwareKeyboardControllerCompat;
        }

        /* access modifiers changed from: package-private */
        public void show(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    showForType(i2);
                }
            }
        }

        private void showForType(int i) {
            if (i == 1) {
                unsetSystemUiFlag(4);
                unsetWindowFlag(1024);
            } else if (i == 2) {
                unsetSystemUiFlag(2);
            } else if (i == 8) {
                this.mSoftwareKeyboardControllerCompat.show();
            }
        }

        /* access modifiers changed from: package-private */
        public void hide(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    hideForType(i2);
                }
            }
        }

        private void hideForType(int i) {
            if (i == 1) {
                setSystemUiFlag(4);
            } else if (i == 2) {
                setSystemUiFlag(2);
            } else if (i == 8) {
                this.mSoftwareKeyboardControllerCompat.hide();
            }
        }

        /* access modifiers changed from: protected */
        public void setSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }

        /* access modifiers changed from: protected */
        public void unsetSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        /* access modifiers changed from: protected */
        public void setWindowFlag(int i) {
            this.mWindow.addFlags(i);
        }

        /* access modifiers changed from: protected */
        public void unsetWindowFlag(int i) {
            this.mWindow.clearFlags(i);
        }

        /* access modifiers changed from: package-private */
        public void setSystemBarsBehavior(int i) {
            if (i == 0) {
                unsetSystemUiFlag(6144);
            } else if (i == 1) {
                unsetSystemUiFlag(4096);
                setSystemUiFlag(2048);
            } else if (i == 2) {
                unsetSystemUiFlag(2048);
                setSystemUiFlag(4096);
            }
        }
    }

    private static class Impl23 extends Impl20 {
        Impl23(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, softwareKeyboardControllerCompat);
        }

        public boolean isAppearanceLightStatusBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        public void setAppearanceLightStatusBars(boolean z) {
            if (z) {
                unsetWindowFlag(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
                setWindowFlag(Integer.MIN_VALUE);
                setSystemUiFlag(8192);
                return;
            }
            unsetSystemUiFlag(8192);
        }
    }

    private static class Impl26 extends Impl23 {
        Impl26(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, softwareKeyboardControllerCompat);
        }

        public boolean isAppearanceLightNavigationBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 16) != 0;
        }

        public void setAppearanceLightNavigationBars(boolean z) {
            if (z) {
                unsetWindowFlag(134217728);
                setWindowFlag(Integer.MIN_VALUE);
                setSystemUiFlag(16);
                return;
            }
            unsetSystemUiFlag(16);
        }
    }

    private static class Impl30 extends Impl {
        final WindowInsetsControllerCompat mCompatController;
        final WindowInsetsController mInsetsController;
        private final SimpleArrayMap<OnControllableInsetsChangedListener, WindowInsetsController.OnControllableInsetsChangedListener> mListeners;
        final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected Window mWindow;

        Impl30(Window window, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this(window.getInsetsController(), windowInsetsControllerCompat, softwareKeyboardControllerCompat);
            this.mWindow = window;
        }

        Impl30(WindowInsetsController windowInsetsController, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.mListeners = new SimpleArrayMap<>();
            this.mInsetsController = windowInsetsController;
            this.mCompatController = windowInsetsControllerCompat;
            this.mSoftwareKeyboardControllerCompat = softwareKeyboardControllerCompat;
        }

        /* access modifiers changed from: package-private */
        public void show(int i) {
            if ((i & 8) != 0) {
                this.mSoftwareKeyboardControllerCompat.show();
            }
            this.mInsetsController.show(i & -9);
        }

        /* access modifiers changed from: package-private */
        public void hide(int i) {
            if ((i & 8) != 0) {
                this.mSoftwareKeyboardControllerCompat.hide();
            }
            this.mInsetsController.hide(i & -9);
        }

        public boolean isAppearanceLightStatusBars() {
            this.mInsetsController.setSystemBarsAppearance(0, 0);
            if ((this.mInsetsController.getSystemBarsAppearance() & 8) != 0) {
                return true;
            }
            return false;
        }

        public void setAppearanceLightStatusBars(boolean z) {
            if (z) {
                if (this.mWindow != null) {
                    setSystemUiFlag(8192);
                }
                this.mInsetsController.setSystemBarsAppearance(8, 8);
                return;
            }
            if (this.mWindow != null) {
                unsetSystemUiFlag(8192);
            }
            this.mInsetsController.setSystemBarsAppearance(0, 8);
        }

        public boolean isAppearanceLightNavigationBars() {
            this.mInsetsController.setSystemBarsAppearance(0, 0);
            if ((this.mInsetsController.getSystemBarsAppearance() & 16) != 0) {
                return true;
            }
            return false;
        }

        public void setAppearanceLightNavigationBars(boolean z) {
            if (z) {
                if (this.mWindow != null) {
                    setSystemUiFlag(16);
                }
                this.mInsetsController.setSystemBarsAppearance(16, 16);
                return;
            }
            if (this.mWindow != null) {
                unsetSystemUiFlag(16);
            }
            this.mInsetsController.setSystemBarsAppearance(0, 16);
        }

        /* access modifiers changed from: package-private */
        public void controlWindowInsetsAnimation(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, final WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
            this.mInsetsController.controlWindowInsetsAnimation(i, j, interpolator, cancellationSignal, new WindowInsetsAnimationControlListener() {
                private WindowInsetsAnimationControllerCompat mCompatAnimController = null;

                public void onReady(WindowInsetsAnimationController windowInsetsAnimationController, int i) {
                    WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = new WindowInsetsAnimationControllerCompat(windowInsetsAnimationController);
                    this.mCompatAnimController = windowInsetsAnimationControllerCompat;
                    windowInsetsAnimationControlListenerCompat.onReady(windowInsetsAnimationControllerCompat, i);
                }

                public void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
                    windowInsetsAnimationControlListenerCompat.onFinished(this.mCompatAnimController);
                }

                public void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
                    windowInsetsAnimationControlListenerCompat.onCancelled(windowInsetsAnimationController == null ? null : this.mCompatAnimController);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void setSystemBarsBehavior(int i) {
            this.mInsetsController.setSystemBarsBehavior(i);
        }

        /* access modifiers changed from: package-private */
        public int getSystemBarsBehavior() {
            return this.mInsetsController.getSystemBarsBehavior();
        }

        /* access modifiers changed from: package-private */
        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            if (!this.mListeners.containsKey(onControllableInsetsChangedListener)) {
                WindowInsetsControllerCompat$Impl30$$ExternalSyntheticLambda0 windowInsetsControllerCompat$Impl30$$ExternalSyntheticLambda0 = new WindowInsetsControllerCompat$Impl30$$ExternalSyntheticLambda0(this, onControllableInsetsChangedListener);
                this.mListeners.put(onControllableInsetsChangedListener, windowInsetsControllerCompat$Impl30$$ExternalSyntheticLambda0);
                this.mInsetsController.addOnControllableInsetsChangedListener(windowInsetsControllerCompat$Impl30$$ExternalSyntheticLambda0);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$addOnControllableInsetsChangedListener$0$androidx-core-view-WindowInsetsControllerCompat$Impl30  reason: not valid java name */
        public /* synthetic */ void m34lambda$addOnControllableInsetsChangedListener$0$androidxcoreviewWindowInsetsControllerCompat$Impl30(OnControllableInsetsChangedListener onControllableInsetsChangedListener, WindowInsetsController windowInsetsController, int i) {
            if (this.mInsetsController == windowInsetsController) {
                onControllableInsetsChangedListener.onControllableInsetsChanged(this.mCompatController, i);
            }
        }

        /* access modifiers changed from: package-private */
        public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            WindowInsetsController.OnControllableInsetsChangedListener remove = this.mListeners.remove(onControllableInsetsChangedListener);
            if (remove != null) {
                this.mInsetsController.removeOnControllableInsetsChangedListener(remove);
            }
        }

        /* access modifiers changed from: protected */
        public void unsetSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        /* access modifiers changed from: protected */
        public void setSystemUiFlag(int i) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }
    }
}
