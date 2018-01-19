package com.example.martasantos.myapplication.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.martasantos.myapplication.R;

/**
 * Created by martasantos on 18/01/18.
 */

public class About extends Fragment {
    Button telefone,email;
    View myView;
    TextView e1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.about, container, false);

        telefone=(Button)myView.findViewById(R.id.botaoChamada);
        email=(Button)myView.findViewById(R.id.botaoEmail);
        e1 = (TextView) myView.findViewById(R.id.numero);
        final String num="913212212";

        telefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Intent.ACTION_VIEW, Uri.parse("tel:"+num));
                startActivity(i);
            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Intent.ACTION_SENDTO);
                x.setType("text/plain");
                x.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                x.putExtra(Intent.EXTRA_TEXT, "Body of email");
                x.setData(Uri.parse("mailto:xAgenda@android.com"));
                x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(x);
            }
        });
    return myView;}}
