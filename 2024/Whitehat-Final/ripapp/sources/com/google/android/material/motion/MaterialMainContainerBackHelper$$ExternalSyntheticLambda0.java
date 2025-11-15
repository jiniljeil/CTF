package com.google.android.material.motion;

import android.animation.ValueAnimator;
import com.google.android.material.internal.ClippableRoundedCornerLayout;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MaterialMainContainerBackHelper$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ClippableRoundedCornerLayout f$0;

    public /* synthetic */ MaterialMainContainerBackHelper$$ExternalSyntheticLambda0(ClippableRoundedCornerLayout clippableRoundedCornerLayout) {
        this.f$0 = clippableRoundedCornerLayout;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f$0.updateCornerRadius(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
