package net.dejanjokic.mediadb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.util.Constants.DB.TABLE_TV_SHOW_DETAILS

@Dao
interface TvShowDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(movie: TvShowDetails): Completable

    @Query("SELECT * FROM $TABLE_TV_SHOW_DETAILS WHERE id=:id")
    fun getTvShowDetails(id: Long): Flowable<List<TvShowDetails>>
}