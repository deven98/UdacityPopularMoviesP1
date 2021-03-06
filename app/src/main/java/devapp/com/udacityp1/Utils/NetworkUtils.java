package devapp.com.udacityp1.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static devapp.com.udacityp1.MainActivity.movieDescription;
import static devapp.com.udacityp1.MainActivity.movieNames;
import static devapp.com.udacityp1.MainActivity.moviePosterLinks;
import static devapp.com.udacityp1.MainActivity.movieRating;
import static devapp.com.udacityp1.MainActivity.movieReleaseDate;

public class NetworkUtils {

    //TODO:Insert API KEY here
    private static String API_KEY = "";

    private static String POPULAR_BASE_URL = "http://api.themoviedb.org/3/movie/popular?api_key="+API_KEY;

    private static String RATING_BASE_URL= "http://api.themoviedb.org/3/movie/top_rated?api_key="+API_KEY;

    private static String IMAGE_BASE_URL =  "http://image.tmdb.org/t/p/w185/";

    public static boolean searchByPopularity = true;

    public static void loadMovies(Context context){

        String URLToUse = null;

        if(searchByPopularity){URLToUse = POPULAR_BASE_URL;}
        else if(!searchByPopularity){URLToUse = RATING_BASE_URL;}

        Uri uri = Uri.parse(URLToUse).buildUpon().build();
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

                String movieOverview = movie.getString("overview");
                movieDescription.add(movieOverview);

                String release = movie.getString("release_date");
                movieReleaseDate.add(release);

                String rating = movie.getString("vote_average");
                movieRating.add(rating);

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
