package cz.muni.fi.pv256.movio2.uco_410371.interfaces;

import cz.muni.fi.pv256.movio2.uco_410371.models.MovieCredits;
import cz.muni.fi.pv256.movio2.uco_410371.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Benjamin Varga on 25.10.2016.
 */

public interface RetrofitInterface {

    @GET("movie/{type}")
    Call<MoviesResponse> getMovies(@Path("type") String movieType, @Query("api_key") String apiKey);

    @GET("/movie/{movie_id}/credits")
    Call<MovieCredits> getMovieCredits(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MoviesResponse> getDiscoverMovies(
            @Query("api_key") String apiKey,
            @Query("sort_by") String sortBy,
            @Query("primary_release_date.gte") String minimumDate,
            @Query("primary_release_date.lte") String maximumDate);
}
