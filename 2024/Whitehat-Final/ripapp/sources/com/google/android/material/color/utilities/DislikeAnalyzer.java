package com.google.android.material.color.utilities;

public final class DislikeAnalyzer {
    private DislikeAnalyzer() {
        throw new UnsupportedOperationException();
    }

    public static boolean isDisliked(Hct hct) {
        boolean z = ((double) Math.round(hct.getHue())) >= 90.0d && ((double) Math.round(hct.getHue())) <= 111.0d;
        boolean z2 = ((double) Math.round(hct.getChroma())) > 16.0d;
        boolean z3 = ((double) Math.round(hct.getTone())) < 65.0d;
        if (!z || !z2 || !z3) {
            return false;
        }
        return true;
    }

    public static Hct fixIfDisliked(Hct hct) {
        return isDisliked(hct) ? Hct.from(hct.getHue(), hct.getChroma(), 70.0d) : hct;
    }
}
