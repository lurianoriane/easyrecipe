package com.example.easyrecipe.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easyrecipe.R
import com.example.easyrecipe.retrieveRecipeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SearchScreenSuccess(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
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
            SearchComponent()
            SearchResultList()
        }
    })
}

@Composable
fun SearchResultList() {
    val recipeList = retrieveRecipeList()
    LazyColumn {
        items(count = recipeList.count(), itemContent = {
            val recipe = recipeList[it]
            HorizontalCard(recipe.name, recipe.image)
        })
    }
}

@Composable
fun SearchComponent() {
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

@Composable
fun HorizontalCard(recipeName: String, imageRecipe: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .height(100.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageRecipe),
                    contentDescription = recipeName,
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    modifier = Modifier.clip(RoundedCornerShape(12.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = recipeName,
                        modifier = Modifier,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_right_small),
                        contentDescription = "food image",
                        contentScale = ContentScale.Inside,
                        alignment = Alignment.Center
                    )
                }, onClick = { }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HorizontalCardPreview() {
    val recipeList = retrieveRecipeList()
    HorizontalCard(recipeList[0].name, recipeList[0].image)
}

@Preview(showBackground = true)
@Composable
private fun SearchComponentPreview() {
    SearchComponent()
}
