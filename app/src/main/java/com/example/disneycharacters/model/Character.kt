package com.example.disneycharacters.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val _id: Int,
    val sourceUrl: String,
    val name: String,
    val imageUrl: String?,
    val films: ArrayList<String>?,
    val shortFilms: ArrayList<String>?,
    val tvShows: ArrayList<String>?,
    val videoGames: ArrayList<String>?,
    val parkAttractions: ArrayList<String>?
)