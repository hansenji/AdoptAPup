package com.example.androiddevchallenge.ux.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pup
import com.example.androiddevchallenge.model.PupRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ux.Screen
import com.example.androiddevchallenge.ux.detail.DetailScreen
import dev.chrisbanes.accompanist.coil.CoilImage

object HomeScreen : Screen {
    override val route = "home"
    override val showBack = false

    @Composable
    fun Content(navController: NavHostController) {
        Surface(Modifier.background(MaterialTheme.colors.background)) {
            LazyColumn {
                items(PupRepository.getPupList()) {
                    PupCard(it) {
                        navController.navigate(DetailScreen.getRoute(it))
                    }
                }
            }
        }
    }
}

@Composable
private fun PupCard(pup: Pup, onClick: (Pup) -> Unit) {
    Card(
        Modifier
            .padding(8.dp)
            .clickable { onClick(pup) }) {
        CoilImage(
            data = pup.photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.33f),
            error = {
                Icon(
                    painterResource(id = R.drawable.ic_dog),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary.copy(alpha = 0.5F)
                )
            },
            loading = {
                Icon(
                    painterResource(id = R.drawable.ic_dog),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary.copy(alpha = 0.5F)
                )
            }
        )
        Text(
            pup.name,
            Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.surface.copy(alpha = .2F))
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewPupCard() {
    MyTheme {
        PupCard(
            Pup(
                "1",
                "Jeffery D. Campbell",
                "",
                "https://placedog.net/500"
            )
        ) {

        }
    }
}