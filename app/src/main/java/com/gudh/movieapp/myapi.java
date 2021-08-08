package com.gudh.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


import java.util.List;

public interface myapi {
    @GET("3/movie/{category}")
     Call<model> getmodels(
             @Path("category") String category,
             @Query("api_key") String api_key
    );
}
