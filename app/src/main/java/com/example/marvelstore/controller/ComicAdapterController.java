package com.example.marvelstore.controller;

import com.example.marvelstore.view.adapters.ComicRecyclerViewAdapter;
import com.example.marvelstore.model.Comic;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.utils.DownloadImage;

import java.util.List;

public class ComicAdapterController {

    public void loadiItem(List<Comic> comics, final ComicRecyclerViewAdapter.ViewHolder holder, int position){
        holder.mItem = comics.get(position);

        String url = comics.get(position).getThumbnail().getPath()+"/portrait_medium."+comics.get(position).getThumbnail().getExtension();
        url = url.replace("http://","https://");

        new DownloadImage(holder.picture).execute(url);

        holder.title.setText(comics.get(position).getTitle());
        holder.price.setText("USD "+ Pratice.converterDoubleString(comics.get(position).getPrices().get(0).getPrice()));
    }

}
