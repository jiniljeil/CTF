package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.util.Locale;

public final class BadgeState {
    private static final String BADGE_RESOURCE_TAG = "badge";
    final float badgeHeight;
    final float badgeRadius;
    final float badgeWidth;
    final float badgeWithTextHeight;
    final float badgeWithTextRadius;
    final float badgeWithTextWidth;
    private final State currentState;
    final int horizontalInset;
    final int horizontalInsetWithText;
    int offsetAlignmentMode;
    private final State overridingState;

    BadgeState(Context context, int i, int i2, int i3, State state) {
        CharSequence charSequence;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        boolean z;
        State state2 = new State();
        this.currentState = state2;
        state = state == null ? new State() : state;
        if (i != 0) {
            int unused = state.badgeResId = i;
        }
        TypedArray generateTypedArray = generateTypedArray(context, state.badgeResId, i2, i3);
        Resources resources = context.getResources();
        this.badgeRadius = (float) generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeRadius, -1);
        this.horizontalInset = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_horizontal_edge_offset);
        this.horizontalInsetWithText = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.badgeWithTextRadius = (float) generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeWithTextRadius, -1);
        this.badgeWidth = generateTypedArray.getDimension(R.styleable.Badge_badgeWidth, resources.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextWidth = generateTypedArray.getDimension(R.styleable.Badge_badgeWithTextWidth, resources.getDimension(R.dimen.m3_badge_with_text_size));
        this.badgeHeight = generateTypedArray.getDimension(R.styleable.Badge_badgeHeight, resources.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextHeight = generateTypedArray.getDimension(R.styleable.Badge_badgeWithTextHeight, resources.getDimension(R.dimen.m3_badge_with_text_size));
        boolean z2 = true;
        this.offsetAlignmentMode = generateTypedArray.getInt(R.styleable.Badge_offsetAlignmentMode, 1);
        int unused2 = state2.alpha = state.alpha == -2 ? 255 : state.alpha;
        if (state.number != -2) {
            int unused3 = state2.number = state.number;
        } else if (generateTypedArray.hasValue(R.styleable.Badge_number)) {
            int unused4 = state2.number = generateTypedArray.getInt(R.styleable.Badge_number, 0);
        } else {
            int unused5 = state2.number = -1;
        }
        if (state.text != null) {
            String unused6 = state2.text = state.text;
        } else if (generateTypedArray.hasValue(R.styleable.Badge_badgeText)) {
            String unused7 = state2.text = generateTypedArray.getString(R.styleable.Badge_badgeText);
        }
        CharSequence unused8 = state2.contentDescriptionForText = state.contentDescriptionForText;
        if (state.contentDescriptionNumberless == null) {
            charSequence = context.getString(R.string.mtrl_badge_numberless_content_description);
        } else {
            charSequence = state.contentDescriptionNumberless;
        }
        CharSequence unused9 = state2.contentDescriptionNumberless = charSequence;
        if (state.contentDescriptionQuantityStrings == 0) {
            i4 = R.plurals.mtrl_badge_content_description;
        } else {
            i4 = state.contentDescriptionQuantityStrings;
        }
        int unused10 = state2.contentDescriptionQuantityStrings = i4;
        if (state.contentDescriptionExceedsMaxBadgeNumberRes == 0) {
            i5 = R.string.mtrl_exceed_max_badge_number_content_description;
        } else {
            i5 = state.contentDescriptionExceedsMaxBadgeNumberRes;
        }
        int unused11 = state2.contentDescriptionExceedsMaxBadgeNumberRes = i5;
        if (state.isVisible != null && !state.isVisible.booleanValue()) {
            z2 = false;
        }
        Boolean unused12 = state2.isVisible = Boolean.valueOf(z2);
        if (state.maxCharacterCount == -2) {
            i6 = generateTypedArray.getInt(R.styleable.Badge_maxCharacterCount, -2);
        } else {
            i6 = state.maxCharacterCount;
        }
        int unused13 = state2.maxCharacterCount = i6;
        if (state.maxNumber == -2) {
            i7 = generateTypedArray.getInt(R.styleable.Badge_maxNumber, -2);
        } else {
            i7 = state.maxNumber;
        }
        int unused14 = state2.maxNumber = i7;
        if (state.badgeShapeAppearanceResId == null) {
            i8 = generateTypedArray.getResourceId(R.styleable.Badge_badgeShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
        } else {
            i8 = state.badgeShapeAppearanceResId.intValue();
        }
        Integer unused15 = state2.badgeShapeAppearanceResId = Integer.valueOf(i8);
        if (state.badgeShapeAppearanceOverlayResId == null) {
            i9 = generateTypedArray.getResourceId(R.styleable.Badge_badgeShapeAppearanceOverlay, 0);
        } else {
            i9 = state.badgeShapeAppearanceOverlayResId.intValue();
        }
        Integer unused16 = state2.badgeShapeAppearanceOverlayResId = Integer.valueOf(i9);
        if (state.badgeWithTextShapeAppearanceResId == null) {
            i10 = generateTypedArray.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
        } else {
            i10 = state.badgeWithTextShapeAppearanceResId.intValue();
        }
        Integer unused17 = state2.badgeWithTextShapeAppearanceResId = Integer.valueOf(i10);
        if (state.badgeWithTextShapeAppearanceOverlayResId == null) {
            i11 = generateTypedArray.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0);
        } else {
            i11 = state.badgeWithTextShapeAppearanceOverlayResId.intValue();
        }
        Integer unused18 = state2.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i11);
        if (state.backgroundColor == null) {
            i12 = readColorFromAttributes(context, generateTypedArray, R.styleable.Badge_backgroundColor);
        } else {
            i12 = state.backgroundColor.intValue();
        }
        Integer unused19 = state2.backgroundColor = Integer.valueOf(i12);
        if (state.badgeTextAppearanceResId == null) {
            i13 = generateTypedArray.getResourceId(R.styleable.Badge_badgeTextAppearance, R.style.TextAppearance_MaterialComponents_Badge);
        } else {
            i13 = state.badgeTextAppearanceResId.intValue();
        }
        Integer unused20 = state2.badgeTextAppearanceResId = Integer.valueOf(i13);
        if (state.badgeTextColor != null) {
            Integer unused21 = state2.badgeTextColor = state.badgeTextColor;
        } else if (generateTypedArray.hasValue(R.styleable.Badge_badgeTextColor)) {
            Integer unused22 = state2.badgeTextColor = Integer.valueOf(readColorFromAttributes(context, generateTypedArray, R.styleable.Badge_badgeTextColor));
        } else {
            Integer unused23 = state2.badgeTextColor = Integer.valueOf(new TextAppearance(context, state2.badgeTextAppearanceResId.intValue()).getTextColor().getDefaultColor());
        }
        if (state.badgeGravity == null) {
            i14 = generateTypedArray.getInt(R.styleable.Badge_badgeGravity, 8388661);
        } else {
            i14 = state.badgeGravity.intValue();
        }
        Integer unused24 = state2.badgeGravity = Integer.valueOf(i14);
        if (state.badgeHorizontalPadding == null) {
            i15 = generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding));
        } else {
            i15 = state.badgeHorizontalPadding.intValue();
        }
        Integer unused25 = state2.badgeHorizontalPadding = Integer.valueOf(i15);
        if (state.badgeVerticalPadding == null) {
            i16 = generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeVerticalPadding, resources.getDimensionPixelSize(R.dimen.m3_badge_with_text_vertical_padding));
        } else {
            i16 = state.badgeVerticalPadding.intValue();
        }
        Integer unused26 = state2.badgeVerticalPadding = Integer.valueOf(i16);
        if (state.horizontalOffsetWithoutText == null) {
            i17 = generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0);
        } else {
            i17 = state.horizontalOffsetWithoutText.intValue();
        }
        Integer unused27 = state2.horizontalOffsetWithoutText = Integer.valueOf(i17);
        if (state.verticalOffsetWithoutText == null) {
            i18 = generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0);
        } else {
            i18 = state.verticalOffsetWithoutText.intValue();
        }
        Integer unused28 = state2.verticalOffsetWithoutText = Integer.valueOf(i18);
        if (state.horizontalOffsetWithText == null) {
            i19 = generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_horizontalOffsetWithText, state2.horizontalOffsetWithoutText.intValue());
        } else {
            i19 = state.horizontalOffsetWithText.intValue();
        }
        Integer unused29 = state2.horizontalOffsetWithText = Integer.valueOf(i19);
        if (state.verticalOffsetWithText == null) {
            i20 = generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_verticalOffsetWithText, state2.verticalOffsetWithoutText.intValue());
        } else {
            i20 = state.verticalOffsetWithText.intValue();
        }
        Integer unused30 = state2.verticalOffsetWithText = Integer.valueOf(i20);
        if (state.largeFontVerticalOffsetAdjustment == null) {
            i21 = generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_largeFontVerticalOffsetAdjustment, 0);
        } else {
            i21 = state.largeFontVerticalOffsetAdjustment.intValue();
        }
        Integer unused31 = state2.largeFontVerticalOffsetAdjustment = Integer.valueOf(i21);
        Integer unused32 = state2.additionalHorizontalOffset = Integer.valueOf(state.additionalHorizontalOffset == null ? 0 : state.additionalHorizontalOffset.intValue());
        Integer unused33 = state2.additionalVerticalOffset = Integer.valueOf(state.additionalVerticalOffset == null ? 0 : state.additionalVerticalOffset.intValue());
        if (state.autoAdjustToWithinGrandparentBounds == null) {
            z = generateTypedArray.getBoolean(R.styleable.Badge_autoAdjustToWithinGrandparentBounds, false);
        } else {
            z = state.autoAdjustToWithinGrandparentBounds.booleanValue();
        }
        Boolean unused34 = state2.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z);
        generateTypedArray.recycle();
        if (state.numberLocale == null) {
            Locale unused35 = state2.numberLocale = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            Locale unused36 = state2.numberLocale = state.numberLocale;
        }
        this.overridingState = state;
    }

    private TypedArray generateTypedArray(Context context, int i, int i2, int i3) {
        AttributeSet attributeSet;
        int i4;
        if (i != 0) {
            AttributeSet parseDrawableXml = DrawableUtils.parseDrawableXml(context, i, BADGE_RESOURCE_TAG);
            i4 = parseDrawableXml.getStyleAttribute();
            attributeSet = parseDrawableXml;
        } else {
            attributeSet = null;
            i4 = 0;
        }
        return ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Badge, i2, i4 == 0 ? i3 : i4, new int[0]);
    }

    /* access modifiers changed from: package-private */
    public State getOverridingState() {
        return this.overridingState;
    }

    /* access modifiers changed from: package-private */
    public boolean isVisible() {
        return this.currentState.isVisible.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public void setVisible(boolean z) {
        Boolean unused = this.overridingState.isVisible = Boolean.valueOf(z);
        Boolean unused2 = this.currentState.isVisible = Boolean.valueOf(z);
    }

    /* access modifiers changed from: package-private */
    public boolean hasNumber() {
        return this.currentState.number != -1;
    }

    /* access modifiers changed from: package-private */
    public int getNumber() {
        return this.currentState.number;
    }

    /* access modifiers changed from: package-private */
    public void setNumber(int i) {
        int unused = this.overridingState.number = i;
        int unused2 = this.currentState.number = i;
    }

    /* access modifiers changed from: package-private */
    public void clearNumber() {
        setNumber(-1);
    }

    /* access modifiers changed from: package-private */
    public boolean hasText() {
        return this.currentState.text != null;
    }

    /* access modifiers changed from: package-private */
    public String getText() {
        return this.currentState.text;
    }

    /* access modifiers changed from: package-private */
    public void setText(String str) {
        String unused = this.overridingState.text = str;
        String unused2 = this.currentState.text = str;
    }

    /* access modifiers changed from: package-private */
    public void clearText() {
        setText((String) null);
    }

    /* access modifiers changed from: package-private */
    public int getAlpha() {
        return this.currentState.alpha;
    }

    /* access modifiers changed from: package-private */
    public void setAlpha(int i) {
        int unused = this.overridingState.alpha = i;
        int unused2 = this.currentState.alpha = i;
    }

    /* access modifiers changed from: package-private */
    public int getMaxCharacterCount() {
        return this.currentState.maxCharacterCount;
    }

    /* access modifiers changed from: package-private */
    public void setMaxCharacterCount(int i) {
        int unused = this.overridingState.maxCharacterCount = i;
        int unused2 = this.currentState.maxCharacterCount = i;
    }

    /* access modifiers changed from: package-private */
    public int getMaxNumber() {
        return this.currentState.maxNumber;
    }

    /* access modifiers changed from: package-private */
    public void setMaxNumber(int i) {
        int unused = this.overridingState.maxNumber = i;
        int unused2 = this.currentState.maxNumber = i;
    }

    /* access modifiers changed from: package-private */
    public int getBackgroundColor() {
        return this.currentState.backgroundColor.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundColor(int i) {
        Integer unused = this.overridingState.backgroundColor = Integer.valueOf(i);
        Integer unused2 = this.currentState.backgroundColor = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeTextColor() {
        return this.currentState.badgeTextColor.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeTextColor(int i) {
        Integer unused = this.overridingState.badgeTextColor = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeTextColor = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getTextAppearanceResId() {
        return this.currentState.badgeTextAppearanceResId.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setTextAppearanceResId(int i) {
        Integer unused = this.overridingState.badgeTextAppearanceResId = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeTextAppearanceResId = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeShapeAppearanceResId() {
        return this.currentState.badgeShapeAppearanceResId.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeShapeAppearanceResId(int i) {
        Integer unused = this.overridingState.badgeShapeAppearanceResId = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeShapeAppearanceResId = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeShapeAppearanceOverlayResId() {
        return this.currentState.badgeShapeAppearanceOverlayResId.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeShapeAppearanceOverlayResId(int i) {
        Integer unused = this.overridingState.badgeShapeAppearanceOverlayResId = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeShapeAppearanceOverlayResId = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeWithTextShapeAppearanceResId() {
        return this.currentState.badgeWithTextShapeAppearanceResId.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeWithTextShapeAppearanceResId(int i) {
        Integer unused = this.overridingState.badgeWithTextShapeAppearanceResId = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeWithTextShapeAppearanceResId = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeWithTextShapeAppearanceOverlayResId() {
        return this.currentState.badgeWithTextShapeAppearanceOverlayResId.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeWithTextShapeAppearanceOverlayResId(int i) {
        Integer unused = this.overridingState.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeGravity() {
        return this.currentState.badgeGravity.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeGravity(int i) {
        Integer unused = this.overridingState.badgeGravity = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeGravity = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeHorizontalPadding() {
        return this.currentState.badgeHorizontalPadding.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeHorizontalPadding(int i) {
        Integer unused = this.overridingState.badgeHorizontalPadding = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeHorizontalPadding = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getBadgeVerticalPadding() {
        return this.currentState.badgeVerticalPadding.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setBadgeVerticalPadding(int i) {
        Integer unused = this.overridingState.badgeVerticalPadding = Integer.valueOf(i);
        Integer unused2 = this.currentState.badgeVerticalPadding = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getHorizontalOffsetWithoutText() {
        return this.currentState.horizontalOffsetWithoutText.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalOffsetWithoutText(int i) {
        Integer unused = this.overridingState.horizontalOffsetWithoutText = Integer.valueOf(i);
        Integer unused2 = this.currentState.horizontalOffsetWithoutText = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getVerticalOffsetWithoutText() {
        return this.currentState.verticalOffsetWithoutText.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setVerticalOffsetWithoutText(int i) {
        Integer unused = this.overridingState.verticalOffsetWithoutText = Integer.valueOf(i);
        Integer unused2 = this.currentState.verticalOffsetWithoutText = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getHorizontalOffsetWithText() {
        return this.currentState.horizontalOffsetWithText.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalOffsetWithText(int i) {
        Integer unused = this.overridingState.horizontalOffsetWithText = Integer.valueOf(i);
        Integer unused2 = this.currentState.horizontalOffsetWithText = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getVerticalOffsetWithText() {
        return this.currentState.verticalOffsetWithText.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setVerticalOffsetWithText(int i) {
        Integer unused = this.overridingState.verticalOffsetWithText = Integer.valueOf(i);
        Integer unused2 = this.currentState.verticalOffsetWithText = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getLargeFontVerticalOffsetAdjustment() {
        return this.currentState.largeFontVerticalOffsetAdjustment.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setLargeFontVerticalOffsetAdjustment(int i) {
        Integer unused = this.overridingState.largeFontVerticalOffsetAdjustment = Integer.valueOf(i);
        Integer unused2 = this.currentState.largeFontVerticalOffsetAdjustment = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getAdditionalHorizontalOffset() {
        return this.currentState.additionalHorizontalOffset.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setAdditionalHorizontalOffset(int i) {
        Integer unused = this.overridingState.additionalHorizontalOffset = Integer.valueOf(i);
        Integer unused2 = this.currentState.additionalHorizontalOffset = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public int getAdditionalVerticalOffset() {
        return this.currentState.additionalVerticalOffset.intValue();
    }

    /* access modifiers changed from: package-private */
    public void setAdditionalVerticalOffset(int i) {
        Integer unused = this.overridingState.additionalVerticalOffset = Integer.valueOf(i);
        Integer unused2 = this.currentState.additionalVerticalOffset = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public CharSequence getContentDescriptionForText() {
        return this.currentState.contentDescriptionForText;
    }

    /* access modifiers changed from: package-private */
    public void setContentDescriptionForText(CharSequence charSequence) {
        CharSequence unused = this.overridingState.contentDescriptionForText = charSequence;
        CharSequence unused2 = this.currentState.contentDescriptionForText = charSequence;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getContentDescriptionNumberless() {
        return this.currentState.contentDescriptionNumberless;
    }

    /* access modifiers changed from: package-private */
    public void setContentDescriptionNumberless(CharSequence charSequence) {
        CharSequence unused = this.overridingState.contentDescriptionNumberless = charSequence;
        CharSequence unused2 = this.currentState.contentDescriptionNumberless = charSequence;
    }

    /* access modifiers changed from: package-private */
    public int getContentDescriptionQuantityStrings() {
        return this.currentState.contentDescriptionQuantityStrings;
    }

    /* access modifiers changed from: package-private */
    public void setContentDescriptionQuantityStringsResource(int i) {
        int unused = this.overridingState.contentDescriptionQuantityStrings = i;
        int unused2 = this.currentState.contentDescriptionQuantityStrings = i;
    }

    /* access modifiers changed from: package-private */
    public int getContentDescriptionExceedsMaxBadgeNumberStringResource() {
        return this.currentState.contentDescriptionExceedsMaxBadgeNumberRes;
    }

    /* access modifiers changed from: package-private */
    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(int i) {
        int unused = this.overridingState.contentDescriptionExceedsMaxBadgeNumberRes = i;
        int unused2 = this.currentState.contentDescriptionExceedsMaxBadgeNumberRes = i;
    }

    /* access modifiers changed from: package-private */
    public Locale getNumberLocale() {
        return this.currentState.numberLocale;
    }

    /* access modifiers changed from: package-private */
    public void setNumberLocale(Locale locale) {
        Locale unused = this.overridingState.numberLocale = locale;
        Locale unused2 = this.currentState.numberLocale = locale;
    }

    /* access modifiers changed from: package-private */
    public boolean isAutoAdjustedToGrandparentBounds() {
        return this.currentState.autoAdjustToWithinGrandparentBounds.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public void setAutoAdjustToGrandparentBounds(boolean z) {
        Boolean unused = this.overridingState.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z);
        Boolean unused2 = this.currentState.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z);
    }

    private static int readColorFromAttributes(Context context, TypedArray typedArray, int i) {
        return MaterialResources.getColorStateList(context, typedArray, i).getDefaultColor();
    }

    public static final class State implements Parcelable {
        private static final int BADGE_NUMBER_NONE = -1;
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
            public State createFromParcel(Parcel parcel) {
                return new State(parcel);
            }

            public State[] newArray(int i) {
                return new State[i];
            }
        };
        private static final int NOT_SET = -2;
        /* access modifiers changed from: private */
        public Integer additionalHorizontalOffset;
        /* access modifiers changed from: private */
        public Integer additionalVerticalOffset;
        /* access modifiers changed from: private */
        public int alpha = 255;
        /* access modifiers changed from: private */
        public Boolean autoAdjustToWithinGrandparentBounds;
        /* access modifiers changed from: private */
        public Integer backgroundColor;
        /* access modifiers changed from: private */
        public Integer badgeGravity;
        /* access modifiers changed from: private */
        public Integer badgeHorizontalPadding;
        /* access modifiers changed from: private */
        public int badgeResId;
        /* access modifiers changed from: private */
        public Integer badgeShapeAppearanceOverlayResId;
        /* access modifiers changed from: private */
        public Integer badgeShapeAppearanceResId;
        /* access modifiers changed from: private */
        public Integer badgeTextAppearanceResId;
        /* access modifiers changed from: private */
        public Integer badgeTextColor;
        /* access modifiers changed from: private */
        public Integer badgeVerticalPadding;
        /* access modifiers changed from: private */
        public Integer badgeWithTextShapeAppearanceOverlayResId;
        /* access modifiers changed from: private */
        public Integer badgeWithTextShapeAppearanceResId;
        /* access modifiers changed from: private */
        public int contentDescriptionExceedsMaxBadgeNumberRes;
        /* access modifiers changed from: private */
        public CharSequence contentDescriptionForText;
        /* access modifiers changed from: private */
        public CharSequence contentDescriptionNumberless;
        /* access modifiers changed from: private */
        public int contentDescriptionQuantityStrings;
        /* access modifiers changed from: private */
        public Integer horizontalOffsetWithText;
        /* access modifiers changed from: private */
        public Integer horizontalOffsetWithoutText;
        /* access modifiers changed from: private */
        public Boolean isVisible = true;
        /* access modifiers changed from: private */
        public Integer largeFontVerticalOffsetAdjustment;
        /* access modifiers changed from: private */
        public int maxCharacterCount = -2;
        /* access modifiers changed from: private */
        public int maxNumber = -2;
        /* access modifiers changed from: private */
        public int number = -2;
        /* access modifiers changed from: private */
        public Locale numberLocale;
        /* access modifiers changed from: private */
        public String text;
        /* access modifiers changed from: private */
        public Integer verticalOffsetWithText;
        /* access modifiers changed from: private */
        public Integer verticalOffsetWithoutText;

        public int describeContents() {
            return 0;
        }

        public State() {
        }

        State(Parcel parcel) {
            this.badgeResId = parcel.readInt();
            this.backgroundColor = (Integer) parcel.readSerializable();
            this.badgeTextColor = (Integer) parcel.readSerializable();
            this.badgeTextAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.alpha = parcel.readInt();
            this.text = parcel.readString();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.maxNumber = parcel.readInt();
            this.contentDescriptionForText = parcel.readString();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
            this.badgeGravity = (Integer) parcel.readSerializable();
            this.badgeHorizontalPadding = (Integer) parcel.readSerializable();
            this.badgeVerticalPadding = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithText = (Integer) parcel.readSerializable();
            this.largeFontVerticalOffsetAdjustment = (Integer) parcel.readSerializable();
            this.additionalHorizontalOffset = (Integer) parcel.readSerializable();
            this.additionalVerticalOffset = (Integer) parcel.readSerializable();
            this.isVisible = (Boolean) parcel.readSerializable();
            this.numberLocale = (Locale) parcel.readSerializable();
            this.autoAdjustToWithinGrandparentBounds = (Boolean) parcel.readSerializable();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.badgeResId);
            parcel.writeSerializable(this.backgroundColor);
            parcel.writeSerializable(this.badgeTextColor);
            parcel.writeSerializable(this.badgeTextAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceOverlayResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceOverlayResId);
            parcel.writeInt(this.alpha);
            parcel.writeString(this.text);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeInt(this.maxNumber);
            CharSequence charSequence = this.contentDescriptionForText;
            String str = null;
            parcel.writeString(charSequence != null ? charSequence.toString() : null);
            CharSequence charSequence2 = this.contentDescriptionNumberless;
            if (charSequence2 != null) {
                str = charSequence2.toString();
            }
            parcel.writeString(str);
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeSerializable(this.badgeGravity);
            parcel.writeSerializable(this.badgeHorizontalPadding);
            parcel.writeSerializable(this.badgeVerticalPadding);
            parcel.writeSerializable(this.horizontalOffsetWithoutText);
            parcel.writeSerializable(this.verticalOffsetWithoutText);
            parcel.writeSerializable(this.horizontalOffsetWithText);
            parcel.writeSerializable(this.verticalOffsetWithText);
            parcel.writeSerializable(this.largeFontVerticalOffsetAdjustment);
            parcel.writeSerializable(this.additionalHorizontalOffset);
            parcel.writeSerializable(this.additionalVerticalOffset);
            parcel.writeSerializable(this.isVisible);
            parcel.writeSerializable(this.numberLocale);
            parcel.writeSerializable(this.autoAdjustToWithinGrandparentBounds);
        }
    }
}
