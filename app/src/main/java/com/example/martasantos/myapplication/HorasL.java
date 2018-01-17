package com.example.martasantos.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.martasantos.myapplication.adapter.HorasAdapter;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.models.Evento;
import com.example.martasantos.myapplication.models.Horas;

import java.util.ArrayList;

/**
 * Created by martasantos on 31/12/17.
 */

public class HorasL extends AppCompatActivity {

    ArrayList<Horas> horas;
    ArrayList<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horas);
        Intent i = getIntent();
        String evento = i.getStringExtra("nomeEvento");
        RecyclerView rvHoras = (RecyclerView) findViewById(R.id.rvHoras);
        eventos = new ArrayList<>();
        reloadEventList(eventos);
        horas = createHorasList(24);

        HorasAdapter adapter = new HorasAdapter(this, horas,eventos);
        //atualizar data
        adapter.notifyDataSetChanged();
        rvHoras.setAdapter(adapter);


        rvHoras.setLayoutManager(new LinearLayoutManager(
                this));
// O RATO DA MARTA SANTOS

    }

    private ArrayList<Horas> createHorasList(int x) {
        ArrayList<Horas> horas = new ArrayList<>(x);
        for (int i = 0; i < x; i++) {
            Horas h = null;
            String hora = i + ":00";
            if (i <= x) {
                h = new Horas(hora);
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
       // db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeEvento VARCHAR(50) NOT NULL, localEvento VARCHAR(50) NOT NULL, comeca VARCHAR(50) NOT NULL, duracao VARCHAR(50) NOT NULL, lembrete VARCHAR(50) NOT NULL)");
        if (c != null && c.moveToFirst()) {
            do {
                Evento p = new Evento();
                p.setId(c.getInt(0));
                p.setNome(c.getString(1));
                p.setLocal(c.getString(2));
                p.setComeca(c.getString(3));
                p.setDuracao(c.getString(4));
                p.setLembrete(c.getString(5));
                eventos.add(p);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }
}
