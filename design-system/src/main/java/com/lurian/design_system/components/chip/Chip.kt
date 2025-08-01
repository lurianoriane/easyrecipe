package com.lurian.design_system.components.chip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Chip(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val backgroundColor =
        if (isSelected) Color.Black else Color.LightGray
    val textColor = if (isSelected) Color.White else Color.Black
    Surface(
        color = backgroundColor,
        modifier = modifier
            .height(40.dp)
            .width(120.dp),
        shape = RoundedCornerShape(32.dp),
        onClick = onClick
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = textColor,
                textAlign = TextAlign.Center,
                maxLines = 2,
            )
        }
    }
}

@Preview
@Composable
private fun ChipPreview() {
    Chip(text = "Comida Mexicana", isSelected = true, modifier = Modifier, onClick = {})
}