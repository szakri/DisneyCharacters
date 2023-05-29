package com.example.disneycharacters.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.disneycharacters.ui.details.Details
import com.example.disneycharacters.ui.favorites.Favorites
import com.example.disneycharacters.ui.main.ListCharacters

@Composable
fun DisneyCharactersNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "characters"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("characters") { ListCharacters(navController = navController) }

        composable("characters/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            val id = it.arguments?.getInt("id")
            id?.let {
                Details(navController = navController, id = id)
            }
        }

        composable("favorites") { Favorites(navController = navController) }
    }
}