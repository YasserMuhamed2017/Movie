package edu.harvard.cs50.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MovieAdapter(getApplicationContext(), MovieAdapter.popular_movies_url);
        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.movie_public :
                {
                    adapter = new MovieAdapter(this, MovieAdapter.popular_movies_url);
                    recyclerView.setAdapter(adapter);
                } return true;
            case R.id.movie_top_rated :
                {
                    adapter = new MovieAdapter(this, MovieAdapter.top_rated_movies_url);
                    recyclerView.setAdapter(adapter);
                } return true;
//            case R.id.favorites :
//            {
//                MovieDatabaseAdapter movieDatabaseAdapter = new MovieDatabaseAdapter(getApplicationContext());
//                recyclerView.setAdapter(movieDatabaseAdapter);
//            } return true;
        }
        return super.onOptionsItemSelected(item);
    }
}