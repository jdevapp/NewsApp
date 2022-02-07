package com.newsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.newsapp.domain.repository.ArticlesRepository
import com.newsapp.util.FakeArticlesRepository
import com.newsapp.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration test for the SharedViewModel
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SharedViewModelTest {
    // Use repository to be injected into the viewmodel
    private lateinit var articlesRepository: ArticlesRepository
    private lateinit var sharedViewModel: SharedViewModel
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel() {
        articlesRepository = FakeArticlesRepository()
        sharedViewModel = SharedViewModel(articlesRepository)
    }

    /**
     * check if articles are loaded correclty
     * */
    @Test
    fun articlesIsLoaded()  {
        val articles = sharedViewModel.articles.value
        // Check that data were loaded correctly
        assertThat(articles.size).isEqualTo(20) // 20 articles from the Json file
    }
}