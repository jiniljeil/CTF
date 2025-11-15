package com.google.android.material.color.utilities;

public final class PointProviderLab implements PointProvider {
    public double[] fromInt(int i) {
        double[] labFromArgb = ColorUtils.labFromArgb(i);
        return new double[]{labFromArgb[0], labFromArgb[1], labFromArgb[2]};
    }

    public int toInt(double[] dArr) {
        return ColorUtils.argbFromLab(dArr[0], dArr[1], dArr[2]);
    }

    public double distance(double[] dArr, double[] dArr2) {
        double d = dArr[0] - dArr2[0];
        double d2 = dArr[1] - dArr2[1];
        double d3 = dArr[2] - dArr2[2];
        return (d * d) + (d2 * d2) + (d3 * d3);
    }
}
