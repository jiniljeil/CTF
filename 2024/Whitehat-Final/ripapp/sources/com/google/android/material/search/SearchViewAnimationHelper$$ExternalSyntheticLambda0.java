package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.graphics.Rect;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SearchViewAnimationHelper$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SearchViewAnimationHelper f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ Rect f$3;

    public /* synthetic */ SearchViewAnimationHelper$$ExternalSyntheticLambda0(SearchViewAnimationHelper searchViewAnimationHelper, float f, float f2, Rect rect) {
        this.f$0 = searchViewAnimationHelper;
        this.f$1 = f;
        this.f$2 = f2;
        this.f$3 = rect;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f$0.m124lambda$getRootViewAnimator$2$comgoogleandroidmaterialsearchSearchViewAnimationHelper(this.f$1, this.f$2, this.f$3, valueAnimator);
    }
}
