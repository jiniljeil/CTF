package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.carousel.KeylineState;

public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private static final float MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD = 0.85f;

    /* access modifiers changed from: package-private */
    public boolean isContained() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        float f;
        float containerWidth = (float) (carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight());
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f2 = (float) (layoutParams.topMargin + layoutParams.bottomMargin);
        float measuredHeight = (float) view.getMeasuredHeight();
        if (carousel.isHorizontal()) {
            float f3 = (float) (layoutParams.leftMargin + layoutParams.rightMargin);
            measuredHeight = (float) view.getMeasuredWidth();
            f = f3;
        } else {
            f = f2;
        }
        float f4 = measuredHeight + f;
        float extraSmallSize = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f;
        float extraSmallSize2 = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f;
        int max = Math.max(1, (int) Math.floor((double) (containerWidth / f4)));
        float f5 = containerWidth - (((float) max) * f4);
        if (carousel.getCarouselAlignment() == 1) {
            float f6 = f5 / 2.0f;
            return createCenterAlignedKeylineState(containerWidth, f, f4, max, Math.max(Math.min(3.0f * f6, f4), getSmallItemSizeMin() + f), extraSmallSize2, f6);
        }
        return createLeftAlignedKeylineState(view.getContext(), f, containerWidth, f4, max, calculateMediumChildSize(extraSmallSize, f4, f5), f5 > 0.0f ? 1 : 0, extraSmallSize2);
    }

    private float calculateMediumChildSize(float f, float f2, float f3) {
        float max = Math.max(1.5f * f3, f);
        float f4 = MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD * f2;
        if (max > f4) {
            max = Math.max(f4, f3 * 1.2f);
        }
        return Math.min(f2, max);
    }

    private KeylineState createCenterAlignedKeylineState(float f, float f2, float f3, int i, float f4, float f5, float f6) {
        float f7 = f2;
        float f8 = f3;
        float f9 = f4;
        float min = Math.min(f5, f8);
        float childMaskPercentage = getChildMaskPercentage(min, f8, f7);
        float childMaskPercentage2 = getChildMaskPercentage(f9, f8, f7);
        float f10 = f9 / 2.0f;
        float f11 = (f6 + 0.0f) - f10;
        float f12 = f11 + f10;
        float f13 = min / 2.0f;
        float f14 = (((float) i) * f8) + f12;
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(f8, f).addAnchorKeyline((f11 - f10) - f13, childMaskPercentage, min).addKeyline(f11, childMaskPercentage2, f9, false).addKeylineRange((f8 / 2.0f) + f12, 0.0f, f8, i, true);
        addKeylineRange.addKeyline(f10 + f14, childMaskPercentage2, f9, false);
        addKeylineRange.addAnchorKeyline(f14 + f9 + f13, childMaskPercentage, min);
        return addKeylineRange.build();
    }

    private KeylineState createLeftAlignedKeylineState(Context context, float f, float f2, float f3, int i, float f4, int i2, float f5) {
        float f6 = f;
        float f7 = f3;
        float f8 = f4;
        float min = Math.min(f5, f7);
        float max = Math.max(min, 0.5f * f8);
        float childMaskPercentage = getChildMaskPercentage(max, f7, f6);
        float childMaskPercentage2 = getChildMaskPercentage(min, f7, f6);
        float childMaskPercentage3 = getChildMaskPercentage(f8, f7, f6);
        float f9 = (((float) i) * f7) + 0.0f;
        KeylineState.Builder addKeylineRange = new KeylineState.Builder(f7, f2).addAnchorKeyline(0.0f - (max / 2.0f), childMaskPercentage, max).addKeylineRange(f7 / 2.0f, 0.0f, f7, i, true);
        if (i2 > 0) {
            float f10 = (f8 / 2.0f) + f9;
            f9 += f8;
            addKeylineRange.addKeyline(f10, childMaskPercentage3, f8, false);
        }
        addKeylineRange.addAnchorKeyline(f9 + (CarouselStrategyHelper.getExtraSmallSize(context) / 2.0f), childMaskPercentage2, min);
        return addKeylineRange.build();
    }
}
