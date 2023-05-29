package com.example.disneycharacters.ui.main

import androidx.paging.PagingData
import com.example.disneycharacters.model.Character
import kotlinx.coroutines.flow.Flow

sealed interface ListUIState {
    data class Success(val characters: Flow<PagingData<Character>>) : ListUIState
    object Error : ListUIState
    object Loading : ListUIState
}