package net.dejanjokic.mediadb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.dejanjokic.mediadb.data.model.MovieDetails
import net.dejanjokic.mediadb.data.model.TvShowDetails
import net.dejanjokic.mediadb.util.Constants.DB.DB_VERSION

@Database(
    entities = [MovieDetails::class, TvShowDetails::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class MediaDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun tvShowDetailsDao(): TvShowDetailsDao
}