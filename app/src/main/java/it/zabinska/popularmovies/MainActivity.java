package it.zabinska.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import it.zabinska.popularmovies.adapters.ImageAdapter;

public class MainActivity extends AppCompatActivity {

    static int pageNumber = -1;
    static int sorting = 1;
    static String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        GridView gridView = findViewById(R.id.grid_view);
 //       gridView.setAdapter(new ImageAdapter(this));
        pageNumber = 1; //PAGEVIEW
        url = createUrl();
        FetchData f = new FetchData();
        f.execute();

    }
    static class FetchData extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            try {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/octet-stream");
                RequestBody body = RequestBody.create(mediaType, "{}");
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                Response response = client.newCall(request).execute();
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            else response = "EVERYTHING WENT WELL";
            System.out.println(response);
        }
    }

    public String createUrl() {

        /** Please, remember to put your own the Movie Database API Key in res/values/strings.xml **/

        String apiURL = getResources().getString(R.string.query_part_1);
        StringBuilder apiURLBuilder = new StringBuilder(apiURL);
        if(sorting == 1)
            apiURLBuilder.append(getResources().getString(R.string.by_popularity));
        else
            apiURLBuilder.append(getResources().getString(R.string.by_rating));
        apiURLBuilder.append("?");
        apiURLBuilder.append(getResources().getString(R.string.query_part_2));
        apiURLBuilder.append(getResources().getString(R.string.API_KEY));
        apiURL = apiURLBuilder.toString();
        return apiURL;

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
