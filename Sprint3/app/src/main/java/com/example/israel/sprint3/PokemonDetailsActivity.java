package com.example.israel.sprint3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PokemonDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        Intent intent = getIntent();
        Pokemon pokemon = (Pokemon)intent.getSerializableExtra(MainActivity.KEY_EXTRA_POKEMON);

        TextView pokemonIdTextView = findViewById(R.id.text_view_pokemon_detail_id);
        pokemonIdTextView.setText("Id: " + Integer.toString(pokemon.getId()));
        TextView pokemonNameTextView = findViewById(R.id.text_view_pokemon_detail_name);
        pokemonNameTextView.setText("Name: " + pokemon.getName());
        TextView pokemonTypesTextView = findViewById(R.id.text_view_pokemon_detail_types);
        TextView pokemonAbilitiesTextView = findViewById(R.id.text_view_pokemon_detail_abilities);

        DownloadPokemonSpriteAsyncTask downloadPokemonSpriteAsyncTask = new DownloadPokemonSpriteAsyncTask();
        downloadPokemonSpriteAsyncTask.execute(pokemon.getSpriteUrl());
    }

    private class DownloadPokemonSpriteAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            // TODO
            return NetworkAdapter.httpImageRequestGET(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            ImageView pokemonSpriteImageView = findViewById(R.id.image_view_pokemon_sprite);
            pokemonSpriteImageView.setImageBitmap(bitmap);
        }
    }
}
