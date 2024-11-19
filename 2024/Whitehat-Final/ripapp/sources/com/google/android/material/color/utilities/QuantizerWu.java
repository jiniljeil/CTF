package com.google.android.material.color.utilities;

import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class QuantizerWu implements Quantizer {
    private static final int INDEX_BITS = 5;
    private static final int INDEX_COUNT = 33;
    private static final int TOTAL_SIZE = 35937;
    Box[] cubes;
    double[] moments;
    int[] momentsB;
    int[] momentsG;
    int[] momentsR;
    int[] weights;

    private enum Direction {
        RED,
        GREEN,
        BLUE
    }

    static int getIndex(int i, int i2, int i3) {
        return (i << 10) + (i << 6) + i + (i2 << 5) + i2 + i3;
    }

    public QuantizerResult quantize(int[] iArr, int i) {
        constructHistogram(new QuantizerMap().quantize(iArr, i).colorToCount);
        createMoments();
        List<Integer> createResult = createResult(createBoxes(i).resultCount);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Integer intValue : createResult) {
            linkedHashMap.put(Integer.valueOf(intValue.intValue()), 0);
        }
        return new QuantizerResult(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    public void constructHistogram(Map<Integer, Integer> map) {
        this.weights = new int[TOTAL_SIZE];
        this.momentsR = new int[TOTAL_SIZE];
        this.momentsG = new int[TOTAL_SIZE];
        this.momentsB = new int[TOTAL_SIZE];
        this.moments = new double[TOTAL_SIZE];
        for (Map.Entry next : map.entrySet()) {
            int intValue = ((Integer) next.getKey()).intValue();
            int intValue2 = ((Integer) next.getValue()).intValue();
            int redFromArgb = ColorUtils.redFromArgb(intValue);
            int greenFromArgb = ColorUtils.greenFromArgb(intValue);
            int blueFromArgb = ColorUtils.blueFromArgb(intValue);
            int index = getIndex((redFromArgb >> 3) + 1, (greenFromArgb >> 3) + 1, (blueFromArgb >> 3) + 1);
            int[] iArr = this.weights;
            iArr[index] = iArr[index] + intValue2;
            int[] iArr2 = this.momentsR;
            iArr2[index] = iArr2[index] + (redFromArgb * intValue2);
            int[] iArr3 = this.momentsG;
            iArr3[index] = iArr3[index] + (greenFromArgb * intValue2);
            int[] iArr4 = this.momentsB;
            iArr4[index] = iArr4[index] + (blueFromArgb * intValue2);
            double[] dArr = this.moments;
            dArr[index] = dArr[index] + ((double) (intValue2 * ((redFromArgb * redFromArgb) + (greenFromArgb * greenFromArgb) + (blueFromArgb * blueFromArgb))));
        }
    }

    /* access modifiers changed from: package-private */
    public void createMoments() {
        int i = 1;
        while (true) {
            int i2 = 33;
            if (i < 33) {
                int[] iArr = new int[33];
                int[] iArr2 = new int[33];
                int[] iArr3 = new int[33];
                int[] iArr4 = new int[33];
                double[] dArr = new double[33];
                int i3 = 1;
                while (i3 < i2) {
                    int i4 = 0;
                    int i5 = 0;
                    double d = 0.0d;
                    int i6 = 1;
                    int i7 = 0;
                    int i8 = 0;
                    while (i6 < i2) {
                        int index = getIndex(i, i3, i6);
                        int i9 = i4 + this.weights[index];
                        i7 += this.momentsR[index];
                        i8 += this.momentsG[index];
                        i5 += this.momentsB[index];
                        d += this.moments[index];
                        iArr[i6] = iArr[i6] + i9;
                        iArr2[i6] = iArr2[i6] + i7;
                        iArr3[i6] = iArr3[i6] + i8;
                        iArr4[i6] = iArr4[i6] + i5;
                        dArr[i6] = dArr[i6] + d;
                        int index2 = getIndex(i - 1, i3, i6);
                        int i10 = i9;
                        int[] iArr5 = this.weights;
                        iArr5[index] = iArr5[index2] + iArr[i6];
                        int[] iArr6 = this.momentsR;
                        iArr6[index] = iArr6[index2] + iArr2[i6];
                        int[] iArr7 = this.momentsG;
                        iArr7[index] = iArr7[index2] + iArr3[i6];
                        int[] iArr8 = this.momentsB;
                        iArr8[index] = iArr8[index2] + iArr4[i6];
                        double[] dArr2 = this.moments;
                        dArr2[index] = dArr2[index2] + dArr[i6];
                        i6++;
                        i4 = i10;
                        i2 = 33;
                    }
                    i3++;
                    i2 = 33;
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CreateBoxesResult createBoxes(int i) {
        int i2;
        this.cubes = new Box[i];
        for (int i3 = 0; i3 < i; i3++) {
            this.cubes[i3] = new Box((AnonymousClass1) null);
        }
        double[] dArr = new double[i];
        Box box = this.cubes[0];
        box.r1 = 32;
        box.g1 = 32;
        box.b1 = 32;
        int i4 = 0;
        int i5 = 1;
        while (true) {
            if (i5 >= i) {
                i2 = i;
                break;
            }
            Box[] boxArr = this.cubes;
            if (cut(boxArr[i4], boxArr[i5]).booleanValue()) {
                dArr[i4] = this.cubes[i4].vol > 1 ? variance(this.cubes[i4]) : 0.0d;
                dArr[i5] = this.cubes[i5].vol > 1 ? variance(this.cubes[i5]) : 0.0d;
            } else {
                dArr[i4] = 0.0d;
                i5--;
            }
            double d = dArr[0];
            int i6 = 0;
            for (int i7 = 1; i7 <= i5; i7++) {
                double d2 = dArr[i7];
                if (d2 > d) {
                    i6 = i7;
                    d = d2;
                }
            }
            if (d <= 0.0d) {
                i2 = i5 + 1;
                break;
            }
            i5++;
            i4 = i6;
        }
        return new CreateBoxesResult(i, i2);
    }

    /* access modifiers changed from: package-private */
    public List<Integer> createResult(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            Box box = this.cubes[i2];
            int volume = volume(box, this.weights);
            if (volume > 0) {
                int volume2 = volume(box, this.momentsG) / volume;
                arrayList.add(Integer.valueOf(((volume(box, this.momentsB) / volume) & 255) | (((volume(box, this.momentsR) / volume) & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((volume2 & 255) << 8)));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public double variance(Box box) {
        int volume = volume(box, this.momentsR);
        int volume2 = volume(box, this.momentsG);
        int volume3 = volume(box, this.momentsB);
        return (((((((this.moments[getIndex(box.r1, box.g1, box.b1)] - this.moments[getIndex(box.r1, box.g1, box.b0)]) - this.moments[getIndex(box.r1, box.g0, box.b1)]) + this.moments[getIndex(box.r1, box.g0, box.b0)]) - this.moments[getIndex(box.r0, box.g1, box.b1)]) + this.moments[getIndex(box.r0, box.g1, box.b0)]) + this.moments[getIndex(box.r0, box.g0, box.b1)]) - this.moments[getIndex(box.r0, box.g0, box.b0)]) - (((double) (((volume * volume) + (volume2 * volume2)) + (volume3 * volume3))) / ((double) volume(box, this.weights)));
    }

    /* access modifiers changed from: package-private */
    public Boolean cut(Box box, Box box2) {
        Direction direction;
        Box box3 = box;
        Box box4 = box2;
        int volume = volume(box3, this.momentsR);
        int volume2 = volume(box3, this.momentsG);
        int volume3 = volume(box3, this.momentsB);
        int volume4 = volume(box3, this.weights);
        Box box5 = box;
        int i = volume;
        int i2 = volume2;
        int i3 = volume3;
        MaximizeResult maximize = maximize(box5, Direction.RED, box3.r0 + 1, box3.r1, i, i2, i3, volume4);
        MaximizeResult maximizeResult = maximize;
        MaximizeResult maximize2 = maximize(box5, Direction.GREEN, box3.g0 + 1, box3.g1, i, i2, i3, volume4);
        MaximizeResult maximizeResult2 = maximize2;
        MaximizeResult maximize3 = maximize(box5, Direction.BLUE, box3.b0 + 1, box3.b1, i, i2, i3, volume4);
        MaximizeResult maximizeResult3 = maximizeResult;
        double d = maximizeResult3.maximum;
        double d2 = maximizeResult2.maximum;
        double d3 = maximize3.maximum;
        if (d < d2 || d < d3) {
            if (d2 < d || d2 < d3) {
                direction = Direction.BLUE;
            } else {
                direction = Direction.GREEN;
            }
        } else if (maximizeResult3.cutLocation < 0) {
            return false;
        } else {
            direction = Direction.RED;
        }
        box4.r1 = box3.r1;
        box4.g1 = box3.g1;
        box4.b1 = box3.b1;
        int i4 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i4 == 1) {
            box3.r1 = maximizeResult3.cutLocation;
            box4.r0 = box3.r1;
            box4.g0 = box3.g0;
            box4.b0 = box3.b0;
        } else if (i4 == 2) {
            box3.g1 = maximizeResult2.cutLocation;
            box4.r0 = box3.r0;
            box4.g0 = box3.g1;
            box4.b0 = box3.b0;
        } else if (i4 == 3) {
            box3.b1 = maximize3.cutLocation;
            box4.r0 = box3.r0;
            box4.g0 = box3.g0;
            box4.b0 = box3.b1;
        }
        box3.vol = (box3.r1 - box3.r0) * (box3.g1 - box3.g0) * (box3.b1 - box3.b0);
        box4.vol = (box4.r1 - box4.r0) * (box4.g1 - box4.g0) * (box4.b1 - box4.b0);
        return true;
    }

    /* renamed from: com.google.android.material.color.utilities.QuantizerWu$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.android.material.color.utilities.QuantizerWu$Direction[] r0 = com.google.android.material.color.utilities.QuantizerWu.Direction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction = r0
                com.google.android.material.color.utilities.QuantizerWu$Direction r1 = com.google.android.material.color.utilities.QuantizerWu.Direction.RED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.android.material.color.utilities.QuantizerWu$Direction r1 = com.google.android.material.color.utilities.QuantizerWu.Direction.GREEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.android.material.color.utilities.QuantizerWu$Direction r1 = com.google.android.material.color.utilities.QuantizerWu.Direction.BLUE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.QuantizerWu.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public MaximizeResult maximize(Box box, Direction direction, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        QuantizerWu quantizerWu = this;
        Box box2 = box;
        Direction direction2 = direction;
        int bottom = bottom(box2, direction2, quantizerWu.momentsR);
        int bottom2 = bottom(box2, direction2, quantizerWu.momentsG);
        int bottom3 = bottom(box2, direction2, quantizerWu.momentsB);
        int bottom4 = bottom(box2, direction2, quantizerWu.weights);
        int i8 = i2;
        int i9 = -1;
        double d = 0.0d;
        int i10 = i;
        while (i10 < i8) {
            int pVar = top(box2, direction2, i10, quantizerWu.momentsR) + bottom;
            int pVar2 = top(box2, direction2, i10, quantizerWu.momentsG) + bottom2;
            int pVar3 = top(box2, direction2, i10, quantizerWu.momentsB) + bottom3;
            int pVar4 = top(box2, direction2, i10, quantizerWu.weights) + bottom4;
            if (pVar4 == 0) {
                i7 = bottom;
            } else {
                i7 = bottom;
                double d2 = ((double) (((pVar * pVar) + (pVar2 * pVar2)) + (pVar3 * pVar3))) / ((double) pVar4);
                int i11 = i3 - pVar;
                int i12 = i4 - pVar2;
                int i13 = i5 - pVar3;
                int i14 = i6 - pVar4;
                if (i14 != 0) {
                    double d3 = d2 + (((double) (((i11 * i11) + (i12 * i12)) + (i13 * i13))) / ((double) i14));
                    if (d3 > d) {
                        d = d3;
                        i9 = i10;
                    }
                }
            }
            i10++;
            quantizerWu = this;
            box2 = box;
            direction2 = direction;
            bottom = i7;
        }
        return new MaximizeResult(i9, d);
    }

    static int volume(Box box, int[] iArr) {
        return ((((((iArr[getIndex(box.r1, box.g1, box.b1)] - iArr[getIndex(box.r1, box.g1, box.b0)]) - iArr[getIndex(box.r1, box.g0, box.b1)]) + iArr[getIndex(box.r1, box.g0, box.b0)]) - iArr[getIndex(box.r0, box.g1, box.b1)]) + iArr[getIndex(box.r0, box.g1, box.b0)]) + iArr[getIndex(box.r0, box.g0, box.b1)]) - iArr[getIndex(box.r0, box.g0, box.b0)];
    }

    static int bottom(Box box, Direction direction, int[] iArr) {
        int i;
        int i2;
        int i3 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i3 == 1) {
            i = (-iArr[getIndex(box.r0, box.g1, box.b1)]) + iArr[getIndex(box.r0, box.g1, box.b0)] + iArr[getIndex(box.r0, box.g0, box.b1)];
            i2 = iArr[getIndex(box.r0, box.g0, box.b0)];
        } else if (i3 == 2) {
            i = (-iArr[getIndex(box.r1, box.g0, box.b1)]) + iArr[getIndex(box.r1, box.g0, box.b0)] + iArr[getIndex(box.r0, box.g0, box.b1)];
            i2 = iArr[getIndex(box.r0, box.g0, box.b0)];
        } else if (i3 == 3) {
            i = (-iArr[getIndex(box.r1, box.g1, box.b0)]) + iArr[getIndex(box.r1, box.g0, box.b0)] + iArr[getIndex(box.r0, box.g1, box.b0)];
            i2 = iArr[getIndex(box.r0, box.g0, box.b0)];
        } else {
            throw new IllegalArgumentException("unexpected direction " + direction);
        }
        return i - i2;
    }

    static int top(Box box, Direction direction, int i, int[] iArr) {
        int i2;
        int i3;
        int i4 = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$QuantizerWu$Direction[direction.ordinal()];
        if (i4 == 1) {
            i2 = (iArr[getIndex(i, box.g1, box.b1)] - iArr[getIndex(i, box.g1, box.b0)]) - iArr[getIndex(i, box.g0, box.b1)];
            i3 = iArr[getIndex(i, box.g0, box.b0)];
        } else if (i4 == 2) {
            i2 = (iArr[getIndex(box.r1, i, box.b1)] - iArr[getIndex(box.r1, i, box.b0)]) - iArr[getIndex(box.r0, i, box.b1)];
            i3 = iArr[getIndex(box.r0, i, box.b0)];
        } else if (i4 == 3) {
            i2 = (iArr[getIndex(box.r1, box.g1, i)] - iArr[getIndex(box.r1, box.g0, i)]) - iArr[getIndex(box.r0, box.g1, i)];
            i3 = iArr[getIndex(box.r0, box.g0, i)];
        } else {
            throw new IllegalArgumentException("unexpected direction " + direction);
        }
        return i2 + i3;
    }

    private static final class MaximizeResult {
        int cutLocation;
        double maximum;

        MaximizeResult(int i, double d) {
            this.cutLocation = i;
            this.maximum = d;
        }
    }

    private static final class CreateBoxesResult {
        int resultCount;

        CreateBoxesResult(int i, int i2) {
            this.resultCount = i2;
        }
    }

    private static final class Box {
        int b0;
        int b1;
        int g0;
        int g1;
        int r0;
        int r1;
        int vol;

        private Box() {
            this.r0 = 0;
            this.r1 = 0;
            this.g0 = 0;
            this.g1 = 0;
            this.b0 = 0;
            this.b1 = 0;
            this.vol = 0;
        }

        /* synthetic */ Box(AnonymousClass1 r12) {
            this();
        }
    }
}
