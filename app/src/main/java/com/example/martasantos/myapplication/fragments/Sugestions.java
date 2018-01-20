package com.example.martasantos.myapplication.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.register.UserRegister;

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


        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase db= dbHelper.getWritableDatabase();

//db.execSQL();
        myView = inflater.inflate(R.layout.sugestions, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        nome = sharedPreferences.getString("nome", "");

    return myView;
    }}
