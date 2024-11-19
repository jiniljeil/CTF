package com.google.android.material.color.utilities;

import androidx.core.view.ViewCompat;
import java.util.HashMap;
import java.util.function.Function;

public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final ContrastCurve contrastCurve;
    private final HashMap<DynamicScheme, Hct> hctCache = new HashMap<>();
    public final boolean isBackground;
    public final String name;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, TonalPalette> palette;
    public final Function<DynamicScheme, DynamicColor> secondBackground;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaPair> toneDeltaPair;

    static /* synthetic */ TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette, DynamicScheme dynamicScheme) {
        return tonalPalette;
    }

    public DynamicColor(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, DynamicColor> function4, ContrastCurve contrastCurve2, Function<DynamicScheme, ToneDeltaPair> function5) {
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve2;
        this.toneDeltaPair = function5;
        this.opacity = null;
    }

    public DynamicColor(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, DynamicColor> function4, ContrastCurve contrastCurve2, Function<DynamicScheme, ToneDeltaPair> function5, Function<DynamicScheme, Double> function6) {
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve2;
        this.toneDeltaPair = function5;
        this.opacity = function6;
    }

    public static DynamicColor fromPalette(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2) {
        return new DynamicColor(str, function, function2, false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public static DynamicColor fromPalette(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z) {
        return new DynamicColor(str, function, function2, z, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public static DynamicColor fromArgb(String str, int i) {
        return fromPalette(str, new DynamicColor$$ExternalSyntheticLambda0(TonalPalette.fromInt(i)), new DynamicColor$$ExternalSyntheticLambda1(Hct.fromInt(i)));
    }

    public int getArgb(DynamicScheme dynamicScheme) {
        int i = getHct(dynamicScheme).toInt();
        Function<DynamicScheme, Double> function = this.opacity;
        if (function == null) {
            return i;
        }
        return (MathUtils.clampInt(0, 255, (int) Math.round(function.apply(dynamicScheme).doubleValue() * 255.0d)) << 24) | (i & ViewCompat.MEASURED_SIZE_MASK);
    }

    public Hct getHct(DynamicScheme dynamicScheme) {
        Hct hct = this.hctCache.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct hct2 = this.palette.apply(dynamicScheme).getHct(getTone(dynamicScheme));
        if (this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme, hct2);
        return hct2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0155  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double getTone(com.google.android.material.color.utilities.DynamicScheme r31) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            double r2 = r1.contrastLevel
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x000e
            r2 = 1
            goto L_0x000f
        L_0x000e:
            r2 = 0
        L_0x000f:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.ToneDeltaPair> r7 = r0.toneDeltaPair
            if (r7 == 0) goto L_0x0158
            java.lang.Object r7 = r7.apply(r1)
            com.google.android.material.color.utilities.ToneDeltaPair r7 = (com.google.android.material.color.utilities.ToneDeltaPair) r7
            com.google.android.material.color.utilities.DynamicColor r16 = r7.getRoleA()
            com.google.android.material.color.utilities.DynamicColor r17 = r7.getRoleB()
            double r18 = r7.getDelta()
            com.google.android.material.color.utilities.TonePolarity r6 = r7.getPolarity()
            boolean r7 = r7.getStayTogether()
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r3 = r0.background
            java.lang.Object r3 = r3.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r3 = (com.google.android.material.color.utilities.DynamicColor) r3
            double r8 = r3.getTone(r1)
            com.google.android.material.color.utilities.TonePolarity r3 = com.google.android.material.color.utilities.TonePolarity.NEARER
            if (r6 == r3) goto L_0x0050
            com.google.android.material.color.utilities.TonePolarity r3 = com.google.android.material.color.utilities.TonePolarity.LIGHTER
            if (r6 != r3) goto L_0x0045
            boolean r3 = r1.isDark
            if (r3 == 0) goto L_0x0050
        L_0x0045:
            com.google.android.material.color.utilities.TonePolarity r3 = com.google.android.material.color.utilities.TonePolarity.DARKER
            if (r6 != r3) goto L_0x004e
            boolean r3 = r1.isDark
            if (r3 == 0) goto L_0x004e
            goto L_0x0050
        L_0x004e:
            r3 = 0
            goto L_0x0051
        L_0x0050:
            r3 = 1
        L_0x0051:
            if (r3 == 0) goto L_0x0056
            r6 = r16
            goto L_0x0058
        L_0x0056:
            r6 = r17
        L_0x0058:
            if (r3 == 0) goto L_0x005d
            r3 = r17
            goto L_0x005f
        L_0x005d:
            r3 = r16
        L_0x005f:
            java.lang.String r12 = r0.name
            java.lang.String r13 = r6.name
            boolean r12 = r12.equals(r13)
            boolean r13 = r1.isDark
            if (r13 == 0) goto L_0x006e
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0070
        L_0x006e:
            r20 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0070:
            com.google.android.material.color.utilities.ContrastCurve r13 = r6.contrastCurve
            double r4 = r1.contrastLevel
            double r4 = r13.getContrast(r4)
            com.google.android.material.color.utilities.ContrastCurve r13 = r3.contrastCurve
            double r14 = r1.contrastLevel
            double r13 = r13.getContrast(r14)
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r6 = r6.tone
            java.lang.Object r6 = r6.apply(r1)
            java.lang.Double r6 = (java.lang.Double) r6
            double r10 = r6.doubleValue()
            double r22 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r8, r10)
            int r6 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0095
            goto L_0x0099
        L_0x0095:
            double r10 = foregroundTone(r8, r4)
        L_0x0099:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r3 = r3.tone
            java.lang.Object r1 = r3.apply(r1)
            java.lang.Double r1 = (java.lang.Double) r1
            r22 = r10
            double r10 = r1.doubleValue()
            double r24 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r8, r10)
            int r1 = (r24 > r13 ? 1 : (r24 == r13 ? 0 : -1))
            if (r1 < 0) goto L_0x00b0
            goto L_0x00b4
        L_0x00b0:
            double r10 = foregroundTone(r8, r13)
        L_0x00b4:
            if (r2 == 0) goto L_0x00c1
            double r10 = foregroundTone(r8, r4)
            double r1 = foregroundTone(r8, r13)
            r22 = r10
            r10 = r1
        L_0x00c1:
            double r1 = r10 - r22
            double r1 = r1 * r20
            int r1 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1))
            if (r1 >= 0) goto L_0x00e7
            double r1 = r18 * r20
            double r28 = r22 + r1
            r24 = 0
            r26 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r10 = com.google.android.material.color.utilities.MathUtils.clampDouble(r24, r26, r28)
            double r3 = r10 - r22
            double r3 = r3 * r20
            int r3 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r3 >= 0) goto L_0x00e7
            r26 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r28 = r10 - r1
            r24 = 0
            double r22 = com.google.android.material.color.utilities.MathUtils.clampDouble(r24, r26, r28)
        L_0x00e7:
            r1 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r3 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            r1 = 4633641066610819072(0x404e000000000000, double:60.0)
            if (r3 > 0) goto L_0x0118
            int r3 = (r22 > r1 ? 1 : (r22 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x0118
            r3 = 0
            int r3 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0105
            double r18 = r18 * r20
            double r3 = r18 + r1
            double r3 = java.lang.Math.max(r10, r3)
            r22 = r1
            r1 = r3
            goto L_0x0153
        L_0x0105:
            double r18 = r18 * r20
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r3 = r18 + r1
            double r1 = java.lang.Math.min(r10, r3)
        L_0x0112:
            r22 = 4632092954238910464(0x4048800000000000, double:49.0)
            goto L_0x0153
        L_0x0118:
            r3 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 > 0) goto L_0x0152
            int r3 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x0152
            if (r7 == 0) goto L_0x0143
            r3 = 0
            int r3 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0135
            double r18 = r18 * r20
            double r3 = r18 + r1
            double r1 = java.lang.Math.max(r10, r3)
            r22 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x0153
        L_0x0135:
            double r18 = r18 * r20
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r3 = r18 + r1
            double r1 = java.lang.Math.min(r10, r3)
            goto L_0x0112
        L_0x0143:
            r3 = 0
            int r1 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x014c
            r1 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x0153
        L_0x014c:
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            goto L_0x0153
        L_0x0152:
            r1 = r10
        L_0x0153:
            if (r12 == 0) goto L_0x0157
            r1 = r22
        L_0x0157:
            return r1
        L_0x0158:
            r3 = r4
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r5 = r0.tone
            java.lang.Object r5 = r5.apply(r1)
            java.lang.Double r5 = (java.lang.Double) r5
            double r5 = r5.doubleValue()
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r7 = r0.background
            if (r7 != 0) goto L_0x016a
            return r5
        L_0x016a:
            java.lang.Object r7 = r7.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r7 = (com.google.android.material.color.utilities.DynamicColor) r7
            double r7 = r7.getTone(r1)
            com.google.android.material.color.utilities.ContrastCurve r9 = r0.contrastCurve
            double r10 = r1.contrastLevel
            double r9 = r9.getContrast(r10)
            double r11 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r7, r5)
            int r11 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x0185
            goto L_0x0189
        L_0x0185:
            double r5 = foregroundTone(r7, r9)
        L_0x0189:
            if (r2 == 0) goto L_0x018f
            double r5 = foregroundTone(r7, r9)
        L_0x018f:
            boolean r2 = r0.isBackground
            if (r2 == 0) goto L_0x01b0
            r11 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r2 > 0) goto L_0x01b0
            r11 = 4633641066610819072(0x404e000000000000, double:60.0)
            int r2 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x01b0
            r13 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r5 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r13, r7)
            int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x01ae
            r12 = r13
            goto L_0x01b1
        L_0x01ae:
            r12 = r11
            goto L_0x01b1
        L_0x01b0:
            r12 = r5
        L_0x01b1:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.secondBackground
            if (r2 == 0) goto L_0x0236
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.background
            java.lang.Object r2 = r2.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r2 = (com.google.android.material.color.utilities.DynamicColor) r2
            double r5 = r2.getTone(r1)
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.secondBackground
            java.lang.Object r2 = r2.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r2 = (com.google.android.material.color.utilities.DynamicColor) r2
            double r1 = r2.getTone(r1)
            double r7 = java.lang.Math.max(r5, r1)
            double r14 = java.lang.Math.min(r5, r1)
            double r16 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r7, r12)
            int r11 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x01e6
            double r16 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r14, r12)
            int r11 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x01e6
            return r12
        L_0x01e6:
            double r7 = com.google.android.material.color.utilities.Contrast.lighter(r7, r9)
            double r9 = com.google.android.material.color.utilities.Contrast.darker(r14, r9)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r14 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x0200
            java.lang.Double r15 = java.lang.Double.valueOf(r7)
            r11.add(r15)
        L_0x0200:
            int r12 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r12 == 0) goto L_0x020b
            java.lang.Double r13 = java.lang.Double.valueOf(r9)
            r11.add(r13)
        L_0x020b:
            boolean r5 = tonePrefersLightForeground(r5)
            if (r5 != 0) goto L_0x0231
            boolean r1 = tonePrefersLightForeground(r1)
            if (r1 == 0) goto L_0x0218
            goto L_0x0231
        L_0x0218:
            int r1 = r11.size()
            r2 = 1
            if (r1 != r2) goto L_0x022b
            r1 = 0
            java.lang.Object r1 = r11.get(r1)
            java.lang.Double r1 = (java.lang.Double) r1
            double r1 = r1.doubleValue()
            return r1
        L_0x022b:
            if (r12 != 0) goto L_0x022f
            r4 = r3
            goto L_0x0230
        L_0x022f:
            r4 = r9
        L_0x0230:
            return r4
        L_0x0231:
            if (r14 != 0) goto L_0x0235
            r7 = 4636737291354636288(0x4059000000000000, double:100.0)
        L_0x0235:
            return r7
        L_0x0236:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.DynamicColor.getTone(com.google.android.material.color.utilities.DynamicScheme):double");
    }

    public static double foregroundTone(double d, double d2) {
        double lighterUnsafe = Contrast.lighterUnsafe(d, d2);
        double darkerUnsafe = Contrast.darkerUnsafe(d, d2);
        double ratioOfTones = Contrast.ratioOfTones(lighterUnsafe, d);
        double ratioOfTones2 = Contrast.ratioOfTones(darkerUnsafe, d);
        if (!tonePrefersLightForeground(d)) {
            return (ratioOfTones2 >= d2 || ratioOfTones2 >= ratioOfTones) ? darkerUnsafe : lighterUnsafe;
        }
        return (ratioOfTones >= d2 || ratioOfTones >= ratioOfTones2 || ((Math.abs(ratioOfTones - ratioOfTones2) > 0.1d ? 1 : (Math.abs(ratioOfTones - ratioOfTones2) == 0.1d ? 0 : -1)) < 0 && (ratioOfTones > d2 ? 1 : (ratioOfTones == d2 ? 0 : -1)) < 0 && (ratioOfTones2 > d2 ? 1 : (ratioOfTones2 == d2 ? 0 : -1)) < 0)) ? lighterUnsafe : darkerUnsafe;
    }

    public static double enableLightForeground(double d) {
        if (!tonePrefersLightForeground(d) || toneAllowsLightForeground(d)) {
            return d;
        }
        return 49.0d;
    }

    public static boolean tonePrefersLightForeground(double d) {
        return Math.round(d) < 60;
    }

    public static boolean toneAllowsLightForeground(double d) {
        return Math.round(d) <= 49;
    }
}
