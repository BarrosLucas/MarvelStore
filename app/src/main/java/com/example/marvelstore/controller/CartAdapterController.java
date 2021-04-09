package com.example.marvelstore.controller;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelstore.model.ComicToCart;
import com.example.marvelstore.utils.DownloadImage;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.view.CartActivity;
import com.example.marvelstore.view.HomeActivity;
import com.example.marvelstore.view.adapters.CartItemViewAdapter;

import java.util.List;

public class CartAdapterController {
    CartItemViewAdapter.ViewHolder holder;
    public void loadiItem(final CartItemViewAdapter.ViewHolder h, int position, CartController cartController, List<ComicToCart> comics){
        holder = h;

        holder.mItem = comics.get(position);

        new DownloadImage(holder.picture).execute(holder.mItem.getUrlImg());

        holder.title.setText(holder.mItem.getTitle());
        setAmount(holder.mItem.getAmount(),position,cartController);

        holder.less.setVisibility(View.INVISIBLE);


        holder.less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAmount(holder.mItem.getAmount()-1,position,cartController);
            }
        });

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAmount(holder.mItem.getAmount()+1,position,cartController);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.comics.remove(getIndex(holder.mItem.getCode()));
                cartController.refreshRecycle();
            }
        });
    }

    public void setAmount(int value, int position, CartController cartController){
        Log.i("Antes","Title: "+holder.mItem.getTitle()+"\n\tAmount: "+holder.mItem.getAmount()+"\n\tCode: "+holder.mItem.getCode());
        Log.i("Antes","Title: "+HomeActivity.comics.get(getIndex(holder.mItem.getCode())).getTitle()+"\n\tAmount: "+HomeActivity.comics.get(getIndex(holder.mItem.getCode())).getAmount()+"\n\tCode: "+HomeActivity.comics.get(getIndex(holder.mItem.getCode())).getCode());

        HomeActivity.comics.get(getIndex(holder.mItem.getCode())).setAmount(value);

        Log.i("Depois",HomeActivity.comics.get(getIndex(holder.mItem.getCode())).getAmount()+"");

        holder.amount.setText(holder.mItem.getAmount()+"");
        holder.price.setText("USD "+ Pratice.converterDoubleString(holder.mItem.getPriceUnity()*holder.mItem.getAmount()));
        controllButtons(position);
        cartController.refreshValues();
    }

    private int getIndex(int code){
        for(int i = 0; i < HomeActivity.comics.size();i++){
            if(HomeActivity.comics.get(i).getCode()==code){
                return i;
            }
        }
        return -1;
    }

    public void controllButtons(int position){
        if(HomeActivity.comics.get(position).getAmount()>1){
            holder.less.setVisibility(View.VISIBLE);
        }else{
            holder.less.setVisibility(View.INVISIBLE);
        }
    }


}
