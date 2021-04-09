package com.example.marvelstore.retrofit;

import com.example.marvelstore.utils.Connection;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInit {
    private final Retrofit retrofit;

    public RetrofitInit() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        retrofit = new retrofit2.Retrofit.Builder().
                baseUrl(Connection.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();

    }

    public MarvelRequests getMarvelService() {

        return retrofit.create(MarvelRequests.class);

    }
}
