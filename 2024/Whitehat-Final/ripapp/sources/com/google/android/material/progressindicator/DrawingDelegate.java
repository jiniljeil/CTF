package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    S spec;

    /* access modifiers changed from: package-private */
    public abstract void adjustCanvas(Canvas canvas, Rect rect, float f, boolean z, boolean z2);

    /* access modifiers changed from: package-private */
    public abstract void drawStopIndicator(Canvas canvas, Paint paint, int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void fillIndicator(Canvas canvas, Paint paint, ActiveIndicator activeIndicator, int i);

    /* access modifiers changed from: package-private */
    public abstract void fillTrack(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public abstract int getPreferredHeight();

    /* access modifiers changed from: package-private */
    public abstract int getPreferredWidth();

    public DrawingDelegate(S s) {
        this.spec = s;
    }

    /* access modifiers changed from: package-private */
    public void validateSpecAndAdjustCanvas(Canvas canvas, Rect rect, float f, boolean z, boolean z2) {
        this.spec.validateSpec();
        adjustCanvas(canvas, rect, f, z, z2);
    }

    protected static class ActiveIndicator {
        int color;
        float endFraction;
        int gapSize;
        float startFraction;

        protected ActiveIndicator() {
        }
    }
}
