package com.google.android.material.internal;

import android.animation.TimeInterpolator;

public class ReversableAnimatedValueInterpolator implements TimeInterpolator {
    private final TimeInterpolator sourceInterpolator;

    public ReversableAnimatedValueInterpolator(TimeInterpolator timeInterpolator) {
        this.sourceInterpolator = timeInterpolator;
    }

    public static TimeInterpolator of(boolean z, TimeInterpolator timeInterpolator) {
        return z ? timeInterpolator : new ReversableAnimatedValueInterpolator(timeInterpolator);
    }

    public float getInterpolation(float f) {
        return 1.0f - this.sourceInterpolator.getInterpolation(f);
    }
}
