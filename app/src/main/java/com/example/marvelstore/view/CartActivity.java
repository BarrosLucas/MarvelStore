package com.example.marvelstore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.CartController;
import com.example.marvelstore.model.ComicToCart;
import com.example.marvelstore.model.ReturnBody;
import com.example.marvelstore.view.adapters.CartItemViewAdapter;
import com.google.gson.Gson;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editText;
    private Button apply;
    private TextView totalPrice;
    private TextView removePrice;
    private TextView price;
    private Button finish;
    private Button back;


    private CartController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        editText = (EditText) findViewById(R.id.edit);
        apply = (Button) findViewById(R.id.apply);
        totalPrice = (TextView) findViewById(R.id.total_price);
        removePrice = (TextView) findViewById(R.id.remove_price);
        price = (TextView) findViewById(R.id.price);
        finish = (Button) findViewById(R.id.finish);
        back = (Button) findViewById(R.id.back);

        controller = new CartController(recyclerView,editText,apply,totalPrice,removePrice,price,finish,back,this,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartItemViewAdapter adapter = new CartItemViewAdapter(HomeActivity.comics,controller);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}