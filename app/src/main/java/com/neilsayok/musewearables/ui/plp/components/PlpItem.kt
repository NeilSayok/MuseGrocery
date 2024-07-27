package com.neilsayok.musewearables.ui.plp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse.GetCategoryByTypeResponseItem
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.utils.loadImage




@Composable
fun PlpItem(catType: GetCategoryByTypeResponseItem, onClick : (GetCategoryByTypeResponseItem)-> Unit) {

    Card(modifier = Modifier
        .clickable { onClick(catType) }
        .height(dimensionResource(id = com.intuit.sdp.R.dimen._130sdp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row {
            Card(modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._130sdp)), shape = RoundedCornerShape(
                dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {
                Image(
                    painter = loadImage(url = catType.thumbnailImage),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(1f),
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.White)
                    .fillMaxHeight()
                    .padding(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)))

                Text(
                    text = catType.typeName?:EMPTY_STRING,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Normal,
                    fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._20ssp),
                    color = Black
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)))
                Row {
                    Text(
                        text = "${catType.pricePerPiece?: EMPTY_STRING}",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._14ssp),
                        color = Black
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))

                    Text(
                        text = "$/Kg",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Light,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._14ssp),
                        color = Black
                    )
                }

            }
        }
    }

}