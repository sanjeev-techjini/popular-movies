package com.techjini.nanodegree.spotify.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Sanjeev on 3/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesMainModel implements Parcelable {
    public static final Creator<MoviesMainModel> CREATOR = new Creator<MoviesMainModel>() {
        @Override
        public MoviesMainModel createFromParcel(Parcel in) {
            return new MoviesMainModel(in);
        }

        @Override
        public MoviesMainModel[] newArray(int size) {
            return new MoviesMainModel[size];
        }
    };
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("results")
    private ArrayList<MoviesItemModel> movies;

    protected MoviesMainModel(Parcel in) {
        totalPages = in.readInt();
    }

    public MoviesMainModel() {

    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<MoviesItemModel> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<MoviesItemModel> movies) {
        this.movies = movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalPages);
    }
}
