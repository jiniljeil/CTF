package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

public class MaterialBottomContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistance;
    private final float maxScaleYDistance;

    public MaterialBottomContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.maxScaleXDistance = resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        this.maxScaleYDistance = resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    public void startBackProgress(BackEventCompat backEventCompat) {
        super.onStartBackProgress(backEventCompat);
    }

    public void updateBackProgress(BackEventCompat backEventCompat) {
        if (super.onUpdateBackProgress(backEventCompat) != null) {
            updateBackProgress(backEventCompat.getProgress());
        }
    }

    public void updateBackProgress(float f) {
        float interpolateProgress = interpolateProgress(f);
        float width = (float) this.view.getWidth();
        float height = (float) this.view.getHeight();
        if (width > 0.0f && height > 0.0f) {
            float lerp = 1.0f - AnimationUtils.lerp(0.0f, this.maxScaleXDistance / width, interpolateProgress);
            float lerp2 = 1.0f - AnimationUtils.lerp(0.0f, this.maxScaleYDistance / height, interpolateProgress);
            this.view.setScaleX(lerp);
            this.view.setPivotY(height);
            this.view.setScaleY(lerp2);
            if (this.view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) this.view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    childAt.setPivotY((float) (-childAt.getTop()));
                    childAt.setScaleY(lerp2 != 0.0f ? lerp / lerp2 : 1.0f);
                }
            }
        }
    }

    public void finishBackProgressPersistent(BackEventCompat backEventCompat, Animator.AnimatorListener animatorListener) {
        Animator createResetScaleAnimator = createResetScaleAnimator();
        createResetScaleAnimator.setDuration((long) AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat.getProgress()));
        if (animatorListener != null) {
            createResetScaleAnimator.addListener(animatorListener);
        }
        createResetScaleAnimator.start();
    }

    public void finishBackProgressNotPersistent(BackEventCompat backEventCompat, Animator.AnimatorListener animatorListener) {
        float height = ((float) this.view.getHeight()) * this.view.getScaleY();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, new float[]{height});
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration((long) AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat.getProgress()));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                MaterialBottomContainerBackHelper.this.view.setTranslationY(0.0f);
                MaterialBottomContainerBackHelper.this.updateBackProgress(0.0f);
            }
        });
        if (animatorListener != null) {
            ofFloat.addListener(animatorListener);
        }
        ofFloat.start();
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() != null) {
            Animator createResetScaleAnimator = createResetScaleAnimator();
            createResetScaleAnimator.setDuration((long) this.cancelDuration);
            createResetScaleAnimator.start();
        }
    }

    private Animator createResetScaleAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
        if (this.view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) this.view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(viewGroup.getChildAt(i), View.SCALE_Y, new float[]{1.0f})});
            }
        }
        animatorSet.setInterpolator(new FastOutSlowInInterpolator());
        return animatorSet;
    }
}
