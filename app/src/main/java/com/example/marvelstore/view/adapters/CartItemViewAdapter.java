package com.example.marvelstore.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.CartAdapterController;
import com.example.marvelstore.controller.CartController;
import com.example.marvelstore.model.ComicToCart;
import com.example.marvelstore.view.HomeActivity;

import java.util.List;

public class CartItemViewAdapter extends RecyclerView.Adapter<CartItemViewAdapter.ViewHolder> {
    private CartAdapterController controller;

    private CartController cartController;
    private List<ComicToCart> comicToCarts;
    public CartItemViewAdapter(List<ComicToCart> comics,CartController cartController) {
        this.cartController = cartController;
        comicToCarts = comics;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_cart, parent, false);
        controller = new CartAdapterController();
        return new CartItemViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        controller.loadiItem(holder,position,cartController,comicToCarts);
    }

    @Override
    public int getItemCount() {
        return HomeActivity.comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView picture;
        public final TextView title;
        public final Button less;
        public final Button more;
        public final TextView amount;
        public final ImageView delete;
        public final TextView price;
        public final CardView cardView;

        public ComicToCart mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            less = (Button) view.findViewById(R.id.less);
            more = (Button) view.findViewById(R.id.more);
            amount = (TextView) view.findViewById(R.id.amount);
            picture = (ImageView) view.findViewById(R.id.pic);
            delete = (ImageView) view.findViewById(R.id.delete);
            title   = (TextView) view.findViewById(R.id.title);
            price   = (TextView) view.findViewById(R.id.price);
            cardView= (CardView) view.findViewById(R.id.card);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
