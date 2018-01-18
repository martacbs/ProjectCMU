package com.example.martasantos.myapplication;

import android.content.ContentValues;
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
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import com.example.martasantos.myapplication.database.DbHelper;

import java.util.ArrayList;

public class CriarEvento extends AppCompatActivity {

    Button criarEvento;
    ArrayList<DbHelper> eventos;
    EditText nomeEvento, localEvento, comeca, duracao, dataE;
    private String [] lembrete  = new String[]{"1","2","3","4"};
    private Spinner sp;
    SharedPreferences sharedPreferences;

EditText time;

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
        values.put("lembrete",sp.getSelectedItem().toString());
        long rowId = db.insert("events", null, values);
        if (rowId < 0) {
            throw new Exception("Erro aqui");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);
        ArrayList<DbHelper> eventos = new ArrayList<DbHelper>(10);
        sp=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter= new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,lembrete );
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        //sp.setAdapter(adapter);

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


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        criarEvento=(Button)findViewById(R.id.buttonCriarEvento);
        criarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insertEvent();
                    Intent d = new Intent(getApplicationContext(), HorasL.class);
                    Bundle b = new Bundle();
                    b.putString("nomeEvento",nomeEvento.getText().toString());
                    d.putExtras(b);
                    startActivity(d);
                    Toast.makeText(getApplicationContext(), "Evento Criado com sucesso", Toast.LENGTH_SHORT).show();
                }catch (Exception e ){
                    e.printStackTrace();
               }
                }
        });

    }
}
