package com.lurian.design_system.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lurian.design_system.components.utils.DesignSystemDrawableRes


@Composable
fun SquareCard(imageRecipe: String, recipeName: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 20.dp)
            .height(190.dp)
            .width(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {}
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
        ) {
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
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .size(24.dp)
                    .background(Color.White)
                    .align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(DesignSystemDrawableRes.favorite_icon),
                    contentDescription = "favorite button",
                )
            }
        }
        Column(modifier = Modifier.padding(2.dp)) {
            Text(
                text = "Japanese-style Pancakes Recipe",
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(Color.Cyan),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = DesignSystemDrawableRes.kcal_icon),
                    modifier = Modifier.size(16.dp),
                    contentDescription = "Calories icon",
                    tint = Color.Gray
                )
                Text(
                    text = " 64 Kcal",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = DesignSystemDrawableRes.clock_icon),
                    contentDescription = "Time icon",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
                Text(
                    text = " 12 Min",
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SquareCardPreview() {
    SquareCard(imageRecipe = "https://example.com/image.jpg", recipeName = "Hamburguer")
}