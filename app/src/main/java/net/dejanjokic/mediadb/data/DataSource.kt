package net.dejanjokic.mediadb.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.MovieResponse
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.data.model.TvShowResponse

interface DataSource {

    // Movies
    fun getPopularMovies(page: Int = 1): Single<MovieResponse> = Single.create(null)

    fun getMovieSearchResults(query: String, page: Int = 1): Single<MovieResponse> = Single.create(null)

    fun getMovieDetails(id: Long): Flowable<List<MovieDetails>> = Flowable.fromArray(listOf())

    fun getMovieDetailsAsSingle(id: Long): Single<MovieDetails> = Single.create(null)

    fun saveMovieDetails(movie: MovieDetails): Completable = Completable.complete()

    // TV Shows
    fun getPopularTvShows(page: Int = 1): Single<TvShowResponse> = Single.create(null)

    fun getTvShowSearchResults(query: String, page: Int = 1): Single<TvShowResponse> = Single.create(null)

    fun getTvShowDetails(id: Long): Flowable<List<TvShowDetails>> = Flowable.fromArray(listOf())

    fun getTvShowDetailsAsSingle(id: Long): Single<TvShowDetails> = Single.create(null)

    fun saveTvShowDetails(tvShow: TvShowDetails): Completable = Completable.complete()
}