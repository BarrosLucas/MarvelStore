package com.example.marvelstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.marvelstore.R;
import com.example.marvelstore.model.ReturnBody;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    public static ReturnBody returnBody;

    private static final String ARG_COMICS = "comics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Gson gson = new Gson();

        String retBody = getIntent().getStringExtra(ARG_COMICS);
        Log.i("VEIO","Aqui: "+retBody);
        returnBody = gson.fromJson(retBody,ReturnBody.class);

        setContentView(R.layout.activity_home);
    }
}