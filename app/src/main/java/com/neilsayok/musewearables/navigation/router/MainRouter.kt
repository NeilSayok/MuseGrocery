package com.neilsayok.musewearables.navigation.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.navigation.components.CustomNavHost
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.ui.categories.screen.CategoryScreen
import com.neilsayok.musewearables.ui.home.screen.HomeScreen
import com.neilsayok.musewearables.ui.plp.screen.PlpScreen


@Composable
fun MainRouter(navHostController: NavHostController, modifier: Modifier){

    CustomNavHost(
        navController = navHostController,
        startDestination = Routes.HomeScreen.path,
        modifier = modifier
    ) {
        composableScreen(Routes.HomeScreen.path, screen = HomeScreen(navHostController) )
        composableScreen(Routes.CategoryScreen.path, screen = CategoryScreen(navHostController) )
        composableScreen(Routes.PLPScreen.path, screen = PlpScreen(navHostController) )

    }


}