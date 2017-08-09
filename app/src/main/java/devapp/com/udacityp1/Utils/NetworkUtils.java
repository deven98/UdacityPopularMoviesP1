package devapp.com.udacityp1.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import devapp.com.udacityp1.CustomAdapter;
import devapp.com.udacityp1.MainActivity;

import static devapp.com.udacityp1.MainActivity.movieNames;
import static devapp.com.udacityp1.MainActivity.moviePosterLinks;

/**
 * Created by HP on 09-08-2017.
 */

public class NetworkUtils {

    private static String API_KEY = "7ed6c9e0c8221f0764db55ce52e1cfda";

    public static String BASE_URL = "http://api.themoviedb.org/3/movie/popular?api_key="+API_KEY;

    public static String SIZE = "w185";

    public static String IMAGE_BASE_URL =  "http://image.tmdb.org/t/p/w185/";



    public static void loadMovies(Context context){

        Uri uri = Uri.parse(BASE_URL).buildUpon().build();
        String s = null;
        try{
        s = getResponseFromHttpUrl(new URL(uri.toString()));}
        catch (Exception e){
            e.printStackTrace();
        }
            JSONSeperator(context,s);

    }

    public static ArrayList<String> JSONSeperator(Context c,String s){

        try {

            JSONObject j = new JSONObject(s);

            JSONArray movies = j.getJSONArray("results");

            for(int i = 0; i<movies.length()-1 ; i++) {

                JSONObject movie = movies.getJSONObject(i);

                String title = movie.getString("title");

                movieNames.add(title);

                String posterLink = movie.getString("poster_path");

                moviePosterLinks.add(IMAGE_BASE_URL+posterLink);

                Log.d("pos",posterLink);

                Log.d("res", title);
            }

        }catch (Exception e){

            e.printStackTrace();

        }
        return null;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
