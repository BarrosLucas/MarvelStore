package com.example.marvelstore.controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.retrofit.RetrofitInit;
import com.example.marvelstore.utils.Keys;
import com.example.marvelstore.view.HomeActivity;
import com.example.marvelstore.view.SplashScreenActivity;
import com.google.gson.Gson;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenController{
    private static final String ARG_COMICS = "comics";

    Context context;
    SplashScreenActivity activity;

    public SplashScreenController(Context context, SplashScreenActivity activity){
        this.context = context;
        this.activity = activity;
        getComics();
    }

    public void getComics(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long ts = timestamp.getTime();
        String hash = getHash(ts+"",Keys.privateApiKeyMarvel,Keys.publicApiKeyMarvel);

        Call<ReturnBody> call = new RetrofitInit().getMarvelService().getComics("10",ts+"", Keys.publicApiKeyMarvel,hash);
        call.enqueue(new Callback<ReturnBody>() {
            @Override
            public void onResponse(Call<ReturnBody> call, Response<ReturnBody> response) {
                if(response.isSuccessful()){
                    ReturnBody returnBody = response.body();

                    Gson gson = new Gson();

                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra(ARG_COMICS,gson.toJson(returnBody));
                    context.startActivity(intent);
                    activity.finish();
                }else{
                    Log.i("Response Error Code: ",response.code()+"");
                    Log.i("Response Error Body: ",response.message());
                }
            }

            @Override
            public void onFailure(Call<ReturnBody> call, Throwable t) {
                Log.i("Request","Fail");
                Log.i("Request",t.getMessage());
                Log.i("Request",t.getLocalizedMessage());
                Log.i("Request",t.getStackTrace().toString());
            }
        });
    }

    private String getHash(String timestamp, String privateApiKey, String publicApiKey){
        String s = timestamp + privateApiKey + publicApiKey;
        MessageDigest m;

        try{
            m=MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new BigInteger(1,m.digest()).toString(16);
    }



}
