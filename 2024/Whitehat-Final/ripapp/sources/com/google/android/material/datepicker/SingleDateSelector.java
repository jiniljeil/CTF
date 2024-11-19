package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = new Parcelable.Creator<SingleDateSelector>() {
        public SingleDateSelector createFromParcel(Parcel parcel) {
            SingleDateSelector singleDateSelector = new SingleDateSelector();
            Long unused = singleDateSelector.selectedItem = (Long) parcel.readValue(Long.class.getClassLoader());
            return singleDateSelector;
        }

        public SingleDateSelector[] newArray(int i) {
            return new SingleDateSelector[i];
        }
    };
    /* access modifiers changed from: private */
    public CharSequence error;
    /* access modifiers changed from: private */
    public Long selectedItem;
    private SimpleDateFormat textInputFormat;

    public int describeContents() {
        return 0;
    }

    public void select(long j) {
        this.selectedItem = Long.valueOf(j);
    }

    /* access modifiers changed from: private */
    public void clearSelection() {
        this.selectedItem = null;
    }

    public void setSelection(Long l) {
        this.selectedItem = l == null ? null : Long.valueOf(UtcDates.canonicalYearMonthDay(l.longValue()));
    }

    public boolean isSelectionComplete() {
        return this.selectedItem != null;
    }

    public Collection<Pair<Long, Long>> getSelectedRanges() {
        return new ArrayList();
    }

    public Collection<Long> getSelectedDays() {
        ArrayList arrayList = new ArrayList();
        Long l = this.selectedItem;
        if (l != null) {
            arrayList.add(l);
        }
        return arrayList;
    }

    public Long getSelection() {
        return this.selectedItem;
    }

    public void setTextInputFormat(SimpleDateFormat simpleDateFormat) {
        if (simpleDateFormat != null) {
            simpleDateFormat = (SimpleDateFormat) UtcDates.getNormalizedFormat(simpleDateFormat);
        }
        this.textInputFormat = simpleDateFormat;
    }

    public View onCreateTextInputView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, OnSelectionChangedListener<Long> onSelectionChangedListener) {
        String str;
        View inflate = layoutInflater.inflate(R.layout.mtrl_picker_text_input_date, viewGroup, false);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_date);
        EditText editText = textInputLayout.getEditText();
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            editText.setInputType(17);
        }
        SimpleDateFormat simpleDateFormat = this.textInputFormat;
        boolean z = simpleDateFormat != null;
        if (!z) {
            simpleDateFormat = UtcDates.getDefaultTextInputFormat();
        }
        SimpleDateFormat simpleDateFormat2 = simpleDateFormat;
        if (z) {
            str = simpleDateFormat2.toPattern();
        } else {
            str = UtcDates.getDefaultTextInputHint(inflate.getResources(), simpleDateFormat2);
        }
        String str2 = str;
        textInputLayout.setPlaceholderText(str2);
        Long l = this.selectedItem;
        if (l != null) {
            editText.setText(simpleDateFormat2.format(l));
        }
        final OnSelectionChangedListener<Long> onSelectionChangedListener2 = onSelectionChangedListener;
        editText.addTextChangedListener(new DateFormatTextWatcher(str2, simpleDateFormat2, textInputLayout, calendarConstraints) {
            /* access modifiers changed from: package-private */
            public void onValidDate(Long l) {
                if (l == null) {
                    SingleDateSelector.this.clearSelection();
                } else {
                    SingleDateSelector.this.select(l.longValue());
                }
                CharSequence unused = SingleDateSelector.this.error = null;
                onSelectionChangedListener2.onSelectionChanged(SingleDateSelector.this.getSelection());
            }

            /* access modifiers changed from: package-private */
            public void onInvalidDate() {
                CharSequence unused = SingleDateSelector.this.error = textInputLayout.getError();
                onSelectionChangedListener2.onIncompleteSelectionChanged();
            }
        });
        DateSelector.showKeyboardWithAutoHideBehavior(editText);
        return inflate;
    }

    public int getDefaultThemeResId(Context context) {
        return MaterialAttributes.resolveOrThrow(context, R.attr.materialCalendarTheme, MaterialDatePicker.class.getCanonicalName());
    }

    public String getSelectionDisplayString(Context context) {
        Resources resources = context.getResources();
        Long l = this.selectedItem;
        if (l == null) {
            return resources.getString(R.string.mtrl_picker_date_header_unselected);
        }
        return resources.getString(R.string.mtrl_picker_date_header_selected, new Object[]{DateStrings.getYearMonthDay(l.longValue())});
    }

    public String getSelectionContentDescription(Context context) {
        String str;
        Resources resources = context.getResources();
        Long l = this.selectedItem;
        if (l == null) {
            str = resources.getString(R.string.mtrl_picker_announce_current_selection_none);
        } else {
            str = DateStrings.getYearMonthDay(l.longValue());
        }
        return resources.getString(R.string.mtrl_picker_announce_current_selection, new Object[]{str});
    }

    public String getError() {
        if (TextUtils.isEmpty(this.error)) {
            return null;
        }
        return this.error.toString();
    }

    public int getDefaultTitleResId() {
        return R.string.mtrl_picker_date_header_title;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.selectedItem);
    }
}
