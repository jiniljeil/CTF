package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    private static final int DEFAULT_LABEL_ANIMATION_ENTER_DURATION = 83;
    private static final int DEFAULT_LABEL_ANIMATION_EXIT_DURATION = 117;
    static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_Slider;
    private static final String EXCEPTION_ILLEGAL_DISCRETE_VALUE = "Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION = "minSeparation(%s) must be greater or equal to 0";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE = "minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT = "minSeparation(%s) cannot be set as a dimension when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_STEP_SIZE = "The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range";
    private static final String EXCEPTION_ILLEGAL_VALUE = "Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_FROM = "valueFrom(%s) must be smaller than valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_TO = "valueTo(%s) must be greater than valueFrom(%s)";
    private static final int HALO_ALPHA = 63;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = R.attr.motionDurationMedium4;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = R.attr.motionDurationShort3;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;
    private static final int MIN_TOUCH_TARGET_DP = 48;
    private static final String TAG = "BaseSlider";
    private static final double THRESHOLD = 1.0E-4d;
    private static final float THUMB_WIDTH_PRESSED_RATIO = 0.5f;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    static final int UNIT_PX = 0;
    static final int UNIT_VALUE = 1;
    private static final String WARNING_FLOATING_POINT_ERROR = "Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.";
    private BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender;
    /* access modifiers changed from: private */
    public final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    private int activeThumbIdx;
    private final Paint activeTicksPaint;
    private final Paint activeTrackPaint;
    private final List<L> changeListeners;
    private final RectF cornerRect;
    private Drawable customThumbDrawable;
    private List<Drawable> customThumbDrawablesForValues;
    private final MaterialShapeDrawable defaultThumbDrawable;
    private int defaultThumbRadius;
    private int defaultThumbTrackGapSize;
    private int defaultThumbWidth;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackHeight;
    private boolean dirtyConfig;
    private int focusedThumbIdx;
    private boolean forceDrawCompatHalo;
    private LabelFormatter formatter;
    private ColorStateList haloColor;
    private final Paint haloPaint;
    private int haloRadius;
    private final Paint inactiveTicksPaint;
    private final Paint inactiveTrackPaint;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private int labelStyle;
    /* access modifiers changed from: private */
    public final List<TooltipDrawable> labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTickSpacing;
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetHeight;
    private final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private final int scaledTouchSlop;
    private int separationUnit;
    private float stepSize;
    private final Paint stopIndicatorPaint;
    private int thumbHeight;
    private boolean thumbIsPressed;
    private final Paint thumbPaint;
    private int thumbTrackGapSize;
    private int thumbWidth;
    private int tickActiveRadius;
    private ColorStateList tickColorActive;
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private boolean tickVisible;
    private float[] ticksCoordinates;
    private float touchDownX;
    private final List<T> touchListeners;
    private float touchPosition;
    private ColorStateList trackColorActive;
    private ColorStateList trackColorInactive;
    private int trackHeight;
    private int trackInsideCornerSize;
    private final Path trackPath;
    private final RectF trackRect;
    private int trackSidePadding;
    private int trackStopIndicatorSize;
    private int trackWidth;
    private float valueFrom;
    private float valueTo;
    private ArrayList<Float> values;
    private int widgetHeight;

    private enum FullCornerDirection {
        BOTH,
        LEFT,
        RIGHT,
        NONE
    }

    /* access modifiers changed from: protected */
    public float getMinSeparation() {
        return 0.0f;
    }

    public BaseSlider(Context context) {
        this(context, (AttributeSet) null);
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.defaultThumbWidth = -1;
        this.defaultThumbTrackGapSize = -1;
        this.thumbIsPressed = false;
        this.values = new ArrayList<>();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.tickVisible = true;
        this.isLongPress = false;
        this.trackPath = new Path();
        this.trackRect = new RectF();
        this.cornerRect = new RectF();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.defaultThumbDrawable = materialShapeDrawable;
        this.customThumbDrawablesForValues = Collections.emptyList();
        this.separationUnit = 0;
        this.onScrollChangedListener = new BaseSlider$$ExternalSyntheticLambda0(this);
        Context context2 = getContext();
        this.inactiveTrackPaint = new Paint();
        this.activeTrackPaint = new Paint();
        Paint paint = new Paint(1);
        this.thumbPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint2 = new Paint(1);
        this.haloPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.inactiveTicksPaint = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeCap(Paint.Cap.ROUND);
        Paint paint4 = new Paint();
        this.activeTicksPaint = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeCap(Paint.Cap.ROUND);
        Paint paint5 = new Paint();
        this.stopIndicatorPaint = paint5;
        paint5.setStyle(Paint.Style.FILL);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        loadResources(context2.getResources());
        processAttributes(context2, attributeSet, i);
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.setShadowCompatibilityMode(2);
        this.scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        AccessibilityHelper accessibilityHelper2 = new AccessibilityHelper(this);
        this.accessibilityHelper = accessibilityHelper2;
        ViewCompat.setAccessibilityDelegate(this, accessibilityHelper2);
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    private void loadResources(Resources resources) {
        this.minWidgetHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.minTrackSidePadding = dimensionPixelOffset;
        this.trackSidePadding = dimensionPixelOffset;
        this.defaultThumbRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.defaultTrackHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_track_height);
        this.defaultTickActiveRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.defaultTickInactiveRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_radius);
        this.minTickSpacing = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_min_spacing);
        this.labelPadding = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
    }

    private void processAttributes(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Slider, i, DEF_STYLE_RES, new int[0]);
        this.labelStyle = obtainStyledAttributes.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip);
        this.valueFrom = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.valueTo = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.valueFrom));
        this.stepSize = obtainStyledAttributes.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        this.minTouchTargetSize = (int) Math.ceil((double) obtainStyledAttributes.getDimension(R.styleable.Slider_minTouchTargetSize, (float) Math.ceil((double) ViewUtils.dpToPx(getContext(), 48))));
        boolean hasValue = obtainStyledAttributes.hasValue(R.styleable.Slider_trackColor);
        int i2 = hasValue ? R.styleable.Slider_trackColor : R.styleable.Slider_trackColorInactive;
        int i3 = hasValue ? R.styleable.Slider_trackColor : R.styleable.Slider_trackColorActive;
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, i2);
        if (colorStateList == null) {
            colorStateList = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateList);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i3);
        if (colorStateList2 == null) {
            colorStateList2 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateList2);
        this.defaultThumbDrawable.setFillColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_thumbColor));
        if (obtainStyledAttributes.hasValue(R.styleable.Slider_thumbStrokeColor)) {
            setThumbStrokeColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_thumbStrokeColor));
        }
        setThumbStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_haloColor);
        if (colorStateList3 == null) {
            colorStateList3 = AppCompatResources.getColorStateList(context, R.color.material_slider_halo_color);
        }
        setHaloTintList(colorStateList3);
        this.tickVisible = obtainStyledAttributes.getBoolean(R.styleable.Slider_tickVisible, true);
        boolean hasValue2 = obtainStyledAttributes.hasValue(R.styleable.Slider_tickColor);
        int i4 = hasValue2 ? R.styleable.Slider_tickColor : R.styleable.Slider_tickColorInactive;
        int i5 = hasValue2 ? R.styleable.Slider_tickColor : R.styleable.Slider_tickColorActive;
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i4);
        if (colorStateList4 == null) {
            colorStateList4 = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i5);
        if (colorStateList5 == null) {
            colorStateList5 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateList5);
        setThumbTrackGapSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbTrackGapSize, 0));
        setTrackStopIndicatorSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackStopIndicatorSize, 0));
        setTrackInsideCornerSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackInsideCornerSize, 0));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0) * 2;
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbWidth, dimensionPixelSize);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbHeight, dimensionPixelSize);
        setThumbWidth(dimensionPixelSize2);
        setThumbHeight(dimensionPixelSize3);
        setHaloRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        setTickActiveRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_tickRadiusActive, this.trackStopIndicatorSize / 2));
        setTickInactiveRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_tickRadiusInactive, this.trackStopIndicatorSize / 2));
        setLabelBehavior(obtainStyledAttributes.getInt(R.styleable.Slider_labelBehavior, 0));
        if (!obtainStyledAttributes.getBoolean(R.styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        obtainStyledAttributes.recycle();
    }

    private boolean maybeIncreaseTrackSidePadding() {
        int max = this.minTrackSidePadding + Math.max(Math.max(Math.max((this.thumbWidth / 2) - this.defaultThumbRadius, 0), Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0)), Math.max(Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0), Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0)));
        if (this.trackSidePadding == max) {
            return false;
        }
        this.trackSidePadding = max;
        if (!ViewCompat.isLaidOut(this)) {
            return true;
        }
        updateTrackWidth(getWidth());
        return true;
    }

    private void validateValueFrom() {
        if (this.valueFrom >= this.valueTo) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_VALUE_FROM, new Object[]{Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)}));
        }
    }

    private void validateValueTo() {
        if (this.valueTo <= this.valueFrom) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_VALUE_TO, new Object[]{Float.valueOf(this.valueTo), Float.valueOf(this.valueFrom)}));
        }
    }

    private boolean valueLandsOnTick(float f) {
        return isMultipleOfStepSize(new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(this.valueFrom)), MathContext.DECIMAL64).doubleValue());
    }

    private boolean isMultipleOfStepSize(double d) {
        double doubleValue = new BigDecimal(Double.toString(d)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(doubleValue)) - doubleValue) < THRESHOLD;
    }

    private void validateStepSize() {
        if (this.stepSize > 0.0f && !valueLandsOnTick(this.valueTo)) {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_STEP_SIZE, new Object[]{Float.valueOf(this.stepSize), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)}));
        }
    }

    private void validateValues() {
        Iterator<Float> it = this.values.iterator();
        while (it.hasNext()) {
            Float next = it.next();
            if (next.floatValue() < this.valueFrom || next.floatValue() > this.valueTo) {
                throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_VALUE, new Object[]{next, Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)}));
            } else if (this.stepSize > 0.0f && !valueLandsOnTick(next.floatValue())) {
                throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_DISCRETE_VALUE, new Object[]{next, Float.valueOf(this.valueFrom), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)}));
            }
        }
    }

    private void validateMinSeparation() {
        float minSeparation = getMinSeparation();
        if (minSeparation >= 0.0f) {
            float f = this.stepSize;
            if (f > 0.0f && minSeparation > 0.0f) {
                if (this.separationUnit != 1) {
                    throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT, new Object[]{Float.valueOf(minSeparation), Float.valueOf(this.stepSize)}));
                } else if (minSeparation < f || !isMultipleOfStepSize((double) minSeparation)) {
                    throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE, new Object[]{Float.valueOf(minSeparation), Float.valueOf(this.stepSize), Float.valueOf(this.stepSize)}));
                }
            }
        } else {
            throw new IllegalStateException(String.format(EXCEPTION_ILLEGAL_MIN_SEPARATION, new Object[]{Float.valueOf(minSeparation)}));
        }
    }

    private void warnAboutFloatingPointError() {
        float f = this.stepSize;
        if (f != 0.0f) {
            if (((float) ((int) f)) != f) {
                Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, new Object[]{"stepSize", Float.valueOf(f)}));
            }
            float f2 = this.valueFrom;
            if (((float) ((int) f2)) != f2) {
                Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, new Object[]{"valueFrom", Float.valueOf(f2)}));
            }
            float f3 = this.valueTo;
            if (((float) ((int) f3)) != f3) {
                Log.w(TAG, String.format(WARNING_FLOATING_POINT_ERROR, new Object[]{"valueTo", Float.valueOf(f3)}));
            }
        }
    }

    private void validateConfigurationIfDirty() {
        if (this.dirtyConfig) {
            validateValueFrom();
            validateValueTo();
            validateStepSize();
            validateValues();
            validateMinSeparation();
            warnAboutFloatingPointError();
            this.dirtyConfig = false;
        }
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    public void setValueFrom(float f) {
        this.valueFrom = f;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public float getValueTo() {
        return this.valueTo;
    }

    public void setValueTo(float f) {
        this.valueTo = f;
        this.dirtyConfig = true;
        postInvalidate();
    }

    /* access modifiers changed from: package-private */
    public List<Float> getValues() {
        return new ArrayList(this.values);
    }

    /* access modifiers changed from: package-private */
    public void setValues(Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    /* access modifiers changed from: package-private */
    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList(list));
    }

    private void setValuesInternal(ArrayList<Float> arrayList) {
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            if (this.values.size() != arrayList.size() || !this.values.equals(arrayList)) {
                this.values = arrayList;
                this.dirtyConfig = true;
                this.focusedThumbIdx = 0;
                updateHaloHotspot();
                createLabelPool();
                dispatchOnChangedProgrammatically();
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("At least one value must be set");
    }

    private void createLabelPool() {
        int i;
        if (this.labels.size() > this.values.size()) {
            List<TooltipDrawable> subList = this.labels.subList(this.values.size(), this.labels.size());
            for (TooltipDrawable next : subList) {
                if (ViewCompat.isAttachedToWindow(this)) {
                    detachLabelFromContentView(next);
                }
            }
            subList.clear();
        }
        while (true) {
            i = 0;
            if (this.labels.size() >= this.values.size()) {
                break;
            }
            TooltipDrawable createFromAttributes = TooltipDrawable.createFromAttributes(getContext(), (AttributeSet) null, 0, this.labelStyle);
            this.labels.add(createFromAttributes);
            if (ViewCompat.isAttachedToWindow(this)) {
                attachLabelToContentView(createFromAttributes);
            }
        }
        if (this.labels.size() != 1) {
            i = 1;
        }
        for (TooltipDrawable strokeWidth : this.labels) {
            strokeWidth.setStrokeWidth((float) i);
        }
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public void setStepSize(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException(String.format(EXCEPTION_ILLEGAL_STEP_SIZE, new Object[]{Float.valueOf(f), Float.valueOf(this.valueFrom), Float.valueOf(this.valueTo)}));
        } else if (this.stepSize != f) {
            this.stepSize = f;
            this.dirtyConfig = true;
            postInvalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public void setCustomThumbDrawable(int i) {
        setCustomThumbDrawable(getResources().getDrawable(i));
    }

    /* access modifiers changed from: package-private */
    public void setCustomThumbDrawable(Drawable drawable) {
        this.customThumbDrawable = initializeCustomThumbDrawable(drawable);
        this.customThumbDrawablesForValues.clear();
        postInvalidate();
    }

    /* access modifiers changed from: package-private */
    public void setCustomThumbDrawablesForValues(int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            drawableArr[i] = getResources().getDrawable(iArr[i]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    /* access modifiers changed from: package-private */
    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.customThumbDrawable = null;
        this.customThumbDrawablesForValues = new ArrayList();
        for (Drawable initializeCustomThumbDrawable : drawableArr) {
            this.customThumbDrawablesForValues.add(initializeCustomThumbDrawable(initializeCustomThumbDrawable));
        }
        postInvalidate();
    }

    private Drawable initializeCustomThumbDrawable(Drawable drawable) {
        Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
        adjustCustomThumbDrawableBounds(newDrawable);
        return newDrawable;
    }

    private void adjustCustomThumbDrawableBounds(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
            return;
        }
        float max = ((float) Math.max(this.thumbWidth, this.thumbHeight)) / ((float) Math.max(intrinsicWidth, intrinsicHeight));
        drawable.setBounds(0, 0, (int) (((float) intrinsicWidth) * max), (int) (((float) intrinsicHeight) * max));
    }

    public int getFocusedThumbIndex() {
        return this.focusedThumbIdx;
    }

    public void setFocusedThumbIndex(int i) {
        if (i < 0 || i >= this.values.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.focusedThumbIdx = i;
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(i);
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void setActiveThumbIndex(int i) {
        this.activeThumbIdx = i;
    }

    public int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    public void addOnChangeListener(L l) {
        this.changeListeners.add(l);
    }

    public void removeOnChangeListener(L l) {
        this.changeListeners.remove(l);
    }

    public void clearOnChangeListeners() {
        this.changeListeners.clear();
    }

    public void addOnSliderTouchListener(T t) {
        this.touchListeners.add(t);
    }

    public void removeOnSliderTouchListener(T t) {
        this.touchListeners.remove(t);
    }

    public void clearOnSliderTouchListeners() {
        this.touchListeners.clear();
    }

    public boolean hasLabelFormatter() {
        return this.formatter != null;
    }

    public void setLabelFormatter(LabelFormatter labelFormatter) {
        this.formatter = labelFormatter;
    }

    public float getThumbElevation() {
        return this.defaultThumbDrawable.getElevation();
    }

    public void setThumbElevation(float f) {
        this.defaultThumbDrawable.setElevation(f);
    }

    public void setThumbElevationResource(int i) {
        setThumbElevation(getResources().getDimension(i));
    }

    public int getThumbRadius() {
        return this.thumbWidth / 2;
    }

    public void setThumbRadius(int i) {
        int i2 = i * 2;
        setThumbWidth(i2);
        setThumbHeight(i2);
    }

    public void setThumbRadiusResource(int i) {
        setThumbRadius(getResources().getDimensionPixelSize(i));
    }

    public int getThumbWidth() {
        return this.thumbWidth;
    }

    public void setThumbWidth(int i) {
        if (i != this.thumbWidth) {
            this.thumbWidth = i;
            this.defaultThumbDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, ((float) this.thumbWidth) / 2.0f).build());
            this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                adjustCustomThumbDrawableBounds(drawable);
            }
            for (Drawable adjustCustomThumbDrawableBounds : this.customThumbDrawablesForValues) {
                adjustCustomThumbDrawableBounds(adjustCustomThumbDrawableBounds);
            }
            updateWidgetLayout();
        }
    }

    public void setThumbWidthResource(int i) {
        setThumbWidth(getResources().getDimensionPixelSize(i));
    }

    public int getThumbHeight() {
        return this.thumbHeight;
    }

    public void setThumbHeight(int i) {
        if (i != this.thumbHeight) {
            this.thumbHeight = i;
            this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, i);
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                adjustCustomThumbDrawableBounds(drawable);
            }
            for (Drawable adjustCustomThumbDrawableBounds : this.customThumbDrawablesForValues) {
                adjustCustomThumbDrawableBounds(adjustCustomThumbDrawableBounds);
            }
            updateWidgetLayout();
        }
    }

    public void setThumbHeightResource(int i) {
        setThumbHeight(getResources().getDimensionPixelSize(i));
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.defaultThumbDrawable.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(int i) {
        if (i != 0) {
            setThumbStrokeColor(AppCompatResources.getColorStateList(getContext(), i));
        }
    }

    public ColorStateList getThumbStrokeColor() {
        return this.defaultThumbDrawable.getStrokeColor();
    }

    public void setThumbStrokeWidth(float f) {
        this.defaultThumbDrawable.setStrokeWidth(f);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(int i) {
        if (i != 0) {
            setThumbStrokeWidth(getResources().getDimension(i));
        }
    }

    public float getThumbStrokeWidth() {
        return this.defaultThumbDrawable.getStrokeWidth();
    }

    public int getHaloRadius() {
        return this.haloRadius;
    }

    public void setHaloRadius(int i) {
        if (i != this.haloRadius) {
            this.haloRadius = i;
            Drawable background = getBackground();
            if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
                postInvalidate();
            } else {
                DrawableUtils.setRippleDrawableRadius((RippleDrawable) background, this.haloRadius);
            }
        }
    }

    public void setHaloRadiusResource(int i) {
        setHaloRadius(getResources().getDimensionPixelSize(i));
    }

    public int getLabelBehavior() {
        return this.labelBehavior;
    }

    public void setLabelBehavior(int i) {
        if (this.labelBehavior != i) {
            this.labelBehavior = i;
            requestLayout();
        }
    }

    private boolean shouldAlwaysShowLabel() {
        return this.labelBehavior == 3;
    }

    public int getTrackSidePadding() {
        return this.trackSidePadding;
    }

    public int getTrackWidth() {
        return this.trackWidth;
    }

    public int getTrackHeight() {
        return this.trackHeight;
    }

    public void setTrackHeight(int i) {
        if (this.trackHeight != i) {
            this.trackHeight = i;
            invalidateTrack();
            updateWidgetLayout();
        }
    }

    public int getTickActiveRadius() {
        return this.tickActiveRadius;
    }

    public void setTickActiveRadius(int i) {
        if (this.tickActiveRadius != i) {
            this.tickActiveRadius = i;
            this.activeTicksPaint.setStrokeWidth((float) (i * 2));
            updateWidgetLayout();
        }
    }

    public int getTickInactiveRadius() {
        return this.tickInactiveRadius;
    }

    public void setTickInactiveRadius(int i) {
        if (this.tickInactiveRadius != i) {
            this.tickInactiveRadius = i;
            this.inactiveTicksPaint.setStrokeWidth((float) (i * 2));
            updateWidgetLayout();
        }
    }

    private void updateWidgetLayout() {
        boolean maybeIncreaseWidgetHeight = maybeIncreaseWidgetHeight();
        boolean maybeIncreaseTrackSidePadding = maybeIncreaseTrackSidePadding();
        if (maybeIncreaseWidgetHeight) {
            requestLayout();
        } else if (maybeIncreaseTrackSidePadding) {
            postInvalidate();
        }
    }

    private boolean maybeIncreaseWidgetHeight() {
        int max = Math.max(this.minWidgetHeight, Math.max(this.trackHeight + getPaddingTop() + getPaddingBottom(), this.thumbHeight + getPaddingTop() + getPaddingBottom()));
        if (max == this.widgetHeight) {
            return false;
        }
        this.widgetHeight = max;
        return true;
    }

    public ColorStateList getHaloTintList() {
        return this.haloColor;
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.haloColor)) {
            this.haloColor = colorStateList;
            Drawable background = getBackground();
            if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
                this.haloPaint.setColor(getColorForState(colorStateList));
                this.haloPaint.setAlpha(63);
                invalidate();
                return;
            }
            ((RippleDrawable) background).setColor(colorStateList);
        }
    }

    public ColorStateList getThumbTintList() {
        return this.defaultThumbDrawable.getFillColor();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.defaultThumbDrawable.getFillColor())) {
            this.defaultThumbDrawable.setFillColor(colorStateList);
            invalidate();
        }
    }

    public ColorStateList getTickTintList() {
        if (this.tickColorInactive.equals(this.tickColorActive)) {
            return this.tickColorActive;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public ColorStateList getTickActiveTintList() {
        return this.tickColorActive;
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.tickColorActive)) {
            this.tickColorActive = colorStateList;
            this.activeTicksPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public ColorStateList getTickInactiveTintList() {
        return this.tickColorInactive;
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.tickColorInactive)) {
            this.tickColorInactive = colorStateList;
            this.inactiveTicksPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public boolean isTickVisible() {
        return this.tickVisible;
    }

    public void setTickVisible(boolean z) {
        if (this.tickVisible != z) {
            this.tickVisible = z;
            postInvalidate();
        }
    }

    public ColorStateList getTrackTintList() {
        if (this.trackColorInactive.equals(this.trackColorActive)) {
            return this.trackColorActive;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public ColorStateList getTrackActiveTintList() {
        return this.trackColorActive;
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.trackColorActive)) {
            this.trackColorActive = colorStateList;
            this.activeTrackPaint.setColor(getColorForState(colorStateList));
            this.stopIndicatorPaint.setColor(getColorForState(this.trackColorActive));
            invalidate();
        }
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.trackColorInactive;
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (!colorStateList.equals(this.trackColorInactive)) {
            this.trackColorInactive = colorStateList;
            this.inactiveTrackPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public int getThumbTrackGapSize() {
        return this.thumbTrackGapSize;
    }

    public void setThumbTrackGapSize(int i) {
        if (this.thumbTrackGapSize != i) {
            this.thumbTrackGapSize = i;
            invalidate();
        }
    }

    public int getTrackStopIndicatorSize() {
        return this.trackStopIndicatorSize;
    }

    public void setTrackStopIndicatorSize(int i) {
        if (this.trackStopIndicatorSize != i) {
            this.trackStopIndicatorSize = i;
            this.stopIndicatorPaint.setStrokeWidth((float) i);
            invalidate();
        }
    }

    public int getTrackInsideCornerSize() {
        return this.trackInsideCornerSize;
    }

    public void setTrackInsideCornerSize(int i) {
        if (this.trackInsideCornerSize != i) {
            this.trackInsideCornerSize = i;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        ViewOverlayImpl contentViewOverlay;
        super.onVisibilityChanged(view, i);
        if (i != 0 && (contentViewOverlay = ViewUtils.getContentViewOverlay(this)) != null) {
            for (TooltipDrawable remove : this.labels) {
                contentViewOverlay.remove(remove);
            }
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, (Paint) null);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
        for (TooltipDrawable attachLabelToContentView : this.labels) {
            attachLabelToContentView(attachLabelToContentView);
        }
    }

    private void attachLabelToContentView(TooltipDrawable tooltipDrawable) {
        tooltipDrawable.setRelativeToView(ViewUtils.getContentView(this));
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
        if (accessibilityEventSender2 != null) {
            removeCallbacks(accessibilityEventSender2);
        }
        this.labelsAreAnimatedIn = false;
        for (TooltipDrawable detachLabelFromContentView : this.labels) {
            detachLabelFromContentView(detachLabelFromContentView);
        }
        getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        super.onDetachedFromWindow();
    }

    private void detachLabelFromContentView(TooltipDrawable tooltipDrawable) {
        ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(this);
        if (contentViewOverlay != null) {
            contentViewOverlay.remove(tooltipDrawable);
            tooltipDrawable.detachView(ViewUtils.getContentView(this));
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = this.widgetHeight;
        int i4 = 0;
        if (this.labelBehavior == 1 || shouldAlwaysShowLabel()) {
            i4 = this.labels.get(0).getIntrinsicHeight();
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3 + i4, BasicMeasure.EXACTLY));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        updateTrackWidth(i);
        updateHaloHotspot();
    }

    private void maybeCalculateTicksCoordinates() {
        if (this.stepSize > 0.0f) {
            validateConfigurationIfDirty();
            int min = Math.min((int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f), (this.trackWidth / this.minTickSpacing) + 1);
            float[] fArr = this.ticksCoordinates;
            if (fArr == null || fArr.length != min * 2) {
                this.ticksCoordinates = new float[(min * 2)];
            }
            float f = ((float) this.trackWidth) / ((float) (min - 1));
            for (int i = 0; i < min * 2; i += 2) {
                float[] fArr2 = this.ticksCoordinates;
                fArr2[i] = ((float) this.trackSidePadding) + ((((float) i) / 2.0f) * f);
                fArr2[i + 1] = (float) calculateTrackCenter();
            }
        }
    }

    private void updateTrackWidth(int i) {
        this.trackWidth = Math.max(i - (this.trackSidePadding * 2), 0);
        maybeCalculateTicksCoordinates();
    }

    /* access modifiers changed from: private */
    public void updateHaloHotspot() {
        if (!shouldDrawCompatHalo() && getMeasuredWidth() > 0) {
            Drawable background = getBackground();
            if (background instanceof RippleDrawable) {
                int normalizeValue = (int) ((normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * ((float) this.trackWidth)) + ((float) this.trackSidePadding));
                int calculateTrackCenter = calculateTrackCenter();
                int i = this.haloRadius;
                DrawableCompat.setHotspotBounds(background, normalizeValue - i, calculateTrackCenter - i, normalizeValue + i, calculateTrackCenter + i);
            }
        }
    }

    private int calculateTrackCenter() {
        int i = this.widgetHeight / 2;
        int i2 = 0;
        if (this.labelBehavior == 1 || shouldAlwaysShowLabel()) {
            i2 = this.labels.get(0).getIntrinsicHeight();
        }
        return i + i2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.dirtyConfig) {
            validateConfigurationIfDirty();
            maybeCalculateTicksCoordinates();
        }
        super.onDraw(canvas);
        int calculateTrackCenter = calculateTrackCenter();
        float floatValue = this.values.get(0).floatValue();
        ArrayList<Float> arrayList = this.values;
        float floatValue2 = arrayList.get(arrayList.size() - 1).floatValue();
        if (floatValue2 < this.valueTo || (this.values.size() > 1 && floatValue > this.valueFrom)) {
            drawInactiveTrack(canvas, this.trackWidth, calculateTrackCenter);
        }
        if (floatValue2 > this.valueFrom) {
            drawActiveTrack(canvas, this.trackWidth, calculateTrackCenter);
        }
        maybeDrawTicks(canvas);
        maybeDrawStopIndicator(canvas, calculateTrackCenter);
        if ((this.thumbIsPressed || isFocused()) && isEnabled()) {
            maybeDrawCompatHalo(canvas, this.trackWidth, calculateTrackCenter);
        }
        updateLabels();
        drawThumbs(canvas, this.trackWidth, calculateTrackCenter);
    }

    private float[] getActiveRange() {
        float floatValue = this.values.get(0).floatValue();
        ArrayList<Float> arrayList = this.values;
        float floatValue2 = arrayList.get(arrayList.size() - 1).floatValue();
        if (this.values.size() == 1) {
            floatValue = this.valueFrom;
        }
        float normalizeValue = normalizeValue(floatValue);
        float normalizeValue2 = normalizeValue(floatValue2);
        if (isRtl()) {
            return new float[]{normalizeValue2, normalizeValue};
        }
        return new float[]{normalizeValue, normalizeValue2};
    }

    private void drawInactiveTrack(Canvas canvas, int i, int i2) {
        float[] activeRange = getActiveRange();
        int i3 = this.trackSidePadding;
        float f = (float) i;
        float f2 = ((float) i3) + (activeRange[1] * f);
        if (f2 < ((float) (i3 + i))) {
            if (hasGapBetweenThumbAndTrack()) {
                RectF rectF = this.trackRect;
                float f3 = f2 + ((float) this.thumbTrackGapSize);
                float f4 = (float) i2;
                int i4 = this.trackHeight;
                rectF.set(f3, f4 - (((float) i4) / 2.0f), ((float) (this.trackSidePadding + i)) + (((float) i4) / 2.0f), f4 + (((float) i4) / 2.0f));
                updateTrack(canvas, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.RIGHT);
            } else {
                this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
                this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
                float f5 = (float) i2;
                canvas.drawLine(f2, f5, (float) (this.trackSidePadding + i), f5, this.inactiveTrackPaint);
            }
        }
        int i5 = this.trackSidePadding;
        float f6 = ((float) i5) + (activeRange[0] * f);
        if (f6 <= ((float) i5)) {
            return;
        }
        if (hasGapBetweenThumbAndTrack()) {
            RectF rectF2 = this.trackRect;
            int i6 = this.trackHeight;
            float f7 = (float) i2;
            rectF2.set(((float) this.trackSidePadding) - (((float) i6) / 2.0f), f7 - (((float) i6) / 2.0f), f6 - ((float) this.thumbTrackGapSize), f7 + (((float) i6) / 2.0f));
            updateTrack(canvas, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.LEFT);
            return;
        }
        this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
        this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
        float f8 = (float) i2;
        canvas.drawLine((float) this.trackSidePadding, f8, f6, f8, this.inactiveTrackPaint);
    }

    private float normalizeValue(float f) {
        float f2 = this.valueFrom;
        float f3 = (f - f2) / (this.valueTo - f2);
        return isRtl() ? 1.0f - f3 : f3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b4 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawActiveTrack(android.graphics.Canvas r12, int r13, int r14) {
        /*
            r11 = this;
            float[] r0 = r11.getActiveRange()
            int r1 = r11.trackSidePadding
            float r2 = (float) r1
            r3 = 1
            r4 = r0[r3]
            float r13 = (float) r13
            float r4 = r4 * r13
            float r8 = r2 + r4
            float r1 = (float) r1
            r2 = 0
            r0 = r0[r2]
            float r0 = r0 * r13
            float r6 = r1 + r0
            boolean r13 = r11.hasGapBetweenThumbAndTrack()
            if (r13 == 0) goto L_0x00b8
            com.google.android.material.slider.BaseSlider$FullCornerDirection r13 = com.google.android.material.slider.BaseSlider.FullCornerDirection.NONE
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r0 = r0.size()
            if (r0 != r3) goto L_0x0030
            boolean r13 = r11.isRtl()
            if (r13 == 0) goto L_0x002e
            com.google.android.material.slider.BaseSlider$FullCornerDirection r13 = com.google.android.material.slider.BaseSlider.FullCornerDirection.RIGHT
            goto L_0x0030
        L_0x002e:
            com.google.android.material.slider.BaseSlider$FullCornerDirection r13 = com.google.android.material.slider.BaseSlider.FullCornerDirection.LEFT
        L_0x0030:
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r0 = r0.size()
            if (r2 >= r0) goto L_0x00ce
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r0 = r0.size()
            if (r0 <= r3) goto L_0x006e
            if (r2 <= 0) goto L_0x0054
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r1 = r2 + -1
            java.lang.Object r0 = r0.get(r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            float r6 = r11.valueToX(r0)
        L_0x0054:
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            java.lang.Object r0 = r0.get(r2)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            float r0 = r11.valueToX(r0)
            boolean r1 = r11.isRtl()
            if (r1 == 0) goto L_0x006d
            r8 = r6
            r6 = r0
            goto L_0x006e
        L_0x006d:
            r8 = r0
        L_0x006e:
            int[] r0 = com.google.android.material.slider.BaseSlider.AnonymousClass3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection
            int r1 = r13.ordinal()
            r0 = r0[r1]
            r1 = 1073741824(0x40000000, float:2.0)
            if (r0 == r3) goto L_0x0093
            r4 = 2
            if (r0 == r4) goto L_0x008b
            r4 = 3
            if (r0 == r4) goto L_0x0081
            goto L_0x0099
        L_0x0081:
            int r0 = r11.thumbTrackGapSize
            float r0 = (float) r0
            float r6 = r6 + r0
            int r0 = r11.trackHeight
            float r0 = (float) r0
            float r0 = r0 / r1
            float r8 = r8 + r0
            goto L_0x0099
        L_0x008b:
            int r0 = r11.trackHeight
            float r0 = (float) r0
            float r0 = r0 / r1
            float r6 = r6 - r0
            int r0 = r11.thumbTrackGapSize
            goto L_0x0097
        L_0x0093:
            int r0 = r11.thumbTrackGapSize
            float r4 = (float) r0
            float r6 = r6 + r4
        L_0x0097:
            float r0 = (float) r0
            float r8 = r8 - r0
        L_0x0099:
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 < 0) goto L_0x009e
            goto L_0x00b4
        L_0x009e:
            android.graphics.RectF r0 = r11.trackRect
            float r4 = (float) r14
            int r5 = r11.trackHeight
            float r7 = (float) r5
            float r7 = r7 / r1
            float r7 = r4 - r7
            float r5 = (float) r5
            float r5 = r5 / r1
            float r4 = r4 + r5
            r0.set(r6, r7, r8, r4)
            android.graphics.Paint r0 = r11.activeTrackPaint
            android.graphics.RectF r1 = r11.trackRect
            r11.updateTrack(r12, r0, r1, r13)
        L_0x00b4:
            int r2 = r2 + 1
            goto L_0x0030
        L_0x00b8:
            android.graphics.Paint r13 = r11.activeTrackPaint
            android.graphics.Paint$Style r0 = android.graphics.Paint.Style.STROKE
            r13.setStyle(r0)
            android.graphics.Paint r13 = r11.activeTrackPaint
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.ROUND
            r13.setStrokeCap(r0)
            float r9 = (float) r14
            android.graphics.Paint r10 = r11.activeTrackPaint
            r5 = r12
            r7 = r9
            r5.drawLine(r6, r7, r8, r9, r10)
        L_0x00ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.drawActiveTrack(android.graphics.Canvas, int, int):void");
    }

    /* renamed from: com.google.android.material.slider.BaseSlider$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.android.material.slider.BaseSlider$FullCornerDirection[] r0 = com.google.android.material.slider.BaseSlider.FullCornerDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection = r0
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.LEFT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.RIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.BOTH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.AnonymousClass3.<clinit>():void");
        }
    }

    private boolean hasGapBetweenThumbAndTrack() {
        return this.thumbTrackGapSize > 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateTrack(android.graphics.Canvas r8, android.graphics.Paint r9, android.graphics.RectF r10, com.google.android.material.slider.BaseSlider.FullCornerDirection r11) {
        /*
            r7 = this;
            int r0 = r7.trackHeight
            float r1 = (float) r0
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            float r0 = (float) r0
            float r0 = r0 / r2
            int[] r3 = com.google.android.material.slider.BaseSlider.AnonymousClass3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection
            int r4 = r11.ordinal()
            r3 = r3[r4]
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == r6) goto L_0x0021
            if (r3 == r5) goto L_0x001e
            if (r3 == r4) goto L_0x001a
            goto L_0x0025
        L_0x001a:
            int r1 = r7.trackInsideCornerSize
            float r1 = (float) r1
            goto L_0x0025
        L_0x001e:
            int r0 = r7.trackInsideCornerSize
            goto L_0x0024
        L_0x0021:
            int r0 = r7.trackInsideCornerSize
            float r1 = (float) r0
        L_0x0024:
            float r0 = (float) r0
        L_0x0025:
            android.graphics.Paint$Style r3 = android.graphics.Paint.Style.FILL
            r9.setStyle(r3)
            android.graphics.Paint$Cap r3 = android.graphics.Paint.Cap.BUTT
            r9.setStrokeCap(r3)
            r9.setAntiAlias(r6)
            android.graphics.Path r3 = r7.trackPath
            r3.reset()
            float r3 = r10.width()
            float r6 = r1 + r0
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 < 0) goto L_0x0052
            android.graphics.Path r11 = r7.trackPath
            float[] r0 = r7.getCornerRadii(r1, r0)
            android.graphics.Path$Direction r1 = android.graphics.Path.Direction.CW
            r11.addRoundRect(r10, r0, r1)
            android.graphics.Path r10 = r7.trackPath
            r8.drawPath(r10, r9)
            goto L_0x00b0
        L_0x0052:
            float r3 = java.lang.Math.min(r1, r0)
            float r0 = java.lang.Math.max(r1, r0)
            r8.save()
            android.graphics.Path r1 = r7.trackPath
            android.graphics.Path$Direction r6 = android.graphics.Path.Direction.CW
            r1.addRoundRect(r10, r3, r3, r6)
            android.graphics.Path r1 = r7.trackPath
            r8.clipPath(r1)
            int[] r1 = com.google.android.material.slider.BaseSlider.AnonymousClass3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection
            int r11 = r11.ordinal()
            r11 = r1[r11]
            if (r11 == r5) goto L_0x0099
            if (r11 == r4) goto L_0x0089
            android.graphics.RectF r11 = r7.cornerRect
            float r1 = r10.centerX()
            float r1 = r1 - r0
            float r2 = r10.top
            float r3 = r10.centerX()
            float r3 = r3 + r0
            float r10 = r10.bottom
            r11.set(r1, r2, r3, r10)
            goto L_0x00a8
        L_0x0089:
            android.graphics.RectF r11 = r7.cornerRect
            float r1 = r10.right
            float r2 = r2 * r0
            float r1 = r1 - r2
            float r2 = r10.top
            float r3 = r10.right
            float r10 = r10.bottom
            r11.set(r1, r2, r3, r10)
            goto L_0x00a8
        L_0x0099:
            android.graphics.RectF r11 = r7.cornerRect
            float r1 = r10.left
            float r3 = r10.top
            float r4 = r10.left
            float r2 = r2 * r0
            float r4 = r4 + r2
            float r10 = r10.bottom
            r11.set(r1, r3, r4, r10)
        L_0x00a8:
            android.graphics.RectF r10 = r7.cornerRect
            r8.drawRoundRect(r10, r0, r0, r9)
            r8.restore()
        L_0x00b0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.updateTrack(android.graphics.Canvas, android.graphics.Paint, android.graphics.RectF, com.google.android.material.slider.BaseSlider$FullCornerDirection):void");
    }

    private float[] getCornerRadii(float f, float f2) {
        return new float[]{f, f, f2, f2, f2, f2, f, f};
    }

    private void maybeDrawTicks(Canvas canvas) {
        if (this.tickVisible && this.stepSize > 0.0f) {
            float[] activeRange = getActiveRange();
            int ceil = (int) Math.ceil((double) (activeRange[0] * ((((float) this.ticksCoordinates.length) / 2.0f) - 1.0f)));
            int floor = (int) Math.floor((double) (activeRange[1] * ((((float) this.ticksCoordinates.length) / 2.0f) - 1.0f)));
            if (ceil > 0) {
                canvas.drawPoints(this.ticksCoordinates, 0, ceil * 2, this.inactiveTicksPaint);
            }
            if (ceil <= floor) {
                canvas.drawPoints(this.ticksCoordinates, ceil * 2, ((floor - ceil) + 1) * 2, this.activeTicksPaint);
            }
            int i = (floor + 1) * 2;
            float[] fArr = this.ticksCoordinates;
            if (i < fArr.length) {
                canvas.drawPoints(fArr, i, fArr.length - i, this.inactiveTicksPaint);
            }
        }
    }

    private void maybeDrawStopIndicator(Canvas canvas, int i) {
        if (this.trackStopIndicatorSize > 0) {
            if (this.values.size() >= 1) {
                ArrayList<Float> arrayList = this.values;
                float floatValue = arrayList.get(arrayList.size() - 1).floatValue();
                float f = this.valueTo;
                if (floatValue < f) {
                    canvas.drawPoint(valueToX(f), (float) i, this.stopIndicatorPaint);
                }
            }
            if (this.values.size() > 1) {
                float floatValue2 = this.values.get(0).floatValue();
                float f2 = this.valueFrom;
                if (floatValue2 > f2) {
                    canvas.drawPoint(valueToX(f2), (float) i, this.stopIndicatorPaint);
                }
            }
        }
    }

    private void drawThumbs(Canvas canvas, int i, int i2) {
        for (int i3 = 0; i3 < this.values.size(); i3++) {
            float floatValue = this.values.get(i3).floatValue();
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                drawThumbDrawable(canvas, i, i2, floatValue, drawable);
            } else if (i3 < this.customThumbDrawablesForValues.size()) {
                drawThumbDrawable(canvas, i, i2, floatValue, this.customThumbDrawablesForValues.get(i3));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle(((float) this.trackSidePadding) + (normalizeValue(floatValue) * ((float) i)), (float) i2, (float) getThumbRadius(), this.thumbPaint);
                }
                drawThumbDrawable(canvas, i, i2, floatValue, this.defaultThumbDrawable);
            }
        }
    }

    private void drawThumbDrawable(Canvas canvas, int i, int i2, float f, Drawable drawable) {
        canvas.save();
        canvas.translate(((float) (this.trackSidePadding + ((int) (normalizeValue(f) * ((float) i))))) - (((float) drawable.getBounds().width()) / 2.0f), ((float) i2) - (((float) drawable.getBounds().height()) / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    private void maybeDrawCompatHalo(Canvas canvas, int i, int i2) {
        if (shouldDrawCompatHalo()) {
            int normalizeValue = (int) (((float) this.trackSidePadding) + (normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * ((float) i)));
            if (Build.VERSION.SDK_INT < 28) {
                int i3 = this.haloRadius;
                canvas.clipRect((float) (normalizeValue - i3), (float) (i2 - i3), (float) (normalizeValue + i3), (float) (i3 + i2), Region.Op.UNION);
            }
            canvas.drawCircle((float) normalizeValue, (float) i2, (float) this.haloRadius, this.haloPaint);
        }
    }

    private boolean shouldDrawCompatHalo() {
        return this.forceDrawCompatHalo || !(getBackground() instanceof RippleDrawable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        if (r2 != 3) goto L_0x0125;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            float r0 = r6.getX()
            int r2 = r5.trackSidePadding
            float r2 = (float) r2
            float r2 = r0 - r2
            int r3 = r5.trackWidth
            float r3 = (float) r3
            float r2 = r2 / r3
            r5.touchPosition = r2
            r3 = 0
            float r2 = java.lang.Math.max(r3, r2)
            r5.touchPosition = r2
            r3 = 1065353216(0x3f800000, float:1.0)
            float r2 = java.lang.Math.min(r3, r2)
            r5.touchPosition = r2
            int r2 = r6.getActionMasked()
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x00da
            if (r2 == r4) goto L_0x006f
            if (r2 == r3) goto L_0x0037
            r0 = 3
            if (r2 == r0) goto L_0x006f
            goto L_0x0125
        L_0x0037:
            boolean r2 = r5.thumbIsPressed
            if (r2 != 0) goto L_0x005a
            boolean r2 = r5.isPotentialVerticalScroll(r6)
            if (r2 == 0) goto L_0x0050
            float r2 = r5.touchDownX
            float r0 = r0 - r2
            float r0 = java.lang.Math.abs(r0)
            int r2 = r5.scaledTouchSlop
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0050
            return r1
        L_0x0050:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r4)
            r5.onStartTrackingTouch()
        L_0x005a:
            boolean r0 = r5.pickActiveThumb()
            if (r0 != 0) goto L_0x0062
            goto L_0x0125
        L_0x0062:
            r5.thumbIsPressed = r4
            r5.snapTouchPosition()
            r5.updateHaloHotspot()
            r5.invalidate()
            goto L_0x0125
        L_0x006f:
            r5.thumbIsPressed = r1
            android.view.MotionEvent r0 = r5.lastEvent
            if (r0 == 0) goto L_0x00b0
            int r0 = r0.getActionMasked()
            if (r0 != 0) goto L_0x00b0
            android.view.MotionEvent r0 = r5.lastEvent
            float r0 = r0.getX()
            float r1 = r6.getX()
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            int r1 = r5.scaledTouchSlop
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x00b0
            android.view.MotionEvent r0 = r5.lastEvent
            float r0 = r0.getY()
            float r1 = r6.getY()
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            int r1 = r5.scaledTouchSlop
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x00b0
            boolean r0 = r5.pickActiveThumb()
            if (r0 == 0) goto L_0x00b0
            r5.onStartTrackingTouch()
        L_0x00b0:
            int r0 = r5.activeThumbIdx
            r1 = -1
            if (r0 == r1) goto L_0x00d6
            r5.snapTouchPosition()
            r5.updateHaloHotspot()
            boolean r0 = r5.hasGapBetweenThumbAndTrack()
            if (r0 == 0) goto L_0x00d1
            int r0 = r5.defaultThumbWidth
            if (r0 == r1) goto L_0x00d1
            int r2 = r5.defaultThumbTrackGapSize
            if (r2 == r1) goto L_0x00d1
            r5.setThumbWidth(r0)
            int r0 = r5.defaultThumbTrackGapSize
            r5.setThumbTrackGapSize(r0)
        L_0x00d1:
            r5.activeThumbIdx = r1
            r5.onStopTrackingTouch()
        L_0x00d6:
            r5.invalidate()
            goto L_0x0125
        L_0x00da:
            r5.touchDownX = r0
            boolean r0 = r5.isPotentialVerticalScroll(r6)
            if (r0 == 0) goto L_0x00e3
            goto L_0x0125
        L_0x00e3:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r4)
            boolean r0 = r5.pickActiveThumb()
            if (r0 != 0) goto L_0x00f1
            goto L_0x0125
        L_0x00f1:
            r5.requestFocus()
            r5.thumbIsPressed = r4
            r5.snapTouchPosition()
            r5.updateHaloHotspot()
            boolean r0 = r5.hasGapBetweenThumbAndTrack()
            if (r0 == 0) goto L_0x011f
            int r0 = r5.thumbWidth
            r5.defaultThumbWidth = r0
            int r1 = r5.thumbTrackGapSize
            r5.defaultThumbTrackGapSize = r1
            float r0 = (float) r0
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 * r1
            int r0 = java.lang.Math.round(r0)
            int r1 = r5.thumbWidth
            int r1 = r1 - r0
            r5.setThumbWidth(r0)
            int r0 = r5.thumbTrackGapSize
            int r1 = r1 / r3
            int r0 = r0 - r1
            r5.setThumbTrackGapSize(r0)
        L_0x011f:
            r5.invalidate()
            r5.onStartTrackingTouch()
        L_0x0125:
            boolean r0 = r5.thumbIsPressed
            r5.setPressed(r0)
            android.view.MotionEvent r6 = android.view.MotionEvent.obtain(r6)
            r5.lastEvent = r6
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private double snapPosition(float f) {
        float f2 = this.stepSize;
        if (f2 <= 0.0f) {
            return (double) f;
        }
        int i = (int) ((this.valueTo - this.valueFrom) / f2);
        return ((double) Math.round(f * ((float) i))) / ((double) i);
    }

    /* access modifiers changed from: protected */
    public boolean pickActiveThumb() {
        if (this.activeThumbIdx != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float valueToX = valueToX(valueOfTouchPositionAbsolute);
        this.activeThumbIdx = 0;
        float abs = Math.abs(this.values.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i = 1; i < this.values.size(); i++) {
            float abs2 = Math.abs(this.values.get(i).floatValue() - valueOfTouchPositionAbsolute);
            float valueToX2 = valueToX(this.values.get(i).floatValue());
            if (Float.compare(abs2, abs) > 0) {
                break;
            }
            boolean z = !isRtl() ? valueToX2 - valueToX < 0.0f : valueToX2 - valueToX > 0.0f;
            if (Float.compare(abs2, abs) < 0) {
                this.activeThumbIdx = i;
            } else {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else if (Math.abs(valueToX2 - valueToX) < ((float) this.scaledTouchSlop)) {
                    this.activeThumbIdx = -1;
                    return false;
                } else if (z) {
                    this.activeThumbIdx = i;
                }
            }
            abs = abs2;
        }
        if (this.activeThumbIdx != -1) {
            return true;
        }
        return false;
    }

    private float getValueOfTouchPositionAbsolute() {
        float f = this.touchPosition;
        if (isRtl()) {
            f = 1.0f - f;
        }
        float f2 = this.valueTo;
        float f3 = this.valueFrom;
        return (f * (f2 - f3)) + f3;
    }

    private boolean snapTouchPosition() {
        return snapActiveThumbToValue(getValueOfTouchPosition());
    }

    private boolean snapActiveThumbToValue(float f) {
        return snapThumbToValue(this.activeThumbIdx, f);
    }

    /* access modifiers changed from: private */
    public boolean snapThumbToValue(int i, float f) {
        this.focusedThumbIdx = i;
        if (((double) Math.abs(f - this.values.get(i).floatValue())) < THRESHOLD) {
            return false;
        }
        this.values.set(i, Float.valueOf(getClampedValue(i, f)));
        dispatchOnChangedFromUser(i);
        return true;
    }

    private float getClampedValue(int i, float f) {
        float minSeparation = getMinSeparation();
        if (this.separationUnit == 0) {
            minSeparation = dimenToValue(minSeparation);
        }
        if (isRtl()) {
            minSeparation = -minSeparation;
        }
        int i2 = i + 1;
        int i3 = i - 1;
        return MathUtils.clamp(f, i3 < 0 ? this.valueFrom : this.values.get(i3).floatValue() + minSeparation, i2 >= this.values.size() ? this.valueTo : this.values.get(i2).floatValue() - minSeparation);
    }

    private float dimenToValue(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        float f2 = (f - ((float) this.trackSidePadding)) / ((float) this.trackWidth);
        float f3 = this.valueFrom;
        return (f2 * (f3 - this.valueTo)) + f3;
    }

    /* access modifiers changed from: protected */
    public void setSeparationUnit(int i) {
        this.separationUnit = i;
        this.dirtyConfig = true;
        postInvalidate();
    }

    private float getValueOfTouchPosition() {
        double snapPosition = snapPosition(this.touchPosition);
        if (isRtl()) {
            snapPosition = 1.0d - snapPosition;
        }
        float f = this.valueTo;
        float f2 = this.valueFrom;
        return (float) ((snapPosition * ((double) (f - f2))) + ((double) f2));
    }

    private float valueToX(float f) {
        return (normalizeValue(f) * ((float) this.trackWidth)) + ((float) this.trackSidePadding);
    }

    private static float getAnimatorCurrentValueOrDefault(ValueAnimator valueAnimator, float f) {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return f;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        valueAnimator.cancel();
        return floatValue;
    }

    private ValueAnimator createLabelAnimator(boolean z) {
        int i;
        TimeInterpolator timeInterpolator;
        float f = 0.0f;
        float animatorCurrentValueOrDefault = getAnimatorCurrentValueOrDefault(z ? this.labelsOutAnimator : this.labelsInAnimator, z ? 0.0f : 1.0f);
        if (z) {
            f = 1.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{animatorCurrentValueOrDefault, f});
        if (z) {
            i = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_ENTER_DURATION_ATTR, DEFAULT_LABEL_ANIMATION_ENTER_DURATION);
            timeInterpolator = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.DECELERATE_INTERPOLATOR);
        } else {
            i = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_EXIT_DURATION_ATTR, DEFAULT_LABEL_ANIMATION_EXIT_DURATION);
            timeInterpolator = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
        ofFloat.setDuration((long) i);
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                for (TooltipDrawable revealFraction : BaseSlider.this.labels) {
                    revealFraction.setRevealFraction(floatValue);
                }
                ViewCompat.postInvalidateOnAnimation(BaseSlider.this);
            }
        });
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public void updateLabels() {
        int i = this.labelBehavior;
        if (i == 0 || i == 1) {
            if (this.activeThumbIdx == -1 || !isEnabled()) {
                ensureLabelsRemoved();
            } else {
                ensureLabelsAdded();
            }
        } else if (i == 2) {
            ensureLabelsRemoved();
        } else if (i != 3) {
            throw new IllegalArgumentException("Unexpected labelBehavior: " + this.labelBehavior);
        } else if (!isEnabled() || !isSliderVisibleOnScreen()) {
            ensureLabelsRemoved();
        } else {
            ensureLabelsAdded();
        }
    }

    private boolean isSliderVisibleOnScreen() {
        Rect rect = new Rect();
        ViewUtils.getContentView(this).getHitRect(rect);
        return getLocalVisibleRect(rect);
    }

    private void ensureLabelsRemoved() {
        if (this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator createLabelAnimator = createLabelAnimator(false);
            this.labelsOutAnimator = createLabelAnimator;
            this.labelsInAnimator = null;
            createLabelAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(BaseSlider.this);
                    for (TooltipDrawable remove : BaseSlider.this.labels) {
                        contentViewOverlay.remove(remove);
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    private void ensureLabelsAdded() {
        if (!this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = true;
            ValueAnimator createLabelAnimator = createLabelAnimator(true);
            this.labelsInAnimator = createLabelAnimator;
            this.labelsOutAnimator = null;
            createLabelAnimator.start();
        }
        Iterator<TooltipDrawable> it = this.labels.iterator();
        for (int i = 0; i < this.values.size() && it.hasNext(); i++) {
            if (i != this.focusedThumbIdx) {
                setValueForLabel(it.next(), this.values.get(i).floatValue());
            }
        }
        if (it.hasNext()) {
            setValueForLabel(it.next(), this.values.get(this.focusedThumbIdx).floatValue());
            return;
        }
        throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", new Object[]{Integer.valueOf(this.labels.size()), Integer.valueOf(this.values.size())}));
    }

    /* access modifiers changed from: private */
    public String formatValue(float f) {
        if (hasLabelFormatter()) {
            return this.formatter.getFormattedValue(f);
        }
        return String.format(((float) ((int) f)) == f ? "%.0f" : "%.2f", new Object[]{Float.valueOf(f)});
    }

    private void setValueForLabel(TooltipDrawable tooltipDrawable, float f) {
        tooltipDrawable.setText(formatValue(f));
        positionLabel(tooltipDrawable, f);
        ViewUtils.getContentViewOverlay(this).add(tooltipDrawable);
    }

    private void positionLabel(TooltipDrawable tooltipDrawable, float f) {
        int normalizeValue = (this.trackSidePadding + ((int) (normalizeValue(f) * ((float) this.trackWidth)))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int calculateTrackCenter = calculateTrackCenter() - (this.labelPadding + (this.thumbHeight / 2));
        tooltipDrawable.setBounds(normalizeValue, calculateTrackCenter - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + normalizeValue, calculateTrackCenter);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
    }

    private void invalidateTrack() {
        this.inactiveTrackPaint.setStrokeWidth((float) this.trackHeight);
        this.activeTrackPaint.setStrokeWidth((float) this.trackHeight);
    }

    private boolean isInVerticalScrollingContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMouseEvent(MotionEvent motionEvent) {
        return motionEvent.getToolType(0) == 3;
    }

    private boolean isPotentialVerticalScroll(MotionEvent motionEvent) {
        return !isMouseEvent(motionEvent) && isInVerticalScrollingContainer();
    }

    private void dispatchOnChangedProgrammatically() {
        for (L l : this.changeListeners) {
            Iterator<Float> it = this.values.iterator();
            while (it.hasNext()) {
                l.onValueChange(this, it.next().floatValue(), false);
            }
        }
    }

    private void dispatchOnChangedFromUser(int i) {
        for (L onValueChange : this.changeListeners) {
            onValueChange.onValueChange(this, this.values.get(i).floatValue(), true);
        }
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null && accessibilityManager2.isEnabled()) {
            scheduleAccessibilityEventSender(i);
        }
    }

    private void onStartTrackingTouch() {
        for (T onStartTrackingTouch : this.touchListeners) {
            onStartTrackingTouch.onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        for (T onStopTrackingTouch : this.touchListeners) {
            onStopTrackingTouch.onStopTrackingTouch(this);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(getColorForState(this.trackColorInactive));
        this.activeTrackPaint.setColor(getColorForState(this.trackColorActive));
        this.inactiveTicksPaint.setColor(getColorForState(this.tickColorInactive));
        this.activeTicksPaint.setColor(getColorForState(this.tickColorActive));
        this.stopIndicatorPaint.setColor(getColorForState(this.trackColorActive));
        for (TooltipDrawable next : this.labels) {
            if (next.isStateful()) {
                next.setState(getDrawableState());
            }
        }
        if (this.defaultThumbDrawable.isStateful()) {
            this.defaultThumbDrawable.setState(getDrawableState());
        }
        this.haloPaint.setColor(getColorForState(this.haloColor));
        this.haloPaint.setAlpha(63);
    }

    private int getColorForState(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    /* access modifiers changed from: package-private */
    public void forceDrawCompatHalo(boolean z) {
        this.forceDrawCompatHalo = z;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.values.size() == 1) {
            this.activeThumbIdx = 0;
        }
        if (this.activeThumbIdx == -1) {
            Boolean onKeyDownNoActiveThumb = onKeyDownNoActiveThumb(i, keyEvent);
            return onKeyDownNoActiveThumb != null ? onKeyDownNoActiveThumb.booleanValue() : super.onKeyDown(i, keyEvent);
        }
        this.isLongPress |= keyEvent.isLongPress();
        Float calculateIncrementForKey = calculateIncrementForKey(i);
        if (calculateIncrementForKey != null) {
            if (snapActiveThumbToValue(this.values.get(this.activeThumbIdx).floatValue() + calculateIncrementForKey.floatValue())) {
                updateHaloHotspot();
                postInvalidate();
            }
            return true;
        }
        if (i != 23) {
            if (i != 61) {
                if (i != 66) {
                    return super.onKeyDown(i, keyEvent);
                }
            } else if (keyEvent.hasNoModifiers()) {
                return moveFocus(1);
            } else {
                if (keyEvent.isShiftPressed()) {
                    return moveFocus(-1);
                }
                return false;
            }
        }
        this.activeThumbIdx = -1;
        postInvalidate();
        return true;
    }

    private Boolean onKeyDownNoActiveThumb(int i, KeyEvent keyEvent) {
        if (i != 61) {
            if (i != 66) {
                if (i != 81) {
                    if (i == 69) {
                        moveFocus(-1);
                        return true;
                    } else if (i != 70) {
                        switch (i) {
                            case 21:
                                moveFocusInAbsoluteDirection(-1);
                                return true;
                            case 22:
                                moveFocusInAbsoluteDirection(1);
                                return true;
                            case 23:
                                break;
                            default:
                                return null;
                        }
                    }
                }
                moveFocus(1);
                return true;
            }
            this.activeThumbIdx = this.focusedThumbIdx;
            postInvalidate();
            return true;
        } else if (keyEvent.hasNoModifiers()) {
            return Boolean.valueOf(moveFocus(1));
        } else {
            if (keyEvent.isShiftPressed()) {
                return Boolean.valueOf(moveFocus(-1));
            }
            return false;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.isLongPress = false;
        return super.onKeyUp(i, keyEvent);
    }

    /* access modifiers changed from: package-private */
    public final boolean isRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    private boolean moveFocus(int i) {
        int i2 = this.focusedThumbIdx;
        int clamp = (int) MathUtils.clamp(((long) i2) + ((long) i), 0, (long) (this.values.size() - 1));
        this.focusedThumbIdx = clamp;
        if (clamp == i2) {
            return false;
        }
        if (this.activeThumbIdx != -1) {
            this.activeThumbIdx = clamp;
        }
        updateHaloHotspot();
        postInvalidate();
        return true;
    }

    private boolean moveFocusInAbsoluteDirection(int i) {
        if (isRtl()) {
            i = i == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i;
        }
        return moveFocus(i);
    }

    private Float calculateIncrementForKey(int i) {
        float calculateStepIncrement = this.isLongPress ? calculateStepIncrement(20) : calculateStepIncrement();
        if (i == 21) {
            if (!isRtl()) {
                calculateStepIncrement = -calculateStepIncrement;
            }
            return Float.valueOf(calculateStepIncrement);
        } else if (i == 22) {
            if (isRtl()) {
                calculateStepIncrement = -calculateStepIncrement;
            }
            return Float.valueOf(calculateStepIncrement);
        } else if (i == 69) {
            return Float.valueOf(-calculateStepIncrement);
        } else {
            if (i == 70 || i == 81) {
                return Float.valueOf(calculateStepIncrement);
            }
            return null;
        }
    }

    private float calculateStepIncrement() {
        float f = this.stepSize;
        if (f == 0.0f) {
            return 1.0f;
        }
        return f;
    }

    /* access modifiers changed from: private */
    public float calculateStepIncrement(int i) {
        float calculateStepIncrement = calculateStepIncrement();
        float f = (this.valueTo - this.valueFrom) / calculateStepIncrement;
        float f2 = (float) i;
        if (f <= f2) {
            return calculateStepIncrement;
        }
        return ((float) Math.round(f / f2)) * calculateStepIncrement;
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (!z) {
            this.activeThumbIdx = -1;
            this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
            return;
        }
        focusThumbOnFocusGained(i);
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
    }

    private void focusThumbOnFocusGained(int i) {
        if (i == 1) {
            moveFocus(Integer.MAX_VALUE);
        } else if (i == 2) {
            moveFocus(Integer.MIN_VALUE);
        } else if (i == 17) {
            moveFocusInAbsoluteDirection(Integer.MAX_VALUE);
        } else if (i == 66) {
            moveFocusInAbsoluteDirection(Integer.MIN_VALUE);
        }
    }

    /* access modifiers changed from: package-private */
    public final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.getAccessibilityFocusedVirtualViewId();
    }

    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.accessibilityHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    private void scheduleAccessibilityEventSender(int i) {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
        if (accessibilityEventSender2 == null) {
            this.accessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(accessibilityEventSender2);
        }
        this.accessibilityEventSender.setVirtualViewId(i);
        postDelayed(this.accessibilityEventSender, 200);
    }

    private class AccessibilityEventSender implements Runnable {
        int virtualViewId;

        private AccessibilityEventSender() {
            this.virtualViewId = -1;
        }

        /* access modifiers changed from: package-private */
        public void setVirtualViewId(int i) {
            this.virtualViewId = i;
        }

        public void run() {
            BaseSlider.this.accessibilityHelper.sendEventForVirtualView(this.virtualViewId, 4);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.valueFrom = this.valueFrom;
        sliderState.valueTo = this.valueTo;
        sliderState.values = new ArrayList<>(this.values);
        sliderState.stepSize = this.stepSize;
        sliderState.hasFocus = hasFocus();
        return sliderState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.valueFrom = sliderState.valueFrom;
        this.valueTo = sliderState.valueTo;
        setValuesInternal(sliderState.values);
        this.stepSize = sliderState.stepSize;
        if (sliderState.hasFocus) {
            requestFocus();
        }
    }

    static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new Parcelable.Creator<SliderState>() {
            public SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel);
            }

            public SliderState[] newArray(int i) {
                return new SliderState[i];
            }
        };
        boolean hasFocus;
        float stepSize;
        float valueFrom;
        float valueTo;
        ArrayList<Float> values;

        SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        private SliderState(Parcel parcel) {
            super(parcel);
            this.valueFrom = parcel.readFloat();
            this.valueTo = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.values = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.stepSize = parcel.readFloat();
            this.hasFocus = parcel.createBooleanArray()[0];
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.valueFrom);
            parcel.writeFloat(this.valueTo);
            parcel.writeList(this.values);
            parcel.writeFloat(this.stepSize);
            parcel.writeBooleanArray(new boolean[]{this.hasFocus});
        }
    }

    /* access modifiers changed from: package-private */
    public void updateBoundsForVirtualViewId(int i, Rect rect) {
        int normalizeValue = this.trackSidePadding + ((int) (normalizeValue(getValues().get(i).floatValue()) * ((float) this.trackWidth)));
        int calculateTrackCenter = calculateTrackCenter();
        int max = Math.max(this.thumbWidth / 2, this.minTouchTargetSize / 2);
        int max2 = Math.max(this.thumbHeight / 2, this.minTouchTargetSize / 2);
        rect.set(normalizeValue - max, calculateTrackCenter - max2, normalizeValue + max, calculateTrackCenter + max2);
    }

    private static class AccessibilityHelper extends ExploreByTouchHelper {
        private final BaseSlider<?, ?, ?> slider;
        final Rect virtualViewBounds = new Rect();

        AccessibilityHelper(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.slider = baseSlider;
        }

        /* access modifiers changed from: protected */
        public int getVirtualViewAt(float f, float f2) {
            for (int i = 0; i < this.slider.getValues().size(); i++) {
                this.slider.updateBoundsForVirtualViewId(i, this.virtualViewBounds);
                if (this.virtualViewBounds.contains((int) f, (int) f2)) {
                    return i;
                }
            }
            return -1;
        }

        /* access modifiers changed from: protected */
        public void getVisibleVirtualViews(List<Integer> list) {
            for (int i = 0; i < this.slider.getValues().size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }

        /* access modifiers changed from: protected */
        public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List<Float> values = this.slider.getValues();
            float floatValue = values.get(i).floatValue();
            float valueFrom = this.slider.getValueFrom();
            float valueTo = this.slider.getValueTo();
            if (this.slider.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
            accessibilityNodeInfoCompat.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, valueFrom, valueTo, floatValue));
            accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.slider.getContentDescription() != null) {
                sb.append(this.slider.getContentDescription()).append(",");
            }
            String access$400 = this.slider.formatValue(floatValue);
            String string = this.slider.getContext().getString(R.string.material_slider_value);
            if (values.size() > 1) {
                string = startOrEndDescription(i);
            }
            sb.append(String.format(Locale.US, "%s, %s", new Object[]{string, access$400}));
            accessibilityNodeInfoCompat.setContentDescription(sb.toString());
            this.slider.updateBoundsForVirtualViewId(i, this.virtualViewBounds);
            accessibilityNodeInfoCompat.setBoundsInParent(this.virtualViewBounds);
        }

        private String startOrEndDescription(int i) {
            if (i == this.slider.getValues().size() - 1) {
                return this.slider.getContext().getString(R.string.material_slider_range_end);
            }
            if (i == 0) {
                return this.slider.getContext().getString(R.string.material_slider_range_start);
            }
            return "";
        }

        /* access modifiers changed from: protected */
        public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
            if (!this.slider.isEnabled()) {
                return false;
            }
            if (i2 == 4096 || i2 == 8192) {
                float access$700 = this.slider.calculateStepIncrement(20);
                if (i2 == 8192) {
                    access$700 = -access$700;
                }
                if (this.slider.isRtl()) {
                    access$700 = -access$700;
                }
                if (!this.slider.snapThumbToValue(i, MathUtils.clamp(this.slider.getValues().get(i).floatValue() + access$700, this.slider.getValueFrom(), this.slider.getValueTo()))) {
                    return false;
                }
                this.slider.updateHaloHotspot();
                this.slider.postInvalidate();
                invalidateVirtualView(i);
                return true;
            }
            if (i2 == 16908349 && bundle != null && bundle.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE)) {
                if (this.slider.snapThumbToValue(i, bundle.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE))) {
                    this.slider.updateHaloHotspot();
                    this.slider.postInvalidate();
                    invalidateVirtualView(i);
                    return true;
                }
            }
            return false;
        }
    }
}
