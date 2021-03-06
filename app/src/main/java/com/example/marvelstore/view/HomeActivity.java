package com.example.marvelstore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.HomeController;
import com.example.marvelstore.model.Comic;
import com.example.marvelstore.model.ComicToCart;
import com.example.marvelstore.model.Image;
import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.utils.RareComics;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    public static RecyclerView recyclerView;
    public static ArrayList<ComicToCart> comics;
    public static String coupon;
    public static ReturnBody returnBody;
    public static ArrayList<Integer> rareComics;

    /*Constante para desserialização dos quadrinhos*/
    public static final String ARG_COMICS = "comics";

    /*Atributos da view*/
    private ImageView firstPage;
    private ImageView backButton;
    private Button firstButton;
    private Button secondButton;
    private Button thirtyButton;
    private ImageView forwardButton;
    private ImageView lastPage;

    private LinearLayout fragment;
    private LinearLayout progress;

    private HomeController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        comics = new ArrayList<>();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    /*Ao clicar no carrinho o usuário é redirecionado à tela de checkout... Porém se ele não tiver
    * nada no carrinho, uma mensagem em toast é enviada informando que não há nada no carrinho*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                if(comics.size()>0) {
                    Intent intent = new Intent(this, CartActivity.class);
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