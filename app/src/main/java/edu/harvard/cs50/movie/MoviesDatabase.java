package edu.harvard.cs50.movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    public abstract MovieDao MovieDao();

}
