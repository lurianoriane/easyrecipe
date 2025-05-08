package com.lurian.design_system.components.chip

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChipList(chipList: List<String>, modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    var selectedChipIndex by remember { mutableIntStateOf(-1) }
    LazyRow(modifier) {
        items(chipList.size) { index ->
            val chip = chipList[index]
            Chip(
                text = chip,
                isSelected = selectedChipIndex == index,
                modifier = Modifier.padding(12.dp),
                onClick = {
                    selectedChipIndex = if (selectedChipIndex == index) {
                        onClick("")
                        -1
                    } else {
                        onClick(chip)
                        index
                    }
                }
            )

        }
    }
}

@Preview
@Composable
private fun ChipListPrev() {
    ChipList(chipList = getFoodCategories(), onClick = {})
}

private fun getFoodCategories(): List<String> {
    return listOf(
        "Pizza",
        "Sushi",
        "Hambúrguer",
        "Salada",
        "Massas",
        "Sobremesas",
        "Churrasco",
        "Vegano",
        "Frutos do Mar",
        "Comida Mexicana"
    )
}