package com.example.martasantos.myapplication.interests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.mapas.attractions.MapasAmesterdamAttractions;
import com.example.martasantos.myapplication.mapas.attractions.MapasLondresAttractions;
import com.example.martasantos.myapplication.mapas.attractions.MapasParisAttractions;

public class Cidades extends AppCompatActivity {

    Button cidades;
    Spinner cidadesSpinner;
    private String[] cidadesAtracoes = new String[]{"Amesterdão", "Londres", "Paris"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidades);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cidadesSpinner = (Spinner) findViewById(R.id.spinnerCidades);
        ArrayAdapter<String> adapterCidades = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cidadesAtracoes);
        cidadesSpinner.setAdapter(adapterCidades);
        cidades = (Button) findViewById(R.id.botaoCidades);

        cidadesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicoesAtractions = cidadesSpinner.getSelectedItemPosition();

                if(posicoesAtractions==0){
                        Intent amesterdao = new Intent(getApplicationContext(), MapasAmesterdamAttractions.class);
                        startActivity(amesterdao);
                }else if(posicoesAtractions==1) {
                    Intent londres = new Intent(getApplicationContext(), MapasLondresAttractions.class);
                    startActivity(londres);
                }else if(posicoesAtractions==2){
                        Intent paris = new Intent(getApplicationContext(), MapasParisAttractions.class);
                        startActivity(paris);

                }
           }
       });
    }
}
