package com.newsapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.newsapp.R
import com.newsapp.data.source.local.AppLocalDataSource
import com.newsapp.data.source.local.AppLocalDataSourceImpl
import com.newsapp.data.source.local.db.AppDatabase
import com.newsapp.data.source.local.db.ArticlesDao
import com.newsapp.data.source.local.mapper.ArticleLocalMapper
import com.newsapp.domain.model.Article
import com.newsapp.result.data
import com.newsapp.result.succeeded
import com.newsapp.util.Data
import com.newsapp.util.Util.getJson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Unit tests for [AppLocalDataSource]
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class AppDataSourceTest {
    // Subject under test
    private lateinit var appLocalDataSource: AppLocalDataSource

    private lateinit var articlesDao: ArticlesDao
    private lateinit var db: AppDatabase

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        articlesDao = db.articlesDao()
        appLocalDataSource = AppLocalDataSourceImpl(articlesDao, ArticleLocalMapper())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
    /**
     * check if we can insert and get articles
     */
    @Test
    fun insertAndGetArticlesFromDBTest() = runBlockingTest {
        val articles = Data.articles

        appLocalDataSource.insertArticles(articles)

        val result = appLocalDataSource.getArticles()
        assertThat(result.succeeded).isTrue()
        assertThat(result.data!!.size).isEqualTo(articles.size)
    }
    /**
     * check if we can insert and delete articles
     */
    @Test
    fun insertAndDeleteArticlesFromDBTest() = runBlockingTest {
        val articles = Data.articles

        appLocalDataSource.insertArticles(articles)
        appLocalDataSource.deleteAllArticles()

        val result = appLocalDataSource.getArticles()
        assertThat(result.succeeded).isTrue()
        assertThat(result.data!!.size).isEqualTo(0)
    }
}