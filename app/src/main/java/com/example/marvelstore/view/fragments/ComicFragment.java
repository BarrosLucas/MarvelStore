package com.example.marvelstore.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marvelstore.view.adapters.ComicRecyclerViewAdapter;
import com.example.marvelstore.R;
import com.example.marvelstore.view.HomeActivity;


public class ComicFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 3;

    public ComicFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ComicFragment newInstance(int columnCount) {
        ComicFragment fragment = new ComicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /*Se houver apenas um elemento na lista ele ficará em formato de lista horizontal.
    * Os dados são passados por meio do adapter para a recyclerview*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list_comic, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            HomeActivity.recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                HomeActivity.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                HomeActivity.recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            HomeActivity.recyclerView.setAdapter(new ComicRecyclerViewAdapter(HomeActivity.returnBody.getData().getResults()));
        }
        return view;
    }


}