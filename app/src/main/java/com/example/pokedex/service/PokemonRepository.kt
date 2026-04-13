package com.example.pokedex.service

import com.example.pokedex.model.PokemonDetailResponse
import com.example.pokedex.model.PokemonListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}

class PokemonRepository {
    private val api = RetrofitClient.api

    suspend fun getPokemonList(limit: Int = 151): PokemonListResponse {
        return api.getPokemonList(limit = limit)
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return api.getPokemonDetail(name = name)
    }
}