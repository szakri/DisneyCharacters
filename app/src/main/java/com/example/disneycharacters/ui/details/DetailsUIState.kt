package com.example.disneycharacters.ui.details

import com.example.disneycharacters.model.Character

sealed interface DetailsUIState {
    data class Success(val character: Character) : DetailsUIState
    object Error : DetailsUIState
    object Loading : DetailsUIState
}