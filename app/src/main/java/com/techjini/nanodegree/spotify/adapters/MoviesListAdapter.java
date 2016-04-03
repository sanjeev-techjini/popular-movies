package com.techjini.nanodegree.spotify.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techjini.nanodegree.spotify.R;
import com.techjini.nanodegree.spotify.databinding.AdapterMoviesListBinding;
import com.techjini.nanodegree.spotify.listeners.OnItemClickListener;
import com.techjini.nanodegree.spotify.models.MoviesItemModel;

import java.util.ArrayList;

/**
 * Created by Sanjeev on 3/27/16.
 */
public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {
    private final ArrayList<MoviesItemModel> mMoviesList;
    private final OnItemClickListener mListener;

    public MoviesListAdapter(ArrayList<MoviesItemModel> moviesList, OnItemClickListener listener) {
        this.mMoviesList = moviesList;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterMoviesListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_movies_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setMovies(mMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final AdapterMoviesListBinding binding;

        public ViewHolder(AdapterMoviesListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setHandler(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getLayoutPosition());
        }
    }
}
