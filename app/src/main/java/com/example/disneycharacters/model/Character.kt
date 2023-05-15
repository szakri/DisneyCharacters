package com.example.disneycharacters.model

data class Character(
    val _id: Int,
    val url: String,
    val name: String,
    val sourceUrl: String?,
    val films: ArrayList<String>?,
    val shortFilms: ArrayList<String>?,
    val tvShows: ArrayList<String>?,
    val videoGames: ArrayList<String>?,
    val parkAttractions: ArrayList<String>?
)