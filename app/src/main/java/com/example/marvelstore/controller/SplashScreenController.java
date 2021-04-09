package com.example.marvelstore.controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.retrofit.RetrofitInit;
import com.example.marvelstore.utils.Dialog;
import com.example.marvelstore.utils.Keys;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.view.HomeActivity;
import com.example.marvelstore.view.SplashScreenActivity;
import com.google.gson.Gson;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenController{
    private static final String ARG_COMICS = "comics";

    Context context;
    SplashScreenActivity activity;
    ProgressBar progressBar;

    public SplashScreenController(Context context, SplashScreenActivity activity, ProgressBar progressBar){
        this.context = context;
        this.activity = activity;
        this.progressBar = progressBar;
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                getComics();
                progressBar.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    public void getComics(){
        long ts = Pratice.getTimestamp();
        String hash = Pratice.getHash(ts+"",Keys.privateApiKeyMarvel,Keys.publicApiKeyMarvel);

        Call<ReturnBody> call = new RetrofitInit().getMarvelService().getComics("-onsaleDate","48","0",ts+"", Keys.publicApiKeyMarvel,hash);
        call.enqueue(new Callback<ReturnBody>() {
            @Override
            public void onResponse(Call<ReturnBody> call, Response<ReturnBody> response) {
                if(response.isSuccessful()){
                    ReturnBody returnBody = response.body();

                    Gson gson = new Gson();

                    progressBar.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra(ARG_COMICS,gson.toJson(returnBody));
                    context.startActivity(intent);
                    activity.finish();
                }else{
                    Dialog.showAlertDialog(context,"Something Went Wrong","Try again later");
                    Log.i("Response Error Code: ",response.code()+"");
                    Log.i("Response Error Body: ",response.message());
                }
            }

            @Override
            public void onFailure(Call<ReturnBody> call, Throwable t) {
                Dialog.showAlertDialog(context,"Connection Fail","Check your internet connection");
                Log.i("Request","Fail");
                Log.i("Request",t.getMessage());
                Log.i("Request",t.getLocalizedMessage());
                Log.i("Request",t.getStackTrace().toString());
            }
        });
    }



}
