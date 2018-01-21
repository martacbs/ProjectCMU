package com.example.martasantos.myapplication.retrofit;

import com.example.martasantos.myapplication.retrofit.POI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Interface da Tourpedia que nos permite obter a localização, categoria e nome
 */

public interface TourDataApi {


    @GET("getPlaces")
    Call<List<POI>> getListPointsOfInterest(@Query("location") String location,
                                            @Query("category") String category,
                                            @Query("name") String name);
}
