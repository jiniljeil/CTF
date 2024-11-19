package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import com.google.android.material.internal.ViewUtils;
import java.text.SimpleDateFormat;
import java.util.Collection;

public interface DateSelector<S> extends Parcelable {
    int getDefaultThemeResId(Context context);

    int getDefaultTitleResId();

    String getError();

    Collection<Long> getSelectedDays();

    Collection<Pair<Long, Long>> getSelectedRanges();

    S getSelection();

    String getSelectionContentDescription(Context context);

    String getSelectionDisplayString(Context context);

    boolean isSelectionComplete();

    View onCreateTextInputView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, OnSelectionChangedListener<S> onSelectionChangedListener);

    void select(long j);

    void setSelection(S s);

    void setTextInputFormat(SimpleDateFormat simpleDateFormat);

    static void showKeyboardWithAutoHideBehavior(EditText... editTextArr) {
        if (editTextArr.length != 0) {
            DateSelector$$ExternalSyntheticLambda0 dateSelector$$ExternalSyntheticLambda0 = new DateSelector$$ExternalSyntheticLambda0(editTextArr);
            for (EditText onFocusChangeListener : editTextArr) {
                onFocusChangeListener.setOnFocusChangeListener(dateSelector$$ExternalSyntheticLambda0);
            }
            EditText editText = editTextArr[0];
            editText.postDelayed(new DateSelector$$ExternalSyntheticLambda1(editText), 100);
        }
    }

    static /* synthetic */ void lambda$showKeyboardWithAutoHideBehavior$0(EditText[] editTextArr, View view, boolean z) {
        int length = editTextArr.length;
        int i = 0;
        while (i < length) {
            if (!editTextArr[i].hasFocus()) {
                i++;
            } else {
                return;
            }
        }
        ViewUtils.hideKeyboard(view, false);
    }
}
