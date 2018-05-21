package it.zabinska.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.zabinska.popularmovies.adapters.ImageAdapter;

public class MainActivity extends AppCompatActivity {

    static int pageNumber = -1;
    static int sorting = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.grid_view);
        gridView.setAdapter(new ImageAdapter(this));
        pageNumber = 1; //PAGEVIEW
        FetchData f = new FetchData();
        f.execute();

    }
    static class FetchData extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = createUrl();

                assert url != null;
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            System.out.println(response);
        }
    }

    static private URL createUrl() {

        /** Please, remember to put your own the Movie Database API Key in res/values/strings.xml **/

        String apiURL = sorting == 1 ? (R.string.query_part_1 + R.string.API_KEY + R.string.query_part_2 + R.string.popularity_descending + R.string.query_part_3 + String.valueOf(pageNumber)) : (R.string.query_part_1 + R.string.api_key + R.string.query_part_2 + R.string.rating_descending + R.string.query_part_3 + String.valueOf(pageNumber));
        try {
            return new URL(apiURL);
        }
        catch(MalformedURLException m){
            m.printStackTrace();
            return null;
        }
    }
}
        //TODO (B) Create settings
        //TODO (B.1) Add sorting by most popular to settings
        //TODO (B.2) Add sorting by least popular to settings
        //TODO (B.3) Add sorting by highest-rated to settings
        //TODO (B.4) Add sorting by lowest-rated to settings
        //TODO (B.5) Add pager
        //TODO (B.6) Add changing sorting value from settings
        //TODO (C) Go to detailed activity onPosterClick()
