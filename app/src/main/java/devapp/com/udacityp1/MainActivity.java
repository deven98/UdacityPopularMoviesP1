package devapp.com.udacityp1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import devapp.com.udacityp1.Utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements CustomAdapter.ListItemClickListener{

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    CustomAdapter customAdapter;
    ProgressBar progressBar;
    CustomAdapter.ListItemClickListener listItemClickListener;

    public static ArrayList<String> movieNames = new ArrayList<>();
    public static ArrayList<String> moviePosterLinks = new ArrayList<>();
    public static ArrayList<String> movieDescription = new ArrayList<>();
    public static ArrayList<String> movieRating = new ArrayList<>();
    public static ArrayList<String> movieReleaseDate = new ArrayList<>();

    void initialize(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(this,3);
        customAdapter = new CustomAdapter(this,this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.sort_by_popularity){

            NetworkUtils.searchByPopularity = true;

            new fetchMovieData().execute();

        }else if(item.getItemId() == R.id.sort_by_rating){

            NetworkUtils.searchByPopularity = false;

            new fetchMovieData().execute();
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        if(movieNames.size() == 0) {

            new fetchMovieData().execute();

        }

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(customAdapter);

        customAdapter.notifyDataSetChanged();

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent in = new Intent(this,DetailActivity.class);
        in.putExtra("index",clickedItemIndex);

        startActivity(in);

    }


    public class fetchMovieData extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            movieNames.clear();
            moviePosterLinks.clear();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {


            NetworkUtils.loadMovies(MainActivity.this);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            customAdapter.notifyDataSetChanged();

            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
