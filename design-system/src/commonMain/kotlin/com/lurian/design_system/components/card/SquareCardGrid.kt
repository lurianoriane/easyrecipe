package com.lurian.design_system.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SquareCardGrid(titleSection: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .fillMaxSize()
    ) {
        Text(
            text = titleSection,
            modifier = Modifier.align(Alignment.Start),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

}

@Preview
@Composable
private fun SquareCardGridPreview() {
    SquareCardGrid(titleSection = "Popular Recipes")
}