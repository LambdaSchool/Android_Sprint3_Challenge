package com.example.pokemonsprint

import Model.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiInterface {
    @GET("/")
    fun getPokemon(): Call<Pokemon>
}