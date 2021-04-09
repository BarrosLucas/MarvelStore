package com.example.marvelstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.ComicController;
import com.example.marvelstore.model.Comic;
import com.example.marvelstore.model.ReturnBody;
import com.google.gson.Gson;

public class ComicActivity extends AppCompatActivity {
    private static final String ARG_COMIC = "comic";

    private ImageView thumb;

    private TextView title;
    private TextView price;
    private Button cart;
    private TextView about;

    private Comic comic;

    private ComicController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        thumb = (ImageView) findViewById(R.id.thumb);
        title = (TextView) findViewById(R.id.description_title);
        price = (TextView) findViewById(R.id.price_description);
        cart = (Button) findViewById(R.id.cart);
        about = (TextView) findViewById(R.id.about);

        Gson gson = new Gson();
        String retBody = getIntent().getStringExtra(ARG_COMIC);
        comic = gson.fromJson(retBody, Comic.class);

        controller = new ComicController(thumb,title,price,cart,about,comic,this,this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}