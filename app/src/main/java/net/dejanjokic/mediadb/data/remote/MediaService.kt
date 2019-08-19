package net.dejanjokic.mediadb.data.remote

import io.reactivex.Single
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.MovieResponse
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.data.model.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaService {

    // Movies
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int = 1): Single<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Long): Single<MovieDetails>

    @GET("search/movie")
    fun getMovieSearchResults(
        @Query("query") query: String,
        @Query("language") language: String = "un_US",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): Single<MovieResponse>

    // TV Shows
    @GET("tv/popular")
    fun getPopularTvShows(@Query("page") page: Int = 1): Single<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetails(@Path("id") id: Long): Single<TvShowDetails>

    @GET("search/tv")
    fun getTvShowSearchResults(
        @Query("query") query: String,
        @Query("language") language: String = "un_US",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): Single<TvShowResponse>
}