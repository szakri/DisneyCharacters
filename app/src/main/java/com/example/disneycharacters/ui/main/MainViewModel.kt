package com.example.disneycharacters.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.disneycharacters.network.PagedCharacters
import com.example.disneycharacters.network.PagedSearchedCharacters
import com.example.disneycharacters.ui.favorites.FavoritesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {
    var uiState: ListUIState by mutableStateOf(ListUIState.Loading)
        private set

    init {
        viewModelScope.launch {
            uiState = try {
                val characters = Pager(PagingConfig(pageSize = 10))
                {
                    PagedCharacters(mainRepository)
                }.flow.cachedIn(viewModelScope)
                ListUIState.Success(characters)
            } catch (e: IOException) {
                ListUIState.Error
            } catch (e: HttpException) {
                ListUIState.Error
            }
        }
    }

    fun getCharacter(name:String) {
        viewModelScope.launch {
            uiState = try {
                val characters = Pager(PagingConfig(pageSize = 6))
                {
                    PagedSearchedCharacters(mainRepository, name)
                }.flow.cachedIn(viewModelScope)
                ListUIState.Success(characters)
            } catch (e: IOException) {
                ListUIState.Error
            } catch (e: HttpException) {
                ListUIState.Error
            }
        }
    }
}