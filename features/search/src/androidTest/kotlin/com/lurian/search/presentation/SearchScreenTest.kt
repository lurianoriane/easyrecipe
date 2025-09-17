package com.lurian.search.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lurian.search.domain.model.Recipe
import com.lurian.search.presentation.state.SearchRecipeUiState
import com.lurian.search.presentation.view.SearchRoute
import com.lurian.search.presentation.viewmodel.SearchRecipeViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin


@RunWith(AndroidJUnit4::class)
internal class SearchScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel = mockk<SearchRecipeViewModel>(relaxed = true)

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun given_a_list_of_recipes_when_searching_then_displays_results() = runTest {
        val recipes = listOf(
            Recipe("1", "Pizza Margherita", "image1.jpg", listOf("Dinner")),
            Recipe("2", "Pizza Pepperoni", "image2.jpg", listOf("Dinner"))
        )
        val uiState = SearchRecipeUiState(
            listSearchRecipe = recipes,
            isLoading = false,
            isError = false,
            listMealType = emptyList()
        )
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        composeTestRule.setContent {
            SearchRoute(viewModel = mockViewModel)
        }
        composeTestRule.onNodeWithText("Pesquise sua receita aqui").performTextInput("Pizza")
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithText("Pizza Margherita").isDisplayed()
        }
    }

// todo arrumar os testes
    @Test
    fun given_an_empty_list_when_searching_then_displays_no_results() = runTest {
//        coEvery { searchRepository.searchRecipes("Pizza") } returns flowOf(emptyList())

//        composeTestRule.setContent {
//            SearchRoute(hiltViewModel())
//        }
//
//        composeTestRule.onNodeWithText("Pesquise sua receita aqui").performTextInput("Pizza")
//        composeTestRule.onNodeWithText("Pizza Pepperoni").isNotDisplayed()
    }

    @Test
    fun given_a_network_error_when_searching_then_displays_error() = runTest {
//        coEvery { searchRepository.searchRecipes("Pizza") } throws Exception("Network error")

//        composeTestRule.setContent {
//            SearchRoute(hiltViewModel())
//        }
//
//        composeTestRule.onNodeWithText("Pesquise sua receita aqui").performTextInput("Pizza")
//        composeTestRule.onNodeWithText("Pizza Pepperoni").isNotDisplayed()
    }
}