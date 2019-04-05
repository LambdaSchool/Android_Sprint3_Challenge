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

        // id
        TextView pokemonIdTextView = findViewById(R.id.text_view_pokemon_detail_id);
        pokemonIdTextView.setText("Id: " + Integer.toString(pokemon.getId()));

        // name
        TextView pokemonNameTextView = findViewById(R.id.text_view_pokemon_detail_name);
        pokemonNameTextView.setText("Name: " + pokemon.getName());

        // types
        TextView pokemonTypesTextView = findViewById(R.id.text_view_pokemon_detail_types);
        StringBuilder typesStrB = new StringBuilder();
        typesStrB.append("Types: ");
        String delimiter = ", ";
        for (String type : pokemon.getTypes()) {
            typesStrB.append(type);
            typesStrB.append(delimiter);
        }
        typesStrB.setLength(typesStrB.length() - delimiter.length());
        pokemonTypesTextView.setText(typesStrB.toString());

        // abilities
        TextView pokemonAbilitiesTextView = findViewById(R.id.text_view_pokemon_detail_abilities);
        StringBuilder abilitiesStrB = new StringBuilder();
        abilitiesStrB.append("Abilities: ");
        for (String ability : pokemon.getAbilities()) {
            abilitiesStrB.append(ability);
            abilitiesStrB.append(delimiter);
        }
        abilitiesStrB.setLength(abilitiesStrB.length() - delimiter.length());
        pokemonAbilitiesTextView.setText(abilitiesStrB.toString());

        // sprite
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
