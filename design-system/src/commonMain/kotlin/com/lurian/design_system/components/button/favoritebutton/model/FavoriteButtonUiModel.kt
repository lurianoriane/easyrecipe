package com.lurian.design_system.components.button.favoritebutton.model

import com.lurian.design_system.components.utils.DesignSystemDrawableRes
import com.lurian.designsystem.generated.resources.ic_favorite_button_selected
import com.lurian.designsystem.generated.resources.ic_favorite_button_unselected
import org.jetbrains.compose.resources.DrawableResource

data class FavoriteButtonUiModel(
    val iconStyle: IconRes = IconRes(),
    val isSelected: Boolean = false,
)

data class IconRes(
    val iconSelected: DrawableResource = DesignSystemDrawableRes.ic_favorite_button_selected,
    val iconUnselected: DrawableResource = DesignSystemDrawableRes.ic_favorite_button_unselected,
)
