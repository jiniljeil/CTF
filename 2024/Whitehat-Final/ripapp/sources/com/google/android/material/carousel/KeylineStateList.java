package com.google.android.material.carousel;

import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KeylineStateList {
    private static final int NO_INDEX = -1;
    private final KeylineState defaultState;
    private final float endShiftRange;
    private final List<KeylineState> endStateSteps;
    private final float[] endStateStepsInterpolationPoints;
    private final float startShiftRange;
    private final List<KeylineState> startStateSteps;
    private final float[] startStateStepsInterpolationPoints;

    private KeylineStateList(KeylineState keylineState, List<KeylineState> list, List<KeylineState> list2) {
        this.defaultState = keylineState;
        this.startStateSteps = Collections.unmodifiableList(list);
        this.endStateSteps = Collections.unmodifiableList(list2);
        float f = list.get(list.size() - 1).getFirstKeyline().loc - keylineState.getFirstKeyline().loc;
        this.startShiftRange = f;
        float f2 = keylineState.getLastKeyline().loc - list2.get(list2.size() - 1).getLastKeyline().loc;
        this.endShiftRange = f2;
        this.startStateStepsInterpolationPoints = getStateStepInterpolationPoints(f, list, true);
        this.endStateStepsInterpolationPoints = getStateStepInterpolationPoints(f2, list2, false);
    }

    static KeylineStateList from(Carousel carousel, KeylineState keylineState, float f, float f2, float f3) {
        return new KeylineStateList(keylineState, getStateStepsStart(carousel, keylineState, f, f2), getStateStepsEnd(carousel, keylineState, f, f3));
    }

    /* access modifiers changed from: package-private */
    public KeylineState getDefaultState() {
        return this.defaultState;
    }

    /* access modifiers changed from: package-private */
    public KeylineState getStartState() {
        List<KeylineState> list = this.startStateSteps;
        return list.get(list.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public KeylineState getEndState() {
        List<KeylineState> list = this.endStateSteps;
        return list.get(list.size() - 1);
    }

    public KeylineState getShiftedState(float f, float f2, float f3) {
        return getShiftedState(f, f2, f3, false);
    }

    /* access modifiers changed from: package-private */
    public KeylineState getShiftedState(float f, float f2, float f3, boolean z) {
        float[] fArr;
        List<KeylineState> list;
        float f4;
        float f5 = this.startShiftRange + f2;
        float f6 = f3 - this.endShiftRange;
        float f7 = getStartState().getFirstFocalKeyline().leftOrTopPaddingShift;
        float f8 = getEndState().getLastFocalKeyline().rightOrBottomPaddingShift;
        if (this.startShiftRange == f7) {
            f5 += f7;
        }
        if (this.endShiftRange == f8) {
            f6 -= f8;
        }
        if (f < f5) {
            f4 = AnimationUtils.lerp(1.0f, 0.0f, f2, f5, f);
            list = this.startStateSteps;
            fArr = this.startStateStepsInterpolationPoints;
        } else if (f <= f6) {
            return this.defaultState;
        } else {
            f4 = AnimationUtils.lerp(0.0f, 1.0f, f6, f3, f);
            list = this.endStateSteps;
            fArr = this.endStateStepsInterpolationPoints;
        }
        if (z) {
            return closestStateStepFromInterpolation(list, f4, fArr);
        }
        return lerp(list, f4, fArr);
    }

    private static KeylineState lerp(List<KeylineState> list, float f, float[] fArr) {
        float[] stateStepsRange = getStateStepsRange(list, f, fArr);
        return KeylineState.lerp(list.get((int) stateStepsRange[1]), list.get((int) stateStepsRange[2]), stateStepsRange[0]);
    }

    private static float[] getStateStepsRange(List<KeylineState> list, float f, float[] fArr) {
        int size = list.size();
        float f2 = fArr[0];
        int i = 1;
        while (i < size) {
            float f3 = fArr[i];
            if (f <= f3) {
                return new float[]{AnimationUtils.lerp(0.0f, 1.0f, f2, f3, f), (float) (i - 1), (float) i};
            }
            i++;
            f2 = f3;
        }
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private KeylineState closestStateStepFromInterpolation(List<KeylineState> list, float f, float[] fArr) {
        float[] stateStepsRange = getStateStepsRange(list, f, fArr);
        if (stateStepsRange[0] >= 0.5f) {
            return list.get((int) stateStepsRange[2]);
        }
        return list.get((int) stateStepsRange[1]);
    }

    private static float[] getStateStepInterpolationPoints(float f, List<KeylineState> list, boolean z) {
        float f2;
        float f3;
        int size = list.size();
        float[] fArr = new float[size];
        for (int i = 1; i < size; i++) {
            int i2 = i - 1;
            KeylineState keylineState = list.get(i2);
            KeylineState keylineState2 = list.get(i);
            if (z) {
                f2 = keylineState2.getFirstKeyline().loc - keylineState.getFirstKeyline().loc;
            } else {
                f2 = keylineState.getLastKeyline().loc - keylineState2.getLastKeyline().loc;
            }
            float f4 = f2 / f;
            if (i == size - 1) {
                f3 = 1.0f;
            } else {
                f3 = fArr[i2] + f4;
            }
            fArr[i] = f3;
        }
        return fArr;
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState keylineState) {
        return keylineState.getFirstFocalKeyline().locOffset - (keylineState.getFirstFocalKeyline().maskedItemSize / 2.0f) >= 0.0f && keylineState.getFirstFocalKeyline() == keylineState.getFirstNonAnchorKeyline();
    }

    private static boolean isLastFocalItemVisibleAtRightOfContainer(Carousel carousel, KeylineState keylineState) {
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        return keylineState.getLastFocalKeyline().locOffset + (keylineState.getLastFocalKeyline().maskedItemSize / 2.0f) <= ((float) containerHeight) && keylineState.getLastFocalKeyline() == keylineState.getLastNonAnchorKeyline();
    }

    private static KeylineState shiftKeylineStateForPadding(KeylineState keylineState, float f, float f2, boolean z, float f3) {
        ArrayList arrayList = new ArrayList(keylineState.getKeylines());
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), f2);
        float numberOfNonAnchorKeylines = f / ((float) keylineState.getNumberOfNonAnchorKeylines());
        float f4 = z ? f : 0.0f;
        int i = 0;
        while (i < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i);
            if (keyline.isAnchor) {
                builder.addKeyline(keyline.locOffset, keyline.mask, keyline.maskedItemSize, false, true, keyline.cutoff);
            } else {
                boolean z2 = i >= keylineState.getFirstFocalKeylineIndex() && i <= keylineState.getLastFocalKeylineIndex();
                float f5 = keyline.maskedItemSize - numberOfNonAnchorKeylines;
                float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(f5, keylineState.getItemSize(), f3);
                float f6 = (f5 / 2.0f) + f4;
                float f7 = f6 - keyline.locOffset;
                builder.addKeyline(f6, childMaskPercentage, f5, z2, false, keyline.cutoff, z ? f7 : 0.0f, z ? 0.0f : f7);
                f4 += f5;
            }
            i++;
        }
        return builder.build();
    }

    private static List<KeylineState> getStateStepsStart(Carousel carousel, KeylineState keylineState, float f, float f2) {
        KeylineState keylineState2 = keylineState;
        float f3 = f;
        float f4 = f2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState2);
        int findFirstNonAnchorKeylineIndex = findFirstNonAnchorKeylineIndex(keylineState);
        float containerWidth = (float) (carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight());
        int i = 1;
        if (isFirstFocalItemAtLeftOfContainer(keylineState) || findFirstNonAnchorKeylineIndex == -1) {
            if (f4 > 0.0f) {
                arrayList.add(shiftKeylineStateForPadding(keylineState2, f4, containerWidth, true, f3));
            }
            return arrayList;
        }
        int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - findFirstNonAnchorKeylineIndex;
        float f5 = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
        if (firstFocalKeylineIndex > 0 || keylineState.getFirstFocalKeyline().cutoff <= 0.0f) {
            int i2 = 0;
            float f6 = 0.0f;
            while (i2 < firstFocalKeylineIndex) {
                KeylineState keylineState3 = (KeylineState) arrayList.get(arrayList.size() - i);
                int i3 = findFirstNonAnchorKeylineIndex + i2;
                int size = keylineState.getKeylines().size() - i;
                float f7 = f6 + keylineState.getKeylines().get(i3).cutoff;
                int i4 = i3 - i;
                int findFirstIndexAfterLastFocalKeylineWithMask = i4 >= 0 ? findFirstIndexAfterLastFocalKeylineWithMask(keylineState3, keylineState.getKeylines().get(i4).mask) - i : size;
                int i5 = i2;
                KeylineState moveKeylineAndCreateKeylineState = moveKeylineAndCreateKeylineState(keylineState3, findFirstNonAnchorKeylineIndex, findFirstIndexAfterLastFocalKeylineWithMask, f5 + f7, (keylineState.getFirstFocalKeylineIndex() - i2) - 1, (keylineState.getLastFocalKeylineIndex() - i2) - 1, containerWidth);
                if (i5 == firstFocalKeylineIndex - 1 && f4 > 0.0f) {
                    moveKeylineAndCreateKeylineState = shiftKeylineStateForPadding(moveKeylineAndCreateKeylineState, f4, containerWidth, true, f3);
                }
                arrayList.add(moveKeylineAndCreateKeylineState);
                i2 = i5 + 1;
                f6 = f7;
                i = 1;
            }
            return arrayList;
        }
        arrayList.add(shiftKeylinesAndCreateKeylineState(keylineState2, f5 + keylineState.getFirstFocalKeyline().cutoff, containerWidth));
        return arrayList;
    }

    private static List<KeylineState> getStateStepsEnd(Carousel carousel, KeylineState keylineState, float f, float f2) {
        KeylineState keylineState2 = keylineState;
        float f3 = f;
        float f4 = f2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(keylineState2);
        int findLastNonAnchorKeylineIndex = findLastNonAnchorKeylineIndex(keylineState);
        float containerWidth = (float) (carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight());
        if (isLastFocalItemVisibleAtRightOfContainer(carousel, keylineState) || findLastNonAnchorKeylineIndex == -1) {
            if (f4 > 0.0f) {
                arrayList.add(shiftKeylineStateForPadding(keylineState2, f4, containerWidth, false, f3));
            }
            return arrayList;
        }
        int lastFocalKeylineIndex = findLastNonAnchorKeylineIndex - keylineState.getLastFocalKeylineIndex();
        float f5 = keylineState.getFirstKeyline().locOffset - (keylineState.getFirstKeyline().maskedItemSize / 2.0f);
        if (lastFocalKeylineIndex > 0 || keylineState.getLastFocalKeyline().cutoff <= 0.0f) {
            float f6 = 0.0f;
            int i = 0;
            while (i < lastFocalKeylineIndex) {
                KeylineState keylineState3 = (KeylineState) arrayList.get(arrayList.size() - 1);
                int i2 = findLastNonAnchorKeylineIndex - i;
                float f7 = f6 + keylineState.getKeylines().get(i2).cutoff;
                int i3 = i2 + 1;
                int i4 = i;
                KeylineState moveKeylineAndCreateKeylineState = moveKeylineAndCreateKeylineState(keylineState3, findLastNonAnchorKeylineIndex, i3 < keylineState.getKeylines().size() ? findLastIndexBeforeFirstFocalKeylineWithMask(keylineState3, keylineState.getKeylines().get(i3).mask) + 1 : 0, f5 - f7, keylineState.getFirstFocalKeylineIndex() + i + 1, keylineState.getLastFocalKeylineIndex() + i + 1, containerWidth);
                if (i4 == lastFocalKeylineIndex - 1 && f4 > 0.0f) {
                    moveKeylineAndCreateKeylineState = shiftKeylineStateForPadding(moveKeylineAndCreateKeylineState, f4, containerWidth, false, f3);
                }
                arrayList.add(moveKeylineAndCreateKeylineState);
                i = i4 + 1;
                f6 = f7;
            }
            return arrayList;
        }
        arrayList.add(shiftKeylinesAndCreateKeylineState(keylineState2, f5 - keylineState.getLastFocalKeyline().cutoff, containerWidth));
        return arrayList;
    }

    private static KeylineState shiftKeylinesAndCreateKeylineState(KeylineState keylineState, float f, float f2) {
        return moveKeylineAndCreateKeylineState(keylineState, 0, 0, f, keylineState.getFirstFocalKeylineIndex(), keylineState.getLastFocalKeylineIndex(), f2);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState keylineState, int i, int i2, float f, int i3, int i4, float f2) {
        ArrayList arrayList = new ArrayList(keylineState.getKeylines());
        arrayList.add(i2, (KeylineState.Keyline) arrayList.remove(i));
        KeylineState.Builder builder = new KeylineState.Builder(keylineState.getItemSize(), f2);
        int i5 = 0;
        while (i5 < arrayList.size()) {
            KeylineState.Keyline keyline = (KeylineState.Keyline) arrayList.get(i5);
            builder.addKeyline(f + (keyline.maskedItemSize / 2.0f), keyline.mask, keyline.maskedItemSize, i5 >= i3 && i5 <= i4, keyline.isAnchor, keyline.cutoff);
            f += keyline.maskedItemSize;
            i5++;
        }
        return builder.build();
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState keylineState, float f) {
        for (int lastFocalKeylineIndex = keylineState.getLastFocalKeylineIndex(); lastFocalKeylineIndex < keylineState.getKeylines().size(); lastFocalKeylineIndex++) {
            if (f == keylineState.getKeylines().get(lastFocalKeylineIndex).mask) {
                return lastFocalKeylineIndex;
            }
        }
        return keylineState.getKeylines().size() - 1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState keylineState, float f) {
        for (int firstFocalKeylineIndex = keylineState.getFirstFocalKeylineIndex() - 1; firstFocalKeylineIndex >= 0; firstFocalKeylineIndex--) {
            if (f == keylineState.getKeylines().get(firstFocalKeylineIndex).mask) {
                return firstFocalKeylineIndex;
            }
        }
        return 0;
    }

    private static int findFirstNonAnchorKeylineIndex(KeylineState keylineState) {
        for (int i = 0; i < keylineState.getKeylines().size(); i++) {
            if (!keylineState.getKeylines().get(i).isAnchor) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastNonAnchorKeylineIndex(KeylineState keylineState) {
        for (int size = keylineState.getKeylines().size() - 1; size >= 0; size--) {
            if (!keylineState.getKeylines().get(size).isAnchor) {
                return size;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public Map<Integer, KeylineState> getKeylineStateForPositionMap(int i, int i2, int i3, boolean z) {
        float itemSize = this.defaultState.getItemSize();
        HashMap hashMap = new HashMap();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = -1;
            if (i4 >= i) {
                break;
            }
            int i7 = z ? (i - i4) - 1 : i4;
            float f = ((float) i7) * itemSize;
            if (!z) {
                i6 = 1;
            }
            if (f * ((float) i6) > ((float) i3) - this.endShiftRange || i4 >= i - this.endStateSteps.size()) {
                Integer valueOf = Integer.valueOf(i7);
                List<KeylineState> list = this.endStateSteps;
                hashMap.put(valueOf, list.get(MathUtils.clamp(i5, 0, list.size() - 1)));
                i5++;
            }
            i4++;
        }
        int i8 = 0;
        for (int i9 = i - 1; i9 >= 0; i9--) {
            int i10 = z ? (i - i9) - 1 : i9;
            if (((float) i10) * itemSize * ((float) (z ? -1 : 1)) < ((float) i2) + this.startShiftRange || i9 < this.startStateSteps.size()) {
                Integer valueOf2 = Integer.valueOf(i10);
                List<KeylineState> list2 = this.startStateSteps;
                hashMap.put(valueOf2, list2.get(MathUtils.clamp(i8, 0, list2.size() - 1)));
                i8++;
            }
        }
        return hashMap;
    }
}
