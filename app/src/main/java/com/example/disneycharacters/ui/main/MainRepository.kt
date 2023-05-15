package com.example.disneycharacters.ui.main

import com.example.disneycharacters.network.DisneyApi
import com.example.disneycharacters.persistence.DisneyDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainRepository @Inject constructor(private val disneyApi: DisneyApi, private val disneyDao: DisneyDao) {
    suspend fun call() {
        val a = disneyApi.getCharacters()
    }
}