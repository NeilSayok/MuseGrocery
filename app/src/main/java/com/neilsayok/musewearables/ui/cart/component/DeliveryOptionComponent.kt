package com.neilsayok.musewearables.ui.cart.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.theme.Black
import com.neilsayok.musewearables.theme.Secondary
import com.neilsayok.musewearables.utils.FontRoboto


@Preview(showBackground = true)
@Composable
fun DeliveryOptionComponentList(){

    var selectedPos by remember { mutableIntStateOf(0) }

    Column (modifier = Modifier.wrapContentHeight()){
        DeliveryOptionComponent(
            icon = painterResource(id = R.drawable.ic_walking),
            text = "I’ll pick it up Myself",
            isSelected = selectedPos == 0,
            position = 0,
            selectedPos = {selectedPos = it},
        )
        DeliveryOptionComponent(
            icon = painterResource(id = R.drawable.ic_bike),
            text = "By Courier",
            isSelected = selectedPos == 1,
            position = 1,
            selectedPos = {selectedPos = it}
        )
        DeliveryOptionComponent(
            icon = painterResource(id = R.drawable.ic_drone),
            text = "By Drone",
            isSelected = selectedPos == 2,
            position = 2,
            selectedPos = {selectedPos = it}
        )

    }
}



@Composable
fun DeliveryOptionComponent(
    icon : Painter ,
    text : String ,
    isSelected : Boolean ,
    position : Int,
    selectedPos : (Int)-> Unit
) {

    val color = if (isSelected) Secondary else Black
    val padding = PaddingValues(
        horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp),
        vertical = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)
    )


    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(padding).clickable { selectedPos(position) }) {
        Icon(painter = icon, contentDescription = null, tint = color)
        Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))
        Text(text = text, modifier = Modifier.weight(1f), color = color,
            fontFamily = FontRoboto,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal)
        if (isSelected)
        Icon(imageVector = Icons.Outlined.Check, contentDescription = "$text is selected $isSelected", tint = color)
    }



    
}