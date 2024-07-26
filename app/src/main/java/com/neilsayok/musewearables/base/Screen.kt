package com.neilsayok.musewearables.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.ui.common.AppLoader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    var sheetState =  MutableStateFlow(SheetValue.PartiallyExpanded)

    var sheetPeekHeight = MutableStateFlow(0.dp)




    abstract fun setNavHost(): NavHostController

    abstract fun setLoadingState(): StateFlow<Boolean>

    @Composable
    abstract fun Content()

    @Composable
    abstract fun Toolbar()





    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BaseScreen() {

        val loading = setLoadingState().collectAsState()





        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {Toolbar()}) {innerPadding->
            val ip = innerPadding
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Content()
                if (loading.value) {
                    AppLoader()
                }

            }

        }

    }


}