package com.example.disneycharacters.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.disneycharacters.model.Character
import com.example.disneycharacters.ui.main.ErrorScreen
import com.example.disneycharacters.ui.main.ListResultScreen
import com.example.disneycharacters.ui.main.ListUIState
import com.example.disneycharacters.ui.main.LoadingScreen
import com.example.disneycharacters.ui.main.MainViewModel

@Composable
fun Favorites(modifier: Modifier = Modifier, navController: NavController, favoritesViewModel: FavoritesViewModel = hiltViewModel()) {
    when (favoritesViewModel.uiState) {
        is FavoritesUIState.Loading -> LoadingScreen(modifier)
        is FavoritesUIState.Success ->
            FavoritesScreen((favoritesViewModel.uiState as FavoritesUIState.Success).characters, navController, favoritesViewModel, modifier)
        is FavoritesUIState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun FavoritesScreen(characters: List<Character>, navController: NavController, favoritesViewModel: FavoritesViewModel, modifier: Modifier = Modifier) {
    Column {
        Text(text = "Favorites", fontSize = 30.sp, textAlign = TextAlign.Center, modifier = modifier.fillMaxWidth())
        Button(onClick = { navController.navigate("characters") }) {
            Text(text = "Back")
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            items(characters.size) { characterIndex ->
                Row(modifier.clickable {
                    navController.navigate("characters/${characters[characterIndex]._id}")
                }) {
                    AsyncImage(
                        model = characters[characterIndex].imageUrl,
                        contentDescription = characters[characterIndex].name,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .size(100.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = characters[characterIndex].name,
                        modifier = modifier,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}