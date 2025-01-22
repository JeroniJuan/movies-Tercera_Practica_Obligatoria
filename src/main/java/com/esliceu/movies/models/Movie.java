package com.esliceu.movies.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @OneToMany(mappedBy = "movie")
    private List<Movie_Cast> movieCasts;

    @OneToMany(mappedBy = "movie")
    private List<Movie_Crew> movieCrews;

    @OneToMany(mappedBy = "movie")
    private List<Movie_Genre> movieGenres;

    @Column(length = 1000)
    private String title;

    int budget;

    @Column(length = 1000)
    private String homepage;

    @Column(length = 1000)
    private String overview;

    @Column(precision = 12, scale = 6)
    private BigDecimal popularity;

    private LocalDate release_date;

    private long revenue;

    private int runtime;

    @Column(length = 50)
    private String movie_status;

    @Column(length = 1000)
    private String tagline;

    @Column(precision = 4, scale = 2)
    private BigDecimal vote_average;

    private int vote_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Movie_Cast> getMovieCasts() {
        return movieCasts;
    }

    public void setMovieCasts(List<Movie_Cast> movieCasts) {
        this.movieCasts = movieCasts;
    }

    public List<Movie_Crew> getMovieCrews() {
        return movieCrews;
    }

    public void setMovieCrews(List<Movie_Crew> movieCrew) {
        this.movieCrews = movieCrew;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMovie_id() {
        return id;
    }

    public void setMovie_id(int movie_id) {
        this.id = movie_id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public void setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getMovie_status() {
        return movie_status;
    }

    public void setMovie_status(String movie_status) {
        this.movie_status = movie_status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public BigDecimal getVote_average() {
        return vote_average;
    }

    public void setVote_average(BigDecimal vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
