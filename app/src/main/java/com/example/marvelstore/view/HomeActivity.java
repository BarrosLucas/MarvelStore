package com.example.marvelstore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.HomeController;
import com.example.marvelstore.model.Image;
import com.example.marvelstore.model.ReturnBody;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity {

    private HomeController controller;

    public static ReturnBody returnBody;

    public static final String ARG_COMICS = "comics";

    private ImageView firstPage;
    private ImageView backButton;
    private Button firstButton;
    private Button secondButton;
    private Button thirtyButton;
    private ImageView forwardButton;
    private ImageView lastPage;

    private LinearLayout fragment;
    private LinearLayout progress;

    public static RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gson gson = new Gson();
        String retBody = getIntent().getStringExtra(ARG_COMICS);
        returnBody = gson.fromJson(retBody,ReturnBody.class);

        setContentView(R.layout.activity_home);


        firstPage = (ImageView) findViewById(R.id.first_page);
        backButton = (ImageView) findViewById(R.id.back);
        firstButton = (Button) findViewById(R.id.first_button);
        secondButton = (Button) findViewById(R.id.second_button);
        thirtyButton = (Button) findViewById(R.id.thirty_button);
        forwardButton = (ImageView) findViewById(R.id.forward);
        lastPage = (ImageView) findViewById(R.id.last_page);

        fragment = (LinearLayout) findViewById(R.id.fragment);
        progress = (LinearLayout) findViewById(R.id.progress);


        controller = new HomeController(firstPage,backButton,firstButton,secondButton,thirtyButton,forwardButton,lastPage,this,fragment,progress);
    }
}