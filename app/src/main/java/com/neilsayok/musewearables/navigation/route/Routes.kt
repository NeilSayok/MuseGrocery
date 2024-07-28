package com.neilsayok.musewearables.navigation.route

sealed class Routes(val path : String) {
    data object HomeScreen : Routes(path = "home_screen")
    data object CategoryScreen : Routes(path = "category_screen")
    data object PLPScreen : Routes(path = "plp_screen")
    data object PDPScreen : Routes(path = "pdp_screen")
    data object CartScreen : Routes(path = "cart_screen")
    data object AddPaymentMethod : Routes(path = "add_payment_method_screen")
    data object ThankYouScreen : Routes(path = "thanks_screen")
}