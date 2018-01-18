package com.example.martasantos.myapplication.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by martasantos on 19/12/17.
 */

public interface FlickrApi {


    @GET("getPhotos")
    Call<List<POI>> getListPointsOfInterest(@Query("location") String location,
                                            @Query("category") String category,
                                            @Query("name") String name);
}
