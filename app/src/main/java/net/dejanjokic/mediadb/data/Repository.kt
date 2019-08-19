package net.dejanjokic.mediadb.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.MovieResponse
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.data.model.TvShowResponse

interface Repository {

    // Movies
    fun getMovies(query: String?, page: Int = 1): Single<MovieResponse>

    fun getCachedMovieDetails(id: Long): Flowable<List<MovieDetails>>

    fun getNewMovieDetails(id: Long): Single<MovieDetails>

    fun cacheMovieDetails(movieDetails: MovieDetails): Completable

    // TV Shows
    fun getTvShows(query: String?, page: Int = 1): Single<TvShowResponse>

    fun getCachedTvShowDetails(id: Long): Flowable<List<TvShowDetails>>

    fun getNewTvShowDetails(id: Long): Single<TvShowDetails>

    fun cacheTvShowDetails(tvShowDetails: TvShowDetails): Completable
}