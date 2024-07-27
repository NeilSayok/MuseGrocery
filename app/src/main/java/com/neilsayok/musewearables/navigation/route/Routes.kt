package com.neilsayok.musewearables.navigation.route

sealed class Routes(val path : String) {
    data object HomeScreen : Routes(path = "home_screen")
    data object CategoryScreen : Routes(path = "category_screen")
    data object PLPScreen : Routes(path = "plp_screen")
    data object PDPScreen : Routes(path = "pdp_screen")
}