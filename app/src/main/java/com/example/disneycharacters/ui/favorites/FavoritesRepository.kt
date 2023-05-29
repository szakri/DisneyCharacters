package com.example.disneycharacters.ui.favorites

import com.example.disneycharacters.model.Character
import com.example.disneycharacters.persistence.DisneyDao
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val disneyDao: DisneyDao) {
    suspend fun getCharacters(): List<Character> = disneyDao.getAllCharacters()

    suspend fun deleteCharacter(character: Character) = disneyDao.deleteCharacter(character)
}