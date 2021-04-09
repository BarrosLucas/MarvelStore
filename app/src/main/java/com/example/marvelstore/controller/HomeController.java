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

    /*Controla a página atual e o offset*/
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

        /*A página e o offset começam com 0*/
        currentPage = 0;
        offset = 0;

        /*Carrega os primeiros quadrinhos raros*/
        RareComics.generateRareComics();

        showMenuButtons();
        setButtonsFunctions();
    }


    /*Mostra os botões*/
    public void showMenuButtons(){
        /*Quando é a primeira página, os botoes de voltar e primeira página são ocultados,
         *e a primeira página apresenta a coloração escura, bem como os botões serão 1, 2 e 3
         * obrigatoriamente.*/
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
        }else if(currentPage > 0 && currentPage < (getAmountPage()-1)){ //Quando está entre a
            firstPage.setVisibility(View.VISIBLE); //primeira e a última, o botão do meio fica
            buttonBack.setVisibility(View.VISIBLE);//escuro e os demais ficam claros. As setas
            first.setVisibility(View.VISIBLE);     //da esquerada e da direitas ficam visíveis
            second.setVisibility(View.VISIBLE);    //e o controle do número da página é baseado
            thirty.setVisibility(View.VISIBLE);    //na página atual
            buttonForward.setVisibility(View.VISIBLE);
            lastPage.setVisibility(View.VISIBLE);

            first.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));
            second.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.selectColorButton,null));
            thirty.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.backgroundButtonsColors,null));

            first.setText(currentPage+"");
            second.setText((currentPage+1)+"");
            thirty.setText((currentPage+2)+"");
        }else if(currentPage == (getAmountPage()-1)){ //Quando é chegada a última página, o último
            firstPage.setVisibility(View.VISIBLE); //botão fica escuro, as setas da direita ficam
            buttonBack.setVisibility(View.VISIBLE);//invisíveis e a numeração das páginas segue
            first.setVisibility(View.VISIBLE);     //conforme o valor da página atual
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
        /*Vai para a primeira página independentemente da página que estiver*/
        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(0);
            }
        });

        /*Vai para a última página independentemente da página que estiver*/
        lastPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(getAmountPage()-1);
            }
        });

        /*Volta uma página em relação a página atual (não fica visível quando a página atual é 1)*/
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(currentPage-1);
            }
        });

        /*Avança uma página em relação a página atual (não fica visível quando chega na última página)*/
        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(currentPage+1);
            }
        });

        /*Vai para a página indicada no primeiro botao*/
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(Integer.parseInt(first.getText().toString()) - 1);
            }
        });

        /*Vai para a página indicada no segundo botão*/
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(Integer.parseInt(second.getText().toString()) - 1);
            }
        });

        /*Vai para a página indicada no terceiro botão*/
        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPage(Integer.parseInt(thirty.getText().toString()) - 1);
            }
        });
    }

    /*Atualiza o valor da página mantendo o controle e carregando as novas páginas*/
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

    /*Calcula o total de páginas*/
    public int getAmountPage(){
        int total = HomeActivity.returnBody.getData().getTotal();
        return (total%48==0)?(total/48):((total/48)+1);
    }

    /*Emite o progressbar para indicar que a página está carregando uma informação*/
    public void loading(){
        fragment.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    /*Omite o progressbar para mostrar a informação que foi carregada*/
    public void finishLoad(){
        fragment.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
    }

}
