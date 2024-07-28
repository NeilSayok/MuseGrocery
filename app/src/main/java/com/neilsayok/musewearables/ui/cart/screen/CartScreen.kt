package com.neilsayok.musewearables.ui.cart.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.base.Screen
import com.neilsayok.musewearables.theme.BackgroundColor
import com.neilsayok.musewearables.theme.Primary
import com.neilsayok.musewearables.ui.cart.component.DeliveryOptionComponentList
import com.neilsayok.musewearables.ui.common.PrimaryButton
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainUIState
import kotlinx.coroutines.flow.StateFlow

class CartScreen(
    private val navController: NavHostController,
    private val uiState: MainUIState,
    private val onEvent: (MainEvent) -> Unit,
) : Screen() {

    override fun setNavHost(): NavHostController = navController

    override fun setLoadingState(): StateFlow<Boolean>? = null

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {

        var isNonContactDelivery by remember { mutableStateOf(true) }



        LazyColumn(
            modifier = Modifier
                .background(BackgroundColor)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))
        ) {
            stickyHeader {
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {
                    Text(
                        text = "Payment Method",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._18ssp),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "CHANGE",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Primary,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._12ssp),

                        )
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)))

                Row(modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {

                    Icon(painter = painterResource(id = R.drawable.credit_card), contentDescription = null)

                    Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))

                    Text(text = "No card Selected",
                        modifier = Modifier.weight(1f))



                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {
                    Text(
                        text = "Delivery Address",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._18ssp),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "CHANGE",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Primary,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._12ssp),

                        )
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)))

                Row(modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {

                    Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = null)

                    Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))

                    Text(text = "Alexandra Smith Cesu 31 k-2 5.st, SIA Chili Riga LV–1012 Latvia",
                        modifier = Modifier.weight(1f))

                    Spacer(modifier = Modifier.weight(1.5f))

                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                    .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {
                    Text(
                        text = "Delivery Options",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._18ssp),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "CHANGE",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        color = Primary,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._12ssp),

                        )
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)))
                DeliveryOptionComponentList()
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {
                    Text(
                        text = "Non-Contact Delivery",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._18ssp),
                        modifier = Modifier.weight(1f)
                    )

                    Switch(checked = isNonContactDelivery, onCheckedChange = {isNonContactDelivery = it})
                }
            }
            item{
                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp)))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    PrimaryButton(onClick = { /*TODO*/ },
                        enabled = false
                    ) {
                        Text(text = "Proceed To Checkout")
                    }
                }

            }




        }
    }

    @Composable
    override fun Toolbar() {
        Card(
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = com.intuit.sdp.R.dimen._56sdp))
                .background(Color.White),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                        contentDescription = "Navigate Up"
                    )
                }

                Text(
                    text = "Confirmation",
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}