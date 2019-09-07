package com.ali.pokemonapp.retrofit

import com.ali.pokemonapp.model.Pokemon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface PokemonApi {
    @GET("pokemon/{name-id}")
    fun getPokemonByIdorName(@Path("name-id")nameOrID:String): Call<Pokemon>

    class Factory {

        companion object {

            private const val BASE_URL = "http://pokeapi.co/api/v2/"

            fun create(): PokemonApi {

                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC
                logger.level = HttpLoggingInterceptor.Level.BODY

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return retrofit.create(PokemonApi::class.java)
            }
        }
    }

}

