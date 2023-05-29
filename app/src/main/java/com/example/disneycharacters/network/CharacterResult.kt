package com.example.disneycharacters.network

import com.example.disneycharacters.model.Character

data class CharacterResult(
    val info: Info,
    val data: Character
)
