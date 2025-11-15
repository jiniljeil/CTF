package com.google.android.material.carousel;

import android.graphics.RectF;

interface Maskable {
    RectF getMaskRectF();

    @Deprecated
    float getMaskXPercentage();

    void setMaskRectF(RectF rectF);

    @Deprecated
    void setMaskXPercentage(float f);

    void setOnMaskChangedListener(OnMaskChangedListener onMaskChangedListener);
}
