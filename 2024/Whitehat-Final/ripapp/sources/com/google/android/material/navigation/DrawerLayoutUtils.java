package com.google.android.material.navigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerLayoutUtils {
    private static final int DEFAULT_SCRIM_ALPHA = Color.alpha(DEFAULT_SCRIM_COLOR);
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;

    private DrawerLayoutUtils() {
    }

    public static ValueAnimator.AnimatorUpdateListener getScrimCloseAnimatorUpdateListener(DrawerLayout drawerLayout) {
        return new DrawerLayoutUtils$$ExternalSyntheticLambda0(drawerLayout);
    }

    public static Animator.AnimatorListener getScrimCloseAnimatorListener(final DrawerLayout drawerLayout, final View view) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                DrawerLayout.this.closeDrawer(view, false);
                DrawerLayout.this.setScrimColor(DrawerLayoutUtils.DEFAULT_SCRIM_COLOR);
            }
        };
    }
}
