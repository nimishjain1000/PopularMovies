package com.example.nimish.popularmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class MainActivityFragment extends Fragment
{  private MovieAdapter mMovieAdapter;
    public static Movie[] movieDetails = new Movie[20];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        FetchData movieTask = new FetchData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// ????????????????????????????????
        mMovieAdapter =
                new MovieAdapter(getActivity(),new Movie(),R.layout.grid_item);

        View rootView = inflater.inflate(R.layout.activity_main, container, false);
return rootView;
    }
}
