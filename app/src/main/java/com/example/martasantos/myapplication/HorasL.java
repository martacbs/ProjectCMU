package com.example.martasantos.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.martasantos.myapplication.adapter.HorasAdapter;
import com.example.martasantos.myapplication.models.Horas;

import java.util.ArrayList;

/**
 * Created by martasantos on 31/12/17.
 */

public class HorasL extends AppCompatActivity {

    ArrayList<Horas> horas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horas);

        RecyclerView rvHoras =(RecyclerView) findViewById(R.id.rvHoras);

        horas = createContactList(24);

        HorasAdapter adapter = new HorasAdapter(this, horas);

        rvHoras.setAdapter(adapter);

        rvHoras.setLayoutManager(new LinearLayoutManager(
                this));

    }

    private ArrayList<Horas> createContactList (int x){
        ArrayList<Horas> horas = new ArrayList<>(x);
        for(int i=0;i<x;i++){
            Horas h =null;
            String hora=  i + ":00" ;
            if(i< x){
                h =new Horas (hora);
            }else{
                h=new Horas(hora);
            }
            horas.add(h);
        }
        return horas;
    }
}
