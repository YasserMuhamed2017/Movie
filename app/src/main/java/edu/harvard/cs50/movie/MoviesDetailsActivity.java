package edu.harvard.cs50.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MoviesDetailsActivity extends AppCompatActivity {

    private ImageView poster_pathImageView;
    private ImageView backdrop_pathImageView;
    private TextView overviewTextView;
    private TextView voteAverageTextView;
    private TextView releaseDateTextView;
    private TextView popularityTextView;
    private TextView voteCountTextView;
    private TextView original_languageTextView;
    private TextView titleTextView;
    private TextView contentTextView;
    private static String get_reviews_id_url;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    public static MoviesDatabase moviesDatabase;
    private Button button;
    private SharedPreferences sharedPreferences;
    private String clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);
        poster_pathImageView = findViewById(R.id.poster_path_image);
        backdrop_pathImageView = findViewById(R.id.backdrop_path);
        overviewTextView = findViewById(R.id.overview);
        voteAverageTextView = findViewById(R.id.vote_average);
        releaseDateTextView = findViewById(R.id.release_date);
        popularityTextView = findViewById(R.id.popularity);
        voteCountTextView = findViewById(R.id.vote_count);
        original_languageTextView = findViewById(R.id.original_language);
        titleTextView = findViewById(R.id.title);
        contentTextView = findViewById(R.id.reviews);
        // button = findViewById(R.id.favorite);
        moviesDatabase = Room.databaseBuilder(getApplicationContext(), MoviesDatabase.class, "movies").allowMainThreadQueries().build();
        clicked = getPreferences(Context.MODE_PRIVATE).getString("Color", " ");


        Glide.with(this).load(MovieAdapter.base_url + getIntent().getStringExtra("poster_path")).into(poster_pathImageView);
        Glide.with(this).load(MovieAdapter.base_url + getIntent().getStringExtra("backdrop_path")).into(backdrop_pathImageView);
        overviewTextView.setText(getIntent().getStringExtra("overview"));
        voteAverageTextView.setText(getIntent().getStringExtra("vote_average") + "/10");
        releaseDateTextView.setText(getIntent().getStringExtra("release_date"));
        popularityTextView.setText(getIntent().getStringExtra("popularity"));
        voteCountTextView.setText(getIntent().getStringExtra("vote_count"));
        original_languageTextView.setText(getIntent().getStringExtra("original_language"));
        titleTextView.setText(getIntent().getStringExtra("title"));

        get_reviews_id_url = MovieAdapter.base_url_movies_reviews + getIntent().getStringExtra("id") + MovieAdapter.part_of_the_path + MovieAdapter.API_KEY + MovieAdapter.rest_of_path;
        requestQueue = Volley.newRequestQueue(this);
        loadReviews(get_reviews_id_url);

        recyclerView = findViewById(R.id.horizontal_recyclerview);
        adapter = new TrailerShowAdapter(getApplicationContext(), getIntent().getStringExtra("id"));
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadReviews(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        String content = result.getString("content");
                        // create text view
                        contentTextView.setText(content);
                        break;
                    }
                    // checking for results are empty or not
                    if (results.length() == 0)
                    {
                        contentTextView.setGravity(Gravity.RIGHT);
                        contentTextView.setText("NO REVIEWS YET");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

//    public void love(View view)
//    {
////        if (clicked.equals("Red"))
////        {
////            moviesDatabase.MovieDao().insert(getIntent().getStringExtra("id"),
////                    getIntent().getStringExtra("poster_path"),
////                    getIntent().getStringExtra("backdrop_path"),
////                    getIntent().getStringExtra("vote_average"),
////                    getIntent().getStringExtra("overview"),
////                    getIntent().getStringExtra("release_date"),
////                    getIntent().getStringExtra("popularity"),
////                    getIntent().getStringExtra("vote_count"),
////                    getIntent().getStringExtra("original_language"),
////                    getIntent().getStringExtra("title"));
////            clicked = "Blue";
////            button.setBackground(getResources().getDrawable(R.drawable.ic_red_favorite_24));
////            getPreferences(Context.MODE_PRIVATE).edit().putString("Color", clicked).commit();
////        }
////        else
////        {
////            moviesDatabase.MovieDao().delete(getIntent().getStringExtra("id"));
////            button.setBackground(getResources().getDrawable(R.drawable.ic_black_favorite_24));
////            clicked = "Red";
////            getPreferences(Context.MODE_PRIVATE).edit().putString("Color", clicked).commit();
////        }
//    }
}