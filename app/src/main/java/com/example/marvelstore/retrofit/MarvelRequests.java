package com.example.marvelstore.retrofit;

import com.example.marvelstore.model.ReturnBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelRequests {
    @GET("comics")
    Call<ReturnBody> getComics(@Query("orderBy") String orderBy,
                               @Query("limit") String limit,
                               @Query("offset") String offset,
                               @Query("ts") String timestamp,
                               @Query("apikey") String apikey,
                               @Query("hash") String hash);
}
