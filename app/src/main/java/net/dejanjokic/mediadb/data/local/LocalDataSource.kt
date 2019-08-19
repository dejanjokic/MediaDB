package net.dejanjokic.mediadb.data.local

import io.reactivex.Completable
import io.reactivex.Flowable
import net.dejanjokic.mediadb.data.DataSource
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.TvShowDetails
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val db: MediaDatabase) : DataSource {

    // Movies
    override fun getMovieDetails(id: Long): Flowable<List<MovieDetails>> =
        db.movieDetailsDao().getMovieDetails(id)

    override fun saveMovieDetails(movie: MovieDetails): Completable =
        db.movieDetailsDao().insertMovie(movie)

    // TV Shows
    override fun getTvShowDetails(id: Long): Flowable<List<TvShowDetails>> =
        db.tvShowDetailsDao().getTvShowDetails(id)

    override fun saveTvShowDetails(tvShow: TvShowDetails): Completable =
        db.tvShowDetailsDao().insertTvShow(tvShow)
}