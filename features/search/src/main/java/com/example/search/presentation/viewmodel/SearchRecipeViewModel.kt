package com.example.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.domain.usecase.SearchRecipeUseCase
import com.example.search.presentation.state.SearchRecipeUiState
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
    private val useCase: SearchRecipeUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchRecipeUiState().initialState())
    val uiState: StateFlow<SearchRecipeUiState> = _uiState

    private fun getRecipes(nameRecipe: String) {
        viewModelScope.launch {
            useCase.searchRecipes(nameRecipe).onStart {
                _uiState.update { it.copy(isLoading = true, isError = false) }
            }.flowOn(Dispatchers.IO).catch {
                _uiState.update { it.copy(isError = true, isLoading = false) }
            }.collect { recipes ->
                _uiState.update { it.copy(searchRecipe = recipes) }
            }
        }
    }
}

// todo bottom nav fazer