package com.example.martasantos.myapplication.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martasantos on 08/01/18.
 */

public class Events extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EVENTS="events";

    public Events(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeEvento VARCHAR(50) NOT NULL, localEvento VARCHAR(50) NOT NULL, comeca VARCHAR(50) NOT NULL, duracao VARCHAR(50) NOT NULL, lembrete VARCHAR(50) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_EVENTS);
        onCreate(db);
    }
}
