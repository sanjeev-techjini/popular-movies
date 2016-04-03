package com.techjini.nanodegree.spotify.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;

import com.techjini.nanodegree.spotify.R;
import com.techjini.nanodegree.spotify.adapters.MoviesGridAdapter;
import com.techjini.nanodegree.spotify.adapters.MoviesListAdapter;
import com.techjini.nanodegree.spotify.connections.handlers.CommonResponseHandlers;
import com.techjini.nanodegree.spotify.connections.listeners.ConnectionListener;
import com.techjini.nanodegree.spotify.connections.managers.APIUtils;
import com.techjini.nanodegree.spotify.constants.Constants;
import com.techjini.nanodegree.spotify.databinding.ActivityDashboardBinding;
import com.techjini.nanodegree.spotify.listeners.OnItemClickListener;
import com.techjini.nanodegree.spotify.models.MoviesMainModel;

public class DashboardActivity extends AppCompatActivity implements OnItemClickListener, AdapterView.OnItemSelectedListener {
    private ActivityDashboardBinding mBinding;
    private int mPageNum;
    private MoviesMainModel mMainMovieData;
    private int mSelectedDisplayPosition;
    private int mSelectedViewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        if (savedInstanceState != null) {
            mMainMovieData = savedInstanceState.getParcelable(Constants.BundleConstants.MOVIE_DATA);
            if (mMainMovieData != null) {
                mSelectedDisplayPosition = savedInstanceState.getInt(Constants.BundleConstants.SELECTED_DISPLAY_TYPE);
                mSelectedViewPosition = savedInstanceState.getInt(Constants.BundleConstants.SELECTED_VIEW_TYPE);
                initialize();
                fillData();
                return;
            }
        }
        initialize();
        getPopularMoviesList(mPageNum);
    }

    private void fillData() {
        switch (mSelectedViewPosition) {
            case 0:
                fillGridData();
                break;
            case 1:
                fillListData();
                break;
        }
        mBinding.viewList.setOnItemSelectedListener(this);
        mBinding.displayList.setOnItemSelectedListener(this);
    }

    private void getPopularMoviesList(int pageNum) {
        new APIUtils().getConnection().getPopularMovies(pageNum)
                .enqueue(new CommonResponseHandlers<MoviesMainModel>(new MovieResponseHandler()));
    }

    private void getMostRatedMoviesList(int pageNum) {
        new APIUtils().getConnection().getTopRatedMovies(pageNum)
                .enqueue(new CommonResponseHandlers<MoviesMainModel>(new MovieResponseHandler()));
    }

    private void initialize() {
        setTitle(getResources().getString(R.string.screen_title));
        mBinding.displayList.setSelection(mSelectedDisplayPosition, false);
        mBinding.viewList.setSelection(mSelectedViewPosition, false);
        mPageNum = 1;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.BundleConstants.MOVIE_DATA, mMainMovieData);
        outState.putInt(Constants.BundleConstants.SELECTED_DISPLAY_TYPE, mSelectedDisplayPosition);
        outState.putInt(Constants.BundleConstants.SELECTED_VIEW_TYPE, mSelectedViewPosition);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, MovieDetailScreenActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair.create(view, Constants.BundleConstants.MOVIE_DATA));
            intent.putExtra(Constants.BundleConstants.MOVIE_DATA, mMainMovieData.getMovies().get(position));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void fillGridData() {
        int count;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            count = 4;
        } else {
            count = 2;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, count);
        mBinding.moviesList.setLayoutManager(gridLayoutManager);
        mBinding.moviesList.setAdapter(new MoviesGridAdapter(mMainMovieData.getMovies(), this));
    }

    private void fillListData() {
        mBinding.moviesList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.moviesList.setAdapter(new MoviesListAdapter(mMainMovieData.getMovies(), this));
        mBinding.moviesList.setHasFixedSize(true);
    }

    private void handleDisplayType(int position) {
        mPageNum = 1;
        switch (position) {
            case 0:
                getPopularMoviesList(mPageNum);
                break;
            case 1:
                getMostRatedMoviesList(mPageNum);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (mMainMovieData == null) {
            return;
        }
        switch (parent.getId()) {
            case R.id.display_list:
                mSelectedDisplayPosition = position;
                handleDisplayType(position);
                break;
            case R.id.view_list:
                mSelectedViewPosition = position;
                fillData();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class MovieResponseHandler implements ConnectionListener<MoviesMainModel> {
        @Override
        public void onResponseSuccess(MoviesMainModel response) {
            if (mPageNum == 1) {
                mMainMovieData = response;
                fillData();
            }
        }

        @Override
        public void onResponseFailure(int statusCode, String message) {

        }
    }
}
