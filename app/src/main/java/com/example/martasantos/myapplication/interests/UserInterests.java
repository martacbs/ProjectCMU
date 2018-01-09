package com.example.martasantos.myapplication.interests;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
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

import java.util.ArrayList;
import java.util.List;

public class UserInterests extends AppCompatActivity {

    CheckBox tecnologia, desporto, moda, musica, escrita, cinema, gastronomia;
    Button submeter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interests);

        submeter = (Button) findViewById(R.id.submeter);

        submeter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int x;
                try {
                    x = 0;
                    Interesses();

                } catch (Exception e) {
                    x = 1;
                    e.printStackTrace();
                }
                Intent j = new Intent(getApplicationContext(), Login.class);
                startActivity(j);

            }
        });
    }

    private void insertInteresse(String valor) throws Exception {

        ContentValues values = new ContentValues();
        values.put("interesses",valor);
        DbHelper dbHelper = new DbHelper(UserInterests.this);
        Interesses inter = new Interesses(UserInterests.this);
        SQLiteDatabase interr= inter.getWritableDatabase();
        SQLiteDatabase db= dbHelper.getWritableDatabase();

        String util = "SELECT * FROM TABLE DbHelper WHERE id=?";

        values.put("user_id", util);

        Interesses interess = new Interesses(UserInterests.this);



        long rowId = interr.insert("interests", null, values);
        Toast.makeText(

                getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).

                show();
        if (rowId < 0)

        {

            throw new Exception("Erro aqui");
        }


        db.close();

    }

    public void Interesses() throws Exception {


        List<String> interesses = new ArrayList<>();

        CheckBox tecnologia = (CheckBox) findViewById(R.id.tecnologia);
        CheckBox desporto = (CheckBox) findViewById(R.id.desporto);
        CheckBox moda = (CheckBox) findViewById(R.id.moda);
        CheckBox musica = (CheckBox) findViewById(R.id.musica);
        CheckBox escrita = (CheckBox) findViewById(R.id.escrita);
        CheckBox cinema = (CheckBox) findViewById(R.id.cinema);
        CheckBox gastronomia = (CheckBox) findViewById(R.id.gastronomia);


        if (tecnologia.isChecked()) {
            interesses.add("Tecnologia");
        }

        if (desporto.isChecked()) {
            interesses.add("Desporto");
        }

        if (moda.isChecked()) {
            interesses.add("Moda");
        }
        if (musica.isChecked()) {
            interesses.add("Musica");
        }
        if (escrita.isChecked()) {
            interesses.add("Escrita");
        }
        if (cinema.isChecked()) {
            interesses.add("Cinema");
        }
        if (gastronomia.isChecked()) {
            interesses.add("Gastronomia");
        }

        for (String interesse : interesses) {
            insertInteresse(interesse);
        }
    }

    public List<Interesses> AdicInt(User user){
        ArrayList<Interesses> intere = new ArrayList<Interesses>(10);
        Interesses inter = new Interesses(UserInterests.this);
        SQLiteDatabase db= inter.getWritableDatabase();

        String SQL = "SELECT * FROM TABLE Interesses WHERE user_id=?";
        Cursor cursor = db.rawQuery(SQL, new String[]{String.valueOf(user.getId())});

        while(cursor.moveToNext()){

           String interesse = cursor.getString(1);

        }
        return intere;
    }
}


