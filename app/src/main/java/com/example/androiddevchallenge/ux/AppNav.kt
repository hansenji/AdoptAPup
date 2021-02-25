package com.example.androiddevchallenge.ux

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.androiddevchallenge.ui.KEY_APP_BAR_TITLE
import com.example.androiddevchallenge.ux.detail.DetailScreen
import com.example.androiddevchallenge.ux.home.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeScreen.route) {
        composable(HomeScreen.route) { HomeScreen.Content(navController) }
        composable(
            DetailScreen.route,
            arguments = listOf(
                navArgument(DetailScreen.Args.ID) { type = NavType.StringType },
                navArgument(KEY_APP_BAR_TITLE) { type = NavType.StringType },
            )
        ) { DetailScreen.Content(requireNotNull(it.arguments?.getString(DetailScreen.Args.ID))) }
    }
}