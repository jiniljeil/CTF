package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

class CutoutDrawable extends MaterialShapeDrawable {
    CutoutDrawableState drawableState;

    static CutoutDrawable create(ShapeAppearanceModel shapeAppearanceModel) {
        if (shapeAppearanceModel == null) {
            shapeAppearanceModel = new ShapeAppearanceModel();
        }
        return create(new CutoutDrawableState(shapeAppearanceModel, new RectF()));
    }

    /* access modifiers changed from: private */
    public static CutoutDrawable create(CutoutDrawableState cutoutDrawableState) {
        return new ImplApi18(cutoutDrawableState);
    }

    private CutoutDrawable(CutoutDrawableState cutoutDrawableState) {
        super((MaterialShapeDrawable.MaterialShapeDrawableState) cutoutDrawableState);
        this.drawableState = cutoutDrawableState;
    }

    public Drawable mutate() {
        this.drawableState = new CutoutDrawableState(this.drawableState);
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean hasCutout() {
        return !this.drawableState.cutoutBounds.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void setCutout(float f, float f2, float f3, float f4) {
        if (f != this.drawableState.cutoutBounds.left || f2 != this.drawableState.cutoutBounds.top || f3 != this.drawableState.cutoutBounds.right || f4 != this.drawableState.cutoutBounds.bottom) {
            this.drawableState.cutoutBounds.set(f, f2, f3, f4);
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void setCutout(RectF rectF) {
        setCutout(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    /* access modifiers changed from: package-private */
    public void removeCutout() {
        setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    private static class ImplApi18 extends CutoutDrawable {
        ImplApi18(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
        }

        /* access modifiers changed from: protected */
        public void drawStrokeShape(Canvas canvas) {
            if (this.drawableState.cutoutBounds.isEmpty()) {
                CutoutDrawable.super.drawStrokeShape(canvas);
                return;
            }
            canvas.save();
            if (Build.VERSION.SDK_INT >= 26) {
                canvas.clipOutRect(this.drawableState.cutoutBounds);
            } else {
                canvas.clipRect(this.drawableState.cutoutBounds, Region.Op.DIFFERENCE);
            }
            CutoutDrawable.super.drawStrokeShape(canvas);
            canvas.restore();
        }
    }

    private static class ImplApi14 extends CutoutDrawable {
        private Paint cutoutPaint;
        private int savedLayer;

        ImplApi14(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
        }

        public void draw(Canvas canvas) {
            preDraw(canvas);
            CutoutDrawable.super.draw(canvas);
            postDraw(canvas);
        }

        /* access modifiers changed from: protected */
        public void drawStrokeShape(Canvas canvas) {
            CutoutDrawable.super.drawStrokeShape(canvas);
            canvas.drawRect(this.drawableState.cutoutBounds, getCutoutPaint());
        }

        private Paint getCutoutPaint() {
            if (this.cutoutPaint == null) {
                Paint paint = new Paint(1);
                this.cutoutPaint = paint;
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                this.cutoutPaint.setColor(-1);
                this.cutoutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            }
            return this.cutoutPaint;
        }

        private void preDraw(Canvas canvas) {
            Drawable.Callback callback = getCallback();
            if (useHardwareLayer(callback)) {
                View view = (View) callback;
                if (view.getLayerType() != 2) {
                    view.setLayerType(2, (Paint) null);
                    return;
                }
                return;
            }
            saveCanvasLayer(canvas);
        }

        private void saveCanvasLayer(Canvas canvas) {
            this.savedLayer = canvas.saveLayer(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), (Paint) null);
        }

        private void postDraw(Canvas canvas) {
            if (!useHardwareLayer(getCallback())) {
                canvas.restoreToCount(this.savedLayer);
            }
        }

        private boolean useHardwareLayer(Drawable.Callback callback) {
            return callback instanceof View;
        }
    }

    private static final class CutoutDrawableState extends MaterialShapeDrawable.MaterialShapeDrawableState {
        /* access modifiers changed from: private */
        public final RectF cutoutBounds;

        private CutoutDrawableState(ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
            super(shapeAppearanceModel, (ElevationOverlayProvider) null);
            this.cutoutBounds = rectF;
        }

        private CutoutDrawableState(CutoutDrawableState cutoutDrawableState) {
            super(cutoutDrawableState);
            this.cutoutBounds = cutoutDrawableState.cutoutBounds;
        }

        public Drawable newDrawable() {
            CutoutDrawable access$400 = CutoutDrawable.create(this);
            access$400.invalidateSelf();
            return access$400;
        }
    }
}
