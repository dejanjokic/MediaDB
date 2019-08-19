package net.dejanjokic.mediadb.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.MovieResponse
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.data.model.TvShowResponse
import net.dejanjokic.mediadb.di.qualifier.Local
import net.dejanjokic.mediadb.di.qualifier.Remote
import javax.inject.Inject

class MediaRepository @Inject constructor(
    @Local private val localDataSource: DataSource,
    @Remote private val remoteDataSource: DataSource
) : Repository {

    // Movies
    override fun getMovies(query: String?, page: Int): Single<MovieResponse> {
        return if (query.isNullOrEmpty())
            remoteDataSource.getPopularMovies()
        else
            remoteDataSource.getMovieSearchResults(query)
    }

    override fun getCachedMovieDetails(id: Long): Flowable<List<MovieDetails>> =
        localDataSource.getMovieDetails(id)

    override fun cacheMovieDetails(movieDetails: MovieDetails): Completable =
        localDataSource.saveMovieDetails(movieDetails)

    override fun getNewMovieDetails(id: Long): Single<MovieDetails> =
        remoteDataSource.getMovieDetailsAsSingle(id)

    // TV Shows
    override fun getTvShows(query: String?, page: Int): Single<TvShowResponse> {
        return if (query.isNullOrEmpty())
            remoteDataSource.getPopularTvShows()
        else
            remoteDataSource.getTvShowSearchResults(query)
    }

    override fun getCachedTvShowDetails(id: Long): Flowable<List<TvShowDetails>> =
            localDataSource.getTvShowDetails(id)

    override fun cacheTvShowDetails(tvShowDetails: TvShowDetails): Completable =
        localDataSource.saveTvShowDetails(tvShowDetails)

    override fun getNewTvShowDetails(id: Long): Single<TvShowDetails> =
        remoteDataSource.getTvShowDetailsAsSingle(id)
}