package com.neilsayok.musewearables.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoToolBar() {
    Box(modifier = Modifier.height(0.dp)) {}
}