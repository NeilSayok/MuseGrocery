package com.neilsayok.musewearables.navigation.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.navigation.components.CustomNavHost
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.ui.home.screen.HomeScreen


@Composable
fun MainRouter(navHostController: NavHostController, modifier: Modifier){

    CustomNavHost(
        navController = navHostController,
        startDestination = Routes.HomeScreen.path,
        modifier = modifier
    ) {
        composableScreen(Routes.HomeScreen.path, screen = HomeScreen(navHostController) )

    }


}