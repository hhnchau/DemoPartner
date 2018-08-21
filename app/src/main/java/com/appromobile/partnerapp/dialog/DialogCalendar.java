package com.appromobile.partnerapp.dialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.appromobile.partnerapp.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class DialogCalendar {
    private static DialogCalendar instance;

    public static DialogCalendar getInstance() {
        if (instance == null) {
            instance = new DialogCalendar();
        }
        return instance;
    }

    public void show(Context context, CalendarDay calendarDay, final CallbackDialogCalendar callbackDialogCalendar) {

        final Dialog dialog = new Dialog(context, R.style.TransparentLoading);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_calendar);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        MaterialCalendarView materialCalendarView = (MaterialCalendarView) dialog.findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2015, 12, 31))
                .setMaximumDate(CalendarDay.from(2020, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        //Set current day
        CalendarDay dayInput = CalendarDay.from(calendarDay.getYear(), calendarDay.getMonth()-1, calendarDay.getDay());
        materialCalendarView.setCurrentDate(dayInput);
        materialCalendarView.setSelectedDate(dayInput);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                CalendarDay dayOutput = CalendarDay.from(date.getYear(), date.getMonth()+1, date.getDay());
                callbackDialogCalendar.getCalendar(dayOutput);
                dialog.dismiss();
            }
        });

        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.linear_calendar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
