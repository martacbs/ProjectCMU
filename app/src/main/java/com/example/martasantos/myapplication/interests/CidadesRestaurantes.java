package com.example.martasantos.myapplication.interests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.mapas.attractions.MapasParisAttractions;
import com.example.martasantos.myapplication.mapas.restaurants.MapasAmsterdamRestaurants;
import com.example.martasantos.myapplication.mapas.restaurants.MapasBarcelonaRestaurants;
import com.example.martasantos.myapplication.mapas.restaurants.MapasBerlimRestaurants;
import com.example.martasantos.myapplication.mapas.restaurants.MapasDubaiRestaurants;
import com.example.martasantos.myapplication.mapas.restaurants.MapasLondresRestaurants;
import com.example.martasantos.myapplication.mapas.restaurants.MapasParisRestaurants;
import com.example.martasantos.myapplication.mapas.restaurants.MapasRomaRestaurants;


/**
 * Classe onde o utilizador seleciona a cidade que pretende para ser mostrado os restaurantes dessa cidade
 */
public class CidadesRestaurantes extends AppCompatActivity {

    Button restaurantesCidades;
    Spinner restaurantesSpinner;
    private String[] cidadesRestaurantes = {"Amesterdão", "Barcelona", "Berlim", "Dubai", "Londres", "Paris", "Roma"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidades_restaurantes);

        //ao clicar na seta no canto superior esquerdo retorna para a atividade anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //criação do spinner
        restaurantesSpinner = (Spinner) findViewById(R.id.spinnerRestaurantes);
        ArrayAdapter<String> adapterRestaurantes = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cidadesRestaurantes);
        restaurantesSpinner.setAdapter(adapterRestaurantes);
        restaurantesCidades = (Button) findViewById(R.id.botaoRestaurantes);

        /**
         * conseguir obter a informação de qual item foi selecionado no spinner
        */
        restaurantesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * botão que de acordo com a cidade que o utilizador escolhe no spinner o reencaminha para outra atividade onde é mostrado
         * markers no mapa de acordo com os restaurantes da cidade
         */
        restaurantesCidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicoesRestaurants = restaurantesSpinner.getSelectedItemPosition();
                if (posicoesRestaurants == 0) {
                    Intent amesterdaoRest = new Intent(getApplicationContext(), MapasAmsterdamRestaurants.class);
                    startActivity(amesterdaoRest);
                } else if (posicoesRestaurants == 1) {
                    Intent barcelonaRest = new Intent(getApplicationContext(), MapasBarcelonaRestaurants.class);
                    startActivity(barcelonaRest);
                } else if (posicoesRestaurants == 2) {
                    Intent berlimRest = new Intent(getApplicationContext(), MapasBerlimRestaurants.class);
                    startActivity(berlimRest);

                } else if (posicoesRestaurants == 3) {
                    Intent dubaiRest = new Intent(getApplicationContext(), MapasDubaiRestaurants.class);
                    startActivity(dubaiRest);
                } else if (posicoesRestaurants == 4) {
                    Intent londresRest = new Intent(getApplicationContext(), MapasLondresRestaurants.class);
                    startActivity(londresRest);
                } else if (posicoesRestaurants == 5) {
                    Intent parisRest = new Intent(getApplicationContext(), MapasParisRestaurants.class);
                    startActivity(parisRest);
                } else if (posicoesRestaurants == 6) {
                    Intent romaRest = new Intent(getApplicationContext(), MapasRomaRestaurants.class);
                    startActivity(romaRest);
                }

            }
        });
    }
}
