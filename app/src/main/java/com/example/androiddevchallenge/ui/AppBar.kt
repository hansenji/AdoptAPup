/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ux.Screen

const val KEY_APP_BAR_TITLE = "appBarTitle"

@Composable
fun AppBar(navController: NavHostController) {
    val currentEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentEntry?.arguments?.getString(KEY_ROUTE)
    val currentScreen = Screen.findScreen(currentRoute)
    val name = currentEntry?.arguments?.getString(KEY_APP_BAR_TITLE) ?: stringResource(id = R.string.app_name)
    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = navIcon(currentScreen, navController)
    )
}
@Composable
private fun navIcon(currentScreen: Screen, navController: NavHostController): (@Composable () -> Unit)? {
    return if (currentScreen.showBack) {
        {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, stringResource(R.string.back_content_description))
            }
        }
    } else {
        null
    }
}
