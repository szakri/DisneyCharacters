package com.example.disneycharacters.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.disneycharacters.model.Character
import com.example.disneycharacters.ui.navigation.DisneyCharactersNavHost
import com.example.disneycharacters.ui.theme.DisneyCharactersTheme
import dagger.hilt.android.AndroidEntryPoint
import coil.compose.AsyncImage
import com.google.firebase.FirebaseApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            DisneyCharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisneyCharactersNavHost()
                }
            }
        }
    }
}

@Composable
fun ListCharacters(modifier: Modifier = Modifier, navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    when (mainViewModel.uiState) {
        is ListUIState.Loading -> LoadingScreen(modifier)
        is ListUIState.Success ->
            ListResultScreen((mainViewModel.uiState as ListUIState.Success).characters.collectAsLazyPagingItems(), navController, mainViewModel, modifier)
        is ListUIState.Error -> ErrorScreen(modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListResultScreen(characters: LazyPagingItems<Character>, navController: NavController, mainViewModel: MainViewModel, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }

    Column {
        Text(text = "Disney characters", fontSize = 30.sp, textAlign = TextAlign.Center, modifier = modifier.fillMaxWidth())
        Column {
            TextField(
                value = name, onValueChange = { name = it }, Modifier.fillMaxWidth().padding(5.dp)
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                Button(onClick = { mainViewModel.getCharacter(name) }) {
                    Text(text = "Search")
                }
                Button(onClick = { navController.navigate("favorites") }) {
                    Text(text = "Favorites")
                }
            }
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            items(characters.itemCount) { characterIndex ->
                characters.getAsState(index = characterIndex).value?.let {
                    Row(modifier.clickable {
                        navController.navigate("characters/${it._id}")
                    }) {
                        AsyncImage(
                            model = it.imageUrl,
                            contentDescription = it.name,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .size(100.dp)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = it.name,
                            modifier = modifier,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
        characters.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingScreen(modifier)
                }
                loadState.append is LoadState.Error -> {
                    ErrorScreen(modifier)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.size(200.dp),
            text = "Loading..."
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Error...")
    }
}