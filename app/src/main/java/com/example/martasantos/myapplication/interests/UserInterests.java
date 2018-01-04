package com.example.martasantos.myapplication.interests;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.calendar.Calendar;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.database.Interesses;
import com.example.martasantos.myapplication.login.Login;
import com.example.martasantos.myapplication.models.User;
import com.example.martasantos.myapplication.register.UserRegister;

public class UserInterests extends AppCompatActivity {

    CheckBox tecnologia, desporto, moda, musica, escrita, cinema, gastronomia;
    Button submeter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interests);

        submeter=(Button) findViewById(R.id.submeter);

    submeter.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {

        try {
            Interesses();

        }catch (Exception e){
            e.printStackTrace();
        }
        Intent j = new Intent(getApplicationContext(), Login.class);
        startActivity(j);


    }
});
    }

   public void Interesses() throws Exception{
        Interesses interess = new Interesses(UserInterests.this);
        SQLiteDatabase db= interess.getWritableDatabase();
        ContentValues values = new ContentValues();

       CheckBox tecnologia = (CheckBox) findViewById(R.id.tecnologia);
       CheckBox desporto = (CheckBox) findViewById(R.id.desporto);
       CheckBox moda = (CheckBox) findViewById(R.id.moda);
       CheckBox musica = (CheckBox) findViewById(R.id.musica);
       CheckBox escrita = (CheckBox) findViewById(R.id.escrita);
       CheckBox cinema = (CheckBox) findViewById(R.id.cinema);
       CheckBox gastronomia = (CheckBox) findViewById(R.id.gastronomia);

        if (tecnologia.isChecked()) {
            values.put("interesses", tecnologia.getText().toString());
        }else if(desporto.isChecked()){
            values.put("interesses", desporto.getText().toString());
        }else if (moda.isChecked()){
            values.put("interesses", moda.getText().toString());
        }else if (musica.isChecked()){
            values.put("interesses", musica.getText().toString());
        }else if (escrita.isChecked()){
            values.put("interesses", escrita.getText().toString());
        }else if (cinema.isChecked()){
            values.put("interesses", cinema.getText().toString());
        }else{
            values.put("interesses", gastronomia.getText().toString());
        }

       long rowId = db.insert("interests", null, values);
       Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
       if (rowId < 0) {

           throw new Exception("Erro aqui");
       }

    }}


