package com.newsapp.di

import android.content.Context
import androidx.room.Room
import com.newsapp.data.repository.ArticlesRepositoryImpl
import com.newsapp.data.source.local.AppLocalDataSource
import com.newsapp.data.source.local.AppLocalDataSourceImpl
import com.newsapp.data.source.local.db.AppDatabase
import com.newsapp.data.source.local.db.ArticlesDao
import com.newsapp.data.source.local.mapper.ArticleLocalMapper
import com.newsapp.data.source.remote.AppRemoteDataSource
import com.newsapp.data.source.remote.AppRemoteDataSourceImpl
import com.newsapp.data.source.remote.mapper.ArticleRemoteMapper
import com.newsapp.data.source.remote.service.DutchNewsService
import com.newsapp.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "newsapp-db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideArticlesDao(appDatabase: AppDatabase): ArticlesDao {
        return appDatabase.articlesDao()
    }

    @Singleton
    @Provides
    fun provideAppRemoteDataSource(
        dutchNewsService: DutchNewsService,
        articleRemoteMapper: ArticleRemoteMapper
    ): AppRemoteDataSource = AppRemoteDataSourceImpl(
        dutchNewsService,
        articleRemoteMapper
    )

    @Singleton
    @Provides
    fun provideAppLocalDataSource(
        articlesDao: ArticlesDao,
        articleLocalMapper: ArticleLocalMapper
    ): AppLocalDataSource = AppLocalDataSourceImpl(
        articlesDao,
        articleLocalMapper
    )

    @Singleton
    @Provides
    fun provideArticlesRepository(
        remoteDataSource: AppRemoteDataSource,
        localDataSource: AppLocalDataSource
    ): ArticlesRepository = ArticlesRepositoryImpl(
        remoteDataSource,
        localDataSource
    )

}