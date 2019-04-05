package com.example.android_sprint3_challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        String pokeNumber = intent.getStringExtra(MainActivity.POKEMON_NUMBER_EXTRA);
        PokemonNetworkDao.getSinglePokemon(pokeNumber);
    }
}
