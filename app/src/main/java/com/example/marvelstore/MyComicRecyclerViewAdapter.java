package com.example.marvelstore;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marvelstore.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class MyComicRecyclerViewAdapter extends RecyclerView.Adapter<MyComicRecyclerViewAdapter.ViewHolder> {

    private final List<Comic> mValues;

    public MyComicRecyclerViewAdapter(List<Comic> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_comic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Picasso.get().load(mValues.get(position).getThumbnail().getPath()+"portrait_medium."+mValues.get(position).getThumbnail().getExtension()).into(holder.picture);
        holder.title.setText(mValues.get(position).getTitle());
        holder.price.setText(mValues.get(position).getPrices().get(0).getPrice()+"");
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