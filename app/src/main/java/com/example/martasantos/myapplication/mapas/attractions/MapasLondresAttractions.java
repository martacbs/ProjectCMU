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
 * Created by martasantos on 17/01/18.
 */

public class MapasLondresAttractions extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapas);
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        //zoomToLocationLondon();
        getApi().getListPointsOfInterest("London", "attraction", "Science")
                .enqueue(new Callback<List<POI>>() {
                    @Override
                    public void onResponse(Call<List<POI>> call, Response<List<POI>> response) {
                        List<POI> points = response.body();
                        zoomToLocationLondon();
                        addMarkerLondon(points);
                    }

                    @Override
                    public void onFailure(Call<List<POI>> call, Throwable t) {
                    }

                });

    }


    private void addMarkerLondon(List<POI> poi) {

        for (int i = 0; i < poi.size(); i++) {
            LatLng latLng = new LatLng(poi.get(i).getLat(), poi.get(i).getLng());

            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(poi.get(i).getCategory())
                    .snippet(poi.get(i).getDetails()));
        }
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });
    }

    private void addMarkerAmsterdan(List<POI> poi){

        for(int i=0; i<poi.size();i++) {
            LatLng latLng = new LatLng(poi.get(i).getLat(), poi.get(i).getLng());

            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(poi.get(i).getCategory())
                    .snippet(poi.get(i).getDetails()));
        }
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });

    }/*
    private void addMarker(){
        LatLng latLng=new LatLng(47.3668491,-8.1947272);

        Marker marker=mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("ESTG")
                .snippet("Escola Superior de Tecnologia e Gestão"));

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });

    }*/

    private void zoomToLocationLondon(){
        LatLng latLng=new LatLng(51.496715,-0.1757499);

        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,18);

        mGoogleMap.animateCamera(cameraUpdate);


    }

    private void zoomToLocationAmsterdam(){
        LatLng latLng=new LatLng(52.3545649,4.7581975);

        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,18);

        mGoogleMap.animateCamera(cameraUpdate);


    }


    private void zoomToLocation(){
        LatLng latLng=new LatLng(0,-0);

        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,18);

        mGoogleMap.animateCamera(cameraUpdate);


    }

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

