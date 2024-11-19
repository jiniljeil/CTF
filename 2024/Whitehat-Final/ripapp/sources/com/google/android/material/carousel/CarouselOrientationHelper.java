package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

abstract class CarouselOrientationHelper {
    final int orientation;

    /* access modifiers changed from: package-private */
    public abstract void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3);

    /* access modifiers changed from: package-private */
    public abstract int getDecoratedCrossAxisMeasurement(View view);

    /* access modifiers changed from: package-private */
    public abstract float getMaskMargins(RecyclerView.LayoutParams layoutParams);

    /* access modifiers changed from: package-private */
    public abstract RectF getMaskRect(float f, float f2, float f3, float f4);

    /* access modifiers changed from: package-private */
    public abstract int getParentBottom();

    /* access modifiers changed from: package-private */
    public abstract int getParentEnd();

    /* access modifiers changed from: package-private */
    public abstract int getParentLeft();

    /* access modifiers changed from: package-private */
    public abstract int getParentRight();

    /* access modifiers changed from: package-private */
    public abstract int getParentStart();

    /* access modifiers changed from: package-private */
    public abstract int getParentTop();

    /* access modifiers changed from: package-private */
    public abstract void layoutDecoratedWithMargins(View view, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3);

    /* access modifiers changed from: package-private */
    public abstract void offsetChild(View view, Rect rect, float f, float f2);

    private CarouselOrientationHelper(int i) {
        this.orientation = i;
    }

    static CarouselOrientationHelper createOrientationHelper(CarouselLayoutManager carouselLayoutManager, int i) {
        if (i == 0) {
            return createHorizontalHelper(carouselLayoutManager);
        }
        if (i == 1) {
            return createVerticalHelper(carouselLayoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    private static CarouselOrientationHelper createVerticalHelper(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(1) {
            /* access modifiers changed from: package-private */
            public int getParentTop() {
                return 0;
            }

            /* access modifiers changed from: package-private */
            public int getParentLeft() {
                return carouselLayoutManager.getPaddingLeft();
            }

            /* access modifiers changed from: package-private */
            public int getParentStart() {
                return getParentTop();
            }

            /* access modifiers changed from: package-private */
            public int getParentRight() {
                return carouselLayoutManager.getWidth() - carouselLayoutManager.getPaddingRight();
            }

            /* access modifiers changed from: package-private */
            public int getParentEnd() {
                return getParentBottom();
            }

            /* access modifiers changed from: package-private */
            public int getParentBottom() {
                return carouselLayoutManager.getHeight();
            }

            /* access modifiers changed from: package-private */
            public int getDecoratedCrossAxisMeasurement(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return carouselLayoutManager.getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
            }

            public void layoutDecoratedWithMargins(View view, int i, int i2) {
                int parentLeft = getParentLeft();
                carouselLayoutManager.layoutDecoratedWithMargins(view, parentLeft, i, parentLeft + getDecoratedCrossAxisMeasurement(view), i2);
            }

            public float getMaskMargins(RecyclerView.LayoutParams layoutParams) {
                return (float) (layoutParams.topMargin + layoutParams.bottomMargin);
            }

            public RectF getMaskRect(float f, float f2, float f3, float f4) {
                return new RectF(0.0f, f3, f2, f - f3);
            }

            public void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.top < rectF3.top && rectF2.bottom > rectF3.top) {
                    float f = rectF3.top - rectF2.top;
                    rectF.top += f;
                    rectF3.top += f;
                }
                if (rectF2.bottom > rectF3.bottom && rectF2.top < rectF3.bottom) {
                    float f2 = rectF2.bottom - rectF3.bottom;
                    rectF.bottom = Math.max(rectF.bottom - f2, rectF.top);
                    rectF2.bottom = Math.max(rectF2.bottom - f2, rectF2.top);
                }
            }

            public void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.bottom <= rectF3.top) {
                    rectF.bottom = ((float) Math.floor((double) rectF.bottom)) - 1.0f;
                    rectF.top = Math.min(rectF.top, rectF.bottom);
                }
                if (rectF2.top >= rectF3.bottom) {
                    rectF.top = ((float) Math.ceil((double) rectF.top)) + 1.0f;
                    rectF.bottom = Math.max(rectF.top, rectF.bottom);
                }
            }

            public void offsetChild(View view, Rect rect, float f, float f2) {
                view.offsetTopAndBottom((int) (f2 - (((float) rect.top) + f)));
            }
        };
    }

    private static CarouselOrientationHelper createHorizontalHelper(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(0) {
            /* access modifiers changed from: package-private */
            public int getParentLeft() {
                return 0;
            }

            /* access modifiers changed from: package-private */
            public int getParentStart() {
                return carouselLayoutManager.isLayoutRtl() ? getParentRight() : getParentLeft();
            }

            /* access modifiers changed from: package-private */
            public int getParentRight() {
                return carouselLayoutManager.getWidth();
            }

            /* access modifiers changed from: package-private */
            public int getParentEnd() {
                return carouselLayoutManager.isLayoutRtl() ? getParentLeft() : getParentRight();
            }

            /* access modifiers changed from: package-private */
            public int getParentTop() {
                return carouselLayoutManager.getPaddingTop();
            }

            /* access modifiers changed from: package-private */
            public int getParentBottom() {
                return carouselLayoutManager.getHeight() - carouselLayoutManager.getPaddingBottom();
            }

            /* access modifiers changed from: package-private */
            public int getDecoratedCrossAxisMeasurement(View view) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                return carouselLayoutManager.getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
            }

            public void layoutDecoratedWithMargins(View view, int i, int i2) {
                int parentTop = getParentTop();
                carouselLayoutManager.layoutDecoratedWithMargins(view, i, parentTop, i2, parentTop + getDecoratedCrossAxisMeasurement(view));
            }

            public float getMaskMargins(RecyclerView.LayoutParams layoutParams) {
                return (float) (layoutParams.rightMargin + layoutParams.leftMargin);
            }

            public RectF getMaskRect(float f, float f2, float f3, float f4) {
                return new RectF(f4, 0.0f, f2 - f4, f);
            }

            public void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.left < rectF3.left && rectF2.right > rectF3.left) {
                    float f = rectF3.left - rectF2.left;
                    rectF.left += f;
                    rectF2.left += f;
                }
                if (rectF2.right > rectF3.right && rectF2.left < rectF3.right) {
                    float f2 = rectF2.right - rectF3.right;
                    rectF.right = Math.max(rectF.right - f2, rectF.left);
                    rectF2.right = Math.max(rectF2.right - f2, rectF2.left);
                }
            }

            public void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.right <= rectF3.left) {
                    rectF.right = ((float) Math.floor((double) rectF.right)) - 1.0f;
                    rectF.left = Math.min(rectF.left, rectF.right);
                }
                if (rectF2.left >= rectF3.right) {
                    rectF.left = ((float) Math.ceil((double) rectF.left)) + 1.0f;
                    rectF.right = Math.max(rectF.left, rectF.right);
                }
            }

            public void offsetChild(View view, Rect rect, float f, float f2) {
                view.offsetLeftAndRight((int) (f2 - (((float) rect.left) + f)));
            }
        };
    }
}
