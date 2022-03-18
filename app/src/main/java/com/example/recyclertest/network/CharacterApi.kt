package com.example.recyclertest.network

import com.example.recyclertest.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("character/{id}")
    suspend fun getCharacters(@Path("id") id: String): Response<List<Character>>
}
