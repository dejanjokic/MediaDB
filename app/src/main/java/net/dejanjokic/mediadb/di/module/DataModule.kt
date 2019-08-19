package net.dejanjokic.mediadb.di.module

import dagger.Module
import dagger.Provides
import net.dejanjokic.mediadb.data.DataSource
import net.dejanjokic.mediadb.data.MediaRepository
import net.dejanjokic.mediadb.data.Repository
import net.dejanjokic.mediadb.data.local.LocalDataSource
import net.dejanjokic.mediadb.data.local.MediaDatabase
import net.dejanjokic.mediadb.data.remote.MediaService
import net.dejanjokic.mediadb.data.remote.RemoteDataSource
import net.dejanjokic.mediadb.di.qualifier.Local
import net.dejanjokic.mediadb.di.qualifier.Remote
import net.dejanjokic.mediadb.di.scope.ApplicationScope

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class DataModule {

    @ApplicationScope
    @Provides
    @Local
    fun provideLocalDataSource(db: MediaDatabase): DataSource = LocalDataSource(db)

    @ApplicationScope
    @Provides
    @Remote
    fun provideRemoteDataSource(api: MediaService): DataSource = RemoteDataSource(api)

    @ApplicationScope
    @Provides
    fun provideMediaRepository(@Local local: DataSource, @Remote remote: DataSource): Repository =
        MediaRepository(local, remote)
}