package com.neilsayok.musewearables.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.neilsayok.musewearables.theme.SecondaryButtonColor


@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Button(onClick = {onClick()},
        modifier = Modifier.then(modifier),
        colors = ButtonDefaults.buttonColors(
            containerColor = SecondaryButtonColor,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))
    ) {
        content()
    }



}