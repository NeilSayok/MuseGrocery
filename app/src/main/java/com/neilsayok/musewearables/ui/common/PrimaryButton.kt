package com.neilsayok.musewearables.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.neilsayok.musewearables.theme.Tertiary


@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {

    Button(onClick = {onClick()},
        modifier = Modifier.then(modifier),
        colors = ButtonDefaults.buttonColors(
            containerColor = Tertiary,
            contentColor = Color.White
        ),
        enabled = enabled,
        shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))
    ) {
        content()
    }



}