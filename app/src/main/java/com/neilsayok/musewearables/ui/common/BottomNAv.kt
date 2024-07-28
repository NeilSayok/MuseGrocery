package com.neilsayok.musewearables.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.theme.Primary


@Composable
fun BottomNav(navController: NavController, backgroundColor: Color) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = com.intuit.sdp.R.dimen._48sdp)),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)) {
            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {

                Row(modifier = Modifier.weight(1f),horizontalArrangement = Arrangement.Center) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grid),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().clickable {
                            navController.navigate(Routes.CategoryScreen.path)
                        },
                        tint = if (navController.currentDestination?.route == Routes.CategoryScreen.path) Primary else Black,
                        )
                }

                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center)  {
                    Icon(painter = painterResource(id = R.drawable.shopping_cart),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().clickable {
                            navController.navigate(Routes.CartScreen.path)
                        },
                        tint = if (navController.currentDestination?.route == Routes.CartScreen.path) Primary else Black
                    )
                }
            }
        }
    }



}