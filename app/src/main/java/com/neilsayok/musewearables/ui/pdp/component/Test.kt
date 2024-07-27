package com.neilsayok.musewearables.ui.pdp.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.gson.Gson
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse.GetCategoryByTypeResponseItem
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.theme.Tertiary
import com.neilsayok.musewearables.ui.common.PrimaryButton
import com.neilsayok.musewearables.ui.common.SecondaryButton
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource

@Preview(showBackground = false, showSystemUi = true)
@Composable
fun Test() {

    val gson = Gson()

    val str =
        "{\n" + "    \"typeID\": 2,\n" + "    \"typeName\": \"Green Apple\",\n" + "    \"description\": \"The Granny Smith, also known as a green apple or sour apple, is an apple cultivar that originated in Australia in 1868.[1] It is named after Maria Ann Smith, who propagated the cultivar from a chance seedling.\",\n" + "    \"thumbnailImage\": \"https://museassignment.s3.ap-south-1.amazonaws.com/ios/cartassignment/green_apple1.jpg\",\n" + "    \"sliderImages\": [\n" + "      \"https://museassignment.s3.ap-south-1.amazonaws.com/ios/cartassignment/green_apple1.jpg\",\n" + "      \"https://museassignment.s3.ap-south-1.amazonaws.com/ios/cartassignment/green_apple2.jpg\"\n" + "    ],\n" + "    \"pricePerPiece\": 2,\n" + "    \"weightPerPiece\": 200\n" + "  }"

    val item: GetCategoryByTypeResponseItem? =
        gson.fromJson(str, GetCategoryByTypeResponseItem::class.java)




}