package com.example.disneycharacters.ui.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.example.disneycharacters.model.Character
import com.example.disneycharacters.ui.main.ErrorScreen
import com.example.disneycharacters.ui.main.LoadingScreen
import com.example.disneycharacters.ui.main.ListResultScreen

@Composable
fun Details(id: Int, navController: NavController, modifier: Modifier = Modifier, detailsViewModel: DetailsViewModel = hiltViewModel()) {
    detailsViewModel.getCharacter(id)
    when (detailsViewModel.uiState) {
        is DetailsUIState.Loading -> LoadingScreen(modifier)
        is DetailsUIState.Success -> {
            detailsViewModel.getCharacter(id)
            DetailsResultScreen(
                (detailsViewModel.uiState as DetailsUIState.Success).character,
                detailsViewModel,
                navController,
                modifier
            )
        }
        is DetailsUIState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun DetailsResultScreen(character: Character, detailsViewModel: DetailsViewModel, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())) {
        Text(
            text = character.name,
            fontSize = 30.sp,
            modifier = modifier,
            overflow = TextOverflow.Ellipsis
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            Button(onClick = {
                var queue = navController.backQueue
                var i = queue.size - 1
                while (i >= 0)
                {
                    if (queue[i].destination.route != navController.currentBackStackEntry?.destination?.route) {
                        queue[i].destination.route?.let { navController.navigate(it) }
                        break
                    }
                    i--
                }
            }) {
                Text(text = "Back")
            }
            if (!detailsViewModel.existsInDb) {
                Button(onClick = {
                    detailsViewModel.addToFavorites(character)
                    navController.navigate("characters/${character._id}")
                }) {
                    Text(text = "Add")
                }
            }
            else {
                Button(onClick = {
                    detailsViewModel.removeCharacter(character)
                    navController.navigate("characters/${character._id}")
                }) {
                    Text(text = "Remove")
                }
            }
        }
        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.size(400.dp)
        )
        Text("Films", fontSize = 20.sp)
        Column(modifier = modifier.padding(50.dp, 0.dp, 0.dp, 0.dp)) {
            character.films?.forEach { film ->
                Text(
                    text = film,
                    modifier = modifier
                )
            }
        }
        Text("Short films", fontSize = 20.sp)
        Column(modifier = modifier.padding(50.dp, 0.dp, 0.dp, 0.dp)) {
            character.shortFilms?.forEach { shortFilm ->
                Text(
                    text = shortFilm,
                    modifier = modifier
                )
            }
        }
        Text("TV shows", fontSize = 20.sp)
        Column(modifier = modifier.padding(50.dp, 0.dp, 0.dp, 0.dp)) {
            character.tvShows?.forEach { tvShow ->
                Text(
                    text = tvShow,
                    modifier = modifier
                )
            }
        }
        Text("Video games", fontSize = 20.sp)
        Column(modifier = modifier.padding(50.dp, 0.dp, 0.dp, 0.dp)) {
            character.videoGames?.forEach { videoGame ->
                Text(
                    text = videoGame,
                    modifier = modifier
                )
            }
        }
        Text("Park attractions", fontSize = 20.sp)
        Column(modifier = modifier.padding(50.dp, 0.dp, 0.dp, 0.dp)) {
            character.parkAttractions?.forEach { parkAttraction ->
                Text(
                    text = parkAttraction,
                    modifier = modifier
                )
            }
        }
    }
}