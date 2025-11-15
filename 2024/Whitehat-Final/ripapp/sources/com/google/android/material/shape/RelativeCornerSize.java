package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

public final class RelativeCornerSize implements CornerSize {
    private final float percent;

    public static RelativeCornerSize createFromCornerSize(RectF rectF, CornerSize cornerSize) {
        if (cornerSize instanceof RelativeCornerSize) {
            return (RelativeCornerSize) cornerSize;
        }
        return new RelativeCornerSize(cornerSize.getCornerSize(rectF) / getMaxCornerSize(rectF));
    }

    private static float getMaxCornerSize(RectF rectF) {
        return Math.min(rectF.width(), rectF.height());
    }

    public RelativeCornerSize(float f) {
        this.percent = f;
    }

    public float getRelativePercent() {
        return this.percent;
    }

    public float getCornerSize(RectF rectF) {
        return this.percent * getMaxCornerSize(rectF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RelativeCornerSize)) {
            return false;
        }
        if (this.percent == ((RelativeCornerSize) obj).percent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }
}
