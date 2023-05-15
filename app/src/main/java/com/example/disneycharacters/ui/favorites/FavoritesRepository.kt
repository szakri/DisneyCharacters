package com.example.disneycharacters.ui.favorites

import com.example.disneycharacters.persistence.DisneyDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
class FavoritesRepository @Inject constructor(private val disneyDao: DisneyDao) {
}