package com.example.earthdefensesystem.android_sprint3_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;

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
        pokemonMoves = findViewById(R.id.pokemonMovesTv);
        pokemonTypes = findViewById(R.id.pokemonType);

        final String searchString = getIntent().getStringExtra(MainActivity.SEARCH_KEY);
        new AsyncUITask().execute(searchString);

//        Pokemon pokemon = new Pokemon(PokeapiDao.getPokemon(searchString));
//        Bitmap bitmap = PokeapiDao.getPokemonSprite(pokemon.getSpriteUrl());
//
//
//        pokemonName.setText(pokemon.getName());
//        pokemonMoves.setText(pokemon.getMoves().toString());
//        pokemonTypes.setText(pokemon.getTypes().toString());
//        pokemonSprite.setImageBitmap(bitmap);

    }

    private class AsyncUITask extends AsyncTask<String, Integer, DataSet> {

        @Override
        protected DataSet doInBackground(String... strings) {
            Pokemon pokemon = new Pokemon(PokeapiDao.getPokemon(strings[0]));
            Bitmap bitmap = PokeapiDao.getPokemonSprite(pokemon.getSpriteUrl());
            DataSet data = new DataSet();
            data.pokemon = pokemon;
            data.bitmap = bitmap;
            return data;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(DataSet data) {
            super.onPostExecute(data);
            Pokemon pokemon = data.pokemon;
            pokemonName.setText(pokemon.getName());
            pokemonMoves.setText(pokemon.getMoves().toString());
            pokemonTypes.setText(pokemon.getTypes().toString());
            pokemonSprite.setImageBitmap(data.bitmap);
        }
    }

    private class DataSet {
        public Pokemon pokemon;
        public Bitmap bitmap;
    }
}
