package com.example.martasantos.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.martasantos.myapplication.login.Login;
import com.example.martasantos.myapplication.register.UserRegister;


public class MainActivity extends AppCompatActivity {


    Button register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(Button)findViewById(R.id.bRegister);
        login=(Button)findViewById(R.id.bLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent x= new Intent(getApplicationContext(),UserRegister.class);
                startActivity(x);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y=new Intent(getApplicationContext(),Login.class);
                startActivity(y);
                //
            }
        });

    }
    }

