package com.techjini.nanodegree.spotify.connections.handlers;

import com.techjini.nanodegree.spotify.connections.listeners.ConnectionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sanjeev on 3/27/16.
 */
public class CommonResponseHandlers<T> implements Callback<T> {
    private ConnectionListener<T> mListener;

    public CommonResponseHandlers(ConnectionListener<T> listener) {
        mListener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            mListener.onResponseSuccess(response.body());
        } else {
            mListener.onResponseFailure(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mListener.onResponseFailure(101, t.getMessage());
    }
}
