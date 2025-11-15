package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

class AppCompatTextHelper {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int TEXT_FONT_WEIGHT_UNSPECIFIED = -1;
    private boolean mAsyncFontPending;
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mFontWeight = -1;
    private int mStyle = 0;
    private final TextView mView;

    AppCompatTextHelper(TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x029c  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:134:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r17, int r18) {
        /*
            r16 = this;
            r7 = r16
            r8 = r17
            r9 = r18
            android.widget.TextView r0 = r7.mView
            android.content.Context r10 = r0.getContext()
            androidx.appcompat.widget.AppCompatDrawableManager r11 = androidx.appcompat.widget.AppCompatDrawableManager.get()
            int[] r0 = androidx.appcompat.R.styleable.AppCompatTextHelper
            r12 = 0
            androidx.appcompat.widget.TintTypedArray r13 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r10, r8, r0, r9, r12)
            android.widget.TextView r0 = r7.mView
            android.content.Context r1 = r0.getContext()
            int[] r2 = androidx.appcompat.R.styleable.AppCompatTextHelper
            android.content.res.TypedArray r4 = r13.getWrappedTypeArray()
            r6 = 0
            r3 = r17
            r5 = r18
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r0, r1, r2, r3, r4, r5, r6)
            int r0 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_textAppearance
            r14 = -1
            int r0 = r13.getResourceId(r0, r14)
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x0046
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableLeftTint = r1
        L_0x0046:
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x005a
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableTopTint = r1
        L_0x005a:
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x006e
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableRightTint = r1
        L_0x006e:
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x0082
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableBottomTint = r1
        L_0x0082:
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x0096
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableStartTint = r1
        L_0x0096:
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd
            boolean r1 = r13.hasValue(r1)
            if (r1 == 0) goto L_0x00aa
            int r1 = androidx.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd
            int r1 = r13.getResourceId(r1, r12)
            androidx.appcompat.widget.TintInfo r1 = createTintInfo(r10, r11, r1)
            r7.mDrawableEndTint = r1
        L_0x00aa:
            r13.recycle()
            android.widget.TextView r1 = r7.mView
            android.text.method.TransformationMethod r1 = r1.getTransformationMethod()
            boolean r1 = r1 instanceof android.text.method.PasswordTransformationMethod
            r2 = 26
            r13 = 0
            if (r0 == r14) goto L_0x00ff
            int[] r4 = androidx.appcompat.R.styleable.TextAppearance
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r10, (int) r0, (int[]) r4)
            if (r1 != 0) goto L_0x00d2
            int r4 = androidx.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r4 = r0.hasValue(r4)
            if (r4 == 0) goto L_0x00d2
            int r4 = androidx.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r4 = r0.getBoolean(r4, r12)
            r5 = 1
            goto L_0x00d4
        L_0x00d2:
            r4 = r12
            r5 = r4
        L_0x00d4:
            r7.updateTypefaceAndStyle(r10, r0)
            int r6 = androidx.appcompat.R.styleable.TextAppearance_textLocale
            boolean r6 = r0.hasValue(r6)
            if (r6 == 0) goto L_0x00e6
            int r6 = androidx.appcompat.R.styleable.TextAppearance_textLocale
            java.lang.String r6 = r0.getString(r6)
            goto L_0x00e7
        L_0x00e6:
            r6 = r13
        L_0x00e7:
            int r15 = android.os.Build.VERSION.SDK_INT
            if (r15 < r2) goto L_0x00fa
            int r15 = androidx.appcompat.R.styleable.TextAppearance_fontVariationSettings
            boolean r15 = r0.hasValue(r15)
            if (r15 == 0) goto L_0x00fa
            int r15 = androidx.appcompat.R.styleable.TextAppearance_fontVariationSettings
            java.lang.String r15 = r0.getString(r15)
            goto L_0x00fb
        L_0x00fa:
            r15 = r13
        L_0x00fb:
            r0.recycle()
            goto L_0x0103
        L_0x00ff:
            r4 = r12
            r5 = r4
            r6 = r13
            r15 = r6
        L_0x0103:
            int[] r0 = androidx.appcompat.R.styleable.TextAppearance
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r10, r8, r0, r9, r12)
            if (r1 != 0) goto L_0x011b
            int r3 = androidx.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r3 = r0.hasValue(r3)
            if (r3 == 0) goto L_0x011b
            int r3 = androidx.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r4 = r0.getBoolean(r3, r12)
            r3 = 1
            goto L_0x011c
        L_0x011b:
            r3 = r5
        L_0x011c:
            int r5 = androidx.appcompat.R.styleable.TextAppearance_textLocale
            boolean r5 = r0.hasValue(r5)
            if (r5 == 0) goto L_0x012a
            int r5 = androidx.appcompat.R.styleable.TextAppearance_textLocale
            java.lang.String r6 = r0.getString(r5)
        L_0x012a:
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r5 < r2) goto L_0x013c
            int r2 = androidx.appcompat.R.styleable.TextAppearance_fontVariationSettings
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x013c
            int r2 = androidx.appcompat.R.styleable.TextAppearance_fontVariationSettings
            java.lang.String r15 = r0.getString(r2)
        L_0x013c:
            int r2 = android.os.Build.VERSION.SDK_INT
            r5 = 28
            if (r2 < r5) goto L_0x0158
            int r2 = androidx.appcompat.R.styleable.TextAppearance_android_textSize
            boolean r2 = r0.hasValue(r2)
            if (r2 == 0) goto L_0x0158
            int r2 = androidx.appcompat.R.styleable.TextAppearance_android_textSize
            int r2 = r0.getDimensionPixelSize(r2, r14)
            if (r2 != 0) goto L_0x0158
            android.widget.TextView r2 = r7.mView
            r5 = 0
            r2.setTextSize(r12, r5)
        L_0x0158:
            r7.updateTypefaceAndStyle(r10, r0)
            r0.recycle()
            if (r1 != 0) goto L_0x0165
            if (r3 == 0) goto L_0x0165
            r7.setAllCaps(r4)
        L_0x0165:
            android.graphics.Typeface r0 = r7.mFontTypeface
            if (r0 == 0) goto L_0x017a
            int r1 = r7.mFontWeight
            if (r1 != r14) goto L_0x0175
            android.widget.TextView r1 = r7.mView
            int r2 = r7.mStyle
            r1.setTypeface(r0, r2)
            goto L_0x017a
        L_0x0175:
            android.widget.TextView r1 = r7.mView
            r1.setTypeface(r0)
        L_0x017a:
            if (r15 == 0) goto L_0x0181
            android.widget.TextView r0 = r7.mView
            androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.setFontVariationSettings(r0, r15)
        L_0x0181:
            if (r6 == 0) goto L_0x018c
            android.widget.TextView r0 = r7.mView
            android.os.LocaleList r1 = androidx.appcompat.widget.AppCompatTextHelper.Api24Impl.forLanguageTags(r6)
            androidx.appcompat.widget.AppCompatTextHelper.Api24Impl.setTextLocales(r0, r1)
        L_0x018c:
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r0 = r7.mAutoSizeTextHelper
            r0.loadFromAttributes(r8, r9)
            boolean r0 = androidx.appcompat.widget.ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r0 == 0) goto L_0x01d0
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r0 = r7.mAutoSizeTextHelper
            int r0 = r0.getAutoSizeTextType()
            if (r0 == 0) goto L_0x01d0
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r0 = r7.mAutoSizeTextHelper
            int[] r0 = r0.getAutoSizeTextAvailableSizes()
            int r1 = r0.length
            if (r1 <= 0) goto L_0x01d0
            android.widget.TextView r1 = r7.mView
            int r1 = androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.getAutoSizeStepGranularity(r1)
            float r1 = (float) r1
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r1 == 0) goto L_0x01cb
            android.widget.TextView r0 = r7.mView
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r1 = r7.mAutoSizeTextHelper
            int r1 = r1.getAutoSizeMinTextSize()
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r2 = r7.mAutoSizeTextHelper
            int r2 = r2.getAutoSizeMaxTextSize()
            androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper r3 = r7.mAutoSizeTextHelper
            int r3 = r3.getAutoSizeStepGranularity()
            androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.setAutoSizeTextTypeUniformWithConfiguration(r0, r1, r2, r3, r12)
            goto L_0x01d0
        L_0x01cb:
            android.widget.TextView r1 = r7.mView
            androidx.appcompat.widget.AppCompatTextHelper.Api26Impl.setAutoSizeTextTypeUniformWithPresetSizes(r1, r0, r12)
        L_0x01d0:
            int[] r0 = androidx.appcompat.R.styleable.AppCompatTextView
            androidx.appcompat.widget.TintTypedArray r8 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r10, (android.util.AttributeSet) r8, (int[]) r0)
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableLeftCompat
            int r0 = r8.getResourceId(r0, r14)
            if (r0 == r14) goto L_0x01e4
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r1 = r0
            goto L_0x01e5
        L_0x01e4:
            r1 = r13
        L_0x01e5:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableTopCompat
            int r0 = r8.getResourceId(r0, r14)
            if (r0 == r14) goto L_0x01f3
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r2 = r0
            goto L_0x01f4
        L_0x01f3:
            r2 = r13
        L_0x01f4:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableRightCompat
            int r0 = r8.getResourceId(r0, r14)
            if (r0 == r14) goto L_0x0202
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r3 = r0
            goto L_0x0203
        L_0x0202:
            r3 = r13
        L_0x0203:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableBottomCompat
            int r0 = r8.getResourceId(r0, r14)
            if (r0 == r14) goto L_0x0211
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r4 = r0
            goto L_0x0212
        L_0x0211:
            r4 = r13
        L_0x0212:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableStartCompat
            int r0 = r8.getResourceId(r0, r14)
            if (r0 == r14) goto L_0x0220
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r5 = r0
            goto L_0x0221
        L_0x0220:
            r5 = r13
        L_0x0221:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableEndCompat
            int r0 = r8.getResourceId(r0, r14)
            if (r0 == r14) goto L_0x022f
            android.graphics.drawable.Drawable r0 = r11.getDrawable(r10, r0)
            r6 = r0
            goto L_0x0230
        L_0x022f:
            r6 = r13
        L_0x0230:
            r0 = r16
            r0.setCompoundDrawables(r1, r2, r3, r4, r5, r6)
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableTint
            boolean r0 = r8.hasValue(r0)
            if (r0 == 0) goto L_0x0248
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableTint
            android.content.res.ColorStateList r0 = r8.getColorStateList(r0)
            android.widget.TextView r1 = r7.mView
            androidx.core.widget.TextViewCompat.setCompoundDrawableTintList(r1, r0)
        L_0x0248:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableTintMode
            boolean r0 = r8.hasValue(r0)
            if (r0 == 0) goto L_0x025f
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_drawableTintMode
            int r0 = r8.getInt(r0, r14)
            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r0, r13)
            android.widget.TextView r1 = r7.mView
            androidx.core.widget.TextViewCompat.setCompoundDrawableTintMode(r1, r0)
        L_0x025f:
            int r0 = androidx.appcompat.R.styleable.AppCompatTextView_firstBaselineToTopHeight
            int r0 = r8.getDimensionPixelSize(r0, r14)
            int r1 = androidx.appcompat.R.styleable.AppCompatTextView_lastBaselineToBottomHeight
            int r1 = r8.getDimensionPixelSize(r1, r14)
            int r2 = androidx.appcompat.R.styleable.AppCompatTextView_lineHeight
            boolean r2 = r8.hasValue(r2)
            if (r2 == 0) goto L_0x0295
            int r2 = androidx.appcompat.R.styleable.AppCompatTextView_lineHeight
            android.util.TypedValue r2 = r8.peekValue(r2)
            if (r2 == 0) goto L_0x028d
            int r3 = r2.type
            r4 = 5
            if (r3 != r4) goto L_0x028d
            int r3 = r2.data
            int r3 = androidx.core.util.TypedValueCompat.getUnitFromComplexDimension(r3)
            int r2 = r2.data
            float r2 = android.util.TypedValue.complexToFloat(r2)
            goto L_0x0297
        L_0x028d:
            int r2 = androidx.appcompat.R.styleable.AppCompatTextView_lineHeight
            int r2 = r8.getDimensionPixelSize(r2, r14)
            float r2 = (float) r2
            goto L_0x0296
        L_0x0295:
            r2 = r9
        L_0x0296:
            r3 = r14
        L_0x0297:
            r8.recycle()
            if (r0 == r14) goto L_0x02a1
            android.widget.TextView r4 = r7.mView
            androidx.core.widget.TextViewCompat.setFirstBaselineToTopHeight(r4, r0)
        L_0x02a1:
            if (r1 == r14) goto L_0x02a8
            android.widget.TextView r0 = r7.mView
            androidx.core.widget.TextViewCompat.setLastBaselineToBottomHeight(r0, r1)
        L_0x02a8:
            int r0 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x02ba
            if (r3 != r14) goto L_0x02b5
            android.widget.TextView r0 = r7.mView
            int r1 = (int) r2
            androidx.core.widget.TextViewCompat.setLineHeight(r0, r1)
            goto L_0x02ba
        L_0x02b5:
            android.widget.TextView r0 = r7.mView
            androidx.core.widget.TextViewCompat.setLineHeight(r0, r3, r2)
        L_0x02ba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        int i;
        String string;
        this.mStyle = tintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
        if (Build.VERSION.SDK_INT >= 28) {
            int i2 = tintTypedArray.getInt(R.styleable.TextAppearance_android_textFontWeight, -1);
            this.mFontWeight = i2;
            if (i2 != -1) {
                this.mStyle &= 2;
            }
        }
        boolean z = true;
        if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily) || tintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
            this.mFontTypeface = null;
            if (tintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
                i = R.styleable.TextAppearance_fontFamily;
            } else {
                i = R.styleable.TextAppearance_android_fontFamily;
            }
            final int i3 = this.mFontWeight;
            final int i4 = this.mStyle;
            if (!context.isRestricted()) {
                final WeakReference weakReference = new WeakReference(this.mView);
                try {
                    Typeface font = tintTypedArray.getFont(i, this.mStyle, new ResourcesCompat.FontCallback() {
                        public void onFontRetrievalFailed(int i) {
                        }

                        public void onFontRetrieved(Typeface typeface) {
                            int i;
                            if (Build.VERSION.SDK_INT >= 28 && (i = i3) != -1) {
                                typeface = Api28Impl.create(typeface, i, (i4 & 2) != 0);
                            }
                            AppCompatTextHelper.this.onAsyncTypefaceReceived(weakReference, typeface);
                        }
                    });
                    if (font != null) {
                        if (Build.VERSION.SDK_INT < 28 || this.mFontWeight == -1) {
                            this.mFontTypeface = font;
                        } else {
                            this.mFontTypeface = Api28Impl.create(Typeface.create(font, 0), this.mFontWeight, (this.mStyle & 2) != 0);
                        }
                    }
                    this.mAsyncFontPending = this.mFontTypeface == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.mFontTypeface == null && (string = tintTypedArray.getString(i)) != null) {
                if (Build.VERSION.SDK_INT < 28 || this.mFontWeight == -1) {
                    this.mFontTypeface = Typeface.create(string, this.mStyle);
                    return;
                }
                Typeface create = Typeface.create(string, 0);
                int i5 = this.mFontWeight;
                if ((this.mStyle & 2) == 0) {
                    z = false;
                }
                this.mFontTypeface = Api28Impl.create(create, i5, z);
            }
        } else if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface)) {
            this.mAsyncFontPending = false;
            int i6 = tintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1);
            if (i6 == 1) {
                this.mFontTypeface = Typeface.SANS_SERIF;
            } else if (i6 == 2) {
                this.mFontTypeface = Typeface.SERIF;
            } else if (i6 == 3) {
                this.mFontTypeface = Typeface.MONOSPACE;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onAsyncTypefaceReceived(WeakReference<TextView> weakReference, final Typeface typeface) {
        if (this.mAsyncFontPending) {
            this.mFontTypeface = typeface;
            final TextView textView = (TextView) weakReference.get();
            if (textView == null) {
                return;
            }
            if (textView.isAttachedToWindow()) {
                final int i = this.mStyle;
                textView.post(new Runnable() {
                    public void run() {
                        textView.setTypeface(typeface, i);
                    }
                });
                return;
            }
            textView.setTypeface(typeface, this.mStyle);
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetTextAppearance(Context context, int i) {
        String string;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i, R.styleable.TextAppearance);
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            setAllCaps(obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TextAppearance_android_textSize) && obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, obtainStyledAttributes);
        if (Build.VERSION.SDK_INT >= 26 && obtainStyledAttributes.hasValue(R.styleable.TextAppearance_fontVariationSettings) && (string = obtainStyledAttributes.getString(R.styleable.TextAppearance_fontVariationSettings)) != null) {
            Api26Impl.setFontVariationSettings(this.mView, string);
        }
        obtainStyledAttributes.recycle();
        Typeface typeface = this.mFontTypeface;
        if (typeface != null) {
            this.mView.setTypeface(typeface, this.mStyle);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllCaps(boolean z) {
        this.mView.setAllCaps(z);
    }

    /* access modifiers changed from: package-private */
    public void onSetCompoundDrawables() {
        applyCompoundDrawablesTints();
    }

    /* access modifiers changed from: package-private */
    public void applyCompoundDrawablesTints() {
        if (!(this.mDrawableLeftTint == null && this.mDrawableTopTint == null && this.mDrawableRightTint == null && this.mDrawableBottomTint == null)) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
        }
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        }
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    /* access modifiers changed from: package-private */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            autoSizeText();
        }
    }

    /* access modifiers changed from: package-private */
    public void setTextSize(int i, float f) {
        if (!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled()) {
            setTextSizeInternal(i, f);
        }
    }

    /* access modifiers changed from: package-private */
    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    /* access modifiers changed from: package-private */
    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    private void setTextSizeInternal(int i, float f) {
        this.mAutoSizeTextHelper.setTextSizeInternal(i, f);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeWithDefaults(int i) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    /* access modifiers changed from: package-private */
    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getCompoundDrawableTintList() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setCompoundDrawableTintList(ColorStateList colorStateList) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        this.mDrawableTint.mTintList = colorStateList;
        this.mDrawableTint.mHasTintList = colorStateList != null;
        setCompoundTints();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getCompoundDrawableTintMode() {
        TintInfo tintInfo = this.mDrawableTint;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setCompoundDrawableTintMode(PorterDuff.Mode mode) {
        if (this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        this.mDrawableTint.mTintMode = mode;
        this.mDrawableTint.mHasTintMode = mode != null;
        setCompoundTints();
    }

    private void setCompoundTints() {
        TintInfo tintInfo = this.mDrawableTint;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    private void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            TextView textView = this.mView;
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
        } else if (drawable != null || drawable2 != null || drawable3 != null || drawable4 != null) {
            Drawable[] compoundDrawablesRelative2 = this.mView.getCompoundDrawablesRelative();
            Drawable drawable7 = compoundDrawablesRelative2[0];
            if (drawable7 == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
                TextView textView2 = this.mView;
                if (drawable == null) {
                    drawable = compoundDrawables[0];
                }
                if (drawable2 == null) {
                    drawable2 = compoundDrawables[1];
                }
                if (drawable3 == null) {
                    drawable3 = compoundDrawables[2];
                }
                if (drawable4 == null) {
                    drawable4 = compoundDrawables[3];
                }
                textView2.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
                return;
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative2[1];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative2[3];
            }
            this.mView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, compoundDrawablesRelative2[2], drawable4);
        }
    }

    /* access modifiers changed from: package-private */
    public void populateSurroundingTextIfNeeded(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        if (Build.VERSION.SDK_INT < 30 && inputConnection != null) {
            EditorInfoCompat.setInitialSurroundingText(editorInfo, textView.getText());
        }
    }

    static class Api26Impl {
        private Api26Impl() {
        }

        static boolean setFontVariationSettings(TextView textView, String str) {
            return textView.setFontVariationSettings(str);
        }

        static int getAutoSizeStepGranularity(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i, int i2, int i3, int i4) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }

        static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, int[] iArr, int i) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }
    }

    static class Api24Impl {
        private Api24Impl() {
        }

        static void setTextLocales(TextView textView, LocaleList localeList) {
            textView.setTextLocales(localeList);
        }

        static LocaleList forLanguageTags(String str) {
            return LocaleList.forLanguageTags(str);
        }
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static Locale forLanguageTag(String str) {
            return Locale.forLanguageTag(str);
        }
    }

    static class Api28Impl {
        private Api28Impl() {
        }

        static Typeface create(Typeface typeface, int i, boolean z) {
            return Typeface.create(typeface, i, z);
        }
    }
}
