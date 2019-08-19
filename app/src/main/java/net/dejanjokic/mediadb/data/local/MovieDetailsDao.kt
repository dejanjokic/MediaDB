package net.dejanjokic.mediadb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.util.Constants.DB.TABLE_MOVIE_DETAILS

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: MovieDetails): Completable

    @Query("SELECT * FROM $TABLE_MOVIE_DETAILS WHERE id=:id")
    fun getMovieDetails(id: Long): Flowable<List<MovieDetails>>
}