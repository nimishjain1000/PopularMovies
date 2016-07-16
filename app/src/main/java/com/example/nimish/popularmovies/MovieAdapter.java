package com.example.nimish.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

  List<Movie> list;
    public Context context;
    public Activity activity;
    public View view;

    public MovieAdapter(Context context, Activity activity, View view) {
        this.context = context;
        this.activity = activity;
        this.view = view;
    }

    public MovieAdapter(FragmentActivity context, List<Movie> list) {
        this.list = list;
        this.context=context;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
   // this class contains the data which inflate over recyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iView;
        View mView;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            iView = (ImageView) itemView.findViewById(R.id.img);
          }
    }
}