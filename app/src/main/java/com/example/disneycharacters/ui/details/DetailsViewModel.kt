package com.example.disneycharacters.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycharacters.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository): ViewModel() {
    var uiState: DetailsUIState by mutableStateOf(DetailsUIState.Loading)
    private set

    var existsInDb = false;

    fun getCharacter(id:Int) {
        viewModelScope.launch {
            uiState = try {
                val character = detailsRepository.getCharacter(id).data
                existsInDb = detailsRepository.characterExistsInDb(id)
                DetailsUIState.Success(character)
            } catch (e: IOException) {
                DetailsUIState.Error
            } catch (e: HttpException) {
                DetailsUIState.Error
            }
        }
    }

    fun addToFavorites(character: Character) {
        viewModelScope.launch {
            detailsRepository.addCharacter(character)
            existsInDb = detailsRepository.characterExistsInDb(character._id)
            uiState = DetailsUIState.Success(character)
        }
    }

    fun removeCharacter(character: Character) {
        viewModelScope.launch {
            detailsRepository.removeCharacter(character)
            existsInDb = detailsRepository.characterExistsInDb(character._id)
            uiState = DetailsUIState.Success(character)
        }
    }
}