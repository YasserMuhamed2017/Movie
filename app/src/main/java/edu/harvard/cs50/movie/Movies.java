package edu.harvard.cs50.movie;

public class Movies
{
    private String id;
    private String vote_average;
    private String overview;
    private String release_date;
    private String poster_path;
    private String backdrop_path;
    private String popularity;
    private String vote_count;
    private String original_language;
    private String title;

    Movies(String id, String poster_path, String backdrop_path, String vote_average, String overview, String release_date, String popularity, String vote_count, String original_language, String title)
    {
        this.id = id;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.original_language = original_language;
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
