package com.google.android.material.color.utilities;

import java.util.Map;

public final class QuantizerResult {
    public final Map<Integer, Integer> colorToCount;

    QuantizerResult(Map<Integer, Integer> map) {
        this.colorToCount = map;
    }
}
