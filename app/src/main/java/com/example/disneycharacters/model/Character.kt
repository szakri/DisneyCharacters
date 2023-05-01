package com.example.disneycharacters.model

data class Character(
    val _id: Int,
    val url: String,
    val name: String,
    val sourceUrl: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val parkAttractions: List<String>
)