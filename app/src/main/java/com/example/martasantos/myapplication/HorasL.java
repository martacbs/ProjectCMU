package com.example.martasantos.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.martasantos.myapplication.adapter.HorasAdapter;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.models.Evento;
import com.example.martasantos.myapplication.models.Horas;

import java.util.ArrayList;

/**
 *
 *
 */

public class HorasL extends AppCompatActivity {

    ArrayList<Horas> horas;
    ArrayList<Evento> eventos;
    private SharedPreferences sharedPreferences;
    private String nome = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horas);
        this.eventos = new ArrayList<>();
        reloadEventList(eventos);

        //ao clicar na seta no canto superior esquerdo retorna para a atividade anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView rvHoras =(RecyclerView) findViewById(R.id.rvHoras);

        horas = createHorasList(24);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        nome = sharedPreferences.getString("nome", "");

        HorasAdapter adapter = new HorasAdapter(this, horas, eventos);
        adapter.notifyDataSetChanged();
        rvHoras.setAdapter(adapter);

        rvHoras.setLayoutManager(new LinearLayoutManager(
                this));

        String dia = getIntent().getStringExtra("dia");
        String mes = getIntent().getStringExtra("mes");
        String ano = getIntent().getStringExtra("ano");

    }

    private ArrayList<Horas> createHorasList (int x){
        ArrayList<Horas> horas = new ArrayList<>(x);
        for(int i=0;i<x;i++){
            Horas h =null;
            String hora=  i + ":00" ;
            if(i<=x){
                h =new Horas (hora);
            }
            horas.add(h);
        }
        return horas;
    }


    public void reloadEventList(  ArrayList<Evento> eventos ) {
        DbHelper
                dbHelper = new DbHelper(this);
        SQLiteDatabase db =
                dbHelper.getWritableDatabase();
        eventos.clear();
        Cursor c = db.rawQuery("SELECT	*	FROM	events", null);

        if (c != null && c.moveToFirst()) {
            do {
                Evento p = new Evento();
                p.setId(c.getInt(0));
                p.setNome(c.getString(1));
                p.setLocal(c.getString(2));
                p.setData(c.getString(3));
                p.setComeca(c.getString(4));
                p.setDuracao(c.getString(5));
                eventos.add(p);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }
}

