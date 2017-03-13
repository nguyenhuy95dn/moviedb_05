package com.framgia.moviedb_05.service;

import com.framgia.moviedb_05.data.model.Movie;
import com.framgia.moviedb_05.data.model.MoviesResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieService {
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@QueryMap Map<String, String> params);
    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
