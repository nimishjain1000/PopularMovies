package com.example.nimish.popularmovies;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    public MovieAdapter(Context context, Movie movie, int resource) {
        super(context,0, resource);
    }
}
