package com.example.marvelstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.SplashScreenController;

public class SplashScreenActivity extends AppCompatActivity {
    SplashScreenController controller;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        controller = new SplashScreenController(this,this,progressBar);

    }
}