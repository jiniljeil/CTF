package com.google.android.material.progressindicator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.R;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.progressindicator.DrawingDelegate;

public final class IndeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    private IndeterminateAnimatorDelegate<ObjectAnimator> animatorDelegate;
    private DrawingDelegate<S> drawingDelegate;
    private Drawable staticDummyDrawable;

    public /* bridge */ /* synthetic */ void clearAnimationCallbacks() {
        super.clearAnimationCallbacks();
    }

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public /* bridge */ /* synthetic */ boolean hideNow() {
        return super.hideNow();
    }

    public /* bridge */ /* synthetic */ boolean isHiding() {
        return super.isHiding();
    }

    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    public /* bridge */ /* synthetic */ boolean isShowing() {
        return super.isShowing();
    }

    public /* bridge */ /* synthetic */ void registerAnimationCallback(Animatable2Compat.AnimationCallback animationCallback) {
        super.registerAnimationCallback(animationCallback);
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2, boolean z3) {
        return super.setVisible(z, z2, z3);
    }

    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    public /* bridge */ /* synthetic */ boolean unregisterAnimationCallback(Animatable2Compat.AnimationCallback animationCallback) {
        return super.unregisterAnimationCallback(animationCallback);
    }

    IndeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate<S> drawingDelegate2, IndeterminateAnimatorDelegate<ObjectAnimator> indeterminateAnimatorDelegate) {
        super(context, baseProgressIndicatorSpec);
        setDrawingDelegate(drawingDelegate2);
        setAnimatorDelegate(indeterminateAnimatorDelegate);
    }

    public static IndeterminateDrawable<LinearProgressIndicatorSpec> createLinearDrawable(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        return createLinearDrawable(context, linearProgressIndicatorSpec, new LinearDrawingDelegate(linearProgressIndicatorSpec));
    }

    static IndeterminateDrawable<LinearProgressIndicatorSpec> createLinearDrawable(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec, LinearDrawingDelegate linearDrawingDelegate) {
        IndeterminateAnimatorDelegate indeterminateAnimatorDelegate;
        if (linearProgressIndicatorSpec.indeterminateAnimationType == 0) {
            indeterminateAnimatorDelegate = new LinearIndeterminateContiguousAnimatorDelegate(linearProgressIndicatorSpec);
        } else {
            indeterminateAnimatorDelegate = new LinearIndeterminateDisjointAnimatorDelegate(context, linearProgressIndicatorSpec);
        }
        return new IndeterminateDrawable<>(context, linearProgressIndicatorSpec, linearDrawingDelegate, indeterminateAnimatorDelegate);
    }

    public static IndeterminateDrawable<CircularProgressIndicatorSpec> createCircularDrawable(Context context, CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        return createCircularDrawable(context, circularProgressIndicatorSpec, new CircularDrawingDelegate(circularProgressIndicatorSpec));
    }

    static IndeterminateDrawable<CircularProgressIndicatorSpec> createCircularDrawable(Context context, CircularProgressIndicatorSpec circularProgressIndicatorSpec, CircularDrawingDelegate circularDrawingDelegate) {
        IndeterminateDrawable<CircularProgressIndicatorSpec> indeterminateDrawable = new IndeterminateDrawable<>(context, circularProgressIndicatorSpec, circularDrawingDelegate, new CircularIndeterminateAnimatorDelegate(circularProgressIndicatorSpec));
        indeterminateDrawable.setStaticDummyDrawable(VectorDrawableCompat.create(context.getResources(), R.drawable.indeterminate_static, (Resources.Theme) null));
        return indeterminateDrawable;
    }

    /* access modifiers changed from: package-private */
    public boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        Drawable drawable;
        boolean visibleInternal = super.setVisibleInternal(z, z2, z3);
        if (isSystemAnimatorDisabled() && (drawable = this.staticDummyDrawable) != null) {
            return drawable.setVisible(z, z2);
        }
        if (!isRunning()) {
            this.animatorDelegate.cancelAnimatorImmediately();
        }
        if (z && z3) {
            this.animatorDelegate.startAnimator();
        }
        return visibleInternal;
    }

    public int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    public int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            if (!isSystemAnimatorDisabled() || (drawable = this.staticDummyDrawable) == null) {
                canvas.save();
                this.drawingDelegate.validateSpecAndAdjustCanvas(canvas, getBounds(), getGrowFraction(), isShowing(), isHiding());
                int i = this.baseSpec.indicatorTrackGapSize;
                int alpha = getAlpha();
                if (i == 0) {
                    this.drawingDelegate.fillTrack(canvas, this.paint, 0.0f, 1.0f, this.baseSpec.trackColor, alpha, 0);
                } else {
                    DrawingDelegate.ActiveIndicator activeIndicator = this.animatorDelegate.activeIndicators.get(0);
                    DrawingDelegate.ActiveIndicator activeIndicator2 = this.animatorDelegate.activeIndicators.get(this.animatorDelegate.activeIndicators.size() - 1);
                    DrawingDelegate<S> drawingDelegate2 = this.drawingDelegate;
                    if (drawingDelegate2 instanceof LinearDrawingDelegate) {
                        int i2 = alpha;
                        int i3 = i;
                        drawingDelegate2.fillTrack(canvas, this.paint, 0.0f, activeIndicator.startFraction, this.baseSpec.trackColor, i2, i3);
                        this.drawingDelegate.fillTrack(canvas, this.paint, activeIndicator2.endFraction, 1.0f, this.baseSpec.trackColor, i2, i3);
                    } else {
                        alpha = 0;
                        drawingDelegate2.fillTrack(canvas, this.paint, activeIndicator2.endFraction, 1.0f + activeIndicator.startFraction, this.baseSpec.trackColor, 0, i);
                    }
                }
                for (int i4 = 0; i4 < this.animatorDelegate.activeIndicators.size(); i4++) {
                    DrawingDelegate.ActiveIndicator activeIndicator3 = this.animatorDelegate.activeIndicators.get(i4);
                    this.drawingDelegate.fillIndicator(canvas, this.paint, activeIndicator3, getAlpha());
                    if (i4 > 0 && i > 0) {
                        this.drawingDelegate.fillTrack(canvas, this.paint, this.animatorDelegate.activeIndicators.get(i4 - 1).endFraction, activeIndicator3.startFraction, this.baseSpec.trackColor, alpha, i);
                    }
                }
                canvas.restore();
                return;
            }
            drawable.setBounds(getBounds());
            DrawableCompat.setTint(this.staticDummyDrawable, this.baseSpec.indicatorColors[0]);
            this.staticDummyDrawable.draw(canvas);
        }
    }

    private boolean isSystemAnimatorDisabled() {
        if (this.animatorDurationScaleProvider == null || this.animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver()) != 0.0f) {
            return false;
        }
        return true;
    }

    public Drawable getStaticDummyDrawable() {
        return this.staticDummyDrawable;
    }

    public void setStaticDummyDrawable(Drawable drawable) {
        this.staticDummyDrawable = drawable;
    }

    /* access modifiers changed from: package-private */
    public IndeterminateAnimatorDelegate<ObjectAnimator> getAnimatorDelegate() {
        return this.animatorDelegate;
    }

    /* access modifiers changed from: package-private */
    public void setAnimatorDelegate(IndeterminateAnimatorDelegate<ObjectAnimator> indeterminateAnimatorDelegate) {
        this.animatorDelegate = indeterminateAnimatorDelegate;
        indeterminateAnimatorDelegate.registerDrawable(this);
    }

    /* access modifiers changed from: package-private */
    public DrawingDelegate<S> getDrawingDelegate() {
        return this.drawingDelegate;
    }

    /* access modifiers changed from: package-private */
    public void setDrawingDelegate(DrawingDelegate<S> drawingDelegate2) {
        this.drawingDelegate = drawingDelegate2;
    }
}
