package edu.harvard.cs50.movie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "movie_id")
    public String movie_id;

    @ColumnInfo(name = "poster_path")
    public String poster_path;

    @ColumnInfo(name = "backdrop_path")
    public String backdrop_path;

    @ColumnInfo(name = "vote_average")
    public String vote_average;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "release_date")
    public String release_date;

    @ColumnInfo(name = "popularity")
    public String popularity;

    @ColumnInfo(name = "vote_count")
    public String vote_count;

    @ColumnInfo(name = "original_language")
    public String original_language;

    @ColumnInfo(name = "title")
    public String title;
}
