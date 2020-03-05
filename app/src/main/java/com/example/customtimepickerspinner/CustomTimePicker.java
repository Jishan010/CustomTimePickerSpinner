package com.example.customtimepickerspinner;

import android.app.TimePickerDialog;
import android.content.Context;

public class CustomTimePicker extends TimePickerDialog {

    public CustomTimePicker(Context context, int themeResId, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, themeResId, listener, hourOfDay, minute, is24HourView);
    }
}
