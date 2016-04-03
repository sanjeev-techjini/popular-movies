package com.techjini.nanodegree.spotify.connections.interfaces;

import com.techjini.nanodegree.spotify.constants.Constants;
import com.techjini.nanodegree.spotify.models.MoviesMainModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sanjeev on 3/27/16.
 */
public interface APIInterface {
    @GET("popular?api_key=" + Constants.API_KEY)
    Call<MoviesMainModel> getPopularMovies(@Query("page") int page);

    @GET("top_rated?api_key=" + Constants.API_KEY)
    Call<MoviesMainModel> getTopRatedMovies(@Query("page") int page);
}
