package com.example.martasantos.myapplication.register;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.fragments.Calendar;
import com.example.martasantos.myapplication.interests.UserInterests;
import com.example.martasantos.myapplication.login.Login;
import com.example.martasantos.myapplication.models.User;

/**
 * Classe onde é feito o registo do utilizador
 */
public class UserRegister extends AppCompatActivity {

    EditText name, email, localidade, username, password, confirmarPassword;
    Button registar;

    /**
     * Classe onde o utilizador faz o registo e é inserido na base de dados
     *
     * @return o id do utilizador
     * @throws Exception se o utilizador não for adicionado a base de dados
     */
    private long insertUser() throws Exception {
        DbHelper dbHelper = new DbHelper(UserRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name.getText().toString());
        values.put("email", email.getText().toString());
        values.put("localidade", localidade.getText().toString());
        values.put("username", username.getText().toString());
        values.put("password", password.getText().toString());
        values.put("confirmarPassword", confirmarPassword.getText().toString());

        //insere os dados na base de dados
        long rowId = db.insert("user", null, values);
        if (rowId < 0) {
            throw new Exception("O utilizador não pode ser inserido na base de dados");
        }

        return rowId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        //ao clicar na seta no canto superior esquerdo retorna para a atividade anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText) findViewById(R.id.insertNome);
        email = (EditText) findViewById(R.id.insertEmail);
        localidade = (EditText) findViewById(R.id.insertLocalidade);
        username = (EditText) findViewById(R.id.insertUsername);
        password = (EditText) findViewById(R.id.insertPassword);
        confirmarPassword = (EditText) findViewById(R.id.insertConfirmPassword);
        registar = (Button) findViewById(R.id.button_guardarRegisto);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obrigar o utilizador a preencher os campos obrigatórios
                if (name.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira o seu nome", Toast.LENGTH_LONG).show();
                } else if (email.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira o seu e-mail", Toast.LENGTH_LONG).show();
                } else if (username.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira um username", Toast.LENGTH_LONG).show();
                } else if (password.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira uma password", Toast.LENGTH_LONG).show();
                } else if (confirmarPassword.length() == 0) {
                    Toast.makeText(UserRegister.this, "Tem que confirmar a password", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().equals(confirmarPassword.getText().toString())) {
                    try {
                        //verifica se o utilizador e o email existe
                        if (!verificarEmail()) {
                            if (!verificarUsername()) {
                                long userid = insertUser();
                                Toast.makeText(getApplicationContext(), "register sucessful", Toast.LENGTH_SHORT).show();
                                Intent d = new Intent(getApplicationContext(), UserInterests.class);
                                //enviar o id do utilizador para os Interesses
                                Bundle b = new Bundle();
                                b.putLong("user_id", userid);
                                d.putExtras(b);
                                startActivity(d);
                            } else {
                                Toast.makeText(getApplicationContext(), "O username já existe", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "O email já existe", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(UserRegister.this, "As passwords não coincidem", Toast.LENGTH_LONG).show();
                }
            }

        });


    }

    /**
     * Verifica na base de dados se o email inserido ja existe
     */
    private boolean verificarEmail() {
        DbHelper dbHelper = new DbHelper(UserRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String em = email.getText().toString();

        String query = "SELECT * FROM user WHERE email=? ";
        Cursor c = db.rawQuery(query, new String[]{em});
        User user = null;
        try {
            if (c != null && c.moveToFirst()) {

                user = new User();
                user.setEmail(c.getString(2));
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    /**
     * Verifica na base de dados se o username inserido ja existe
     */
    private boolean verificarUsername() {
        DbHelper dbHelper = new DbHelper(UserRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String us = username.getText().toString();

        String query = "SELECT * FROM user WHERE username=? ";
        Cursor c = db.rawQuery(query, new String[]{us});
        User user = null;
        try {
            if (c != null && c.moveToFirst()) {

                user = new User();
                user.setEmail(c.getString(4));
                return true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return false;
    }
}



