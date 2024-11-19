package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.progressindicator.DrawingDelegate;

final class LinearIndeterminateContiguousAnimatorDelegate extends IndeterminateAnimatorDelegate<ObjectAnimator> {
    private static final Property<LinearIndeterminateContiguousAnimatorDelegate, Float> ANIMATION_FRACTION = new Property<LinearIndeterminateContiguousAnimatorDelegate, Float>(Float.class, "animationFraction") {
        public Float get(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateContiguousAnimatorDelegate.getAnimationFraction());
        }

        public void set(LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate, Float f) {
            linearIndeterminateContiguousAnimatorDelegate.setAnimationFraction(f.floatValue());
        }
    };
    private static final int DURATION_PER_CYCLE_IN_MS = 333;
    private static final int TOTAL_DURATION_IN_MS = 667;
    private float animationFraction;
    private ObjectAnimator animator;
    /* access modifiers changed from: private */
    public final BaseProgressIndicatorSpec baseSpec;
    /* access modifiers changed from: private */
    public boolean dirtyColors;
    private FastOutSlowInInterpolator interpolator;
    /* access modifiers changed from: private */
    public int newIndicatorColorIndex = 1;

    public void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback animationCallback) {
    }

    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    public void unregisterAnimatorsCompleteCallback() {
    }

    public LinearIndeterminateContiguousAnimatorDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.baseSpec = linearProgressIndicatorSpec;
        this.interpolator = new FastOutSlowInInterpolator();
    }

    public void startAnimator() {
        maybeInitializeAnimators();
        resetPropertiesForNewStart();
        this.animator.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, ANIMATION_FRACTION, new float[]{0.0f, 1.0f});
            this.animator = ofFloat;
            ofFloat.setDuration(333);
            this.animator.setInterpolator((TimeInterpolator) null);
            this.animator.setRepeatCount(-1);
            this.animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    LinearIndeterminateContiguousAnimatorDelegate linearIndeterminateContiguousAnimatorDelegate = LinearIndeterminateContiguousAnimatorDelegate.this;
                    int unused = linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex = (linearIndeterminateContiguousAnimatorDelegate.newIndicatorColorIndex + 1) % LinearIndeterminateContiguousAnimatorDelegate.this.baseSpec.indicatorColors.length;
                    boolean unused2 = LinearIndeterminateContiguousAnimatorDelegate.this.dirtyColors = true;
                }
            });
        }
    }

    public void cancelAnimatorImmediately() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    public void invalidateSpecValues() {
        resetPropertiesForNewStart();
    }

    private void updateSegmentPositions(int i) {
        ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(0)).startFraction = 0.0f;
        float fractionInRange = getFractionInRange(i, 0, TOTAL_DURATION_IN_MS);
        float interpolation = this.interpolator.getInterpolation(fractionInRange);
        ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(1)).startFraction = interpolation;
        ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(0)).endFraction = interpolation;
        float interpolation2 = this.interpolator.getInterpolation(fractionInRange + 0.49925038f);
        ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(2)).startFraction = interpolation2;
        ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(1)).endFraction = interpolation2;
        ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(2)).endFraction = 1.0f;
    }

    private void maybeUpdateSegmentColors() {
        if (this.dirtyColors && ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(1)).endFraction < 1.0f) {
            ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(2)).color = ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(1)).color;
            ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(1)).color = ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(0)).color;
            ((DrawingDelegate.ActiveIndicator) this.activeIndicators.get(0)).color = this.baseSpec.indicatorColors[this.newIndicatorColorIndex];
            this.dirtyColors = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void resetPropertiesForNewStart() {
        this.dirtyColors = true;
        this.newIndicatorColorIndex = 1;
        for (DrawingDelegate.ActiveIndicator activeIndicator : this.activeIndicators) {
            activeIndicator.color = this.baseSpec.indicatorColors[0];
            activeIndicator.gapSize = this.baseSpec.indicatorTrackGapSize / 2;
        }
    }

    /* access modifiers changed from: private */
    public float getAnimationFraction() {
        return this.animationFraction;
    }

    /* access modifiers changed from: package-private */
    public void setAnimationFraction(float f) {
        this.animationFraction = f;
        updateSegmentPositions((int) (f * 333.0f));
        maybeUpdateSegmentColors();
        this.drawable.invalidateSelf();
    }
}
