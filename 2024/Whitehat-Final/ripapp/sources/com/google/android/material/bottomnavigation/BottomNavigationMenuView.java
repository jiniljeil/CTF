package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import java.util.List;

public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private boolean itemHorizontalTranslationEnabled;
    private final List<Integer> tempChildWidths = new ArrayList();

    public BottomNavigationMenuView(Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        MenuBuilder menu = getMenu();
        int size = View.MeasureSpec.getSize(i);
        int size2 = menu.getVisibleItems().size();
        int childCount = getChildCount();
        this.tempChildWidths.clear();
        int size3 = View.MeasureSpec.getSize(i2);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size3, BasicMeasure.EXACTLY);
        int i5 = 1;
        if (!isShifting(getLabelVisibilityMode(), size2) || !isItemHorizontalTranslationEnabled()) {
            if (size2 != 0) {
                i5 = size2;
            }
            int min = Math.min(size / i5, this.activeItemMaxWidth);
            int i6 = size - (size2 * min);
            for (int i7 = 0; i7 < childCount; i7++) {
                if (getChildAt(i7).getVisibility() == 8) {
                    i3 = 0;
                } else if (i6 > 0) {
                    i3 = min + 1;
                    i6--;
                } else {
                    i3 = min;
                }
                this.tempChildWidths.add(Integer.valueOf(i3));
            }
        } else {
            View childAt = getChildAt(getSelectedItemPosition());
            int i8 = this.activeItemMinWidth;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), makeMeasureSpec);
                i8 = Math.max(i8, childAt.getMeasuredWidth());
            }
            int i9 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int min2 = Math.min(size - (this.inactiveItemMinWidth * i9), Math.min(i8, this.activeItemMaxWidth));
            int i10 = size - min2;
            if (i9 != 0) {
                i5 = i9;
            }
            int min3 = Math.min(i10 / i5, this.inactiveItemMaxWidth);
            int i11 = i10 - (i9 * min3);
            int i12 = 0;
            while (i12 < childCount) {
                if (getChildAt(i12).getVisibility() != 8) {
                    i4 = i12 == getSelectedItemPosition() ? min2 : min3;
                    if (i11 > 0) {
                        i4++;
                        i11--;
                    }
                } else {
                    i4 = 0;
                }
                this.tempChildWidths.add(Integer.valueOf(i4));
                i12++;
            }
        }
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt2 = getChildAt(i14);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths.get(i14).intValue(), BasicMeasure.EXACTLY), makeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                i13 += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(i13, size3);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    int i9 = i5 - i7;
                    childAt.layout(i9 - childAt.getMeasuredWidth(), 0, i9, i6);
                } else {
                    childAt.layout(i7, 0, childAt.getMeasuredWidth() + i7, i6);
                }
                i7 += childAt.getMeasuredWidth();
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.itemHorizontalTranslationEnabled = z;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    /* access modifiers changed from: protected */
    public NavigationBarItemView createNavigationBarItemView(Context context) {
        return new BottomNavigationItemView(context);
    }
}
