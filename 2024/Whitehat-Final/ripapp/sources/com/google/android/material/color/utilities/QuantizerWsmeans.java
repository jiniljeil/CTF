package com.google.android.material.color.utilities;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    private QuantizerWsmeans() {
    }

    private static final class Distance implements Comparable<Distance> {
        double distance = -1.0d;
        int index = -1;

        Distance() {
        }

        public int compareTo(Distance distance2) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(distance2.distance));
        }
    }

    public static Map<Integer, Integer> quantize(int[] iArr, int[] iArr2, int i) {
        int[] iArr3;
        int i2;
        int i3;
        int[] iArr4 = iArr;
        int[] iArr5 = iArr2;
        Random random = new Random(272008);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        double[][] dArr = new double[iArr4.length][];
        int[] iArr6 = new int[iArr4.length];
        PointProviderLab pointProviderLab = new PointProviderLab();
        int i4 = 0;
        for (int i5 : iArr4) {
            Integer num = (Integer) linkedHashMap.get(Integer.valueOf(i5));
            if (num == null) {
                dArr[i4] = pointProviderLab.fromInt(i5);
                iArr6[i4] = i5;
                i4++;
                linkedHashMap.put(Integer.valueOf(i5), 1);
            } else {
                linkedHashMap.put(Integer.valueOf(i5), Integer.valueOf(num.intValue() + 1));
            }
        }
        int[] iArr7 = new int[i4];
        for (int i6 = 0; i6 < i4; i6++) {
            iArr7[i6] = ((Integer) linkedHashMap.get(Integer.valueOf(iArr6[i6]))).intValue();
        }
        int min = Math.min(i, i4);
        if (iArr5.length != 0) {
            min = Math.min(min, iArr5.length);
        }
        double[][] dArr2 = new double[min][];
        int i7 = 0;
        for (int i8 = 0; i8 < iArr5.length; i8++) {
            dArr2[i8] = pointProviderLab.fromInt(iArr5[i8]);
            i7++;
        }
        int i9 = min - i7;
        if (i9 > 0) {
            for (int i10 = 0; i10 < i9; i10++) {
            }
        }
        int[] iArr8 = new int[i4];
        for (int i11 = 0; i11 < i4; i11++) {
            iArr8[i11] = random.nextInt(min);
        }
        int[][] iArr9 = new int[min][];
        for (int i12 = 0; i12 < min; i12++) {
            iArr9[i12] = new int[min];
        }
        Distance[][] distanceArr = new Distance[min][];
        for (int i13 = 0; i13 < min; i13++) {
            distanceArr[i13] = new Distance[min];
            for (int i14 = 0; i14 < min; i14++) {
                distanceArr[i13][i14] = new Distance();
            }
        }
        int[] iArr10 = new int[min];
        int i15 = 0;
        while (true) {
            if (i15 >= 10) {
                iArr3 = iArr10;
                i2 = 0;
                break;
            }
            int i16 = 0;
            while (i16 < min) {
                int i17 = i16 + 1;
                int i18 = i17;
                while (i18 < min) {
                    int[] iArr11 = iArr10;
                    double distance = pointProviderLab.distance(dArr2[i16], dArr2[i18]);
                    distanceArr[i18][i16].distance = distance;
                    distanceArr[i18][i16].index = i16;
                    distanceArr[i16][i18].distance = distance;
                    distanceArr[i16][i18].index = i18;
                    i18++;
                    iArr10 = iArr11;
                }
                int[] iArr12 = iArr10;
                Arrays.sort(distanceArr[i16]);
                for (int i19 = 0; i19 < min; i19++) {
                    iArr9[i16][i19] = distanceArr[i16][i19].index;
                }
                iArr10 = iArr12;
                i16 = i17;
            }
            int[] iArr13 = iArr10;
            int i20 = 0;
            int i21 = 0;
            while (i20 < i4) {
                double[] dArr3 = dArr[i20];
                int i22 = iArr8[i20];
                double distance2 = pointProviderLab.distance(dArr3, dArr2[i22]);
                int[][] iArr14 = iArr9;
                int[] iArr15 = iArr7;
                double d = distance2;
                int i23 = -1;
                int i24 = 0;
                while (i24 < min) {
                    Distance[][] distanceArr2 = distanceArr;
                    int i25 = i4;
                    if (distanceArr[i22][i24].distance < 4.0d * distance2) {
                        double distance3 = pointProviderLab.distance(dArr3, dArr2[i24]);
                        if (distance3 < d) {
                            i23 = i24;
                            d = distance3;
                        }
                    }
                    i24++;
                    i4 = i25;
                    distanceArr = distanceArr2;
                }
                Distance[][] distanceArr3 = distanceArr;
                int i26 = i4;
                if (i23 != -1 && Math.abs(Math.sqrt(d) - Math.sqrt(distance2)) > 3.0d) {
                    i21++;
                    iArr8[i20] = i23;
                }
                i20++;
                iArr9 = iArr14;
                iArr7 = iArr15;
                i4 = i26;
                distanceArr = distanceArr3;
            }
            int[] iArr16 = iArr7;
            int[][] iArr17 = iArr9;
            Distance[][] distanceArr4 = distanceArr;
            int i27 = i4;
            if (i21 == 0 && i15 != 0) {
                i2 = 0;
                iArr3 = iArr13;
                break;
            }
            double[] dArr4 = new double[min];
            double[] dArr5 = new double[min];
            double[] dArr6 = new double[min];
            int[] iArr18 = iArr13;
            char c = 0;
            Arrays.fill(iArr18, 0);
            int i28 = 0;
            while (true) {
                i3 = i27;
                if (i28 >= i3) {
                    break;
                }
                int i29 = iArr8[i28];
                double[] dArr7 = dArr[i28];
                int i30 = iArr16[i28];
                iArr18[i29] = iArr18[i29] + i30;
                double d2 = (double) i30;
                dArr4[i29] = dArr4[i29] + (dArr7[c] * d2);
                dArr5[i29] = dArr5[i29] + (dArr7[1] * d2);
                dArr6[i29] = dArr6[i29] + (dArr7[2] * d2);
                i28++;
                i15 = i15;
                i27 = i3;
                c = 0;
            }
            int i31 = i15;
            for (int i32 = 0; i32 < min; i32++) {
                int i33 = iArr18[i32];
                if (i33 == 0) {
                    dArr2[i32] = new double[]{0.0d, 0.0d, 0.0d};
                } else {
                    double d3 = (double) i33;
                    double d4 = dArr4[i32] / d3;
                    double d5 = dArr5[i32] / d3;
                    double d6 = dArr6[i32] / d3;
                    double[] dArr8 = dArr2[i32];
                    dArr8[0] = d4;
                    dArr8[1] = d5;
                    dArr8[2] = d6;
                }
            }
            iArr9 = iArr17;
            i15 = i31 + 1;
            iArr10 = iArr18;
            i4 = i3;
            iArr7 = iArr16;
            distanceArr = distanceArr4;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (int i34 = i2; i34 < min; i34++) {
            int i35 = iArr3[i34];
            if (i35 != 0) {
                int i36 = pointProviderLab.toInt(dArr2[i34]);
                if (!linkedHashMap2.containsKey(Integer.valueOf(i36))) {
                    linkedHashMap2.put(Integer.valueOf(i36), Integer.valueOf(i35));
                }
            }
        }
        return linkedHashMap2;
    }
}
