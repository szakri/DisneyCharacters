package com.example.disneycharacters.ui.details

import com.example.disneycharacters.network.DisneyApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
class DetailsRepository @Inject constructor(private val disneyApi: DisneyApi) {
}