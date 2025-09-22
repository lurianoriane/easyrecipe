package com.lurian.search.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lurian.design_system.components.card.RectangleCard
import com.lurian.design_system.components.chip.ChipList
import com.lurian.search.domain.model.Recipe
import com.lurian.search.presentation.intent.SearchRecipeIntent
import com.lurian.search.presentation.state.SearchRecipeUiState
import com.lurian.search.presentation.viewmodel.SearchRecipeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
private fun SearchScreen(state: SearchRecipeUiState, onIntent: (SearchRecipeIntent) -> Unit) {
    when {
        state.isError -> {}
        else -> {
            SearchScreenSuccess(state, onIntent)
        }
    }
}

@Composable
private fun SearchScreenSuccess(
    state: SearchRecipeUiState,
    onIntent: (SearchRecipeIntent) -> Unit
) {

    Column(modifier = Modifier.padding(16.dp)) {
        SearchComponent(onIntent)
        ChipList(chipList = state.listMealType) { mealType ->
            onClickMealType(mealType = mealType, onIntent = onIntent)
        }
        SearchResultList(recipeList = state.listSearchRecipe, isLoading = state.isLoading)
    }
}

private fun onClickMealType(
    mealType: String,
    onIntent: (SearchRecipeIntent) -> Unit
) {
    if (mealType.isBlank()) {
        onIntent(SearchRecipeIntent.OnFilterClean)
    } else {
        onIntent(SearchRecipeIntent.OnFilterClick(mealType))
    }
}

private fun CoroutineScope.debounce(
    delayMillis: Long = 500L,
    action: () -> Unit
) {
    launch {
        delay(delayMillis)
        action()
    }
}

@Composable
private fun SearchResultList(recipeList: List<Recipe>, isLoading: Boolean) {
    if (isLoading) {
        LoadingScreen()
    } else {
        LazyColumn {
            items(recipeList) { recipe ->
                RectangleCard(
                    recipeName = recipe.name,
                    imageRecipe = recipe.image
                )
            }
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SearchComponent(
    onIntent: (SearchRecipeIntent) -> Unit
) {
    var valueOnTextField by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        value = valueOnTextField,
        textStyle = TextStyle(fontWeight = FontWeight.Thin),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            unfocusedTextColor = Color.LightGray
        ),
        singleLine = true,
        onValueChange = {
            valueOnTextField = it
            coroutineScope.debounce {
                onIntent(SearchRecipeIntent.OnSearchRecipe(it))
            }
        },
        label = { Text(text = "Pesquise sua receita aqui") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
    )
}

@Composable
fun SearchRoute(viewModel: SearchRecipeViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsState()
    SearchScreen(
        state = state,
        onIntent = viewModel::handleIntent
    )
}


@Preview(showBackground = true)
@Composable
private fun SearchComponentPreview() {
    SearchComponent(onIntent = {})
}
