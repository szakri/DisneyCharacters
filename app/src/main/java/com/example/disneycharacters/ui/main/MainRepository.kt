package com.example.disneycharacters.ui.main

import com.example.disneycharacters.network.DisneyApi
import com.example.disneycharacters.network.ListResult
import com.example.disneycharacters.persistence.DisneyDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val disneyApi: DisneyApi) {
    suspend fun getAllCharacters(page:Int): ListResult = disneyApi.getCharacters(page)
    suspend fun getCharacterByName(name:String, page:Int): ListResult = disneyApi.getCharacterByName(name, page)
}