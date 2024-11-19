package com.google.android.material.internal;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

public class RectEvaluator implements TypeEvaluator<Rect> {
    private final Rect rect;

    public RectEvaluator(Rect rect2) {
        this.rect = rect2;
    }

    public Rect evaluate(float f, Rect rect2, Rect rect3) {
        this.rect.set(rect2.left + ((int) (((float) (rect3.left - rect2.left)) * f)), rect2.top + ((int) (((float) (rect3.top - rect2.top)) * f)), rect2.right + ((int) (((float) (rect3.right - rect2.right)) * f)), rect2.bottom + ((int) (((float) (rect3.bottom - rect2.bottom)) * f)));
        return this.rect;
    }
}
