package com.google.android.material.sidesheet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R;

public class SideSheetDialog extends SheetDialog<SideSheetCallback> {
    private static final int SIDE_SHEET_DIALOG_DEFAULT_THEME_RES = R.style.Theme_Material3_Light_SideSheetDialog;
    private static final int SIDE_SHEET_DIALOG_THEME_ATTR = R.attr.sideSheetDialogTheme;

    /* access modifiers changed from: package-private */
    public int getStateOnStart() {
        return 3;
    }

    public /* bridge */ /* synthetic */ void cancel() {
        super.cancel();
    }

    public /* bridge */ /* synthetic */ boolean isDismissWithSheetAnimationEnabled() {
        return super.isDismissWithSheetAnimationEnabled();
    }

    public /* bridge */ /* synthetic */ void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public /* bridge */ /* synthetic */ void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public /* bridge */ /* synthetic */ void setCancelable(boolean z) {
        super.setCancelable(z);
    }

    public /* bridge */ /* synthetic */ void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
    }

    public /* bridge */ /* synthetic */ void setContentView(int i) {
        super.setContentView(i);
    }

    public /* bridge */ /* synthetic */ void setContentView(View view) {
        super.setContentView(view);
    }

    public /* bridge */ /* synthetic */ void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
    }

    public /* bridge */ /* synthetic */ void setDismissWithSheetAnimationEnabled(boolean z) {
        super.setDismissWithSheetAnimationEnabled(z);
    }

    public /* bridge */ /* synthetic */ void setSheetEdge(int i) {
        super.setSheetEdge(i);
    }

    public SideSheetDialog(Context context) {
        this(context, 0);
    }

    public SideSheetDialog(Context context, int i) {
        super(context, i, SIDE_SHEET_DIALOG_THEME_ATTR, SIDE_SHEET_DIALOG_DEFAULT_THEME_RES);
    }

    /* access modifiers changed from: package-private */
    public void addSheetCancelOnHideCallback(Sheet<SideSheetCallback> sheet) {
        sheet.addCallback(new SideSheetCallback() {
            public void onSlide(View view, float f) {
            }

            public void onStateChanged(View view, int i) {
                if (i == 5) {
                    SideSheetDialog.this.cancel();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public int getLayoutResId() {
        return R.layout.m3_side_sheet_dialog;
    }

    /* access modifiers changed from: package-private */
    public int getDialogId() {
        return R.id.m3_side_sheet;
    }

    /* access modifiers changed from: package-private */
    public Sheet<SideSheetCallback> getBehaviorFromSheet(FrameLayout frameLayout) {
        return SideSheetBehavior.from(frameLayout);
    }

    public SideSheetBehavior<? extends View> getBehavior() {
        Sheet behavior = super.getBehavior();
        if (behavior instanceof SideSheetBehavior) {
            return (SideSheetBehavior) behavior;
        }
        throw new IllegalStateException("The view is not associated with SideSheetBehavior");
    }
}
