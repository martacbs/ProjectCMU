package com.example.martasantos.myapplication.interests;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.martasantos.myapplication.Menu_Lateral;
import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.login.Login;
import com.example.martasantos.myapplication.models.User;

import java.util.ArrayList;

import java.util.List;

/**
 * Classe onde o utilizador define os seus interesses e os mesmos são guardados na base de dados
 */
public class UserInterests extends AppCompatActivity {

    CheckBox tecnologia, desporto, moda, musica, escrita, cinema, gastronomia;
    Button submeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interests);

        //ao clicar na seta no canto superior esquerdo retorna para a atividade anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submeter = (Button) findViewById(R.id.submeter);

        submeter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Interesses();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //recebe o id utilizador da google para atribuir os interesses aquele utilizador
                Long id_google = getIntent().getLongExtra("user_id_google", -1);
                if (id_google > 0) {
                    Intent h = new Intent(getApplicationContext(), Menu_Lateral.class);
                    startActivity(h);
                } else {
                    Intent j = new Intent(getApplicationContext(), Login.class);
                    startActivity(j);
                }
            }
        });
    }

    /**
     * Insere os interesses do utilizador na base de dados
     * @param valor interesses do utilizador
     * @throws Exception se não for possível guardar os interesses do utilizador
     */
    private void insertInteresse(String valor) throws Exception {

        ContentValues values = new ContentValues();
        values.put("Interesses", valor);

        DbHelper dbHelper = new DbHelper(UserInterests.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        // recebe o id que vem do registo de utilizador registo normal para atribuir os interesses aquele utilizador
        Long id = getIntent().getLongExtra("user_id", -1);

        if (id > 0) {
            values.put("user_id", id);
            long rowId = db.insert("interests", null, values);
            Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();

            if (rowId < 0) {
                throw new Exception("Não foi possível guardar os seus Interesses!");
            }
        }

        // recebe o id que vem do registo de utilizador registo conta google
        Long id_google = getIntent().getLongExtra("user_id_google", -1);
        if (id_google > 0) {

            values.put("user_id", id_google);
            long rowId = db.insert("interests", null, values);
            Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();

            if (rowId < 0) {
                throw new Exception("Não foi possível guardar os seus Interesses!");
            }
        }


        db.close();
    }

    /**
     * o utilizador escolhe através de uma Checkbox os seus interesses
     * @throws Exception se não for possível guardar os interesses
     */
    public void Interesses() throws Exception {

        List<String> interesses = new ArrayList<>();

        CheckBox tecnologia = (CheckBox) findViewById(R.id.tecnologia);
        CheckBox desporto = (CheckBox) findViewById(R.id.desporto);
        CheckBox moda = (CheckBox) findViewById(R.id.moda);
        CheckBox musica = (CheckBox) findViewById(R.id.musica);
        CheckBox escrita = (CheckBox) findViewById(R.id.escrita);
        CheckBox atracoes = (CheckBox) findViewById(R.id.atracoes);
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
        if (atracoes.isChecked()) {
            interesses.add("Atrações");
        }
        if (gastronomia.isChecked()) {
            interesses.add("Gastronomia");
        }

        for (String interesse : interesses) {
            insertInteresse(interesse);
        }

    }

    /**
     * Adicionar os intesses
     * @param user para saber a que user se refere
     * @return os interesses
     */
    public List<DbHelper> AdicInt(User user) {
        ArrayList<DbHelper> intere = new ArrayList<DbHelper>(10);
        DbHelper dbHelper = new DbHelper(UserInterests.this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String SQL = "SELECT * FROM TABLE interests WHERE user_id=?";
        Cursor cursor = db.rawQuery(SQL, new String[]{String.valueOf(user.getId())});

        while (cursor.moveToNext()) {

            String interesse = cursor.getString(1);

        }
        return intere;
    }

}


