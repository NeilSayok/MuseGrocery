package com.neilsayok.musewearables.navigation.route

sealed class Routes(val path : String) {
    data object HomeScreen : Routes(path = "home_screen")
}