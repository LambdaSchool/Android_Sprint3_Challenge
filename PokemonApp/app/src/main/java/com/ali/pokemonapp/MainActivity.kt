package com.ali.pokemonapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search_pokemon.setOnClickListener {
            val intent = Intent(this, GetPokemonActivity::class.java)
            val pokemonId:Int=et_pokemon_no.text.toString().toInt()
            intent.putExtra("POKEMON_ID",pokemonId)
            startActivity(intent)
        }
    }
}
