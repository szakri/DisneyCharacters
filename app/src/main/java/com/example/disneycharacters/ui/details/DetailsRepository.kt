package com.example.disneycharacters.ui.details

import com.example.disneycharacters.model.Character
import com.example.disneycharacters.network.CharacterResult
import com.example.disneycharacters.network.DisneyApi
import com.example.disneycharacters.network.ListResult
import com.example.disneycharacters.persistence.DisneyDao
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val disneyApi: DisneyApi, private val disneyDao: DisneyDao) {
    suspend fun getCharacter(id: Int): CharacterResult = disneyApi.getCharacter(id)
    suspend fun addCharacter(character: Character) = disneyDao.insertCharacter(character)
    suspend fun characterExistsInDb(id: Int) = disneyDao.characterExists(id)
    suspend fun removeCharacter(character: Character) = disneyDao.deleteCharacter(character)
}