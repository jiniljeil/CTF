package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

public class MaterialDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialDivider;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int color;
    private Drawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private boolean lastItemDecorated;
    private int orientation;
    private final Rect tempRect;
    private int thickness;

    /* access modifiers changed from: protected */
    public boolean shouldDrawDivider(int i, RecyclerView.Adapter<?> adapter) {
        return true;
    }

    public MaterialDividerItemDecoration(Context context, int i) {
        this(context, (AttributeSet) null, i);
    }

    public MaterialDividerItemDecoration(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, R.attr.materialDividerStyle, i);
    }

    public MaterialDividerItemDecoration(Context context, AttributeSet attributeSet, int i, int i2) {
        this.tempRect = new Rect();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.MaterialDivider, i, DEF_STYLE_RES, new int[0]);
        this.color = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.thickness = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.insetStart = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        this.lastItemDecorated = obtainStyledAttributes.getBoolean(R.styleable.MaterialDivider_lastItemDecorated, true);
        obtainStyledAttributes.recycle();
        this.dividerDrawable = new ShapeDrawable();
        setDividerColor(this.color);
        setOrientation(i2);
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            this.orientation = i;
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i + ". It should be either HORIZONTAL or VERTICAL");
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setDividerThickness(int i) {
        this.thickness = i;
    }

    public void setDividerThicknessResource(Context context, int i) {
        setDividerThickness(context.getResources().getDimensionPixelSize(i));
    }

    public int getDividerThickness() {
        return this.thickness;
    }

    public void setDividerColor(int i) {
        this.color = i;
        Drawable wrap = DrawableCompat.wrap(this.dividerDrawable);
        this.dividerDrawable = wrap;
        DrawableCompat.setTint(wrap, i);
    }

    public void setDividerColorResource(Context context, int i) {
        setDividerColor(ContextCompat.getColor(context, i));
    }

    public int getDividerColor() {
        return this.color;
    }

    public void setDividerInsetStart(int i) {
        this.insetStart = i;
    }

    public void setDividerInsetStartResource(Context context, int i) {
        setDividerInsetStart(context.getResources().getDimensionPixelOffset(i));
    }

    public int getDividerInsetStart() {
        return this.insetStart;
    }

    public void setDividerInsetEnd(int i) {
        this.insetEnd = i;
    }

    public void setDividerInsetEndResource(Context context, int i) {
        setDividerInsetEnd(context.getResources().getDimensionPixelOffset(i));
    }

    public int getDividerInsetEnd() {
        return this.insetEnd;
    }

    public void setLastItemDecorated(boolean z) {
        this.lastItemDecorated = z;
    }

    public boolean isLastItemDecorated() {
        return this.lastItemDecorated;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getLayoutManager() != null) {
            if (this.orientation == 1) {
                drawForVerticalOrientation(canvas, recyclerView);
            } else {
                drawForHorizontalOrientation(canvas, recyclerView);
            }
        }
    }

    private void drawForVerticalOrientation(Canvas canvas, RecyclerView recyclerView) {
        int i;
        int i2;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingLeft();
            i = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i2, recyclerView.getPaddingTop(), i, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            i = recyclerView.getWidth();
            i2 = 0;
        }
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(recyclerView);
        int i3 = i2 + (isLayoutRtl ? this.insetEnd : this.insetStart);
        int i4 = i - (isLayoutRtl ? this.insetStart : this.insetEnd);
        int childCount = recyclerView.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = recyclerView.getChildAt(i5);
            if (shouldDrawDivider(recyclerView, childAt)) {
                recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.tempRect);
                int round = this.tempRect.bottom + Math.round(childAt.getTranslationY());
                this.dividerDrawable.setBounds(i3, round - this.thickness, i4, round);
                this.dividerDrawable.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                this.dividerDrawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void drawForHorizontalOrientation(Canvas canvas, RecyclerView recyclerView) {
        int i;
        int i2;
        int i3;
        int i4;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i2 = recyclerView.getPaddingTop();
            i = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i2, recyclerView.getWidth() - recyclerView.getPaddingRight(), i);
        } else {
            i = recyclerView.getHeight();
            i2 = 0;
        }
        int i5 = i2 + this.insetStart;
        int i6 = i - this.insetEnd;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(recyclerView);
        int childCount = recyclerView.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = recyclerView.getChildAt(i7);
            if (shouldDrawDivider(recyclerView, childAt)) {
                recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.tempRect);
                int round = Math.round(childAt.getTranslationX());
                if (isLayoutRtl) {
                    i3 = this.tempRect.left + round;
                    i4 = this.thickness + i3;
                } else {
                    i4 = round + this.tempRect.right;
                    i3 = i4 - this.thickness;
                }
                this.dividerDrawable.setBounds(i3, i5, i4, i6);
                this.dividerDrawable.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                this.dividerDrawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
        if (!shouldDrawDivider(recyclerView, view)) {
            return;
        }
        if (this.orientation == 1) {
            rect.bottom = this.thickness;
        } else if (ViewUtils.isLayoutRtl(recyclerView)) {
            rect.left = this.thickness;
        } else {
            rect.right = this.thickness;
        }
    }

    private boolean shouldDrawDivider(RecyclerView recyclerView, View view) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        boolean z = adapter != null && childAdapterPosition == adapter.getItemCount() - 1;
        if (childAdapterPosition == -1) {
            return false;
        }
        if ((!z || this.lastItemDecorated) && shouldDrawDivider(childAdapterPosition, (RecyclerView.Adapter<?>) adapter)) {
            return true;
        }
        return false;
    }
}
