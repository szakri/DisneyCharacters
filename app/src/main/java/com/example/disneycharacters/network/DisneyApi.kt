package com.example.disneycharacters.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DisneyApi {
    @GET("/character")
    suspend fun getCharacters(): NetworkResult

    @GET("character")
    fun getCharacters(@Query("q") q:String): NetworkResult

    @GET("character/{id}")
    fun getCharacter(@Path("id") id:Int): NetworkResult

    @POST("character")
    fun addCharacter(@Body newCharacter: Character): Int

    @PUT("character/{id}")
    fun updateCharacter(@Path("id") id:Int, @Body character: Character)

    @DELETE("character/{id}")
    fun deleteCharacter(@Path("id") id:Int)
}