package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ViewUtils;

public class MaterialMainContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private static final float MIN_SCALE = 0.9f;
    private Integer expandedCornerSize;
    private Rect initialHideFromClipBounds;
    private Rect initialHideToClipBounds;
    private float initialTouchY;
    private final float maxTranslationY;
    private final float minEdgeGap;

    public MaterialMainContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.minEdgeGap = resources.getDimension(R.dimen.m3_back_progress_main_container_min_edge_gap);
        this.maxTranslationY = resources.getDimension(R.dimen.m3_back_progress_main_container_max_translation_y);
    }

    public Rect getInitialHideToClipBounds() {
        return this.initialHideToClipBounds;
    }

    public Rect getInitialHideFromClipBounds() {
        return this.initialHideFromClipBounds;
    }

    public void startBackProgress(BackEventCompat backEventCompat, View view) {
        super.onStartBackProgress(backEventCompat);
        startBackProgress(backEventCompat.getTouchY(), view);
    }

    public void startBackProgress(float f, View view) {
        this.initialHideToClipBounds = ViewUtils.calculateRectFromBounds(this.view);
        if (view != null) {
            this.initialHideFromClipBounds = ViewUtils.calculateOffsetRectFromBounds(this.view, view);
        }
        this.initialTouchY = f;
    }

    public void updateBackProgress(BackEventCompat backEventCompat, View view, float f) {
        if (super.onUpdateBackProgress(backEventCompat) != null) {
            if (!(view == null || view.getVisibility() == 4)) {
                view.setVisibility(4);
            }
            updateBackProgress(backEventCompat.getProgress(), backEventCompat.getSwipeEdge() == 0, backEventCompat.getTouchY(), f);
        }
    }

    public void updateBackProgress(float f, boolean z, float f2, float f3) {
        float interpolateProgress = interpolateProgress(f);
        float width = (float) this.view.getWidth();
        float height = (float) this.view.getHeight();
        if (width > 0.0f && height > 0.0f) {
            float lerp = AnimationUtils.lerp(1.0f, (float) MIN_SCALE, interpolateProgress);
            float lerp2 = AnimationUtils.lerp(0.0f, Math.max(0.0f, ((width - (MIN_SCALE * width)) / 2.0f) - this.minEdgeGap), interpolateProgress) * ((float) (z ? 1 : -1));
            float min = Math.min(Math.max(0.0f, ((height - (lerp * height)) / 2.0f) - this.minEdgeGap), this.maxTranslationY);
            float f4 = f2 - this.initialTouchY;
            float lerp3 = AnimationUtils.lerp(0.0f, min, Math.abs(f4) / height) * Math.signum(f4);
            this.view.setScaleX(lerp);
            this.view.setScaleY(lerp);
            this.view.setTranslationX(lerp2);
            this.view.setTranslationY(lerp3);
            if (this.view instanceof ClippableRoundedCornerLayout) {
                ((ClippableRoundedCornerLayout) this.view).updateCornerRadius(AnimationUtils.lerp((float) getExpandedCornerSize(), f3, interpolateProgress));
            }
        }
    }

    public void finishBackProgress(long j, View view) {
        AnimatorSet createResetScaleAndTranslationAnimator = createResetScaleAndTranslationAnimator(view);
        createResetScaleAndTranslationAnimator.setDuration(j);
        createResetScaleAndTranslationAnimator.start();
        resetInitialValues();
    }

    public void cancelBackProgress(View view) {
        if (super.onCancelBackProgress() != null) {
            AnimatorSet createResetScaleAndTranslationAnimator = createResetScaleAndTranslationAnimator(view);
            if (this.view instanceof ClippableRoundedCornerLayout) {
                createResetScaleAndTranslationAnimator.playTogether(new Animator[]{createCornerAnimator((ClippableRoundedCornerLayout) this.view)});
            }
            createResetScaleAndTranslationAnimator.setDuration((long) this.cancelDuration);
            createResetScaleAndTranslationAnimator.start();
            resetInitialValues();
        }
    }

    private void resetInitialValues() {
        this.initialTouchY = 0.0f;
        this.initialHideToClipBounds = null;
        this.initialHideFromClipBounds = null;
    }

    private AnimatorSet createResetScaleAndTranslationAnimator(final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_X, new float[]{0.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, new float[]{0.0f})});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                View view = view;
                if (view != null) {
                    view.setVisibility(0);
                }
            }
        });
        return animatorSet;
    }

    private ValueAnimator createCornerAnimator(ClippableRoundedCornerLayout clippableRoundedCornerLayout) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{clippableRoundedCornerLayout.getCornerRadius(), (float) getExpandedCornerSize()});
        ofFloat.addUpdateListener(new MaterialMainContainerBackHelper$$ExternalSyntheticLambda0(clippableRoundedCornerLayout));
        return ofFloat;
    }

    public int getExpandedCornerSize() {
        if (this.expandedCornerSize == null) {
            this.expandedCornerSize = Integer.valueOf(isAtTopOfScreen() ? getMaxDeviceCornerRadius() : 0);
        }
        return this.expandedCornerSize.intValue();
    }

    private boolean isAtTopOfScreen() {
        int[] iArr = new int[2];
        this.view.getLocationOnScreen(iArr);
        if (iArr[1] == 0) {
            return true;
        }
        return false;
    }

    private int getMaxDeviceCornerRadius() {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 31 || (rootWindowInsets = this.view.getRootWindowInsets()) == null) {
            return 0;
        }
        return Math.max(Math.max(getRoundedCornerRadius(rootWindowInsets, 0), getRoundedCornerRadius(rootWindowInsets, 1)), Math.max(getRoundedCornerRadius(rootWindowInsets, 3), getRoundedCornerRadius(rootWindowInsets, 2)));
    }

    private int getRoundedCornerRadius(WindowInsets windowInsets, int i) {
        RoundedCorner roundedCorner = windowInsets.getRoundedCorner(i);
        if (roundedCorner != null) {
            return roundedCorner.getRadius();
        }
        return 0;
    }
}
