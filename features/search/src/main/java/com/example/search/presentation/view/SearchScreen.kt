package com.example.search.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.components.RectangleCard
import com.example.search.domain.model.Recipe
import com.example.search.presentation.intent.SearchRecipeIntent
import com.example.search.presentation.state.SearchRecipeUiState
import com.lurian.search.R

@Composable
fun SearchScreen(state: SearchRecipeUiState, onIntent: (SearchRecipeIntent) -> Unit) {
    when {
        state.isError -> {}
        state.isLoading -> {}
        else -> {
            SearchScreenSuccess(state, onIntent)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenSuccess(state: SearchRecipeUiState, onIntent: (SearchRecipeIntent) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        // Icons.Rounded.ArrowBack, change icon
                        Icons.Rounded.ArrowBack, contentDescription = "back button"
                    )
                }
            }, title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.search_screen_name),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }, actions = {

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                navigationIconContentColor = Color.Gray
            )
        )
    }, content = {
        Column(modifier = Modifier.padding(it)) {
            SearchComponent(onIntent)
            SearchResultList(state.searchRecipe)
        }
    })
}

@Composable
fun SearchResultList(recipeList: List<Recipe>) {
    LazyColumn {
        items(recipeList) { recipe ->
            RectangleCard(
                recipeName = recipe.name,
                imageRecipe = recipe.image
            )
        }
    }
}

@Composable
fun SearchComponent(onIntent: (SearchRecipeIntent) -> Unit) {
    var valueOnTextField by remember {
        mutableStateOf("")
    }
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
        onValueChange = { valueOnTextField = it },
        label = { Text(text = "Pesquise sua receita aqui") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        keyboardActions = KeyboardActions(onSearch = {

        })
    )
}


@Preview(showBackground = true)
@Composable
private fun SearchComponentPreview() {
    SearchComponent(onIntent = {})
}
