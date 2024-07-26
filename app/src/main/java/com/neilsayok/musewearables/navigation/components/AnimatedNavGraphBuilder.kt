package com.neilsayok.musewearables.navigation.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog

import com.neilsayok.musewearables.base.Screen

class AnimatedNavGraphBuilder(private val builder: NavGraphBuilder) {
    fun composableScreen(
        route: String,
        arguments: List<NamedNavArgument> = emptyList(),
        deepLinks: List<NavDeepLink> = emptyList(),
        screen: Screen

    ) = builder.composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
//        enterTransition = {
//            slideIntoContainer(
//                towards = AnimatedContentTransitionScope.SlideDirection.Left,
//                animationSpec = tween(500)
//            )
//        },
//        exitTransition = {
//            slideOutOfContainer(
//                towards = AnimatedContentTransitionScope.SlideDirection.Left,
//                animationSpec = tween(500)
//            )
//        },
//        popEnterTransition = {
//            slideIntoContainer(
//                towards = AnimatedContentTransitionScope.SlideDirection.Right,
//                animationSpec = tween(500)
//            )
//        },
//        popExitTransition = {
//            slideOutOfContainer(
//                towards = AnimatedContentTransitionScope.SlideDirection.Right,
//                animationSpec = tween(500)
//            )
//        },
        content = { screen.BaseScreen() }
    )

}