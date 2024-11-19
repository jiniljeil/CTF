package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

abstract class SheetDelegate {
    /* access modifiers changed from: package-private */
    public abstract int calculateInnerMargin(ViewGroup.MarginLayoutParams marginLayoutParams);

    /* access modifiers changed from: package-private */
    public abstract float calculateSlideOffset(int i);

    /* access modifiers changed from: package-private */
    public abstract int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams marginLayoutParams);

    /* access modifiers changed from: package-private */
    public abstract int getExpandedOffset();

    /* access modifiers changed from: package-private */
    public abstract int getHiddenOffset();

    /* access modifiers changed from: package-private */
    public abstract int getMaxViewPositionHorizontal();

    /* access modifiers changed from: package-private */
    public abstract int getMinViewPositionHorizontal();

    /* access modifiers changed from: package-private */
    public abstract <V extends View> int getOuterEdge(V v);

    /* access modifiers changed from: package-private */
    public abstract int getParentInnerEdge(CoordinatorLayout coordinatorLayout);

    /* access modifiers changed from: package-private */
    public abstract int getSheetEdge();

    /* access modifiers changed from: package-private */
    public abstract boolean isExpandingOutwards(float f);

    /* access modifiers changed from: package-private */
    public abstract boolean isReleasedCloseToInnerEdge(View view);

    /* access modifiers changed from: package-private */
    public abstract boolean isSwipeSignificant(float f, float f2);

    /* access modifiers changed from: package-private */
    public abstract boolean shouldHide(View view, float f);

    /* access modifiers changed from: package-private */
    public abstract void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams marginLayoutParams, int i);

    /* access modifiers changed from: package-private */
    public abstract void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2);

    SheetDelegate() {
    }
}
