package com.example.customtimepickerspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    TextView textview1;
    TimePicker timepicker;
    Button changetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview1 = (TextView) findViewById(R.id.textView1);
        timepicker = (TimePicker) findViewById(R.id.timePicker);
        //Uncomment the below line of code for 24 hour view
        timepicker.setIs24HourView(false);
        changetime = (Button) findViewById(R.id.button1);

        textview1.setText(getCurrentTime());

        changetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview1.setText(getCurrentTime());
            }
        });

        modifyTimePickerSpinner();
    }


    public void modifyTimePickerSpinner() {
        super.onAttachedToWindow();
        try {
            Class<?> classForid = Class.forName("com.android.internal.R$id");
            Field field = classForid.getField("hour");

            //to change the value of am pm spinner
            Field amPm = classForid.getField("amPm");
            final NumberPicker amPmSpinner = (NumberPicker) timepicker
                    .findViewById(amPm.getInt(null));
            amPmSpinner.setMaxValue(0);

            //to change the value of hour spinner
            final NumberPicker mHourSpinner = (NumberPicker) timepicker
                    .findViewById(field.getInt(null));
            mHourSpinner.setMinValue(1);
            mHourSpinner.setMaxValue(8);
            final NumberPicker numberPicker = (NumberPicker) timepicker.findViewById(amPm.getInt(null));
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker np1, int oldVal, int newVal) {
                    if (newVal == 0) { // case AM
                        mHourSpinner.setMinValue(1);
                        mHourSpinner.setMaxValue(8);
                    } else { // case PM
                        mHourSpinner.setMinValue(1);
                        mHourSpinner.setMaxValue(8);
                    }
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCurrentTime() {
        String currentTime = "Current Time: " + timepicker.getHour() + ":" + timepicker.getMinute();
        return currentTime;
    }

}