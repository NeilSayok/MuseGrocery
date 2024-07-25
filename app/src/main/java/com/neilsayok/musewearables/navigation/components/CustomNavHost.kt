package com.neilsayok.musewearables.navigation.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun CustomNavHost(navController: NavHostController,
                  startDestination: String,
                  modifier: Modifier = Modifier,
                  contentAlignment: Alignment = Alignment.Center,
                  route: String? = null,
                  enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
                      { fadeIn(animationSpec = tween(700)) },
                  exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
                      { fadeOut(animationSpec = tween(700)) },
                  popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
                      enterTransition,
                  popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
                      exitTransition,
                  builder: AnimatedNavGraphBuilder.() -> Unit
){


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        contentAlignment = contentAlignment,
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,

        ){
        AnimatedNavGraphBuilder(this).builder()
    }

}