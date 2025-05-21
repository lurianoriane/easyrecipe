package com.lurian.search.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lurian.android_testing.activity.HiltActivity
import com.lurian.search.domain.model.Recipe
import com.lurian.search.domain.repository.SearchRepository
import com.lurian.search.presentation.view.SearchRoute
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SearchScreenTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltActivity>()

    @Inject
    lateinit var searchRepository: SearchRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun search_screen_blabla() {
        coEvery { searchRepository.searchRecipes("Pizza") } returns flowOf(
            listOf(
                Recipe("1", "Pizza Margherita", "image1.jpg", listOf("Dinner")),
                Recipe("2", "Pizza Pepperoni", "image2.jpg", listOf("Dinner"))
            )
        )
        composeTestRule.setContent {
            SearchRoute(
               hiltViewModel()
            )
        }

    }
}
