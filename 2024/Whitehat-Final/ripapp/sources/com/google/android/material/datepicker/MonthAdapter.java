package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.util.Pair;
import java.util.Collection;

class MonthAdapter extends BaseAdapter {
    private static final int MAXIMUM_GRID_CELLS = ((UtcDates.getUtcCalendar().getMaximum(5) + UtcDates.getUtcCalendar().getMaximum(7)) - 1);
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    private static final int NO_DAY_NUMBER = -1;
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final DayViewDecorator dayViewDecorator;
    final Month month;
    private Collection<Long> previouslySelectedDates;

    public boolean hasStableIds() {
        return true;
    }

    MonthAdapter(Month month2, DateSelector<?> dateSelector2, CalendarConstraints calendarConstraints2, DayViewDecorator dayViewDecorator2) {
        this.month = month2;
        this.dateSelector = dateSelector2;
        this.calendarConstraints = calendarConstraints2;
        this.dayViewDecorator = dayViewDecorator2;
        this.previouslySelectedDates = dateSelector2.getSelectedDays();
    }

    public Long getItem(int i) {
        if (i < firstPositionInMonth() || i > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(i)));
    }

    public long getItemId(int i) {
        return (long) (i / this.month.daysInWeek);
    }

    public int getCount() {
        return MAXIMUM_GRID_CELLS;
    }

    /* JADX WARNING: type inference failed for: r7v9, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.initializeStyles(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L_0x001e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L_0x001e:
            int r7 = r5.firstPositionInMonth()
            int r7 = r6 - r7
            if (r7 < 0) goto L_0x0056
            com.google.android.material.datepicker.Month r8 = r5.month
            int r8 = r8.daysInMonth
            if (r7 < r8) goto L_0x002d
            goto L_0x0056
        L_0x002d:
            r8 = 1
            int r7 = r7 + r8
            com.google.android.material.datepicker.Month r2 = r5.month
            r0.setTag(r2)
            android.content.res.Resources r2 = r0.getResources()
            android.content.res.Configuration r2 = r2.getConfiguration()
            java.util.Locale r2 = r2.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r2 = java.lang.String.format(r2, r4, r3)
            r0.setText(r2)
            r0.setVisibility(r1)
            r0.setEnabled(r8)
            goto L_0x005f
        L_0x0056:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L_0x005f:
            java.lang.Long r6 = r5.getItem((int) r6)
            if (r6 != 0) goto L_0x0066
            return r0
        L_0x0066:
            long r1 = r6.longValue()
            r5.updateSelectedState(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public void updateSelectedStates(MaterialCalendarGridView materialCalendarGridView) {
        for (Long longValue : this.previouslySelectedDates) {
            updateSelectedStateForDate(materialCalendarGridView, longValue.longValue());
        }
        DateSelector<?> dateSelector2 = this.dateSelector;
        if (dateSelector2 != null) {
            for (Long longValue2 : dateSelector2.getSelectedDays()) {
                updateSelectedStateForDate(materialCalendarGridView, longValue2.longValue());
            }
            this.previouslySelectedDates = this.dateSelector.getSelectedDays();
        }
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (Month.create(j).equals(this.month)) {
            int dayOfMonth = this.month.getDayOfMonth(j);
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().dayToPosition(dayOfMonth) - materialCalendarGridView.getFirstVisiblePosition()), j, dayOfMonth);
        }
    }

    private void updateSelectedState(TextView textView, long j, int i) {
        boolean z;
        CalendarItemStyle calendarItemStyle;
        CalendarItemStyle calendarItemStyle2;
        TextView textView2 = textView;
        long j2 = j;
        if (textView2 != null) {
            Context context = textView.getContext();
            String dayContentDescription = getDayContentDescription(context, j2);
            textView2.setContentDescription(dayContentDescription);
            boolean isValid = this.calendarConstraints.getDateValidator().isValid(j2);
            if (isValid) {
                textView2.setEnabled(true);
                boolean isSelected = isSelected(j2);
                textView2.setSelected(isSelected);
                if (isSelected) {
                    calendarItemStyle2 = this.calendarStyle.selectedDay;
                } else if (isToday(j2)) {
                    calendarItemStyle2 = this.calendarStyle.todayDay;
                } else {
                    calendarItemStyle2 = this.calendarStyle.day;
                }
                calendarItemStyle = calendarItemStyle2;
                z = isSelected;
            } else {
                textView2.setEnabled(false);
                z = false;
                calendarItemStyle = this.calendarStyle.invalidDay;
            }
            if (this.dayViewDecorator == null || i == -1) {
                calendarItemStyle.styleItem(textView2);
                return;
            }
            int i2 = this.month.year;
            int i3 = this.month.month;
            Context context2 = context;
            int i4 = i2;
            int i5 = i;
            boolean z2 = isValid;
            int i6 = i3;
            ColorStateList backgroundColor = this.dayViewDecorator.getBackgroundColor(context2, i4, i3, i5, z2, z);
            boolean z3 = z;
            calendarItemStyle.styleItem(textView2, backgroundColor, this.dayViewDecorator.getTextColor(context2, i4, i6, i5, z2, z3));
            Drawable compoundDrawableLeft = this.dayViewDecorator.getCompoundDrawableLeft(context2, i4, i6, i5, z2, z3);
            Drawable compoundDrawableTop = this.dayViewDecorator.getCompoundDrawableTop(context2, i4, i6, i5, z2, z3);
            String str = dayContentDescription;
            Drawable compoundDrawableRight = this.dayViewDecorator.getCompoundDrawableRight(context2, i4, i6, i5, z2, z3);
            boolean z4 = z;
            textView2.setCompoundDrawables(compoundDrawableLeft, compoundDrawableTop, compoundDrawableRight, this.dayViewDecorator.getCompoundDrawableBottom(context2, i4, i6, i5, z2, z4));
            textView2.setContentDescription(this.dayViewDecorator.getContentDescription(context2, i4, i6, i5, z2, z4, str));
        }
    }

    private String getDayContentDescription(Context context, long j) {
        return DateStrings.getDayContentDescription(context, j, isToday(j), isStartOfRange(j), isEndOfRange(j));
    }

    private boolean isToday(long j) {
        return UtcDates.getTodayCalendar().getTimeInMillis() == j;
    }

    /* access modifiers changed from: package-private */
    public boolean isStartOfRange(long j) {
        for (Pair next : this.dateSelector.getSelectedRanges()) {
            if (next.first != null && ((Long) next.first).longValue() == j) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isEndOfRange(long j) {
        for (Pair next : this.dateSelector.getSelectedRanges()) {
            if (next.second != null && ((Long) next.second).longValue() == j) {
                return true;
            }
        }
        return false;
    }

    private boolean isSelected(long j) {
        for (Long longValue : this.dateSelector.getSelectedDays()) {
            if (UtcDates.canonicalYearMonthDay(j) == UtcDates.canonicalYearMonthDay(longValue.longValue())) {
                return true;
            }
        }
        return false;
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    /* access modifiers changed from: package-private */
    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth(this.calendarConstraints.getFirstDayOfWeek());
    }

    /* access modifiers changed from: package-private */
    public int lastPositionInMonth() {
        return (firstPositionInMonth() + this.month.daysInMonth) - 1;
    }

    /* access modifiers changed from: package-private */
    public int positionToDay(int i) {
        return (i - firstPositionInMonth()) + 1;
    }

    /* access modifiers changed from: package-private */
    public int dayToPosition(int i) {
        return firstPositionInMonth() + (i - 1);
    }

    /* access modifiers changed from: package-private */
    public boolean withinMonth(int i) {
        return i >= firstPositionInMonth() && i <= lastPositionInMonth();
    }

    /* access modifiers changed from: package-private */
    public boolean isFirstInRow(int i) {
        return i % this.month.daysInWeek == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isLastInRow(int i) {
        return (i + 1) % this.month.daysInWeek == 0;
    }
}
