package com.neilsayok.musewearables.navigation.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.navigation.components.CustomNavHost
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.ui.cart.screen.CartScreen
import com.neilsayok.musewearables.ui.categories.screen.CategoryScreen
import com.neilsayok.musewearables.ui.home.screen.HomeScreen
import com.neilsayok.musewearables.ui.thankyou.screen.ThankYouScreen
import com.neilsayok.musewearables.ui.paymentMethod.screen.AddPaymentMethodScreen
import com.neilsayok.musewearables.ui.pdp.screen.PDPScreen
import com.neilsayok.musewearables.ui.plp.screen.PlpScreen
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainViewModel


@Composable
fun MainRouter(navHostController: NavHostController, modifier: Modifier){

    val mainVm : MainViewModel = hiltViewModel()
    val mainUIState by remember { mainVm.mainUIState }

    mainUIState
    mainVm.onEvent(MainEvent.GetTotalCartCount)

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

        composableScreen(
            Routes.PDPScreen.path, screen = PDPScreen(navController = navHostController,
                uiState = mainUIState,
                onEvent = { event -> mainVm.onEvent(event) })
        )
        composableScreen(
            Routes.CartScreen.path, screen = CartScreen(navController = navHostController,
                uiState = mainUIState,
                onEvent = { event -> mainVm.onEvent(event) })
        )
        composableScreen(
            Routes.AddPaymentMethod.path, screen = AddPaymentMethodScreen(navController = navHostController,
                uiState = mainUIState,
                onEvent = { event -> mainVm.onEvent(event) })
        )
        composableScreen(
            Routes.ThankYouScreen.path, screen = ThankYouScreen(navHostController = navHostController,
                uiState = mainUIState,
                onEvent = { event -> mainVm.onEvent(event) })
        )

    }


}