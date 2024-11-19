package com.google.android.material.drawable;

import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;

public class ScaledDrawableWrapper extends DrawableWrapperCompat {
    private final int height;
    private final int width;

    public ScaledDrawableWrapper(Drawable drawable, int i, int i2) {
        super(drawable);
        this.width = i;
        this.height = i2;
    }

    public int getIntrinsicWidth() {
        return this.width;
    }

    public int getIntrinsicHeight() {
        return this.height;
    }
}
