package com.newsapp.data

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.newsapp.R
import com.newsapp.data.source.remote.entity.TopHeadlineResponse
import com.newsapp.data.source.remote.mapper.ArticleRemoteMapper
import com.newsapp.util.Util
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 *  unit tests for article remote mapper.
 */

@HiltAndroidTest
class ArticleRemoteMapperTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var articleRemoteMapper: ArticleRemoteMapper

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `mapFromEntityListTest`() {
        val articlesEntity =
            Gson().fromJson(Util.getJson(R.raw.data), TopHeadlineResponse::class.java).articles

        val articles = articleRemoteMapper.mapFromEntityList(articlesEntity)

        assertThat(articlesEntity.size).isGreaterThan(0) //check if the list is not empty
        assertThat(articlesEntity.size).isEqualTo(articles.size)
    }
}