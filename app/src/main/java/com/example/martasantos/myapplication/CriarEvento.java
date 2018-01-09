package com.example.martasantos.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.martasantos.myapplication.database.Events;

import java.util.ArrayList;

public class CriarEvento extends AppCompatActivity {

    Button criarEvento;
    ArrayList<Events> eventos;
    EditText nomeEvento, localEvento, comeca, duracao;
   // private String [] lembrete  = new String[]{"1","2","3","4"};
    private Spinner sp;


    private void insertEvent() throws Exception{
        ArrayList<Events> eventos = new ArrayList<Events>();
        Events events = new Events(CriarEvento.this);
        SQLiteDatabase db = events.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("nomeEvento", nomeEvento.getText().toString());
        values.put("localEvento", localEvento.getText().toString());
        values.put("comeca", comeca.getText().toString());
        values.put("duracao", duracao.getText().toString());

        long rowId = db.insert("events", null, values);
        if (rowId < 0) {
            throw new Exception("Erro aqui");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);
        ArrayList<Events> eventos = new ArrayList<Events>(10);
      //  ListAdapter adapter1 = new MyListItemAdapter(eventos, this);
        ArrayAdapter<String> adapter= new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp=(Spinner)findViewById(R.id.spinner);
        sp.setAdapter(adapter);

        nomeEvento=(EditText)findViewById(R.id.nomeEvento);
        localEvento=(EditText)findViewById(R.id.localEvento);
        comeca=(EditText)findViewById(R.id.comeca);
        duracao=(EditText)findViewById(R.id.duracao);

        criarEvento=(Button)findViewById(R.id.buttonCriarEvento);
        criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insertEvent();
                    Intent d = new Intent(getApplicationContext(), HorasL.class);
                    startActivity(d);
                    Toast.makeText(getApplicationContext(), "Evento Criado com sucesso", Toast.LENGTH_SHORT).show();
                }catch (Exception e ){
                    e.printStackTrace();
               }
                }
        });

    }
}
