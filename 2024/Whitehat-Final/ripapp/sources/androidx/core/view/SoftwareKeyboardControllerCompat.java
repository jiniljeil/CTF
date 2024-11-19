package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SoftwareKeyboardControllerCompat {
    private final Impl mImpl;

    public SoftwareKeyboardControllerCompat(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(view);
        } else {
            this.mImpl = new Impl20(view);
        }
    }

    @Deprecated
    SoftwareKeyboardControllerCompat(WindowInsetsController windowInsetsController) {
        this.mImpl = new Impl30(windowInsetsController);
    }

    public void show() {
        this.mImpl.show();
    }

    public void hide() {
        this.mImpl.hide();
    }

    private static class Impl {
        /* access modifiers changed from: package-private */
        public void hide() {
        }

        /* access modifiers changed from: package-private */
        public void show() {
        }

        Impl() {
        }
    }

    private static class Impl20 extends Impl {
        private final View mView;

        Impl20(View view) {
            this.mView = view;
        }

        /* access modifiers changed from: package-private */
        public void show() {
            View view = this.mView;
            if (view != null) {
                if (view.isInEditMode() || view.onCheckIsTextEditor()) {
                    view.requestFocus();
                } else {
                    view = view.getRootView().findFocus();
                }
                if (view == null) {
                    view = this.mView.getRootView().findViewById(16908290);
                }
                if (view != null && view.hasWindowFocus()) {
                    view.post(new SoftwareKeyboardControllerCompat$Impl20$$ExternalSyntheticLambda0(view));
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void hide() {
            View view = this.mView;
            if (view != null) {
                ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
            }
        }
    }

    private static class Impl30 extends Impl20 {
        private View mView;
        private WindowInsetsController mWindowInsetsController;

        Impl30(View view) {
            super(view);
            this.mView = view;
        }

        Impl30(WindowInsetsController windowInsetsController) {
            super((View) null);
            this.mWindowInsetsController = windowInsetsController;
        }

        /* access modifiers changed from: package-private */
        public void show() {
            if (this.mView != null && Build.VERSION.SDK_INT < 33) {
                ((InputMethodManager) this.mView.getContext().getSystemService("input_method")).isActive();
            }
            WindowInsetsController windowInsetsController = this.mWindowInsetsController;
            if (windowInsetsController == null) {
                View view = this.mView;
                windowInsetsController = view != null ? view.getWindowInsetsController() : null;
            }
            if (windowInsetsController != null) {
                windowInsetsController.show(WindowInsets.Type.ime());
            } else {
                super.show();
            }
        }

        /* access modifiers changed from: package-private */
        public void hide() {
            View view;
            WindowInsetsController windowInsetsController = this.mWindowInsetsController;
            if (windowInsetsController == null) {
                View view2 = this.mView;
                windowInsetsController = view2 != null ? view2.getWindowInsetsController() : null;
            }
            if (windowInsetsController != null) {
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0 softwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0 = new SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0(atomicBoolean);
                windowInsetsController.addOnControllableInsetsChangedListener(softwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0);
                if (!atomicBoolean.get() && (view = this.mView) != null) {
                    ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
                }
                windowInsetsController.removeOnControllableInsetsChangedListener(softwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0);
                windowInsetsController.hide(WindowInsets.Type.ime());
                return;
            }
            super.hide();
        }

        static /* synthetic */ void lambda$hide$0(AtomicBoolean atomicBoolean, WindowInsetsController windowInsetsController, int i) {
            atomicBoolean.set((i & 8) != 0);
        }
    }
}
