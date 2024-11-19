package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistanceGrow;
    private final float maxScaleXDistanceShrink;
    private final float maxScaleYDistance;

    public MaterialSideContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.maxScaleXDistanceShrink = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.maxScaleXDistanceGrow = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.maxScaleYDistance = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    public void startBackProgress(BackEventCompat backEventCompat) {
        super.onStartBackProgress(backEventCompat);
    }

    public void updateBackProgress(BackEventCompat backEventCompat, int i) {
        if (super.onUpdateBackProgress(backEventCompat) != null) {
            updateBackProgress(backEventCompat.getProgress(), backEventCompat.getSwipeEdge() == 0, i);
        }
    }

    public void updateBackProgress(float f, boolean z, int i) {
        int i2;
        float interpolateProgress = interpolateProgress(f);
        boolean checkAbsoluteGravity = checkAbsoluteGravity(i, 3);
        boolean z2 = z == checkAbsoluteGravity;
        int width = this.view.getWidth();
        int height = this.view.getHeight();
        float f2 = (float) width;
        if (f2 > 0.0f) {
            float f3 = (float) height;
            if (f3 > 0.0f) {
                float f4 = this.maxScaleXDistanceShrink / f2;
                float f5 = this.maxScaleXDistanceGrow / f2;
                float f6 = this.maxScaleYDistance / f3;
                View view = this.view;
                if (checkAbsoluteGravity) {
                    f2 = 0.0f;
                }
                view.setPivotX(f2);
                if (!z2) {
                    f5 = -f4;
                }
                float lerp = AnimationUtils.lerp(0.0f, f5, interpolateProgress);
                float f7 = lerp + 1.0f;
                this.view.setScaleX(f7);
                float lerp2 = 1.0f - AnimationUtils.lerp(0.0f, f6, interpolateProgress);
                this.view.setScaleY(lerp2);
                if (this.view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) this.view;
                    for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                        View childAt = viewGroup.getChildAt(i3);
                        if (checkAbsoluteGravity) {
                            i2 = (width - childAt.getRight()) + childAt.getWidth();
                        } else {
                            i2 = -childAt.getLeft();
                        }
                        childAt.setPivotX((float) i2);
                        childAt.setPivotY((float) (-childAt.getTop()));
                        float f8 = z2 ? 1.0f - lerp : 1.0f;
                        float f9 = lerp2 != 0.0f ? (f7 / lerp2) * f8 : 1.0f;
                        childAt.setScaleX(f8);
                        childAt.setScaleY(f9);
                    }
                }
            }
        }
    }

    public void finishBackProgress(BackEventCompat backEventCompat, final int i, Animator.AnimatorListener animatorListener, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        final boolean z = backEventCompat.getSwipeEdge() == 0;
        boolean checkAbsoluteGravity = checkAbsoluteGravity(i, 3);
        float width = (((float) this.view.getWidth()) * this.view.getScaleX()) + ((float) getEdgeMargin(checkAbsoluteGravity));
        View view = this.view;
        Property property = View.TRANSLATION_X;
        if (checkAbsoluteGravity) {
            width = -width;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, new float[]{width});
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration((long) AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat.getProgress()));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                MaterialSideContainerBackHelper.this.view.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, z, i);
            }
        });
        if (animatorListener != null) {
            ofFloat.addListener(animatorListener);
        }
        ofFloat.start();
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
            if (this.view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) this.view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(viewGroup.getChildAt(i), View.SCALE_Y, new float[]{1.0f})});
                }
            }
            animatorSet.setDuration((long) this.cancelDuration);
            animatorSet.start();
        }
    }

    private boolean checkAbsoluteGravity(int i, int i2) {
        return (GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this.view)) & i2) == i2;
    }

    private int getEdgeMargin(boolean z) {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return z ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
    }
}
