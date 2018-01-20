package com.example.martasantos.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.martasantos.myapplication.interests.UserInterests;

public class Sugestoes extends ArrayAdapter<UserInterests> {


        private Context mContext;

    public Sugestoes(@NonNull Context context, int resource) {
        super(context, resource);
    }

    }

