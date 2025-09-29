package com.lurian.design_system.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lurian.design_system.components.theme.DarkGray
import com.lurian.design_system.components.theme.White
import com.lurian.designsystem.generated.resources.Res
import com.lurian.designsystem.generated.resources.ic_arrow_right
import org.jetbrains.compose.resources.painterResource


@Composable
fun RectangleCard(recipeName: String, imageRecipe: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .height(100.dp)
            .fillMaxWidth()
            .testTag("rectangle_card"),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        onClick = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = imageRecipe,
                    contentDescription = recipeName,
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
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
                        textAlign = TextAlign.Start,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(DarkGray)
                    .size(24.dp),
                content = {
                    Image(
                        painter = painterResource(Res.drawable.ic_arrow_right),
                        contentDescription = "Arrow Right Icon",
                    )
                }, onClick = { }
            )
        }
    }
}

@Preview
@Composable
private fun RectangleCardPreview() {
    RectangleCard(recipeName = "Hamburguer", imageRecipe = "https://example.com/image.jpg")
}