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
    suspend fun getCharacters(@Query("page") page:Int, @Query("pageSize") pageSize:Int = 20): ListResult

    @GET("/character")
    suspend fun getCharacterByName(@Query("name") name:String, @Query("page") page:Int): ListResult

    @GET("/character/{id}")
    suspend fun getCharacter(@Path("id") id:Int): CharacterResult

    @POST("/character")
    suspend fun addCharacter(@Body newCharacter: Character): Int

    @PUT("/character/{id}")
    suspend fun updateCharacter(@Path("id") id:Int, @Body character: Character)

    @DELETE("/character/{id}")
    suspend fun deleteCharacter(@Path("id") id:Int)
}