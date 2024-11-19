package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.ClampedCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.ShapeableDelegate;

public class MaskableFrameLayout extends FrameLayout implements Maskable, Shapeable {
    private static final int NOT_SET = -1;
    private final RectF maskRect;
    private float maskXPercentage;
    private OnMaskChangedListener onMaskChangedListener;
    private Boolean savedForceCompatClippingEnabled;
    private ShapeAppearanceModel shapeAppearanceModel;
    private final ShapeableDelegate shapeableDelegate;

    public MaskableFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaskableFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maskXPercentage = -1.0f;
        this.maskRect = new RectF();
        this.shapeableDelegate = ShapeableDelegate.create(this);
        this.savedForceCompatClippingEnabled = null;
        setShapeAppearanceModel(ShapeAppearanceModel.builder(context, attributeSet, i, 0, 0).build());
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.maskXPercentage != -1.0f) {
            updateMaskRectForMaskXPercentage();
        }
    }

    public void getFocusedRect(Rect rect) {
        rect.set((int) this.maskRect.left, (int) this.maskRect.top, (int) this.maskRect.right, (int) this.maskRect.bottom);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean bool = this.savedForceCompatClippingEnabled;
        if (bool != null) {
            this.shapeableDelegate.setForceCompatClippingEnabled(this, bool.booleanValue());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.savedForceCompatClippingEnabled = Boolean.valueOf(this.shapeableDelegate.isForceCompatClippingEnabled());
        this.shapeableDelegate.setForceCompatClippingEnabled(this, true);
        super.onDetachedFromWindow();
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel2) {
        ShapeAppearanceModel withTransformedCornerSizes = shapeAppearanceModel2.withTransformedCornerSizes(new MaskableFrameLayout$$ExternalSyntheticLambda0());
        this.shapeAppearanceModel = withTransformedCornerSizes;
        this.shapeableDelegate.onShapeAppearanceChanged(this, withTransformedCornerSizes);
    }

    static /* synthetic */ CornerSize lambda$setShapeAppearanceModel$0(CornerSize cornerSize) {
        return cornerSize instanceof AbsoluteCornerSize ? ClampedCornerSize.createFromCornerSize((AbsoluteCornerSize) cornerSize) : cornerSize;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    @Deprecated
    public void setMaskXPercentage(float f) {
        float clamp = MathUtils.clamp(f, 0.0f, 1.0f);
        if (this.maskXPercentage != clamp) {
            this.maskXPercentage = clamp;
            updateMaskRectForMaskXPercentage();
        }
    }

    private void updateMaskRectForMaskXPercentage() {
        if (this.maskXPercentage != -1.0f) {
            float lerp = AnimationUtils.lerp(0.0f, ((float) getWidth()) / 2.0f, 0.0f, 1.0f, this.maskXPercentage);
            setMaskRectF(new RectF(lerp, 0.0f, ((float) getWidth()) - lerp, (float) getHeight()));
        }
    }

    public void setMaskRectF(RectF rectF) {
        this.maskRect.set(rectF);
        onMaskChanged();
    }

    @Deprecated
    public float getMaskXPercentage() {
        return this.maskXPercentage;
    }

    public RectF getMaskRectF() {
        return this.maskRect;
    }

    public void setOnMaskChangedListener(OnMaskChangedListener onMaskChangedListener2) {
        this.onMaskChangedListener = onMaskChangedListener2;
    }

    private void onMaskChanged() {
        this.shapeableDelegate.onMaskChanged(this, this.maskRect);
        OnMaskChangedListener onMaskChangedListener2 = this.onMaskChangedListener;
        if (onMaskChangedListener2 != null) {
            onMaskChangedListener2.onMaskChanged(this.maskRect);
        }
    }

    public void setForceCompatClipping(boolean z) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, z);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.maskRect.isEmpty() && motionEvent.getAction() == 0) {
            if (!this.maskRect.contains(motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        this.shapeableDelegate.maybeClip(canvas, new MaskableFrameLayout$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchDraw$1$com-google-android-material-carousel-MaskableFrameLayout  reason: not valid java name */
    public /* synthetic */ void m63lambda$dispatchDraw$1$comgoogleandroidmaterialcarouselMaskableFrameLayout(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
