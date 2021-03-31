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
package com.example.androiddevchallenge.ux.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pup
import com.example.androiddevchallenge.model.PupRepository
import com.example.androiddevchallenge.ui.KEY_APP_BAR_TITLE
import com.example.androiddevchallenge.ux.Screen
import com.example.androiddevchallenge.ux.detail.DetailScreen.Args.ID
import com.google.accompanist.coil.CoilImage

object DetailScreen : Screen {
    override val route = "detail/{$ID}?$KEY_APP_BAR_TITLE={$KEY_APP_BAR_TITLE}"
    override val showBack = true

    @Composable
    fun Content(pupId: String) {
        PupDetail(requireNotNull(PupRepository.getPup(pupId)))
    }

    fun getRoute(pup: Pup): String {
        return "detail/${pup.id}?$KEY_APP_BAR_TITLE=${pup.name}"
    }

    object Args {
        const val ID = "id"
    }
}

@Composable
private fun PupDetail(pup: Pup) {
    Surface(
        Modifier
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            CoilImage(
                data = pup.photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(500.dp),
                fadeIn = true,
                contentScale = ContentScale.Crop,
                error = {
                    Icon(
                        painterResource(id = R.drawable.ic_dog),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = MaterialTheme.colors.secondary.copy(alpha = 0.5F)
                    )
                },
                loading = {
                    Icon(
                        painterResource(id = R.drawable.ic_dog),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        tint = MaterialTheme.colors.secondary.copy(alpha = 0.5F)
                    )
                }

            )
            Text(pup.description, Modifier.padding(16.dp))
        }
    }
}
