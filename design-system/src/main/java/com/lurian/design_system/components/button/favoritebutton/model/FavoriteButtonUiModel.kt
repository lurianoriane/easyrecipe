package com.lurian.design_system.components.button.favoritebutton.model

import androidx.annotation.DrawableRes
import com.lurian.design_system.components.utils.DesignSystemDrawableRes

data class FavoriteButtonUiModel(
    val iconStyle: IconRes = IconRes(),
    val isSelected: Boolean = false,
)

data class IconRes(
    @DrawableRes val iconSelected: Int = DesignSystemDrawableRes.ic_favorite_button_selected,
    @DrawableRes val iconUnselected: Int = DesignSystemDrawableRes.ic_favorite_button_unselected,
)
