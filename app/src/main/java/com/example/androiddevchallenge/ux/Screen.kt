package com.example.androiddevchallenge.ux

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ux.detail.DetailScreen
import com.example.androiddevchallenge.ux.home.HomeScreen

interface Screen {

    val route: String
    val showBack: Boolean

    companion object {
        fun findScreen(route: String?): Screen {
            return when (route) {
                DetailScreen.route -> DetailScreen
                HomeScreen.route,
                null -> HomeScreen
                else -> throw IllegalArgumentException("Invalid Route $route")
            }
        }
    }
}