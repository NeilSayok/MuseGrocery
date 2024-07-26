package com.neilsayok.musewearables.ui.home.screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.base.Screen
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.theme.BottomSheetBackground
import com.neilsayok.musewearables.theme.FontColorLight
import com.neilsayok.musewearables.ui.common.PrimaryButton
import com.neilsayok.musewearables.ui.common.SecondaryButton
import com.neilsayok.musewearables.utils.FontPrimary
import com.neilsayok.musewearables.utils.FontSFPro
import com.neilsayok.musewearables.utils.fontDimensionResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreen(private val navHostController: NavHostController) : Screen() {

    private val loadingState = MutableStateFlow(false)


    override fun setNavHost(): NavHostController = navHostController

    override fun setLoadingState(): StateFlow<Boolean> = loadingState

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter)
        {
            Image(
                painter = painterResource(id = R.drawable.home_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Card(shape = RoundedCornerShape(
                topStart = dimensionResource(id = com.intuit.sdp.R.dimen._30sdp),
                topEnd = dimensionResource(id = com.intuit.sdp.R.dimen._30sdp),
            )) {
                Column(
                    modifier = Modifier
                        .background(BottomSheetBackground)
                        .padding(
                            horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp),
                            vertical = dimensionResource(id = com.intuit.sdp.R.dimen._18sdp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._76sdp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Black),
                        shape = CircleShape
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_box),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Black),
                                modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._36sdp))
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._18sdp)))

                    Text(
                        text = "Non-Contact Deliveries",
                        fontFamily = FontPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._24sdp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._18sdp)))

                    Text(
                        text = "When placing an order, select the option “Contactless delivery” and the courier will leave your order at the door.",
                        fontFamily = FontSFPro,
                        fontWeight = FontWeight.Normal,
                        fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._14sdp),
                        textAlign = TextAlign.Center,
                        color = FontColorLight,
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._18sdp)))

                    PrimaryButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "ORDER NOW",
                            fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._14sdp),
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))


                    SecondaryButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Dismiss",
                            fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._14sdp),
                        )
                    }
                }
            }
        }
    }


    @Composable
    override fun Toolbar() {
        Box {}

    }





}