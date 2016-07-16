package com.example.nimish.popularmovies;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class MainActivityFragment extends Fragment
{  private MovieAdapter mMovieAdapter;
    RecyclerView recyclerView;
    public static Movie[] movieDetails = new Movie[20];
    public static boolean sort=false;
    public static View view;
    public static Activity activity;
    public static Context context;
    Movie[] movieList = {};
  //  public static Communicator com;
    ImageView imageView;


  public   MainActivityFragment()
    {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        view=getView();
        context=getContext();
        activity=getActivity();

        FetchData movieTask = new FetchData();
        movieTask.execute();
        recyclerView=(RecyclerView) recyclerView.findViewById(R.id.recycleView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// ????????????????????????????????
        //mMovieAdapter =
            //    new MovieAdapter(getActivity(),new Movie(),R.layout.grid_item);
        recyclerView = (RecyclerView) inflater.inflate(R.layout.activity_main, container, false);
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        imageView = (ImageView) recyclerView.findViewById(R.id.img);

        return recyclerView;

    }
}
