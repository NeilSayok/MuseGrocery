package com.neilsayok.musewearables.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.theme.Tertiary
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource


@Composable
fun AddToCartButton(
    modifier: Modifier = Modifier,
    cartCount: Int,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit
) {


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Tertiary, contentColor = Color.White
        ),
        shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)),
        modifier = Modifier
            .height(40.dp)
            .then(modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(ButtonDefaults.ContentPadding)
        ) {
            if (cartCount == 0) Row(modifier = Modifier
                .clickable {
                    onIncreaseClick()
                }
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.shopping_cart),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "ADD TO CART", fontFamily = FontRoboto)
            }
            else Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "Add to cart",
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onDecreaseClick()
                        })

                Text(
                    text = "$cartCount",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._14ssp)
                )

                Icon(imageVector = Icons.Outlined.Add,
                    contentDescription = "Add to cart",
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onIncreaseClick()
                        })
            }
        }

    }


}