package com.example.pokeapi_mvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val pokemonUrl: String = "https://pokeapi.co/api/v2/"
    private var retrofit: Retrofit? = null
    fun getData(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(pokemonUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!
    }
}