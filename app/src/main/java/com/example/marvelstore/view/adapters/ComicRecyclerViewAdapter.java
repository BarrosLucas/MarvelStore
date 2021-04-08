package com.example.marvelstore.view.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvelstore.R;
import com.example.marvelstore.controller.ComicAdapterController;
import com.example.marvelstore.model.Comic;

import java.util.List;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class ComicRecyclerViewAdapter extends RecyclerView.Adapter<ComicRecyclerViewAdapter.ViewHolder> {

    private final List<Comic> mValues;
    private ComicAdapterController controller;

    public ComicRecyclerViewAdapter(List<Comic> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_comic, parent, false);
        controller = new ComicAdapterController();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        controller.loadiItem(mValues,holder,position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView picture;
        public final TextView title;
        public final TextView price;

        public Comic mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            picture = (ImageView) view.findViewById(R.id.pic);
            title   = (TextView) view.findViewById(R.id.title);
            price   = (TextView) view.findViewById(R.id.price);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}