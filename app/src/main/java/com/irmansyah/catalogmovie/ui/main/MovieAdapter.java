package com.irmansyah.catalogmovie.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.irmansyah.catalogmovie.data.model.Movie;
import com.irmansyah.catalogmovie.databinding.ItemMovieBinding;
import com.irmansyah.catalogmovie.ui.base.BaseViewHolder;
import com.irmansyah.catalogmovie.ui.detailMovie.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irmansyah on 23/02/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movies) {
        this.movieList = movies;
    }

    public void addItems(List<Movie> blogList) {
        movieList.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        movieList.clear();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends BaseViewHolder implements
            ItemMovieViewModel.ItemMovieViewModelListener{

        private ItemMovieBinding mBinding;
        private ItemMovieViewModel mMovieItemViewModel;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Movie movie = movieList.get(position);
            mMovieItemViewModel = new ItemMovieViewModel(movie, this);
            mBinding.setViewModel(mMovieItemViewModel);
            mBinding.executePendingBindings();
        }

        @Override
        public void gotoDetailMovieActivity(Movie movie) {
            Context context = mBinding.getRoot().getContext();
            context.startActivity(DetailMovieActivity.gotoDetailMovieActivity(context, movie));
        }
    }
}
