package com.example.marvelstore.controller;

import android.content.Intent;
import android.view.View;

import com.example.marvelstore.view.ComicActivity;
import com.example.marvelstore.view.adapters.ComicRecyclerViewAdapter;
import com.example.marvelstore.model.Comic;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.utils.DownloadImage;
import com.google.gson.Gson;

import java.util.List;

public class ComicAdapterController {

    private static final String ARG_COMIC = "comic";

    public void loadiItem(List<Comic> comics, final ComicRecyclerViewAdapter.ViewHolder holder, int position){
        holder.mItem = comics.get(position);

        String url = comics.get(position).getThumbnail().getPath()+"/portrait_medium."+comics.get(position).getThumbnail().getExtension();
        url = url.replace("http://","https://");

        new DownloadImage(holder.picture).execute(url);

        holder.title.setText(comics.get(position).getTitle());
        holder.price.setText("USD "+ Pratice.converterDoubleString(comics.get(position).getPrices().get(0).getPrice()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();

                Intent intent = new Intent(view.getContext(), ComicActivity.class);
                intent.putExtra(ARG_COMIC,gson.toJson(comics.get(position)));
                view.getContext().startActivity(intent);
            }
        });
    }

}
