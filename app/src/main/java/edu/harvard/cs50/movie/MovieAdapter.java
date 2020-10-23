package edu.harvard.cs50.movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    public static String youtube_base_url = "https://www.youtube.com/watch?v=";
    public static String base_url_movies_reviews = "https://api.themoviedb.org/3/movie/";
    public static String part_of_the_path = "/reviews?api_key=";
    public static String part_of_the_path_movies = "/videos?api_key=";
    public static String base_url = "https://image.tmdb.org/t/p/w500/";
    public static String API_KEY = "4e21e64da6a3965021ffdaba52ad6ace";
    public static String rest_of_path = "&language=en-US&page=1";
    public List<Movies> images = new ArrayList<>();
    private static RequestQueue requestQueue;
    private Context mContext;
    public static String popular_movies_url = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + rest_of_path;
    public static String top_rated_movies_url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + rest_of_path;
    MovieAdapter(Context context, String url)
    {
        mContext = context;
        requestQueue = Volley.newRequestQueue(context);
        loadUrl(url);
    }
    public void loadUrl(String url)
    {
        images.clear();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        String id = result.getString("id");
                        String poster_path = result.getString("poster_path");
                        String backdrop_path = result.getString("backdrop_path");
                        String vote_average = result.getString("vote_average");
                        String overview = result.getString("overview");
                        String release_date = result.getString("release_date");
                        String popularity = result.getString("popularity");
                        String vote_count = result.getString("vote_count");
                        String original_language = result.getString("original_language");
                        String title = result.getString("title");
                        images.add(new Movies(id, poster_path, backdrop_path , vote_average, overview, release_date, popularity, vote_count, original_language, title));
                    }
                    notifyDataSetChanged();
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
        // add request
        requestQueue.add(request);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String full_image_path = base_url + images.get(position).getPoster_path();
        Glide.with(mContext).load(full_image_path).into(holder.imageView);
        holder.linearLayout.setTag(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public LinearLayout linearLayout;
        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_row_image_view);
            linearLayout = itemView.findViewById(R.id.movie_row);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Movies movies = (Movies) linearLayout.getTag();
                    Intent intent = new Intent(itemView.getContext(), MoviesDetailsActivity.class);
                    intent.putExtra("id", movies.getId());
                    intent.putExtra("poster_path", movies.getPoster_path());
                    intent.putExtra("backdrop_path", movies.getBackdrop_path());
                    intent.putExtra("vote_average", movies.getVote_average());
                    intent.putExtra("overview", movies.getOverview());
                    intent.putExtra("release_date", movies.getRelease_date());
                    intent.putExtra("popularity", movies.getPopularity());
                    intent.putExtra("vote_count", movies.getVote_count());
                    intent.putExtra("original_language", movies.getOriginal_language());
                    intent.putExtra("title", movies.getTitle());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}