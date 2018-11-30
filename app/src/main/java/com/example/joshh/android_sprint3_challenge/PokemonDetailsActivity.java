package com.example.joshh.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

public class PokemonDetailsActivity extends AppCompatActivity {
    Context context;
    private ImageView pokemonImage;
    private TextView pokemonName, pokemonMoves, pokemonTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        context = this;

        pokemonImage = findViewById(R.id.pokemon_image);
        pokemonName = findViewById(R.id.pokemon_name);
        pokemonMoves = findViewById(R.id.pokemon_moves);
        pokemonTypes = findViewById(R.id.pokemon_types);

        Intent intent = getIntent();
        Pokemon pokemon = intent.getParcelableExtra("pokemon_details");

        pokemonImage.setImageBitmap(pokemon.getBitmap());
        pokemonName.setText(pokemon.getName() + "(id: " + pokemon.getId() + ")");
        for(String s : pokemon.getMoves()){
            pokemonMoves.setText(pokemonMoves.getText().toString( )+ '\n' + s);
        }
        pokemonMoves.setText(pokemonMoves.getText().toString().replaceAll(", $", ""));
        for(String s : pokemon.getTypes()){
            pokemonTypes.setText(pokemonTypes.getText().toString() + s);
        }
        pokemonTypes.setText(pokemonTypes.getText().toString().replaceAll(", $", ""));
    }
}
