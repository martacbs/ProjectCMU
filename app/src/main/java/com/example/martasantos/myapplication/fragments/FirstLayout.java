package com.example.martasantos.myapplication.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.martasantos.myapplication.R;

/**
 * Created by martasantos on 18/01/18.
 */

public class FirstLayout extends Fragment {

    CalendarView calendarView;
    SharedPreferences sharedPreferences;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.first_layout, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        /*calendarView = (CalendarView) myView.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {


                sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditor = sharedPreferences.edit();
                mEditor.putInt("mes", i);
                mEditor.putInt("dia", i1 + 1);
                mEditor.putInt("ano", i2);
                mEditor.commit();

                Intent intent = new Intent(getActivity().getApplication(), SecondLayout.class);
                startActivity(intent);
                //Intent j = new Intent(getApplicationContext(), HorasL.class);
                // startActivity(j);


            }
        });*/
  return myView;}
}
