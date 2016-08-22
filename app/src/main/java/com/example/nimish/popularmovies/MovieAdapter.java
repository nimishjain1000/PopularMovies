package com.example.nimish.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class MovieAdapter extends ArrayAdapter<Movie>{
    public MovieAdapter( Context mContext, List<Movie> movieList ){
        super( mContext, R.layout.grid_item, movieList);
    }

    public static class ViewHolder{
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Movie movie = getItem(position);
        ViewHolder viewHolder;
        String url = movie.getPosterPath();

        if( convertView == null ) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from( getContext());
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with( getContext())
                .load(url)
                .placeholder(R.drawable.image)
                .error(R.drawable.image)
                .into(viewHolder.imageView);

        return convertView;
    }
}
