package edu.harvard.cs50.movie;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDatabaseAdapter extends RecyclerView.Adapter<MovieDatabaseAdapter.MovieDatabaseViewHolder> {

    Context mContext;
    public List<Movies> images = new ArrayList<>();
    public void reload() {
        images = MoviesDetailsActivity.moviesDatabase.MovieDao().selectAll();
        notifyDataSetChanged();
    }

    MovieDatabaseAdapter(Context context)
    {
        mContext = context;
        reload();
    }

    @NonNull
    @Override
    public MovieDatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MovieDatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDatabaseViewHolder holder, int position) {
        String full_image_path = MovieAdapter.base_url + images.get(position).getPoster_path();
        Log.d("POSTER_PATH", full_image_path);
        Glide.with(mContext).load(full_image_path).into(holder.imageView);
        holder.linearLayout.setTag(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MovieDatabaseViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public LinearLayout linearLayout;
        public MovieDatabaseViewHolder(@NonNull final View itemView) {
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
