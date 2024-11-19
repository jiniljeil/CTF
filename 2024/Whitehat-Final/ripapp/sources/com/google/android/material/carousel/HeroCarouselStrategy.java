package com.google.android.material.carousel;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

public class HeroCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = {0, 1};
    private static final int[] SMALL_COUNTS = {1};
    private int keylineCount = 0;

    /* access modifiers changed from: package-private */
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        int[] iArr;
        int i;
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f = (float) (layoutParams.topMargin + layoutParams.bottomMargin);
        float measuredWidth = (float) (view.getMeasuredWidth() * 2);
        if (carousel.isHorizontal()) {
            f = (float) (layoutParams.leftMargin + layoutParams.rightMargin);
            measuredWidth = (float) (view.getMeasuredHeight() * 2);
        }
        float smallItemSizeMin = getSmallItemSizeMin() + f;
        float max = Math.max(getSmallItemSizeMax() + f, smallItemSizeMin);
        float f2 = (float) containerHeight;
        float min = Math.min(measuredWidth + f, f2);
        float clamp = MathUtils.clamp((measuredWidth / 3.0f) + f, smallItemSizeMin + f, max + f);
        float f3 = (min + clamp) / 2.0f;
        int[] iArr2 = SMALL_COUNTS;
        int[] iArr3 = f2 < 2.0f * smallItemSizeMin ? new int[]{0} : iArr2;
        int max2 = (int) Math.max(1.0d, Math.floor((double) ((f2 - (((float) CarouselStrategyHelper.maxValue(iArr2)) * max)) / min)));
        int ceil = (((int) Math.ceil((double) (f2 / min))) - max2) + 1;
        int[] iArr4 = new int[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            iArr4[i2] = max2 + i2;
        }
        int i3 = carousel.getCarouselAlignment() == 1 ? 1 : 0;
        int[] doubleCounts = i3 != 0 ? doubleCounts(iArr3) : iArr3;
        if (i3 != 0) {
            iArr = doubleCounts(MEDIUM_COUNTS);
        } else {
            iArr = MEDIUM_COUNTS;
        }
        int[] iArr5 = iArr4;
        Arrangement findLowestCostArrangement = Arrangement.findLowestCostArrangement(f2, clamp, smallItemSizeMin, max, doubleCounts, f3, iArr, min, iArr4);
        this.keylineCount = findLowestCostArrangement.getItemCount();
        if (findLowestCostArrangement.getItemCount() > carousel.getItemCount()) {
            findLowestCostArrangement = Arrangement.findLowestCostArrangement(f2, clamp, smallItemSizeMin, max, iArr3, f3, MEDIUM_COUNTS, min, iArr5);
            i = 0;
        } else {
            i = i3;
        }
        return CarouselStrategyHelper.createKeylineState(view.getContext(), f, f2, findLowestCostArrangement, i);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRefreshKeylineState(Carousel carousel, int i) {
        if (carousel.getCarouselAlignment() == 1) {
            if (i < this.keylineCount && carousel.getItemCount() >= this.keylineCount) {
                return true;
            }
            if (i < this.keylineCount || carousel.getItemCount() >= this.keylineCount) {
                return false;
            }
            return true;
        }
        return false;
    }
}
