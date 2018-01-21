package com.example.martasantos.myapplication.mapas.attractions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.martasantos.myapplication.R;
import com.example.martasantos.myapplication.retrofit.POI;
import com.example.martasantos.myapplication.retrofit.TourDataApi;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe onde é mostado em mapa as atrações na cidade de Amesterdão
 */

public class MapasAmesterdamAttractions extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);

        //ao clicar na seta no canto superior esquerdo retorna para a atividade anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    /**
     * Apresenta as atrações existentes na cidade de Amesterdão
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        getApi().getListPointsOfInterest("Amsterdam", "attraction", "science")
                .enqueue(new Callback<List<POI>>(){
                    @Override
                    public void onResponse(Call<List<POI>> call, Response<List<POI>> response){
                        List<POI> points=response.body();
                        zoomToLocationAmsterdamAttractions();
                        addMarkerAmsterdamAttractions(points);
                    }
                    @Override
                    public void onFailure(Call<List<POI>> call, Throwable t){

                    }

                });
    }


    /**
     * Adiciona no mapa os markers das atrações
     * @param poi retorna as atracoes do local
     */
    private void addMarkerAmsterdamAttractions(List<POI> poi){
        for(int i=0; i<poi.size();i++) {
            LatLng latLng = new LatLng(poi.get(i).getLat(), poi.get(i).getLng());

            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(poi.get(i).getName())
                    .snippet(poi.get(i).getAddress()));
        }
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });

    }

    /**
     * permite-nos mostrar no mapa a cidade de Amsterdão de acordo com as coordenadas
     */
    private void zoomToLocationAmsterdamAttractions(){
        LatLng latLng=new LatLng(52.3545649,4.7581975);
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,18);
        mGoogleMap.animateCamera(cameraUpdate);
    }

    /**
     * permite fazer o zoom do local
     */
    private void zoomToLocation(){
        LatLng latLng=new LatLng(4,-2);
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,18);
        mGoogleMap.animateCamera(cameraUpdate);
    }

    /**
     * API onde se vai buscar a informação para ser disponibilizada em mapas
     */
    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://tour-pedia.org/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private TourDataApi getApi(){
        return getRetrofit().create(TourDataApi.class);
    }
}

