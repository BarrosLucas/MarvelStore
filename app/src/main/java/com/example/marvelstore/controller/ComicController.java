package com.example.marvelstore.controller;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvelstore.model.Comic;
import com.example.marvelstore.utils.DownloadImage;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.view.ComicActivity;

public class ComicController {
    private ImageView thumb;
    private TextView title;
    private TextView price;
    private Button cart;
    private TextView about;
    private Comic comic;
    private Context context;
    private ComicActivity comicActivity;

    public ComicController(ImageView thumb,TextView title,TextView price,Button cart,TextView about,Comic comic,Context context,ComicActivity comicActivity){
        this.thumb = thumb;
        this.title = title;
        this.price = price;
        this.cart = cart;
        this.about = about;
        this.comic = comic;
        this.context = context;
        this.comicActivity = comicActivity;

        populateView();
    }

    private void populateView(){
        String url = comic.getThumbnail().getPath()+"/portrait_medium."+comic.getThumbnail().getExtension();
        url = url.replace("http://","https://");
        new DownloadImage(thumb).execute(url);

        title.setText(comic.getTitle());
        price.setText("USD "+ Pratice.converterDoubleString(comic.getPrices().get(0).getPrice()));
        about.setText(comic.getDescription());

    }

    private void setFunctions(){
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chamar nova tela
            }
        });
    }
}
