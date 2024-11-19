package com.google.android.material.sidesheet;

final class SheetUtils {
    private SheetUtils() {
    }

    static boolean isSwipeMostlyHorizontal(float f, float f2) {
        return Math.abs(f) > Math.abs(f2);
    }
}
