package com.techjini.nanodegree.spotify.connections.managers;

import com.techjini.nanodegree.spotify.connections.interfaces.APIInterface;
import com.techjini.nanodegree.spotify.constants.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Sanjeev on 3/27/16.
 */
public class APIUtils {
    public APIInterface getConnection() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_PATH)
                .client(builder.build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(APIInterface.class);
    }
}
