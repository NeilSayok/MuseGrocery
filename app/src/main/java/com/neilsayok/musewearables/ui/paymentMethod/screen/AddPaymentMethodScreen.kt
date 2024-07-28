package com.neilsayok.musewearables.ui.paymentMethod.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.toUpperCase
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.base.Screen
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.theme.BackgroundColor
import com.neilsayok.musewearables.ui.common.PrimaryButton
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainUIState
import kotlinx.coroutines.flow.StateFlow

class AddPaymentMethodScreen(
    private val navController: NavHostController,
    private val uiState: MainUIState,
    private val onEvent: (MainEvent) -> Unit,
) : Screen() {

    override fun setNavHost(): NavHostController = navController

    override fun setLoadingState(): StateFlow<Boolean>? = null

    @Composable
    override fun Content() {

        var name by remember { mutableStateOf(EMPTY_STRING) }
        var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
        var expDate by remember { mutableStateOf(TextFieldValue("")) }
        var cvv by remember { mutableStateOf(EMPTY_STRING) }


        Column(modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))) {
            Text(
                text = "Credit / Debit card",
                fontFamily = FontRoboto,
                fontWeight = FontWeight.Medium,
                fontSize = fontDimensionResource(
                    id = com.intuit.ssp.R.dimen._24ssp
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))

            val spacing = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp)

            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                val (cardBg, cardNumberText, nameText, expDateText) = createRefs()


                Card(modifier = Modifier.constrainAs(cardBg) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.card),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(text = cardNumber.text,
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = fontDimensionResource(
                        id = com.intuit.sdp.R.dimen._16sdp,
                    ),
                    color = Color.White,
                    modifier = Modifier.constrainAs(cardNumberText) {
                        linkTo(cardBg.start, cardBg.end, startMargin = spacing, endMargin = spacing)
                        top.linkTo(cardBg.top)
                        bottom.linkTo(cardBg.bottom)
                        width = Dimension.fillToConstraints
                    })

                Text(text = name.uppercase(),
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = fontDimensionResource(
                        id = com.intuit.sdp.R.dimen._14sdp,
                    ),
                    color = Color.White,
                    modifier = Modifier.constrainAs(nameText) {
                        start.linkTo(cardBg.start, spacing)
                        top.linkTo(cardNumberText.bottom)
                        bottom.linkTo(cardBg.bottom)
                    })

                Text(text = expDate.text,
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = fontDimensionResource(
                        id = com.intuit.sdp.R.dimen._12sdp,
                    ),
                    color = Color.White,
                    modifier = Modifier.constrainAs(expDateText) {
                        end.linkTo(cardBg.end, spacing)
                        top.linkTo(cardNumberText.bottom)
                        bottom.linkTo(cardBg.bottom)
                    })

            }

            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp)))
            OutlinedTextField(value = name,
                onValueChange = { name = it.uppercase() },
                label = { Text(text = "Name on Card") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text)


            )
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))

            OutlinedTextField(value = cardNumber,
                onValueChange = { if(it.text.length <= 19)cardNumber = formatCardNoWithSpaces(it) },
                label = { Text(text = "Card Number") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.NumberPassword)
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))

            Row {
                OutlinedTextField(value = expDate,
                    onValueChange = { if (it.text.length <= 5)expDate = formatDateWithSlash(it) },
                    label = { Text(text = "Expiry Date") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.NumberPassword)

                )
                Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))

                OutlinedTextField(value = cvv,
                    onValueChange = { if (it.length <= 3) cvv = it },
                    label = { Text(text = "CVV") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.NumberPassword)
                )

            }

            Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._30sdp)))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                PrimaryButton(onClick = {
                    onEvent(MainEvent.SaveCard(cardNumber.text))
                    navController.navigateUp()
                },
                    enabled = name.isNotEmpty() && cardNumber.text.isNotEmpty() && expDate.text.isNotEmpty() && cvv.isNotEmpty(),
                ) {
                    Text(text = "USE THIS CARD")
                }
            }

        }
    }

    @Composable
    override fun Toolbar() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = com.intuit.sdp.R.dimen._56sdp))
                .background(BackgroundColor)
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = "Navigate Up"
                )
            }
        }
    }


    fun formatCardNoWithSpaces(textFieldValue: TextFieldValue): TextFieldValue {
        val text = textFieldValue.text.replace(" ", "")
        val formattedText = text.chunked(4).joinToString(" ")

        // Calculate new cursor position
        val cursorPosition = if (textFieldValue.selection.start + (formattedText.length - text.length) > formattedText.length) {
            formattedText.length
        } else {
            textFieldValue.selection.start + (formattedText.length - text.length)
        }

        return textFieldValue.copy(
            text = formattedText,
            selection = TextRange(cursorPosition)
        )
    }

    fun formatDateWithSlash(textFieldValue: TextFieldValue): TextFieldValue {
        val text = textFieldValue.text.replace("/", "")
        val formattedText = text.chunked(2).joinToString("/")

        // Calculate new cursor position
        val cursorPosition = if (textFieldValue.selection.start + (formattedText.length - text.length) > formattedText.length) {
            formattedText.length
        } else {
            textFieldValue.selection.start + (formattedText.length - text.length)
        }

        return textFieldValue.copy(
            text = formattedText,
            selection = TextRange(cursorPosition)
        )
    }


}