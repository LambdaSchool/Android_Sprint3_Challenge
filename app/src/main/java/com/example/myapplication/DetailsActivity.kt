package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.model.SerializedPokemon
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {



    /*companion object {

        val pokemonList = mutableListOf<SerializedPokemon>()

    }*/



//    lateinit var pokemon: SerializedPokemon



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)



        val data = intent.getSerializableExtra("serializedPokemon") as? SerializedPokemon

//        pokemon = data



        val abilities = data?.ability.toString().replace("[", "").replace("]", "")

        val types = data?.type.toString().replace("[", "").replace("]", "")



   //     Picasso.get().load(data?.sprites).into(pokemon_image)

        text_view_pokemon_name.text = "Name: ${data?.name}"

        text_view_pokemon_number.text = "Pokemon No. ${data?.id}"

        text_view_pokemon_type.text = "Type: $types"

        text_view_pokemon_abilities.text = "Abilities: $abilities"



    }





}
