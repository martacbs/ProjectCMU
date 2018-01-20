package com.example.martasantos.myapplication.login;

/**
 * Created by vieir on 19/01/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class StayLogin {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context Xcontext;

    private static final String STAY_LOGIN = " Stay_Login";
    private static final String NOME = "nomee";



    public  StayLogin (Context context){
        this.Xcontext = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(Xcontext);
        editor = preferences.edit();
    }

    public void createLoginSession(String nome) {
        editor.putBoolean(STAY_LOGIN, true);
        editor.putString(NOME, nome);

        editor.commit();
    }

    public void checkLogin() {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(Xcontext, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Xcontext.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(NOME, preferences.getString(NOME, null));

        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(Xcontext, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Xcontext.startActivity(i);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(STAY_LOGIN, false);
    }
}
