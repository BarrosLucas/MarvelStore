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

        /*Preenche as views (ImageView para a thumb e o TextView para o título)*/
        new DownloadImage(holder.picture).execute(holder.mItem.getUrlImg());
        holder.title.setText(holder.mItem.getTitle());

        /*Atualiza a quantidade do produto, sabendo que o usuário pode está reabrindo a tela
        * e tendo quadrinhos previamente salvos*/
        setAmount(holder.mItem.getAmount(),position,cartController);

        /*Se a quantidade selecionada for 1, não faz sentido ter o botão de "-"*/
        controllButtons(position);

        /*A cada vez que o botão "-" é clicado, é descrescentado 1 da quantidade do item, que
        * permanece visível somente se a quantidade for maior que 1*/
        holder.less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAmount(holder.mItem.getAmount()-1,position,cartController);
            }
        });

        /*Acrescenta 1 a quantidade sempre que o "+" é clicado*/
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAmount(holder.mItem.getAmount()+1,position,cartController);
            }
        });

        /*Deleta o item mantendo a lista sempre atualizada*/
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.comics.remove(getIndex(holder.mItem.getCode()));
                cartController.refreshRecycle();
            }
        });
    }

    /*Controla para que todos os itens que dependem da quantidade se mantenham íntegros e atualizados*/
    public void setAmount(int value, int position, CartController cartController){
        HomeActivity.comics.get(getIndex(holder.mItem.getCode())).setAmount(value);

        holder.amount.setText(holder.mItem.getAmount()+"");
        holder.price.setText("USD "+ Pratice.converterDoubleString(holder.mItem.getPriceUnity()*holder.mItem.getAmount()));
        controllButtons(position);
        cartController.refreshValues();
    }

    /*Pega o índice do Quadrinho baseado no seu código identificador*/
    private int getIndex(int code){
        for(int i = 0; i < HomeActivity.comics.size();i++){
            if(HomeActivity.comics.get(i).getCode()==code){
                return i;
            }
        }
        return -1;
    }

    /*Garante que o botão fique invisível caso a quantidade chegue a 1*/
    public void controllButtons(int position){
        if(HomeActivity.comics.get(position).getAmount()>1){
            holder.less.setVisibility(View.VISIBLE);
        }else{
            holder.less.setVisibility(View.INVISIBLE);
        }
    }


}
