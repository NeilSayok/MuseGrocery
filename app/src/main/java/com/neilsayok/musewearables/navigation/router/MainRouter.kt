package com.neilsayok.musewearables.navigation.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.navigation.components.CustomNavHost
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.ui.categories.screen.CategoryScreen
import com.neilsayok.musewearables.ui.home.screen.HomeScreen
import com.neilsayok.musewearables.ui.plp.screen.PlpScreen
import com.neilsayok.musewearables.viewmodel.MainViewModel


@Composable
fun MainRouter(navHostController: NavHostController, modifier: Modifier){

    val mainVm : MainViewModel = hiltViewModel()
    val mainUIState by remember { mainVm.mainUIState }


    CustomNavHost(
        navController = navHostController,
        startDestination = Routes.HomeScreen.path,
        modifier = modifier
    ) {
        composableScreen(Routes.HomeScreen.path, screen = HomeScreen(
            navHostController = navHostController,
            uiState = mainUIState,
            onEvent = { event -> mainVm.onEvent(event) }
        ) )
        composableScreen(Routes.CategoryScreen.path, screen = CategoryScreen(
            navController = navHostController,
            uiState = mainUIState,
            onEvent = { event -> mainVm.onEvent(event) }) )
        composableScreen(Routes.PLPScreen.path, screen = PlpScreen(
            navController = navHostController,
            uiState = mainUIState,
            onEvent = { event -> mainVm.onEvent(event) }) )

    }


}