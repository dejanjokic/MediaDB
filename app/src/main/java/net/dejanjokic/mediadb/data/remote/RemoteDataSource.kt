package net.dejanjokic.mediadb.data.remote

import io.reactivex.Single
import net.dejanjokic.mediadb.data.DataSource
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.MovieResponse
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.data.model.TvShowResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api: MediaService) : DataSource {

    // Movies
    override fun getPopularMovies(page: Int): Single<MovieResponse> =
        api.getPopularMovies(page)

    override fun getMovieSearchResults(query: String, page: Int): Single<MovieResponse> =
        api.getMovieSearchResults(query = query, page = page)

    override fun getMovieDetailsAsSingle(id: Long): Single<MovieDetails> =
        api.getMovieDetails(id)

    // TV Shows
    override fun getPopularTvShows(page: Int): Single<TvShowResponse> =
        api.getPopularTvShows(page)

    override fun getTvShowSearchResults(query: String, page: Int): Single<TvShowResponse> =
        api.getTvShowSearchResults(query = query, page = page)

    override fun getTvShowDetailsAsSingle(id: Long): Single<TvShowDetails> =
        api.getTvShowDetails(id)
}