package com.example.martasantos.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martasantos on 31/12/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER="user";
    private static final String TABLE_INTERESTS="interests";
    private static final String TABLE_EVENTS="events";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50) , email VARCHAR(50), localidade VARCHAR(50) , username VARCHAR(50) , password VARCHAR(50) ,confirmarPassword VARCHAR(50) )");
        db.execSQL("CREATE TABLE interests (id INTEGER PRIMARY KEY AUTOINCREMENT, interesses VARCHAR(100) NOT NULL, user_id INTEGER)");
        db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeEvento VARCHAR(50) NOT NULL, localEvento VARCHAR(50) NOT NULL, data VARCHAR(50) NOT NULL, comeca TIME NOT NULL, duracao VARCHAR(50) NOT NULL, lembrete VARCHAR(50) NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop database;");
        onCreate(db);
    }





}
