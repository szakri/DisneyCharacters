package com.example.disneycharacters.network

import com.example.disneycharacters.model.Character

data class ListResult(
    val info: Info,
    val data: List<Character>
)

data class Info(
    val totalPages: Int?,
    val count: Int?,
    val previousPage: String?,
    val nextPage: String?
)
