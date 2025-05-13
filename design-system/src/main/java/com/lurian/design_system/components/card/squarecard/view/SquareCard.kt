package com.lurian.design_system.components.card.squarecard.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lurian.design_system.components.button.favoritebutton.model.FavoriteButtonUiModel
import com.lurian.design_system.components.button.favoritebutton.view.FavoriteButton
import com.lurian.design_system.components.card.squarecard.model.SquareCardUiModel
import com.lurian.design_system.components.utils.DesignSystemDrawableRes

@Composable
fun SquareCard(
    squareCardUiModel: SquareCardUiModel,
    modifier: Modifier,
    onCardClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
) {
    // todo revisar alinhamentos
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 20.dp)
            .height(190.dp)
            .width(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onCardClick
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
        ) {
            RecipeImage(
                imageRecipe = squareCardUiModel.recipeImage,
                recipeName = squareCardUiModel.recipeName
            )
            FavoriteButton(
                favoriteButtonUiModel = FavoriteButtonUiModel(
                    isSelected = squareCardUiModel.isFavorite,
                ),
                onClick = onFavoriteButtonClick,
                modifier = modifier
            )
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = squareCardUiModel.recipeName,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = DesignSystemDrawableRes.ic_kcal),
                    modifier = Modifier.size(16.dp),
                    contentDescription = "Calories icon",
                )
                Text(
                    text = squareCardUiModel.recipeKcal, modifier = Modifier.padding(4.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = DesignSystemDrawableRes.ic_clock),
                    contentDescription = "Time icon",
                    modifier = Modifier.size(16.dp),
                )
                Text(
                    text = squareCardUiModel.recipeTime, modifier = Modifier.padding(4.dp)
                )
            }
        }

    }
}


@Composable
private fun RecipeImage(imageRecipe: String, recipeName: String) {
    AsyncImage(
        model = imageRecipe,
        contentDescription = recipeName,
        contentScale = ContentScale.Inside,
        alignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Blue)
            .fillMaxSize()
    )
}


@Preview(showBackground = true)
@Composable
private fun SquareCardPreview() {
    SquareCard(
        squareCardUiModel = SquareCardUiModel(
            recipeKcal = "64 Kcal",
            recipeTime = "12 Min",
            recipeImage = "https://example.com/image.jpg",
            recipeName = "Hamburguer"
        ),
        onCardClick = {},
        onFavoriteButtonClick = {},
        modifier = Modifier
    )
}