package com.example.marvelstore.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelstore.model.ComicToCart;
import com.example.marvelstore.utils.Coupon;
import com.example.marvelstore.utils.Dialog;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.view.CartActivity;
import com.example.marvelstore.view.HomeActivity;
import com.example.marvelstore.view.adapters.CartItemViewAdapter;

public class CartController {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button apply;
    private TextView totalPrice;
    private TextView removePrice;
    private TextView price;
    private Button finish;
    private Button back;
    private Context context;
    private CartActivity cartActivity;

    public CartController(RecyclerView recyclerView, EditText editText, Button apply, TextView totalPrice, TextView removePrice, TextView price, Button finish, Button back, Context context, CartActivity cartActivity) {
        this.recyclerView = recyclerView;
        this.editText = editText;
        this.apply = apply;
        this.totalPrice = totalPrice;
        this.removePrice = removePrice;
        this.price = price;
        this.finish = finish;
        this.back = back;
        this.context = context;
        this.cartActivity = cartActivity;
        defineFunctions();
        refreshValues();
    }

    public void refreshValues(){
        totalPrice.setText("USD "+ Pratice.converterDoubleString(sum()));
        removePrice.setText("- USD "+Pratice.converterDoubleString(verifyDiscount()));
        price.setText("USD "+ (Pratice.converterDoubleString(sum()-verifyDiscount())));
        if(HomeActivity.coupon != null){
            editText.setText(HomeActivity.coupon);
        }
    }

    public double verifyDiscount(){
        if(HomeActivity.coupon != null){
            for(int i = 0; i < Coupon.rare.length;i++){
                if(Coupon.rare[i].equalsIgnoreCase(HomeActivity.coupon)){
                    return (sum()*0.25);
                }
            }

            for(int i = 0; i < Coupon.commom.length;i++){
                if(Coupon.commom[i].equalsIgnoreCase(HomeActivity.coupon)){
                    return (sum()*0.10);
                }
            }
        }
        return 0;
    }

    private double sum(){
        double sum = 0;
        for(ComicToCart comicToCart: HomeActivity.comics){
            sum += comicToCart.getAmount()*comicToCart.getPriceUnity();
        }
        return sum;
    }

    private void defineFunctions(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartActivity.finish();
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyDiscount()==0){
                    Dialog.showAlertDialog(context,"Invalid Coupon","Check your coupon");
                }else{
                    HomeActivity.coupon = editText.getText().toString();
                    refreshValues();
                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog.showAlertDialogToFinishPurchase(view,context,cartActivity);
            }
        });
    }
    public void refreshRecycle(){
        CartItemViewAdapter adapter = new CartItemViewAdapter(HomeActivity.comics,this);
        recyclerView.setAdapter(adapter);
        refreshValues();
        if(HomeActivity.comics.size()==0){
            cartActivity.finish();
        }
    }
}
