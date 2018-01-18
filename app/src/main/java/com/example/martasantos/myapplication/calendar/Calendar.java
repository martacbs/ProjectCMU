package com.example.martasantos.myapplication.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.martasantos.myapplication.HorasL;
import com.example.martasantos.myapplication.R;

public class Calendar extends AppCompatActivity {

    CalendarView calendarView;
    TextView dateDisplay;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                SharedPreferences x = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                SharedPreferences.Editor mEditor = x.edit();
                mEditor.putInt("dia", i);
                mEditor.putInt("mes", i1);
                mEditor.putInt("ano", i2);
                mEditor.commit();



                Intent j = new Intent(getApplicationContext(), HorasL.class);

                startActivity(j);

            }
        });
    }
}
