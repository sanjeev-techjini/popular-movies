package com.techjini.nanodegree.spotify.connections.listeners;

/**
 * Created by Sanjeev on 3/27/16.
 */
public interface ConnectionListener<T> {
    void onResponseSuccess(T response);

    void onResponseFailure(int statusCode, String message);
}
