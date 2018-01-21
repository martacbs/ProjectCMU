package com.example.martasantos.myapplication.login;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martasantos.myapplication.Menu_Lateral;
import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.fragments.Calendar;
import com.example.martasantos.myapplication.models.User;
import com.example.martasantos.myapplication.register.UserRegister;

import java.util.HashMap;

/**
 * Classe onde o utilizador efetua o login
 */

public class Login extends AppCompatActivity {

    Button login;
    EditText user, pass;
    private FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ao clicar na seta no canto superior esquerdo retorna para a atividade anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login = (Button) findViewById(R.id.buttonLogin);
        user = (EditText) findViewById(R.id.insertUser);
        pass = (EditText) findViewById(R.id.insertPass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();

                values.put("username", user.getText().toString());
                values.put("password", pass.getText().toString());

                //verifica se o utilizador existe
                User user = verificarUser();
                if (user != null) {

                    ProcessData p = new ProcessData();
                    p.execute(6000000, null);

                    Intent d = new Intent(getApplicationContext(), Menu_Lateral.class);
                    startActivity(d);

                } else {
                    Toast.makeText(Login.this, "Os dados introduzidos não são válidos", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    /**
     * Verifica se o utilizador já está registado na base de dados, se este estiver é retornado o User
     */

    private User verificarUser() {
        DbHelper dbHelper = new DbHelper(Login.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String query = "SELECT * FROM user WHERE username=? AND password=? ";
        Cursor c = db.rawQuery(query, new String[]{username, password});
        User user = null;
        try {
            if (c != null && c.moveToFirst()) {

                user = new User();
                user.setUsername(c.getString(5));
                user.setPassword(c.getString(6));
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "O utilizador não existe", Toast.LENGTH_SHORT).show();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return user;
    }


    /**
     * AsyncTask para efetuar a progress bar quando o utilizador faz login
     */
    public class ProcessData extends AsyncTask<Integer, String, String> {

        private ProgressDialog mProgressBar;

        @Override
        protected String doInBackground(Integer... integers) {

            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            return "not done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // mProgressBar.dismiss();
        }

        @Override
        protected void onPreExecute() {
            mProgressBar = new ProgressDialog(Login.this);
            mProgressBar.setCancelable(false);// nao pode ser cancelada
            mProgressBar.setTitle("Iniciando sessão...");
            mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressBar.show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }


}
