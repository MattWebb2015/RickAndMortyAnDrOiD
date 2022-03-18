package com.example.recyclertest.repositories

import com.example.recyclertest.models.Character
import com.example.recyclertest.network.CharacterApi
import javax.inject.Inject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersRepository @Inject constructor() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val charactersApi by lazy {
        retrofit.create(CharacterApi::class.java)
    }

    suspend fun getCharacters(characterIds: String): Response<List<Character>> {
        return charactersApi.getCharacters(characterIds)
    }

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}
