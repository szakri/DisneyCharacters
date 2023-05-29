package com.example.disneycharacters.ui.favorites

import androidx.paging.PagingData
import com.example.disneycharacters.model.Character
import kotlinx.coroutines.flow.Flow

sealed interface FavoritesUIState {
    data class Success(val characters: List<Character>) : FavoritesUIState
    object Error : FavoritesUIState
    object Loading : FavoritesUIState
}