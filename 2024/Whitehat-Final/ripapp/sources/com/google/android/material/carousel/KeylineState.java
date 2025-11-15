package com.google.android.material.carousel;

import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class KeylineState {
    private final int firstFocalKeylineIndex;
    private final float itemSize;
    private final List<Keyline> keylines;
    private final int lastFocalKeylineIndex;

    private KeylineState(float f, List<Keyline> list, int i, int i2) {
        this.itemSize = f;
        this.keylines = Collections.unmodifiableList(list);
        this.firstFocalKeylineIndex = i;
        this.lastFocalKeylineIndex = i2;
    }

    /* access modifiers changed from: package-private */
    public float getItemSize() {
        return this.itemSize;
    }

    /* access modifiers changed from: package-private */
    public List<Keyline> getKeylines() {
        return this.keylines;
    }

    /* access modifiers changed from: package-private */
    public Keyline getFirstFocalKeyline() {
        return this.keylines.get(this.firstFocalKeylineIndex);
    }

    /* access modifiers changed from: package-private */
    public int getFirstFocalKeylineIndex() {
        return this.firstFocalKeylineIndex;
    }

    /* access modifiers changed from: package-private */
    public Keyline getLastFocalKeyline() {
        return this.keylines.get(this.lastFocalKeylineIndex);
    }

    /* access modifiers changed from: package-private */
    public int getLastFocalKeylineIndex() {
        return this.lastFocalKeylineIndex;
    }

    /* access modifiers changed from: package-private */
    public List<Keyline> getFocalKeylines() {
        return this.keylines.subList(this.firstFocalKeylineIndex, this.lastFocalKeylineIndex + 1);
    }

    /* access modifiers changed from: package-private */
    public Keyline getFirstKeyline() {
        return this.keylines.get(0);
    }

    /* access modifiers changed from: package-private */
    public Keyline getLastKeyline() {
        List<Keyline> list = this.keylines;
        return list.get(list.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public Keyline getFirstNonAnchorKeyline() {
        for (int i = 0; i < this.keylines.size(); i++) {
            Keyline keyline = this.keylines.get(i);
            if (!keyline.isAnchor) {
                return keyline;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Keyline getLastNonAnchorKeyline() {
        for (int size = this.keylines.size() - 1; size >= 0; size--) {
            Keyline keyline = this.keylines.get(size);
            if (!keyline.isAnchor) {
                return keyline;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getNumberOfNonAnchorKeylines() {
        int i = 0;
        for (Keyline keyline : this.keylines) {
            if (keyline.isAnchor) {
                i++;
            }
        }
        return this.keylines.size() - i;
    }

    static KeylineState lerp(KeylineState keylineState, KeylineState keylineState2, float f) {
        if (keylineState.getItemSize() == keylineState2.getItemSize()) {
            List<Keyline> keylines2 = keylineState.getKeylines();
            List<Keyline> keylines3 = keylineState2.getKeylines();
            if (keylines2.size() == keylines3.size()) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < keylineState.getKeylines().size(); i++) {
                    arrayList.add(Keyline.lerp(keylines2.get(i), keylines3.get(i), f));
                }
                return new KeylineState(keylineState.getItemSize(), arrayList, AnimationUtils.lerp(keylineState.getFirstFocalKeylineIndex(), keylineState2.getFirstFocalKeylineIndex(), f), AnimationUtils.lerp(keylineState.getLastFocalKeylineIndex(), keylineState2.getLastFocalKeylineIndex(), f));
            }
            throw new IllegalArgumentException("Keylines being linearly interpolated must have the same number of keylines.");
        }
        throw new IllegalArgumentException("Keylines being linearly interpolated must have the same item size.");
    }

    static KeylineState reverse(KeylineState keylineState, float f) {
        Builder builder = new Builder(keylineState.getItemSize(), f);
        float f2 = (f - keylineState.getLastKeyline().locOffset) - (keylineState.getLastKeyline().maskedItemSize / 2.0f);
        int size = keylineState.getKeylines().size() - 1;
        while (size >= 0) {
            Keyline keyline = keylineState.getKeylines().get(size);
            builder.addKeyline(f2 + (keyline.maskedItemSize / 2.0f), keyline.mask, keyline.maskedItemSize, size >= keylineState.getFirstFocalKeylineIndex() && size <= keylineState.getLastFocalKeylineIndex(), keyline.isAnchor);
            f2 += keyline.maskedItemSize;
            size--;
        }
        return builder.build();
    }

    static final class Builder {
        private static final int NO_INDEX = -1;
        private static final float UNKNOWN_LOC = Float.MIN_VALUE;
        private final float availableSpace;
        private int firstFocalKeylineIndex = -1;
        private final float itemSize;
        private int lastFocalKeylineIndex = -1;
        private float lastKeylineMaskedSize = 0.0f;
        private int latestAnchorKeylineIndex = -1;
        private Keyline tmpFirstFocalKeyline;
        private final List<Keyline> tmpKeylines = new ArrayList();
        private Keyline tmpLastFocalKeyline;

        private static float calculateKeylineLocationForItemPosition(float f, float f2, int i, int i2) {
            return (f - (((float) i) * f2)) + (((float) i2) * f2);
        }

        Builder(float f, float f2) {
            this.itemSize = f;
            this.availableSpace = f2;
        }

        /* access modifiers changed from: package-private */
        public Builder addKeyline(float f, float f2, float f3, boolean z) {
            return addKeyline(f, f2, f3, z, false);
        }

        /* access modifiers changed from: package-private */
        public Builder addKeyline(float f, float f2, float f3) {
            return addKeyline(f, f2, f3, false);
        }

        /* access modifiers changed from: package-private */
        public Builder addKeyline(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5, float f6) {
            if (f3 <= 0.0f) {
                return this;
            }
            if (z2) {
                if (!z) {
                    int i = this.latestAnchorKeylineIndex;
                    if (i == -1 || i == 0) {
                        this.latestAnchorKeylineIndex = this.tmpKeylines.size();
                    } else {
                        throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
                    }
                } else {
                    throw new IllegalArgumentException("Anchor keylines cannot be focal.");
                }
            }
            Keyline keyline = new Keyline(Float.MIN_VALUE, f, f2, f3, z2, f4, f5, f6);
            if (z) {
                if (this.tmpFirstFocalKeyline == null) {
                    this.tmpFirstFocalKeyline = keyline;
                    this.firstFocalKeylineIndex = this.tmpKeylines.size();
                }
                if (this.lastFocalKeylineIndex != -1 && this.tmpKeylines.size() - this.lastFocalKeylineIndex > 1) {
                    throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
                } else if (f3 == this.tmpFirstFocalKeyline.maskedItemSize) {
                    this.tmpLastFocalKeyline = keyline;
                    this.lastFocalKeylineIndex = this.tmpKeylines.size();
                } else {
                    throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
                }
            } else if (this.tmpFirstFocalKeyline == null && keyline.maskedItemSize < this.lastKeylineMaskedSize) {
                throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
            } else if (this.tmpLastFocalKeyline != null && keyline.maskedItemSize > this.lastKeylineMaskedSize) {
                throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
            }
            this.lastKeylineMaskedSize = keyline.maskedItemSize;
            this.tmpKeylines.add(keyline);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addKeyline(float f, float f2, float f3, boolean z, boolean z2, float f4) {
            return addKeyline(f, f2, f3, z, z2, f4, 0.0f, 0.0f);
        }

        /* access modifiers changed from: package-private */
        public Builder addKeyline(float f, float f2, float f3, boolean z, boolean z2) {
            float f4;
            float f5 = f3 / 2.0f;
            float f6 = f - f5;
            float f7 = f5 + f;
            float f8 = this.availableSpace;
            if (f7 > f8) {
                f4 = Math.abs(f7 - Math.max(f7 - f3, f8));
            } else {
                f4 = 0.0f;
                if (f6 < 0.0f) {
                    f4 = Math.abs(f6 - Math.min(f6 + f3, 0.0f));
                }
            }
            return addKeyline(f, f2, f3, z, z2, f4);
        }

        /* access modifiers changed from: package-private */
        public Builder addAnchorKeyline(float f, float f2, float f3) {
            return addKeyline(f, f2, f3, false, true);
        }

        /* access modifiers changed from: package-private */
        public Builder addKeylineRange(float f, float f2, float f3, int i) {
            return addKeylineRange(f, f2, f3, i, false);
        }

        /* access modifiers changed from: package-private */
        public Builder addKeylineRange(float f, float f2, float f3, int i, boolean z) {
            if (i > 0 && f3 > 0.0f) {
                for (int i2 = 0; i2 < i; i2++) {
                    addKeyline((((float) i2) * f3) + f, f2, f3, z);
                }
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public KeylineState build() {
            if (this.tmpFirstFocalKeyline != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < this.tmpKeylines.size(); i++) {
                    Keyline keyline = this.tmpKeylines.get(i);
                    arrayList.add(new Keyline(calculateKeylineLocationForItemPosition(this.tmpFirstFocalKeyline.locOffset, this.itemSize, this.firstFocalKeylineIndex, i), keyline.locOffset, keyline.mask, keyline.maskedItemSize, keyline.isAnchor, keyline.cutoff, keyline.leftOrTopPaddingShift, keyline.rightOrBottomPaddingShift));
                }
                return new KeylineState(this.itemSize, arrayList, this.firstFocalKeylineIndex, this.lastFocalKeylineIndex);
            }
            throw new IllegalStateException("There must be a keyline marked as focal.");
        }
    }

    static final class Keyline {
        final float cutoff;
        final boolean isAnchor;
        final float leftOrTopPaddingShift;
        final float loc;
        final float locOffset;
        final float mask;
        final float maskedItemSize;
        final float rightOrBottomPaddingShift;

        Keyline(float f, float f2, float f3, float f4) {
            this(f, f2, f3, f4, false, 0.0f, 0.0f, 0.0f);
        }

        Keyline(float f, float f2, float f3, float f4, boolean z, float f5, float f6, float f7) {
            this.loc = f;
            this.locOffset = f2;
            this.mask = f3;
            this.maskedItemSize = f4;
            this.isAnchor = z;
            this.cutoff = f5;
            this.leftOrTopPaddingShift = f6;
            this.rightOrBottomPaddingShift = f7;
        }

        static Keyline lerp(Keyline keyline, Keyline keyline2, float f) {
            return new Keyline(AnimationUtils.lerp(keyline.loc, keyline2.loc, f), AnimationUtils.lerp(keyline.locOffset, keyline2.locOffset, f), AnimationUtils.lerp(keyline.mask, keyline2.mask, f), AnimationUtils.lerp(keyline.maskedItemSize, keyline2.maskedItemSize, f));
        }
    }
}
