package com.ali.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.ali.pokemonapp.model.Pokemon
import com.ali.pokemonapp.retrofit.PokemonApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_get_pokemon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPokemonActivity : AppCompatActivity(), Callback<Pokemon> {
    lateinit var pokemonService:PokemonApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_pokemon)
        pokemonService= PokemonApi.Factory.create()
        val pokemonID=intent.getIntExtra("POKEMON_ID",1)
        getPokemonbyId(pokemonID)
    }
    private fun getPokemonbyId(pokemonId: Int){
        pokemonService.getPokemonById(pokemonId).enqueue(this)
    }
    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        if(response.isSuccessful) {
            progressBar.isVisible=false
            val pokemonDetails= response.body()
           pokemon_Name.text=pokemonDetails?.name
            pokemon_id.text=pokemonDetails?.id.toString()
          // pokemon_abilities.text=pokemonDetails?.abilities.toString()
           // pokemon_types.text=pokemonDetails?.types.toString()
          //  Picasso.get().load(pokemonDetails?.sprites[0].toUri()).into(pokemon_Sprite)

        }


    }

    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
    }
}
