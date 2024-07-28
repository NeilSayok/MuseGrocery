package com.neilsayok.musewearables.ui.thankyou.screen


import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.base.Screen
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.theme.BottomSheetBackground
import com.neilsayok.musewearables.theme.FontColorLight
import com.neilsayok.musewearables.ui.common.NoToolBar
import com.neilsayok.musewearables.ui.common.PrimaryButton
import com.neilsayok.musewearables.ui.common.SecondaryButton
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.FontSFPro
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.utils.showToast
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainUIState
import kotlinx.coroutines.flow.StateFlow

class ThankYouScreen(
    private val navHostController: NavHostController,
    private val uiState: MainUIState,
    private val onEvent: (MainEvent)->Unit,

    ) : Screen() {


    override fun setNavHost(): NavHostController = navHostController

    override fun setLoadingState(): StateFlow<Boolean>? = null

    @Composable
    override fun Content() {

        val context = LocalContext.current
        val activity = (context as? Activity)

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
                        text = "Thank You For shopping with us",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Bold,
                        fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._24sdp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._64sdp)))

                    PrimaryButton(
                        onClick = { navHostController.navigate(Routes.CategoryScreen.path) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "ORDER MORE",
                            fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._14sdp),
                        )
                    }
                    Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))


                    SecondaryButton(onClick = {
                        showToast(context, "Thanks for using our app!")
                        activity?.finish()
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "CLOSE",
                            fontSize = fontDimensionResource(id = com.intuit.sdp.R.dimen._14sdp),
                        )
                    }
                }
            }
        }
    }


    @Composable
    override fun Toolbar() {
        NoToolBar()
    }





}