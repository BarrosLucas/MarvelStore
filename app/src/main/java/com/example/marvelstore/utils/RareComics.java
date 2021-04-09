package com.example.marvelstore.utils;

import android.util.Log;

import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.view.HomeActivity;

import java.util.ArrayList;

public class RareComics {

    public static void generateRareComics(){
        HomeActivity.rareComics = new ArrayList<>();
        int size = ((int)(48*0.12))+1;
        for(int i = 0; i < size; i++){
            int n;
            do{
                n = (int)(Math.random()*((47-0)+1));
            }while(isRare(n));
            HomeActivity.rareComics.add(n);
            Log.i("Rare "+(i+1),"Index: "+HomeActivity.rareComics.get(i));
        }
    }
    public static boolean isRare(int index){
        for(int i: HomeActivity.rareComics){
            if(index == i){
                return true;
            }
        }
        return false;
    }
}
