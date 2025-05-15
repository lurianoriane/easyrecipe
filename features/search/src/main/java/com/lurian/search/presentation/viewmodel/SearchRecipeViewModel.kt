package com.lurian.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lurian.meal_type.domain.usecase.GetMealTypesUseCase
import com.lurian.search.domain.model.Recipe
import com.lurian.search.domain.usecase.SearchRecipeUseCase
import com.lurian.search.presentation.intent.SearchRecipeIntent
import com.lurian.search.presentation.state.SearchRecipeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchRecipeViewModel @Inject constructor(
    private val useCase: SearchRecipeUseCase,
    private val mealTypeUseCase: GetMealTypesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchRecipeUiState().initialState())
    val uiState: StateFlow<SearchRecipeUiState> = _uiState
    private var fullRecipeList: List<Recipe> = emptyList()

    init {
        viewModelScope.launch {
            mealTypeUseCase.getMealTypes().onStart {
                _uiState.update { it.copy(isLoading = true) }
            }.collect { list ->
                _uiState.update { it.copy(listMealType = list, isLoading = false) }
            }
        }
    }

    fun handleIntent(intent: SearchRecipeIntent) {
        when (intent) {
            is SearchRecipeIntent.OnSearchRecipe -> {
                getRecipes(intent.nameRecipe)
            }

            is SearchRecipeIntent.OnFilterClean -> {
                _uiState.update { it.copy(listSearchRecipe = fullRecipeList) }
            }

            is SearchRecipeIntent.OnFilterClick -> {
                filterRecipes(intent.mealType)
            }
        }
    }

    private fun getRecipes(nameRecipe: String) {
        viewModelScope.launch {
            useCase.searchRecipes(nameRecipe).flowOn(Dispatchers.IO).onStart {
                _uiState.update { it.copy(isLoading = true, isError = false) }
            }.catch {
                _uiState.update { it.copy(isError = true, isLoading = false) }
            }.collect { recipes ->
                _uiState.update {
                    it.copy(listSearchRecipe = recipes, isLoading = false)
                }
                fullRecipeList = recipes
            }
        }
    }

    private fun filterRecipes(mealType: String) {
        _uiState.update { state ->
            val filteredList = fullRecipeList.filter { recipe ->
                recipe.mealType.contains(mealType)
            }
            state.copy(listSearchRecipe = filteredList, isLoading = false)
        }
    }
}