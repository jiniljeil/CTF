package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;

class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    private static final float EPSILON = 0.001f;
    private static final int INITIAL_CAPACITY = 12;
    private static final String VALUE_PLACEHOLDER = "";
    /* access modifiers changed from: private */
    public final int clockHandPadding;
    /* access modifiers changed from: private */
    public final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private final RectF scratch;
    private final Rect scratchLineBounds;
    private final ColorStateList textColor;
    /* access modifiers changed from: private */
    public final SparseArray<TextView> textViewPool;
    /* access modifiers changed from: private */
    public final Rect textViewRect;
    private final AccessibilityDelegateCompat valueAccessibilityDelegate;
    private String[] values;

    public ClockFaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.scratchLineBounds = new Rect();
        this.textViewPool = new SparseArray<>();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockFaceView, i, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = colorStateList;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, this, true);
        ClockHandView clockHandView2 = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.clockHandView = clockHandView2;
        this.clockHandPadding = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = colorStateList.getColorForState(new int[]{16842913}, colorStateList.getDefaultColor());
        this.gradientColors = new int[]{colorForState, colorForState, colorStateList.getDefaultColor()};
        clockHandView2.addOnRotateListener(this);
        int defaultColor = AppCompatResources.getColorStateList(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateList2 != null ? colorStateList2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView.this.setRadius(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.clockHandView.getSelectorRadius()) - ClockFaceView.this.clockHandPadding);
                return true;
            }
        });
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                int intValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
                if (intValue > 0) {
                    accessibilityNodeInfoCompat.setTraversalAfter((View) ClockFaceView.this.textViewPool.get(intValue - 1));
                }
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, intValue, 1, false, view.isSelected()));
                accessibilityNodeInfoCompat.setClickable(true);
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 16) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                view.getHitRect(ClockFaceView.this.textViewRect);
                long j = uptimeMillis;
                float centerX = (float) ClockFaceView.this.textViewRect.centerX();
                float centerY = (float) ClockFaceView.this.textViewRect.centerY();
                ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, j, 0, centerX, centerY, 0));
                ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, j, 1, centerX, centerY, 0));
                return true;
            }
        };
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        setValues(strArr, 0);
        this.minimumHeight = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.clockSize = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }

    public void setValues(String[] strArr, int i) {
        this.values = strArr;
        updateTextViews(i);
    }

    private void updateTextViews(int i) {
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = this.textViewPool.size();
        boolean z = false;
        for (int i2 = 0; i2 < Math.max(this.values.length, size); i2++) {
            TextView textView = this.textViewPool.get(i2);
            if (i2 >= this.values.length) {
                removeView(textView);
                this.textViewPool.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(R.layout.material_clockface_textview, this, false);
                    this.textViewPool.put(i2, textView);
                    addView(textView);
                }
                textView.setText(this.values[i2]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i2));
                int i3 = (i2 / 12) + 1;
                textView.setTag(R.id.material_clock_level, Integer.valueOf(i3));
                if (i3 > 1) {
                    z = true;
                }
                ViewCompat.setAccessibilityDelegate(textView, this.valueAccessibilityDelegate);
                textView.setTextColor(this.textColor);
                if (i != 0) {
                    textView.setContentDescription(getResources().getString(i, new Object[]{this.values[i2]}));
                }
            }
        }
        this.clockHandView.setMultiLevel(z);
    }

    /* access modifiers changed from: protected */
    public void updateLayoutParams() {
        super.updateLayoutParams();
        for (int i = 0; i < this.textViewPool.size(); i++) {
            this.textViewPool.get(i).setVisibility(0);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.values.length, false, 1));
    }

    public void setRadius(int i) {
        if (i != getRadius()) {
            super.setRadius(i);
            this.clockHandView.setCircleRadius(getRadius());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        findIntersectingTextView();
    }

    public void setHandRotation(float f) {
        this.clockHandView.setHandRotation(f);
        findIntersectingTextView();
    }

    private void findIntersectingTextView() {
        RectF currentSelectorBox = this.clockHandView.getCurrentSelectorBox();
        TextView selectedTextView = getSelectedTextView(currentSelectorBox);
        for (int i = 0; i < this.textViewPool.size(); i++) {
            TextView textView = this.textViewPool.get(i);
            if (textView != null) {
                textView.setSelected(textView == selectedTextView);
                textView.getPaint().setShader(getGradientForTextView(currentSelectorBox, textView));
                textView.invalidate();
            }
        }
    }

    private TextView getSelectedTextView(RectF rectF) {
        float f = Float.MAX_VALUE;
        TextView textView = null;
        for (int i = 0; i < this.textViewPool.size(); i++) {
            TextView textView2 = this.textViewPool.get(i);
            if (textView2 != null) {
                textView2.getHitRect(this.textViewRect);
                this.scratch.set(this.textViewRect);
                this.scratch.union(rectF);
                float width = this.scratch.width() * this.scratch.height();
                if (width < f) {
                    textView = textView2;
                    f = width;
                }
            }
        }
        return textView;
    }

    private RadialGradient getGradientForTextView(RectF rectF, TextView textView) {
        textView.getHitRect(this.textViewRect);
        this.scratch.set(this.textViewRect);
        textView.getLineBounds(0, this.scratchLineBounds);
        this.scratch.inset((float) this.scratchLineBounds.left, (float) this.scratchLineBounds.top);
        if (!RectF.intersects(rectF, this.scratch)) {
            return null;
        }
        return new RadialGradient(rectF.centerX() - this.scratch.left, rectF.centerY() - this.scratch.top, rectF.width() * 0.5f, this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
    }

    public void onRotate(float f, boolean z) {
        if (Math.abs(this.currentHandRotation - f) > EPSILON) {
            this.currentHandRotation = f;
            findIntersectingTextView();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int max3 = (int) (((float) this.clockSize) / max3(((float) this.minimumHeight) / ((float) displayMetrics.heightPixels), ((float) this.minimumWidth) / ((float) displayMetrics.widthPixels), 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max3, BasicMeasure.EXACTLY);
        setMeasuredDimension(max3, max3);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    private static float max3(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    /* access modifiers changed from: package-private */
    public int getCurrentLevel() {
        return this.clockHandView.getCurrentLevel();
    }

    /* access modifiers changed from: package-private */
    public void setCurrentLevel(int i) {
        this.clockHandView.setCurrentLevel(i);
    }
}
