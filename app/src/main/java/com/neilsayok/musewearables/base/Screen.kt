package com.neilsayok.musewearables.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.ui.common.AppLoader
import kotlinx.coroutines.flow.StateFlow

abstract class Screen {

    abstract fun setNavHost(): NavHostController

    abstract fun setLoadingState(): StateFlow<Boolean>?

    @Composable
    abstract fun Content()

    @Composable
    abstract fun Toolbar()



    @Composable
    fun BaseScreen() {
        val loading = setLoadingState()?.collectAsState()

        Column() {
            Toolbar()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    //.padding(innerPadding)
            ) {
                Content()
                if (loading?.value == true) {
                    AppLoader()
                }

            }
        }

//        Scaffold(modifier = Modifier.fillMaxSize(),
//            topBar = {
//            Toolbar()
//        }
//        ) {innerPadding->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//            ) {
//                Content()
//                if (loading?.value == true) {
//                    AppLoader()
//                }
//
//            }
//
//        }

    }


}