package net.dejanjokic.mediadb.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.dejanjokic.mediadb.data.local.MediaDatabase
import net.dejanjokic.mediadb.di.scope.ApplicationScope
import net.dejanjokic.mediadb.util.Constants.DB.DB_NAME

@Module(includes = [ApplicationModule::class])
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun provideMediaDatabase(context: Context): MediaDatabase =
        Room.databaseBuilder(context, MediaDatabase::class.java, DB_NAME).build()
}