package com.example.earthdefensesystem.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PokemonDetails extends AppCompatActivity {

    private ImageView pokemonSprite;
    private TextView pokemonName, pokemonMoves, pokemonTypes;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = this;

        pokemonSprite = findViewById(R.id.pokemonSprite);
        pokemonName = findViewById(R.id.pokemonName);
        pokemonMoves = findViewById(R.id.pokemonMoves);
        pokemonTypes = findViewById(R.id.pokemonType);

        final String searchString = getIntent().getStringExtra(MainActivity.SEARCH_KEY);

        Pokemon pokemon = new Pokemon(PokeapiDao.getPokemon(searchString));
        Bitmap bitmap = PokeapiDao.getPokemonSprite(pokemon.getSpriteUrl());


        pokemonName.setText(pokemon.getName());
        pokemonMoves.setText(pokemon.getMoves().toString());
        pokemonTypes.setText(pokemon.getTypes().toString());
        pokemonSprite.setImageBitmap(bitmap);

    }
}
