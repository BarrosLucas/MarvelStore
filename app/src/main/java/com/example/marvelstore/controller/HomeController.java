package com.example.marvelstore.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;

import com.example.marvelstore.R;
import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.retrofit.RetrofitInit;
import com.example.marvelstore.utils.Dialog;
import com.example.marvelstore.utils.Keys;
import com.example.marvelstore.utils.Pratice;
import com.example.marvelstore.utils.RareComics;
import com.example.marvelstore.view.HomeActivity;
import com.example.marvelstore.view.adapters.ComicRecyclerViewAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeController {
    private final int ITEMS = 48;

    private ImageView firstPage;
    private ImageView buttonBack;
    private Button first;
    private Button second;
    private Button thirty;
    private ImageView buttonForward;
    private ImageView lastPage;

    LinearLayout fragment;
    LinearLayout progress;


    private int currentPage;

    private int offset;

    private Context context;

    public HomeController(ImageView firstPage, ImageView buttonBack, Button first, Button second, Button thirty, ImageView buttonForward, ImageView lastPage, Context context, LinearLayout fragment, LinearLayout progress){
        this.firstPage = firstPage;
        this.buttonBack = buttonBack;
        this.first = first;
        this.second = second;
        this.thirty = thirty;
        this.buttonForward = buttonForward;
        this.lastPage = lastPage;
        this.context = context;
        this.fragment = fragment;
        this.progress = progress;

        currentPage = 0;
        offset = 0;

        RareComics.generateRareComics();

        showMenuButtons();
        setButtonsFunctions();
    }


    public void showMenuButtons(){
        if(currentPage == 0){
            firstPage.setVisibility(View.INVISIBLE);
            buttonBack.setVisibility(View.INVISIBLE);
            first.setVisibility(View.VISIBLE);
            second.setVisibility(View.VISIBLE);
            thirty.setVisibility(View.VISIBLE);
            buttonForward.setVisibility(View.VISIBLE);
            lastPage.setVisibility(View.VISIBLE);

            first.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.selectColorButton,null));
            second.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));
            thirty.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));

            first.setText("1");
            second.setText("2");
            thirty.setText("3");
        }else if(currentPage > 0 && currentPage < (getAmountPage()-1)){
            firstPage.setVisibility(View.VISIBLE);
            buttonBack.setVisibility(View.VISIBLE);
            first.setVisibility(View.VISIBLE);
            second.setVisibility(View.VISIBLE);
            thirty.setVisibility(View.VISIBLE);
            buttonForward.setVisibility(View.VISIBLE);
            lastPage.setVisibility(View.VISIBLE);

            first.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));
            second.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.selectColorButton,null));
            thirty.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));

            first.setText(currentPage+"");
            second.setText((currentPage+1)+"");
            thirty.setText((currentPage+2)+"");
        }else if(currentPage == (getAmountPage()-1)){
            firstPage.setVisibility(View.VISIBLE);
            buttonBack.setVisibility(View.VISIBLE);
            first.setVisibility(View.VISIBLE);
            second.setVisibility(View.VISIBLE);
            thirty.setVisibility(View.VISIBLE);
            buttonForward.setVisibility(View.INVISIBLE);
            lastPage.setVisibility(View.INVISIBLE);

            first.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));
            second.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));
            thirty.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.selectColorButton,null));

            first.setText((currentPage-1)+"");
            second.setText((currentPage)+"");
            thirty.setText((currentPage+1)+"");
        }
    }

    public void setButtonsFunctions(){
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(0);
            }
        });
        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(getAmountPage()-1);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(currentPage-1);
            }
        });
        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(currentPage+1);
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(Integer.parseInt(first.getText().toString()) - 1);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(Integer.parseInt(second.getText().toString()) - 1);
            }
        });
        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(Integer.parseInt(thirty.getText().toString()) - 1);
            }
        });
    }

    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;

        offset = currentPage*ITEMS+1;

        long ts = Pratice.getTimestamp();

        String hash = Pratice.getHash(ts+"",Keys.privateApiKeyMarvel,Keys.publicApiKeyMarvel);


        showMenuButtons();
        Log.i("Valores","Offset: "+offset+"\n\tCurrentPage: "+currentPage);

        loading();
        Call<ReturnBody> call = new RetrofitInit().getMarvelService().getComics("-onsaleDate","48",""+offset,ts+"", Keys.publicApiKeyMarvel,hash);
        call.enqueue(new Callback<ReturnBody>() {
            @Override
            public void onResponse(Call<ReturnBody> call, Response<ReturnBody> response) {
                if(response.isSuccessful()){
                    HomeActivity.returnBody = response.body();
                    RareComics.generateRareComics();
                    HomeActivity.recyclerView.setAdapter(new ComicRecyclerViewAdapter(HomeActivity.returnBody.getData().getResults()));
                    finishLoad();
                }else{
                    Dialog.showAlertDialog(context,"Something Went Wrong","Try again later");
                    Log.i("Response Error Code: ",response.code()+"");
                    Log.i("Response Error Body: ",response.message());
                }
            }

            @Override
            public void onFailure(Call<ReturnBody> call, Throwable t) {
                Dialog.showAlertDialog(context,"Connection Fail","Check your internet connection");
                Log.i("Request","Fail");
                Log.i("Request",t.getMessage());
                Log.i("Request",t.getLocalizedMessage());
                Log.i("Request",t.getStackTrace().toString());
            }
        });
    }

    public int getAmountPage(){
        int total = HomeActivity.returnBody.getData().getTotal();
        return (total%48==0)?(total/48):((total/48)+1);
    }

    public void loading(){
        fragment.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    public void finishLoad(){
        fragment.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }

}
