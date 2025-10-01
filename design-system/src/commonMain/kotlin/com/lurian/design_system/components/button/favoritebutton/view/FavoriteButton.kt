package com.lurian.design_system.components.button.favoritebutton.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.lurian.design_system.components.button.favoritebutton.model.FavoriteButtonUiModel
import com.lurian.design_system.components.theme.White
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun FavoriteButton(
    favoriteButtonUiModel: FavoriteButtonUiModel,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val iconStyle = getIconStyle(favoriteButtonUiModel)
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .size(24.dp)
            .background(White)
    ) {
        Image(
            painter = painterResource(iconStyle),
            contentDescription = "favorite button",
        )
    }
}

@Composable
private fun getIconStyle(favoriteButtonUiModel: FavoriteButtonUiModel): DrawableResource {
    return if (favoriteButtonUiModel.isSelected) {
        favoriteButtonUiModel.iconStyle.iconSelected
    } else favoriteButtonUiModel.iconStyle.iconUnselected
}

@Preview
@Composable
private fun FavoriteButtonPrev() {
    FavoriteButton(
        favoriteButtonUiModel = FavoriteButtonUiModel(
            isSelected = false
        ),
        modifier = Modifier,
        onClick = {})
}

@Preview
@Composable
private fun FavoriteButtonPrevSelected() {
    FavoriteButton(
        favoriteButtonUiModel = FavoriteButtonUiModel(
            isSelected = true
        ),
        modifier = Modifier,
        onClick = {})
}