package devapp.com.udacityp1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import devapp.com.udacityp1.Utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    CustomAdapter customAdapter;

    public static ArrayList<String> movieNames = new ArrayList<>();
    public static ArrayList<String> moviePosterLinks = new ArrayList<>();

    void initialize(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(this,3);
        customAdapter = new CustomAdapter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        new fetchMovieData().execute();

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(customAdapter);

        customAdapter.notifyDataSetChanged();

    }



    public class fetchMovieData extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Looper.prepare();
            NetworkUtils.loadMovies(MainActivity.this);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            customAdapter.notifyDataSetChanged();
        }
    }
}
