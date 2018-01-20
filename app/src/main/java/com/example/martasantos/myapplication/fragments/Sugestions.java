package com.example.martasantos.myapplication.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.martasantos.myapplication.R;

/**
 * Created by martasantos on 18/01/18.
 */

public class Sugestions extends Fragment {

    View myView;
    SharedPreferences sharedPreferences;
    Context context;
    private String nome = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.sugestions, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        nome = sharedPreferences.getString("nome", "");

    return myView;
    }}
