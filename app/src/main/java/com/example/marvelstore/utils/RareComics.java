package com.example.marvelstore.utils;

import android.util.Log;

import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.view.HomeActivity;

import java.util.ArrayList;

public class RareComics {

    /*Gera índices aleatórios baseado na porcentagem de 12%*/
    public static void generateRareComics(){
        HomeActivity.rareComics = new ArrayList<>();
        int size = ((int)(48*0.12))+1; //Calcula a quantidade de quadrinhos raros que vão aparecer
        for(int i = 0; i < size; i++){ //na página considerando que cada página contém 48 quadrinhos
            int n;
            do{
                n = (int)(Math.random()*((47-0)+1)); //Gera um número aleataório entre 0 e 47
            }while(isRare(n)); //Garante que nenhum seja repetido
            HomeActivity.rareComics.add(n);
        }
    }

    //Analisa se o índice de determinado quadrinho já está na lista dos raros
    public static boolean isRare(int index){
        for(int i: HomeActivity.rareComics){
            if(index == i){
                return true;
            }
        }
        return false;
    }
}
