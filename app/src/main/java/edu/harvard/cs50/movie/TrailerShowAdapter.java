package edu.harvard.cs50.movie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrailerShowAdapter extends RecyclerView.Adapter<TrailerShowAdapter.TrailerShowViewHolder>{

    private String url;
    private RequestQueue requestQueue;
    private List<String> trailers = new ArrayList<>();
    public Context context;
    TrailerShowAdapter(Context context, String id)
    {
        url = MovieAdapter.base_url_movies_reviews + id + MovieAdapter.part_of_the_path_movies + MovieAdapter.API_KEY + "&language=en-US";
        Log.d("url_CS50", url);
        Log.d("url_CS50", id);
        requestQueue = Volley.newRequestQueue(context);
        loadTrailers(url);
        this.context = context;
    }
    private void loadTrailers(String url)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject result = results.getJSONObject(i);
                        String key = result.getString("key");
                        String name = result.getString("name");
                        trailers.add(key);
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
    @NonNull
    @Override
    public TrailerShowAdapter.TrailerShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_show, parent, false);
        return new TrailerShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerShowAdapter.TrailerShowViewHolder holder, int position) {
        Log.d("url_CS50", trailers.get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailerShowViewHolder extends RecyclerView.ViewHolder{

        public TrailerShowViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
