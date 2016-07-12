package com.example.nimish.popularmovies;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class FetchData extends AsyncTask<Void, Void, Movie[]> {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String movieinfo = null;
    String movieDbUrl = null;

     //use api by BuildConfig.THE_MOVIE_DB_API_TOKEN)

    private Movie[] getMovieDataFromJson(String movieJsonStr)
            throws JSONException {

    final String resultArray="results";

        final String mOverView="overview";
        final String mTitle="title";
        final String mReleaseDate="release_date";
        final String mPosterPath="poster_path";

        String baseURL = "http://image.tmdb.org/t/p/w500/";

        JSONObject movieJson = new JSONObject(movieJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(resultArray);
        for(int i=0;i<20;i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            String moviedesc = movieObject.getString(mOverView);
            String movietitle = movieObject.getString(mTitle);
            String movierelaease = movieObject.getString(mReleaseDate);
            String moviePosterPath = baseURL + movieObject.getString(mPosterPath);

            MainActivityFragment.movieDetails[i] = new Movie(moviePosterPath, moviedesc, movietitle, movierelaease);
        }
        return MainActivityFragment.movieDetails;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        super.onPostExecute(movies);
    }

    @Override
    protected Movie[] doInBackground(Void... params) {
        try {
            URL url;
            if (MainActivityFragment.sort) {
                url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=" + BuildConfig.THE_MOVIE_DB_API_TOKEN); //sort by popularity by default
            } else {
                url = new URL("http://api.themoviedb.org/3/movie/top_rated?api_key=" +BuildConfig.THE_MOVIE_DB_API_TOKEN);  //sort by ratings
            }

            movieDbUrl = url.toString();
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieinfo = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        try {
            return getMovieDataFromJson(movieinfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
