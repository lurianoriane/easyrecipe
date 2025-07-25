package com.lurian.design_system.components.button.closebutton.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lurian.designsystem.R

@Composable
fun CloseButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .size(24.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close button",
        )
    }
}

@Preview
@Composable
private fun CloseButtonPrev() {
    CloseButton()
}