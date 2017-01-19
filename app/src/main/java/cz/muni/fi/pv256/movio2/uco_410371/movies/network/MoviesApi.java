package cz.muni.fi.pv256.movio2.uco_410371.movies.network;

import android.support.annotation.Nullable;

import cz.muni.fi.pv256.movio2.uco_410371.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("discover/movie")
    Call<MoviesResponse> getDiscoverMovies(
            @Query("api_key") String apiKey,
            @Nullable @Query("sort_by") String sortBy,
            @Nullable @Query("primary_release_date.gte") String minimumDate,
            @Nullable @Query("primary_release_date.lte") String maximumDate);
}
