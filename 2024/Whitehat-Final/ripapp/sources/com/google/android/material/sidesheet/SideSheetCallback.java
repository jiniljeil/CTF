package com.google.android.material.sidesheet;

import android.view.View;

public abstract class SideSheetCallback implements SheetCallback {
    /* access modifiers changed from: package-private */
    public void onLayout(View view) {
    }

    public abstract void onSlide(View view, float f);

    public abstract void onStateChanged(View view, int i);
}
