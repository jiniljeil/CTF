package com.google.android.material.timepicker;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;

class TimePickerView extends ConstraintLayout implements TimePickerControls {
    static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";
    private final ClockFaceView clockFace;
    private final ClockHandView clockHandView;
    private final Chip hourView;
    private final Chip minuteView;
    /* access modifiers changed from: private */
    public OnDoubleTapListener onDoubleTapListener;
    private OnPeriodChangeListener onPeriodChangeListener;
    /* access modifiers changed from: private */
    public OnSelectionChange onSelectionChangeListener;
    private final View.OnClickListener selectionListener;
    private final MaterialButtonToggleGroup toggle;

    interface OnDoubleTapListener {
        void onDoubleTap();
    }

    interface OnPeriodChangeListener {
        void onPeriodChange(int i);
    }

    interface OnSelectionChange {
        void onSelectionChanged(int i);
    }

    public TimePickerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectionListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (TimePickerView.this.onSelectionChangeListener != null) {
                    TimePickerView.this.onSelectionChangeListener.onSelectionChanged(((Integer) view.getTag(R.id.selection_type)).intValue());
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        this.clockFace = (ClockFaceView) findViewById(R.id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.toggle = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new TimePickerView$$ExternalSyntheticLambda0(this));
        this.minuteView = (Chip) findViewById(R.id.material_minute_tv);
        this.hourView = (Chip) findViewById(R.id.material_hour_tv);
        this.clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        setupDoubleTap();
        setUpDisplay();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-material-timepicker-TimePickerView  reason: not valid java name */
    public /* synthetic */ void m150lambda$new$0$comgoogleandroidmaterialtimepickerTimePickerView(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
        if (z && this.onPeriodChangeListener != null) {
            this.onPeriodChangeListener.onPeriodChange(i == R.id.material_clock_period_pm_button ? 1 : 0);
        }
    }

    private void setupDoubleTap() {
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                OnDoubleTapListener access$100 = TimePickerView.this.onDoubleTapListener;
                if (access$100 == null) {
                    return false;
                }
                access$100.onDoubleTap();
                return true;
            }
        });
        AnonymousClass3 r1 = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (((Checkable) view).isChecked()) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
                return false;
            }
        };
        this.minuteView.setOnTouchListener(r1);
        this.hourView.setOnTouchListener(r1);
    }

    public void setMinuteHourDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.hourView, accessibilityDelegateCompat);
    }

    public void setHourClickDelegate(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.minuteView, accessibilityDelegateCompat);
    }

    private void setUpDisplay() {
        this.minuteView.setTag(R.id.selection_type, 12);
        this.hourView.setTag(R.id.selection_type, 10);
        this.minuteView.setOnClickListener(this.selectionListener);
        this.hourView.setOnClickListener(this.selectionListener);
        this.minuteView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
        this.hourView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
    }

    public void setValues(String[] strArr, int i) {
        this.clockFace.setValues(strArr, i);
    }

    public void setHandRotation(float f) {
        this.clockHandView.setHandRotation(f);
    }

    public void setHandRotation(float f, boolean z) {
        this.clockHandView.setHandRotation(f, z);
    }

    public void setAnimateOnTouchUp(boolean z) {
        this.clockHandView.setAnimateOnTouchUp(z);
    }

    public void updateTime(int i, int i2, int i3) {
        int i4;
        if (i == 1) {
            i4 = R.id.material_clock_period_pm_button;
        } else {
            i4 = R.id.material_clock_period_am_button;
        }
        this.toggle.check(i4);
        Locale locale = getResources().getConfiguration().locale;
        String format = String.format(locale, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(i3)});
        String format2 = String.format(locale, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(i2)});
        if (!TextUtils.equals(this.minuteView.getText(), format)) {
            this.minuteView.setText(format);
        }
        if (!TextUtils.equals(this.hourView.getText(), format2)) {
            this.hourView.setText(format2);
        }
    }

    public void setActiveSelection(int i) {
        boolean z = true;
        updateSelection(this.minuteView, i == 12);
        Chip chip = this.hourView;
        if (i != 10) {
            z = false;
        }
        updateSelection(chip, z);
    }

    private void updateSelection(Chip chip, boolean z) {
        chip.setChecked(z);
        ViewCompat.setAccessibilityLiveRegion(chip, z ? 2 : 0);
    }

    public void addOnRotateListener(ClockHandView.OnRotateListener onRotateListener) {
        this.clockHandView.addOnRotateListener(onRotateListener);
    }

    public void setOnActionUpListener(ClockHandView.OnActionUpListener onActionUpListener) {
        this.clockHandView.setOnActionUpListener(onActionUpListener);
    }

    /* access modifiers changed from: package-private */
    public void setOnPeriodChangeListener(OnPeriodChangeListener onPeriodChangeListener2) {
        this.onPeriodChangeListener = onPeriodChangeListener2;
    }

    /* access modifiers changed from: package-private */
    public void setOnSelectionChangeListener(OnSelectionChange onSelectionChange) {
        this.onSelectionChangeListener = onSelectionChange;
    }

    /* access modifiers changed from: package-private */
    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener2) {
        this.onDoubleTapListener = onDoubleTapListener2;
    }

    public void showToggle() {
        this.toggle.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            this.hourView.sendAccessibilityEvent(8);
        }
    }

    /* access modifiers changed from: package-private */
    public int getCurrentLevel() {
        return this.clockFace.getCurrentLevel();
    }

    /* access modifiers changed from: package-private */
    public void setCurrentLevel(int i) {
        this.clockFace.setCurrentLevel(i);
    }
}
