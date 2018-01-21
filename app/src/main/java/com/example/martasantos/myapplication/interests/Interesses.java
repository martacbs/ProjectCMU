package com.example.martasantos.myapplication.interests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.martasantos.myapplication.R;

public class Interesses extends AppCompatActivity {

    Button atracoes,restaurantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        atracoes=(Button)findViewById(R.id.Atracoes);
        restaurantes=(Button)findViewById(R.id.Restaurantes);

        atracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Cidades.class);
                startActivity(intent);
            }
        });

        restaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restaurante=new Intent(getApplicationContext(),CidadesRestaurantes.class);
                startActivity(restaurante);
            }
        });
    }
}
