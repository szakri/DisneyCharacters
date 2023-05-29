package com.example.disneycharacters.ui.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycharacters.model.Character
import com.example.disneycharacters.ui.main.ListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val favoritesRepository: FavoritesRepository): ViewModel() {
    var uiState: FavoritesUIState by mutableStateOf(FavoritesUIState.Loading)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = try {
                val  characters = favoritesRepository.getCharacters()
                FavoritesUIState.Success(characters)
            } catch (e: IOException) {
                FavoritesUIState.Error
            } catch (e: HttpException) {
                FavoritesUIState.Error
            }
        }
    }

    fun deleteCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.deleteCharacter(character)
        }
    }
}