package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import java.util.Collection;

public class MultiViewUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    private final Listener listener;
    private final View[] views;

    interface Listener {
        void onAnimationUpdate(ValueAnimator valueAnimator, View view);
    }

    public MultiViewUpdateListener(Listener listener2, View... viewArr) {
        this.listener = listener2;
        this.views = viewArr;
    }

    public MultiViewUpdateListener(Listener listener2, Collection<View> collection) {
        this.listener = listener2;
        this.views = (View[]) collection.toArray(new View[0]);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        for (View onAnimationUpdate : this.views) {
            this.listener.onAnimationUpdate(valueAnimator, onAnimationUpdate);
        }
    }

    public static MultiViewUpdateListener alphaListener(View... viewArr) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda3(), viewArr);
    }

    public static MultiViewUpdateListener alphaListener(Collection<View> collection) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda3(), collection);
    }

    /* access modifiers changed from: private */
    public static void setAlpha(ValueAnimator valueAnimator, View view) {
        view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public static MultiViewUpdateListener scaleListener(View... viewArr) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda1(), viewArr);
    }

    public static MultiViewUpdateListener scaleListener(Collection<View> collection) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda1(), collection);
    }

    /* access modifiers changed from: private */
    public static void setScale(ValueAnimator valueAnimator, View view) {
        Float f = (Float) valueAnimator.getAnimatedValue();
        view.setScaleX(f.floatValue());
        view.setScaleY(f.floatValue());
    }

    public static MultiViewUpdateListener translationXListener(View... viewArr) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda0(), viewArr);
    }

    public static MultiViewUpdateListener translationXListener(Collection<View> collection) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda0(), collection);
    }

    /* access modifiers changed from: private */
    public static void setTranslationX(ValueAnimator valueAnimator, View view) {
        view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public static MultiViewUpdateListener translationYListener(View... viewArr) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda2(), viewArr);
    }

    public static MultiViewUpdateListener translationYListener(Collection<View> collection) {
        return new MultiViewUpdateListener((Listener) new MultiViewUpdateListener$$ExternalSyntheticLambda2(), collection);
    }

    /* access modifiers changed from: private */
    public static void setTranslationY(ValueAnimator valueAnimator, View view) {
        view.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
