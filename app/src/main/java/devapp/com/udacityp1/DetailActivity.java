package devapp.com.udacityp1;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static devapp.com.udacityp1.MainActivity.movieDescription;
import static devapp.com.udacityp1.MainActivity.movieNames;
import static devapp.com.udacityp1.MainActivity.moviePosterLinks;
import static devapp.com.udacityp1.MainActivity.movieRating;
import static devapp.com.udacityp1.MainActivity.movieReleaseDate;

public class DetailActivity extends AppCompatActivity {

    TextView movieName,movieRatingTextView,movieDetails,movieReleaseDateTextView;
    ImageView moviePoster;

    int index;


    void initialize(){

        movieName = (TextView)findViewById(R.id.movieNameTextView);
        movieRatingTextView = (TextView) findViewById(R.id.movieRatingTextView);
        movieDetails = (TextView) findViewById(R.id.detailTextView);
        movieReleaseDateTextView = (TextView) findViewById(R.id.movieReleaseTextView);
        moviePoster = (ImageView) findViewById(R.id.detailImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialize();

        Intent in = getIntent();

        if(in.hasExtra("index")){

            index = in.getIntExtra("index",0);

        }

        Resources res = getResources();

        movieName.setText(movieNames.get(index));
        movieRatingTextView.setText(res.getText(R.string.user_rating) + movieRating.get(index));
        movieReleaseDateTextView.setText(res.getText(R.string.release_date) + movieReleaseDate.get(index));
        Picasso.with(this).load(moviePosterLinks.get(index)).into(moviePoster);
        movieDetails.setText(movieDescription.get(index));
    }
}
