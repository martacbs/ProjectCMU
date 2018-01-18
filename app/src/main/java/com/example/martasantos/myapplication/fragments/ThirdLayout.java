package com.example.martasantos.myapplication.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.martasantos.myapplication.R;

/**
 * Created by martasantos on 18/01/18.
 */

public class ThirdLayout extends Fragment {
    CalendarView calendarView;
    SharedPreferences sharedPreferences;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.third_layout, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);

    return myView;}}
