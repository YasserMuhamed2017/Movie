package edu.harvard.cs50.movie;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;


@Dao
public interface MovieDao {
    @Query("INSERT INTO movies (movie_id, poster_path, backdrop_path , vote_average, overview, release_date, popularity, vote_count, original_language, title) VALUES" +
            " (:movie_id, :poster_path, :backdrop_path, :vote_average, :overview, :release_date, :popularity, :vote_count, :original_language, :title)")
    void insert(String movie_id, String poster_path, String backdrop_path, String vote_average, String overview, String release_date, String popularity, String vote_count, String original_language, String title);

    @Query("SELECT movie_id, poster_path, backdrop_path , vote_average, overview, release_date, popularity, vote_count, original_language, title FROM movies")
    List<Movies> selectAll();

    @Query("DELETE FROM movies WHERE movie_id = :movie_id")
    void delete(String movie_id);

}
