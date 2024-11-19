package com.google.android.material.motion;

import android.os.Build;
import android.view.View;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.BackEventCompat;
import java.util.Objects;
import kotlin.time.DurationKt;

public final class MaterialBackOrchestrator {
    private final BackCallbackDelegate backCallbackDelegate;
    private final MaterialBackHandler backHandler;
    private final View view;

    private interface BackCallbackDelegate {
        void startListeningForBackCallbacks(MaterialBackHandler materialBackHandler, View view, boolean z);

        void stopListeningForBackCallbacks(View view);
    }

    public <T extends View & MaterialBackHandler> MaterialBackOrchestrator(T t) {
        this((MaterialBackHandler) t, t);
    }

    public MaterialBackOrchestrator(MaterialBackHandler materialBackHandler, View view2) {
        this.backCallbackDelegate = createBackCallbackDelegate();
        this.backHandler = materialBackHandler;
        this.view = view2;
    }

    public boolean shouldListenForBackCallbacks() {
        return this.backCallbackDelegate != null;
    }

    public void startListeningForBackCallbacksWithPriorityOverlay() {
        startListeningForBackCallbacks(true);
    }

    public void startListeningForBackCallbacks() {
        startListeningForBackCallbacks(false);
    }

    private void startListeningForBackCallbacks(boolean z) {
        BackCallbackDelegate backCallbackDelegate2 = this.backCallbackDelegate;
        if (backCallbackDelegate2 != null) {
            backCallbackDelegate2.startListeningForBackCallbacks(this.backHandler, this.view, z);
        }
    }

    public void stopListeningForBackCallbacks() {
        BackCallbackDelegate backCallbackDelegate2 = this.backCallbackDelegate;
        if (backCallbackDelegate2 != null) {
            backCallbackDelegate2.stopListeningForBackCallbacks(this.view);
        }
    }

    private static BackCallbackDelegate createBackCallbackDelegate() {
        if (Build.VERSION.SDK_INT >= 34) {
            return new Api34BackCallbackDelegate();
        }
        if (Build.VERSION.SDK_INT >= 33) {
            return new Api33BackCallbackDelegate();
        }
        return null;
    }

    private static class Api34BackCallbackDelegate extends Api33BackCallbackDelegate {
        private Api34BackCallbackDelegate() {
            super();
        }

        /* access modifiers changed from: package-private */
        public OnBackInvokedCallback createOnBackInvokedCallback(final MaterialBackHandler materialBackHandler) {
            return new OnBackAnimationCallback() {
                public void onBackStarted(BackEvent backEvent) {
                    if (Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        materialBackHandler.startBackProgress(new BackEventCompat(backEvent));
                    }
                }

                public void onBackProgressed(BackEvent backEvent) {
                    if (Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        materialBackHandler.updateBackProgress(new BackEventCompat(backEvent));
                    }
                }

                public void onBackInvoked() {
                    materialBackHandler.handleBackInvoked();
                }

                public void onBackCancelled() {
                    if (Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        materialBackHandler.cancelBackProgress();
                    }
                }
            };
        }
    }

    private static class Api33BackCallbackDelegate implements BackCallbackDelegate {
        private OnBackInvokedCallback onBackInvokedCallback;

        private Api33BackCallbackDelegate() {
        }

        /* access modifiers changed from: package-private */
        public boolean isListeningForBackCallbacks() {
            return this.onBackInvokedCallback != null;
        }

        public void startListeningForBackCallbacks(MaterialBackHandler materialBackHandler, View view, boolean z) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher;
            if (this.onBackInvokedCallback == null && (findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) != null) {
                OnBackInvokedCallback createOnBackInvokedCallback = createOnBackInvokedCallback(materialBackHandler);
                this.onBackInvokedCallback = createOnBackInvokedCallback;
                findOnBackInvokedDispatcher.registerOnBackInvokedCallback(z ? DurationKt.NANOS_IN_MILLIS : 0, createOnBackInvokedCallback);
            }
        }

        public void stopListeningForBackCallbacks(View view) {
            OnBackInvokedDispatcher findOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher();
            if (findOnBackInvokedDispatcher != null) {
                findOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(this.onBackInvokedCallback);
                this.onBackInvokedCallback = null;
            }
        }

        /* access modifiers changed from: package-private */
        public OnBackInvokedCallback createOnBackInvokedCallback(MaterialBackHandler materialBackHandler) {
            Objects.requireNonNull(materialBackHandler);
            return new MaterialBackOrchestrator$Api33BackCallbackDelegate$$ExternalSyntheticLambda0(materialBackHandler);
        }
    }
}
