package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CheckedTextViewCompat;

class AppCompatCheckedTextViewHelper {
    private ColorStateList mCheckMarkTintList = null;
    private PorterDuff.Mode mCheckMarkTintMode = null;
    private boolean mHasCheckMarkTint = false;
    private boolean mHasCheckMarkTintMode = false;
    private boolean mSkipNextApply;
    private final CheckedTextView mView;

    AppCompatCheckedTextViewHelper(CheckedTextView checkedTextView) {
        this.mView = checkedTextView;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|(2:6|7)|8|9|(1:13)|14|(1:16)|17|(1:19)|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CheckedTextView r0 = r10.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.R.styleable.CheckedTextView
            r2 = 0
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r11, r1, r12, r2)
            android.widget.CheckedTextView r3 = r10.mView
            android.content.Context r4 = r3.getContext()
            int[] r5 = androidx.appcompat.R.styleable.CheckedTextView
            android.content.res.TypedArray r7 = r0.getWrappedTypeArray()
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r3, r4, r5, r6, r7, r8, r9)
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkCompat     // Catch:{ all -> 0x008a }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x003d
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkCompat     // Catch:{ all -> 0x008a }
            int r11 = r0.getResourceId(r11, r2)     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x003d
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ NotFoundException -> 0x003d }
            android.content.Context r1 = r12.getContext()     // Catch:{ NotFoundException -> 0x003d }
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r11)     // Catch:{ NotFoundException -> 0x003d }
            r12.setCheckMarkDrawable(r11)     // Catch:{ NotFoundException -> 0x003d }
            goto L_0x005a
        L_0x003d:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_android_checkMark     // Catch:{ all -> 0x008a }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x005a
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_android_checkMark     // Catch:{ all -> 0x008a }
            int r11 = r0.getResourceId(r11, r2)     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x005a
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ all -> 0x008a }
            android.content.Context r1 = r12.getContext()     // Catch:{ all -> 0x008a }
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r11)     // Catch:{ all -> 0x008a }
            r12.setCheckMarkDrawable(r11)     // Catch:{ all -> 0x008a }
        L_0x005a:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTint     // Catch:{ all -> 0x008a }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x006d
            android.widget.CheckedTextView r11 = r10.mView     // Catch:{ all -> 0x008a }
            int r12 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTint     // Catch:{ all -> 0x008a }
            android.content.res.ColorStateList r12 = r0.getColorStateList(r12)     // Catch:{ all -> 0x008a }
            androidx.core.widget.CheckedTextViewCompat.setCheckMarkTintList(r11, r12)     // Catch:{ all -> 0x008a }
        L_0x006d:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTintMode     // Catch:{ all -> 0x008a }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x0086
            android.widget.CheckedTextView r11 = r10.mView     // Catch:{ all -> 0x008a }
            int r12 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTintMode     // Catch:{ all -> 0x008a }
            r1 = -1
            int r12 = r0.getInt(r12, r1)     // Catch:{ all -> 0x008a }
            r1 = 0
            android.graphics.PorterDuff$Mode r12 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r12, r1)     // Catch:{ all -> 0x008a }
            androidx.core.widget.CheckedTextViewCompat.setCheckMarkTintMode(r11, r12)     // Catch:{ all -> 0x008a }
        L_0x0086:
            r0.recycle()
            return
        L_0x008a:
            r11 = move-exception
            r0.recycle()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCheckedTextViewHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: package-private */
    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        this.mCheckMarkTintList = colorStateList;
        this.mHasCheckMarkTint = true;
        applyCheckMarkTint();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportCheckMarkTintList() {
        return this.mCheckMarkTintList;
    }

    /* access modifiers changed from: package-private */
    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        this.mCheckMarkTintMode = mode;
        this.mHasCheckMarkTintMode = true;
        applyCheckMarkTint();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        return this.mCheckMarkTintMode;
    }

    /* access modifiers changed from: package-private */
    public void onSetCheckMarkDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyCheckMarkTint();
    }

    /* access modifiers changed from: package-private */
    public void applyCheckMarkTint() {
        Drawable checkMarkDrawable = CheckedTextViewCompat.getCheckMarkDrawable(this.mView);
        if (checkMarkDrawable == null) {
            return;
        }
        if (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode) {
            Drawable mutate = DrawableCompat.wrap(checkMarkDrawable).mutate();
            if (this.mHasCheckMarkTint) {
                DrawableCompat.setTintList(mutate, this.mCheckMarkTintList);
            }
            if (this.mHasCheckMarkTintMode) {
                DrawableCompat.setTintMode(mutate, this.mCheckMarkTintMode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.mView.getDrawableState());
            }
            this.mView.setCheckMarkDrawable(mutate);
        }
    }
}
