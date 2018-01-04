package com.example.martasantos.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martasantos on 01/01/18.
 */

public class Interesses extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "interests.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_INTERESTS="interests";

    public Interesses(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE interests (id INTEGER PRIMARY KEY AUTOINCREMENT, interesses VARCHAR(100) NOT NULL, FOREIGN KEY(user_id) REFERENCES user(id) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
