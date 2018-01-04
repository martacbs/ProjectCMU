package com.example.martasantos.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CriarEvento extends AppCompatActivity {

    private String [] lembrete  = new String[]{"1","2","3","4"};
    private Spinner sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        ArrayAdapter<String> adapter= new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, lembrete );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp=(Spinner)findViewById(R.id.spinner);
        sp.setAdapter(adapter);
    }
}
