package com.neilsayok.musewearables.ui.pdp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.base.Screen
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.theme.Tertiary
import com.neilsayok.musewearables.ui.common.AddToCartButton
import com.neilsayok.musewearables.ui.common.NoToolBar
import com.neilsayok.musewearables.ui.common.SecondaryButton
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.utils.loadImage
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainUIState
import kotlinx.coroutines.flow.StateFlow

class PDPScreen(
    private val navController: NavHostController,
    private val uiState: MainUIState,
    private val onEvent: (MainEvent) -> Unit,
) : Screen() {

    override fun setNavHost(): NavHostController = navController

    override fun setLoadingState(): StateFlow<Boolean>? = null

    @Composable
    override fun Content() {

        val item  = uiState.selectedPLPItem

        LaunchedEffect(Unit) {
            item?.let {
                onEvent(MainEvent.GetCartCountForItem(item))
                onEvent(MainEvent.IsItemLikedLike(item))
            }

        }


        val isItemLiked = uiState.isLiked
        val cartCount = uiState.cartCount

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (pager, card) = createRefs()

            val guide50 = createGuidelineFromTop(0.6f)
            val guide40 = createGuidelineFromTop(0.4f)


            Image(painter = loadImage(url = item?.sliderImages?.get(0)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.constrainAs(pager) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(guide50)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                })


            Card(
                modifier = Modifier.constrainAs(card) {
                    top.linkTo(guide40)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }, shape = RoundedCornerShape(
                    topStart = dimensionResource(id = com.intuit.sdp.R.dimen._20sdp),
                    topEnd = dimensionResource(id = com.intuit.sdp.R.dimen._20sdp)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = com.intuit.sdp.R.dimen._24sdp),
                            start = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp),
                            end = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp),
                        )
                ) {
                    Text(
                        text = item?.typeName ?: EMPTY_STRING,
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._20ssp),
                        color = Black
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._20ssp),
                                )
                            ) {
                                append("${item?.pricePerPiece ?: EMPTY_STRING}")

                            }
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._18ssp),

                                    )
                            ) {
                                append(" \$/Piece")

                            }
                        },
                        fontFamily = FontRoboto,

                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._20ssp),
                        color = Black
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "~${item?.weightPerPiece ?: EMPTY_STRING} gr/ piece",
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._12ssp),
                        color = Tertiary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item?.description ?: EMPTY_STRING,
                        fontFamily = FontRoboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = fontDimensionResource(id = com.intuit.ssp.R.dimen._14ssp),
                        color = Black,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SecondaryButton(onClick = {
                            item?.let {
                                onEvent(MainEvent.PressLike(item))
                            }
                        }, modifier = Modifier.weight(1f)) {
                            Icon(
                                painter = if (isItemLiked) painterResource(id = R.drawable.ic_heart_filled)
                                else painterResource(id = R.drawable.ic_heart_outline),
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))

                        AddToCartButton(cartCount = cartCount, onIncreaseClick = {
                            item?.let {
                                onEvent(MainEvent.IncreaseItemInCart(it))
                            }
                        }, onDecreaseClick = {
                            item?.let {
                                onEvent(MainEvent.DecreaseItemInCart(it))
                            }
                        }, modifier = Modifier.weight(3f)
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