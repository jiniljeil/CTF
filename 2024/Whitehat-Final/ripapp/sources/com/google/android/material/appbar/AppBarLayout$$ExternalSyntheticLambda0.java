package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import com.google.android.material.shape.MaterialShapeDrawable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppBarLayout$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AppBarLayout f$0;
    public final /* synthetic */ ColorStateList f$1;
    public final /* synthetic */ ColorStateList f$2;
    public final /* synthetic */ MaterialShapeDrawable f$3;
    public final /* synthetic */ Integer f$4;

    public /* synthetic */ AppBarLayout$$ExternalSyntheticLambda0(AppBarLayout appBarLayout, ColorStateList colorStateList, ColorStateList colorStateList2, MaterialShapeDrawable materialShapeDrawable, Integer num) {
        this.f$0 = appBarLayout;
        this.f$1 = colorStateList;
        this.f$2 = colorStateList2;
        this.f$3 = materialShapeDrawable;
        this.f$4 = num;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f$0.m58lambda$initializeLiftOnScrollWithColor$0$comgoogleandroidmaterialappbarAppBarLayout(this.f$1, this.f$2, this.f$3, this.f$4, valueAnimator);
    }
}
