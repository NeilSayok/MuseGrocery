package com.neilsayok.musewearables.ui.home.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.base.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreen(val navHostController: NavHostController) : Screen() {

    private val loadingState = MutableStateFlow(false)


    override fun setNavHost(): NavHostController = navHostController

    override fun setLoadingState(): StateFlow<Boolean> = loadingState

    @Composable
    override fun Content() {
            Text(text = "Hi")
    }

    @Composable
    override fun Toolbar() {
        Text(text = "Hi")
    }

    @Composable
    override fun StickyBottomSheet() {
        Text(text = "Hi")
    }
}