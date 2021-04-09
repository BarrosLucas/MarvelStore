package com.example.marvelstore.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvelstore.model.Comic;
import com.example.marvelstore.model.ComicToCart;
import com.example.marvelstore.utils.DownloadImage;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.view.CartActivity;
import com.example.marvelstore.view.ComicActivity;
import com.example.marvelstore.view.HomeActivity;

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
        setFunctions();
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
                if(!alreadyExistsThisComic(comic)){
                    String url = comic.getThumbnail().getPath()+"/portrait_medium."+comic.getThumbnail().getExtension();
                    url = url.replace("http://","https://");
                    ComicToCart c = new ComicToCart(comic.getTitle(),comic.getPrices().get(0).getPrice(),url,1,comic.getId());
                    HomeActivity.comics.add(c);


                    Log.i("HomeActivityComics","Is null? "+(HomeActivity.comics==null));

                    Intent intent = new Intent(context, CartActivity.class);
                    context.startActivity(intent);
                    comicActivity.finish();
                }
            }
        });
    }

    private boolean alreadyExistsThisComic(Comic comic){
        for(ComicToCart c: HomeActivity.comics){
            if(c.getCode() == comic.getId()){
                return true;
            }
        }
        return false;
    }
}
