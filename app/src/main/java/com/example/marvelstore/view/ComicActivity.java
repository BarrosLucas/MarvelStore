package com.example.marvelstore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.ComicController;
import com.example.marvelstore.model.Comic;
import com.example.marvelstore.model.ReturnBody;
import com.google.gson.Gson;

public class ComicActivity extends AppCompatActivity {
    private static final String ARG_COMIC = "comic";
    private static final String ARG_POSITION = "position";

    private ImageView thumb;

    private TextView title;
    private TextView price;
    private Button cart;
    private TextView about;
    private LinearLayout rare;

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
        rare = (LinearLayout) findViewById(R.id.rare);

        Gson gson = new Gson();
        String retBody = getIntent().getStringExtra(ARG_COMIC);
        int position = getIntent().getIntExtra(ARG_POSITION,-1);
        comic = gson.fromJson(retBody, Comic.class);

        controller = new ComicController(thumb,title,price,cart,about,comic,rare,position,this,this);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                if(HomeActivity.comics.size()>0){
                    Intent intent = new Intent(this,CartActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"You do not have any products in the cart.",Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}