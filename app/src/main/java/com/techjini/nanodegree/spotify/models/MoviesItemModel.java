package com.techjini.nanodegree.spotify.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Sanjeev on 3/27/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesItemModel implements Parcelable {
    public static final Creator<MoviesItemModel> CREATOR = new Creator<MoviesItemModel>() {
        @Override
        public MoviesItemModel createFromParcel(Parcel in) {
            return new MoviesItemModel(in);
        }

        @Override
        public MoviesItemModel[] newArray(int size) {
            return new MoviesItemModel[size];
        }
    };
    @JsonProperty("poster_path")
    private String mainPoster;
    @JsonProperty("backdrop_path")
    private String backDropPoster;
    private String title;
    private String overview;
    @JsonProperty("vote_average")
    private String voteAverage;
    @JsonProperty("release_date")
    private String releaseDate;

    protected MoviesItemModel(Parcel in) {
        mainPoster = in.readString();
        backDropPoster = in.readString();
        title = in.readString();
        overview = in.readString();
        voteAverage = in.readString();
        releaseDate = in.readString();
    }

    public MoviesItemModel() {
    }

    public String getMainPoster() {
        return mainPoster;
    }

    public void setMainPoster(String mainPoster) {
        this.mainPoster = mainPoster;
    }

    public String getBackDropPoster() {
        return backDropPoster;
    }

    public void setBackDropPoster(String backDropPoster) {
        this.backDropPoster = backDropPoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mainPoster);
        dest.writeString(backDropPoster);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(voteAverage);
        dest.writeString(releaseDate);
    }
}
