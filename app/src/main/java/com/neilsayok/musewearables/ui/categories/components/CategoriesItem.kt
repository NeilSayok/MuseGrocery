package com.neilsayok.musewearables.ui.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.utils.loadImage

@Preview
@Composable
fun CategoriesItemPreview() {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._16sdp)),
    ) {

        items(5){
            CategoriesItem()
        }

    }

}


@Composable
fun CategoriesItem() {

        Card(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._142sdp))) {
            Column {
                Image(painter = loadImage(url = "https://letsenhance.io/static/66c1b6abf8f7cf44c19185254d7adb0c/28ebd/AiArtBefore.jpg"),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )
                Column(modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))
                ) {
                    Text(text = "Vegetables",
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._14ssp),
                        color = Black)
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)))
                    Text(text = "(+100)",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._8ssp),
                        color = Black)
                }
            }
        }

}