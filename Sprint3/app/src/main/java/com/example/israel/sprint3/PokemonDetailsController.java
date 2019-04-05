package com.example.israel.sprint3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/** Allows opening pokemon details anywhere*/
public class PokemonDetailsController {

    public static final String KEY_EXTRA_POKEMON = "pokemon";

    public static void openPokemonDetails(final Context context, String pokemonName) {
        DownloadPokemonAsyncTask downloadPokemonAsyncTask = new DownloadPokemonAsyncTask() {
            @Override
            protected void onPostExecute(Pokemon pokemon) {
                super.onPostExecute(pokemon);

                if (pokemon == null) {
                    return;
                }

                // open details activity
                Intent intent = new Intent(context, PokemonDetailsActivity.class);
                intent.putExtra(KEY_EXTRA_POKEMON, pokemon);
                context.startActivity(intent);
            }
        };

        downloadPokemonAsyncTask.execute(pokemonName);
    }

    private static class DownloadPokemonAsyncTask extends AsyncTask<String, Void, Pokemon> {

        @Override
        protected Pokemon doInBackground(String... strings) {
            return PokemonNetworkDAO.getPokemon(strings[0]);
        }

    }

}
