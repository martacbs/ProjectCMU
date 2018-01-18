package com.example.martasantos.myapplication.retrofit;

import com.example.martasantos.myapplication.retrofit.POI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by martasantos on 19/12/17.
 */

public interface TourDataApi {


    @GET("getPlaces")
    Call<List<POI>> getListPointsOfInterest(@Query("location") String location,
                                            @Query("category") String category,
                                            @Query("name") String name);
}
