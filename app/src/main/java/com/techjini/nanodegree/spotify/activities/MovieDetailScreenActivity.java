package com.techjini.nanodegree.spotify.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.techjini.nanodegree.spotify.R;
import com.techjini.nanodegree.spotify.constants.Constants;
import com.techjini.nanodegree.spotify.databinding.ActivityMovieDetailScreenBinding;
import com.techjini.nanodegree.spotify.models.MoviesItemModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MovieDetailScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail_screen);
        fillData(binding);
    }

    private void fillData(ActivityMovieDetailScreenBinding binding) {
        MoviesItemModel moviesItemModel = getIntent().getParcelableExtra(Constants.BundleConstants.MOVIE_DATA);
        setTitle(moviesItemModel.getTitle());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date;
            date = dateFormat.parse(moviesItemModel.getReleaseDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String dateOutput = simpleDateFormat.format(date);
            moviesItemModel.setReleaseDate(dateOutput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binding.setMovie(moviesItemModel);
    }
}
