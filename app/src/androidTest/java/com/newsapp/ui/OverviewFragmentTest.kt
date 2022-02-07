package com.newsapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.newsapp.R
import com.newsapp.domain.repository.ArticlesRepository
import com.newsapp.launchFragmentInHiltContainer
import com.newsapp.ui.overview.OverviewFragment
import com.newsapp.util.FakeArticlesRepository
import com.newsapp.util.MainCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration test for the Overview screen.
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class OverviewFragmentTest {
    // Use repository to be injected into the viewmodel
    private lateinit var articlesRepository: ArticlesRepository
    private lateinit var sharedViewModel: SharedViewModel

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
        articlesRepository = FakeArticlesRepository()
        sharedViewModel = SharedViewModel(articlesRepository)
    }

    /**
     * check if the navigation is working between fragment (To DetailsFragment)
     * */
    @Test
    fun testNavigationToDetailsFragment() {
        // GIVEN - On the "Overview" screen.
        val navController = TestNavHostController(getApplicationContext())
        launchFragment(navController)

        // WHEN - the 1st Article clicked
        onView(withId(R.id.first_article_card)).perform(click())
        // THEN - Verify that we navigated to the Details screen.
        assertEquals(navController.currentDestination?.id, R.id.detailsFragment)
    }

    private fun launchFragment(navController: TestNavHostController) {
        launchFragmentInHiltContainer<OverviewFragment>() {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.overviewFragment)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

}