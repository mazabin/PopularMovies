package it.zabinska.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}

        //TODO (C.1) Initialize proper layout views for detailed information
        //TODO (C.2) Create populateUi() method, that shows original title, poster thumbnail, plot (overview), rating(vote_average) and release date of the movie.
