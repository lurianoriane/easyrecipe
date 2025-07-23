package presentation.viewmodel

import app.cash.turbine.test
import com.lurian.meal_type.domain.usecase.GetMealTypesUseCase
import com.lurian.search.domain.model.Recipe
import com.lurian.search.domain.usecase.SearchRecipeUseCase
import com.lurian.search.presentation.intent.SearchRecipeIntent
import com.lurian.search.presentation.state.SearchRecipeUiState
import com.lurian.search.presentation.viewmodel.SearchRecipeViewModel
import com.lurian.testing.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchRecipeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: SearchRecipeViewModel

    private val searchRecipeUseCase: SearchRecipeUseCase = mockk()
    private val mealTypeUseCase: GetMealTypesUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = SearchRecipeViewModel(
            useCase = searchRecipeUseCase,
            mealTypeUseCase = mealTypeUseCase
        )
    }

    @Test
    fun `GIVEN recipe search WHEN recipe typed THEN return list of recipe`() = runTest {
        // given
        val recipeList = listOf(
            Recipe(
                id = "1",
                name = "Pasta",
                image = "http://example.com/pasta.jpg",
                mealType = listOf("Dinner")
            ),

            Recipe(
                id = "2",
                name = "Macarrão",
                image = "http://example.com/macarrao.jpg",
                mealType = listOf("Dinner")
            ),
        )

        coEvery { searchRecipeUseCase.searchRecipes("Pasta") } returns flowOf(recipeList)

        //when
        viewModel.handleIntent(SearchRecipeIntent.OnSearchRecipe(nameRecipe = "Pasta"))

        // then
        viewModel.uiState.test {
            assertEquals(
                awaitItem(),
                SearchRecipeUiState().initialState().copy(isLoading = true, isError = false)
            )
            assertEquals(
                awaitItem(),
                SearchRecipeUiState().initialState()
                    .copy(isLoading = false, listSearchRecipe = recipeList)
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `GIVEN recipe search WHEN there is no recipe match THEN return empty list of recipe`() =
        runTest {
            // given
            val recipeList = emptyList<Recipe>()

            coEvery { searchRecipeUseCase.searchRecipes("Pasta") } returns flowOf(recipeList)

            //when
            viewModel.handleIntent(SearchRecipeIntent.OnSearchRecipe(nameRecipe = "Pasta"))

            // then
            viewModel.uiState.test {
                assertEquals(
                    awaitItem(),
                    SearchRecipeUiState().initialState().copy(isLoading = true, isError = false)
                )
                assertEquals(
                    awaitItem(),
                    SearchRecipeUiState().initialState()
                        .copy(isLoading = false, listSearchRecipe = recipeList)
                )
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN recipe search WHEN error occurs THEN return error`() = runTest {
        // given
        coEvery { searchRecipeUseCase.searchRecipes("Pasta") } returns flow {
            throw Exception("Error fetching recipes")
        }

        //when
        viewModel.handleIntent(SearchRecipeIntent.OnSearchRecipe(nameRecipe = "Pasta"))

        // then
        viewModel.uiState.test {
            assertEquals(
                awaitItem(),
                SearchRecipeUiState().initialState().copy(isLoading = true, isError = false)
            )
            assertEquals(
                awaitItem(),
                SearchRecipeUiState().initialState()
                    .copy(isLoading = false, isError = true)
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}