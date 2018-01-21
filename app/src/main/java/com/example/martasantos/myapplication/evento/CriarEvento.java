package com.example.martasantos.myapplication.evento;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

import com.example.martasantos.myapplication.HorasL;
import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.database.DbHelper;

import java.util.ArrayList;

public class CriarEvento extends AppCompatActivity {

    Button criarEvento;
    ArrayList<DbHelper> eventos;
    EditText nomeEvento, localEvento, comeca, duracao, dataE;

   private  SharedPreferences sharedPreferences;
   private SharedPreferences sharedEventos;
   private  SharedPreferences.Editor editor;
    private String nome = "";


    private void insertEvent() throws Exception{
        ArrayList<DbHelper> eventos = new ArrayList<DbHelper>();
        DbHelper events = new DbHelper(CriarEvento.this);
        SQLiteDatabase db = events.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nomeEvento", nomeEvento.getText().toString());
        values.put("localEvento", localEvento.getText().toString());
        values.put("data", dataE.getText().toString());
        values.put("comeca", comeca.getText().toString());
        values.put("duracao", duracao.getText().toString());

        long rowId = db.insert("events", null, values);
        if (rowId < 0) {
            throw new Exception("Erro aqui");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<DbHelper> eventos = new ArrayList<DbHelper>(10);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        nome = sharedPreferences.getString("nome", "");

        sharedPreferences = getSharedPreferences("Data",MODE_PRIVATE);
        int mes=sharedPreferences.getInt("mes",0);
        int dia=sharedPreferences.getInt("dia",0);
        int ano=sharedPreferences.getInt("ano",0);

        String data= String.valueOf(mes)+"/"+String.valueOf(dia)+"/"+String.valueOf(ano);

        nomeEvento=(EditText)findViewById(R.id.nomeEvento);
        localEvento=(EditText)findViewById(R.id.localEvento);
        dataE=(EditText)findViewById(R.id.dataEvento);
        dataE.setText(data);
        comeca=(EditText)findViewById(R.id.comeca);
        duracao=(EditText)findViewById(R.id.duracao);


        // perform click event listener on edit text
        comeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CriarEvento.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        comeca.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });



        sharedEventos = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedEventos.edit();

        criarEvento=(Button)findViewById(R.id.buttonCriarEvento);
        criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeEvento.length()==0){
                    Toast.makeText(CriarEvento.this, "Insira o seu nome do Evento", Toast.LENGTH_LONG).show();
                }else if (localEvento.length()==0) {
                    Toast.makeText(CriarEvento.this, "Insira o local do Evento", Toast.LENGTH_LONG).show();
                }else if (duracao.length()==0) {
                    Toast.makeText(CriarEvento.this, "Insira a duração do Evento", Toast.LENGTH_LONG).show();
                }else {
                    try {
                        insertEvent();

                        editor.putString("nomeEvento", nomeEvento.getText().toString());
                        editor.commit();

                        Intent d = new Intent(getApplicationContext(), HorasL.class);

                        startActivity(d);
                        addNotificationToEvent();

                   } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                }
        });

    }

    public void addNotificationToEvent(){
        Intent intent=new Intent();
        PendingIntent pIntent=PendingIntent.getActivity(CriarEvento.this,0,intent,0);
        Notification noti= new Notification.Builder(CriarEvento.this)
                .setTicker("EVENTO")
                .setContentTitle("Evento\n"+nomeEvento.getText().toString()+"\nCriado!!")
                .setContentText("o seu evento foi adicionado com sucesso!")
                .setSmallIcon(R.drawable.notifications)
                .setContentIntent(pIntent).getNotification();

        noti.flags=Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,noti);
    }

}
