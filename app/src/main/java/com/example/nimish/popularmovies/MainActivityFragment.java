package com.example.nimish.popularmovies;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Nimish on 10-Jul-16.
 */

public class MainActivityFragment extends Fragment
{  public static MovieAdapter mMovieAdapter;

    public static Movie[] movieDetails = new Movie[20];
    public static boolean sort=false;
    public static View view;
    public static Activity activity;
    public static Context context;
    Movie[] movieList = {};
  //  public static Communicator com;
    ImageView imageView;




    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
    }


    @Override
    public void onStart(){
        super.onStart();
        updateMovies();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if( id ==    R.id.settings){
            Intent intent = new Intent( getActivity(), SettingActivity.class );
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected( item );
    }



    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    public void updateMovies(){
        String sortOrder;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sortOrder = preferences.getString( getString(R.string.sort_order), getString(R.string.pref_sort_most_popular));
        if( isOnline() ) {
            FetchMovies fetchMovies = new FetchMovies();
            if(sortOrder=="popular?")
            fetchMovies.execute();// put string here in arguments
        } else {
            Toast.makeText(context,"Please turn on an active internet connection",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// ????????????????????????????????
        //mMovieAdapter =
            //    new MovieAdapter(getActivity(),new Movie(),R.layout.grid_item);
        mMovieAdapter = new MovieAdapter(
                getActivity(),
                new ArrayList<Movie>()
        );

        View rootView = inflater.inflate( R.layout.fragment_main, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_for_posters);
        gridView.setAdapter( mMovieAdapter );
return rootView;
    }

    public class FetchMovies extends AsyncTask< String, Void, Movie[] > {

        private final String LOG_TAG = FetchMovies.class.getSimpleName();

        private Movie[] createObject(String jsonString ){

            final String TMDB_RESULTS = "results";
            final String TMDB_ORIGINAL_TITLE = "original_title";
            final String TMDB_POSTER_PATH = "poster_path";
            final String TMDB_RELEASE_DATE = "release_date";
            final String TMDB_USER_RATING = "vote_average";
            final String TMDB_OVERVIEW = "overview";
            final String TMDB_BACKDROP_PATH = "backdrop_path";

            try {
                JSONObject json = new JSONObject( jsonString );
                JSONArray jsonArray = json.optJSONArray( TMDB_RESULTS );
                Movie moviesArray[] = new Movie[jsonArray.length()];

                for( int i = 0; i < jsonArray.length(); i++ ){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String originalTitle = jsonObject.getString( TMDB_ORIGINAL_TITLE);
                    String posterPath = jsonObject.getString( TMDB_POSTER_PATH);
                    String releaseDate = jsonObject.getString( TMDB_RELEASE_DATE);
                    float userRating = Float.parseFloat(jsonObject.getString( TMDB_USER_RATING));
                    String overView = jsonObject.getString( TMDB_OVERVIEW);
                    String backdropPath = jsonObject.getString(TMDB_BACKDROP_PATH);

                    moviesArray[i] = new Movie( originalTitle, posterPath, releaseDate, userRating, overView, backdropPath );
                }
                return moviesArray;
            } catch ( JSONException j ){
                j.printStackTrace();
            }
            return null;
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            String JSONStr = null;
            HttpsURLConnection httpsURLConnection = null;
            BufferedReader reader = null;

            try {
                final String BASE_URL = "https://api.themoviedb.org/3/movie/" + params[0];
                final String APIID_PARAM = "api_key";

                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(APIID_PARAM, BuildConfig.THE_MOVIE_DB_API_TOKEN)
                        .build();

                URL url = new URL(builtUri.toString());

                httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.connect();

                StringBuffer stringBuffer = new StringBuffer();
                InputStream stream = httpsURLConnection.getInputStream();

                if (stream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(stream));

                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }

                if (stringBuffer.length() == 0) {
                    return null;
                }

                JSONStr = stringBuffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return createObject(JSONStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            if( movies != null ) {
                mMovieAdapter.clear();
                for (Movie movie: movies) {
                    mMovieAdapter.add(movie);
                }
            }
        }
    }
}
