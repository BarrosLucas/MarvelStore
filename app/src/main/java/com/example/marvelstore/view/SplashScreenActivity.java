package com.example.marvelstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.marvelstore.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override public void run() {
                goToHome();
            }
        }, 2000);
    }


    private void goToHome(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}