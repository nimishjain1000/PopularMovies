package com.example.nimish.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    ImageView thumbnail, backdropImage;
    TextView movieTitle, releaseDate, movieDescription;
    RatingBar movieRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(Intent.EXTRA_TEXT);

        thumbnail = (ImageView) findViewById(R.id.movie_thumbnail);
        movieTitle = (TextView) findViewById(R.id.movie_title);
        releaseDate = (TextView) findViewById(R.id.movie_release_date);
        movieDescription = (TextView) findViewById(R.id.movie_description);
        movieRating = (RatingBar) findViewById(R.id.movie_rating);
        backdropImage = (ImageView) findViewById(R.id.movie_backdrop_image);

        movieTitle.setText( movie.getTitle());
        releaseDate.setText( movie.getReleaseDate());
        movieDescription.setText( movie.getOverView());
        movieRating.setRating( movie.getUserRating());

        Picasso.with( this )
                .load( movie.getPosterPath())
                .placeholder(R.drawable.image)
                .error(R.drawable.image)
                .into(thumbnail);

        Picasso.with(this)
                .load(movie.getBackdropPath())
                .placeholder(R.drawable.image)
                .error(R.drawable.image)
                .into(backdropImage);
    }
}
