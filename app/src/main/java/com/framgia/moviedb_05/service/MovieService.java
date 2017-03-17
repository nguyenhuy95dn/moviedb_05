package com.framgia.moviedb_05.service;

import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.data.model.MoviesResponse;
import com.framgia.moviedb_05.data.model.RelatedMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    public static final String API_KEY = "api_key";
    public static final String PAGE = "page";
    public static final String MOVIE_ID = "movie_id";
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query(API_KEY) String apiKey, @Query(PAGE) int pages);
    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path(MOVIE_ID) int id, @Query(API_KEY) String apiKey);
    @GET("genre/{genre_id}/movies")
    Call<RelatedMovieResponse> getRelatedMovie(@Path("genre_id") int genre_id,
                                               @Query("api_key") String apiKey);
    @GET("search/movie")
    Call<MoviesResponse> searchMovie(@Query("api_key") String apiKey, @Query("query") String
        query, @Query("page") int page);
}
