package com.example.israel.sprint3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_POKEMON = "pokemon";

    EditText pokemonSearchEditText;
    Button pokemonSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_pokemon_search_results);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        final PokemonSearchResultsAdapter pokemonSearchResultsAdapter = new PokemonSearchResultsAdapter(this);
        recyclerView.setAdapter(pokemonSearchResultsAdapter);

        // edit text search
        pokemonSearchEditText = findViewById(R.id.edit_text_pokemon_search);
        pokemonSearchEditText.setHint("Downloading pokemon names...");
        pokemonSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> searchedPokemonNames = PokemonNamesRepository.searchPokemonByName(s.toString());
                pokemonSearchResultsAdapter.setPokemonNames(searchedPokemonNames);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // button search
        pokemonSearchButton = findViewById(R.id.button_pokemon_search);
        pokemonSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        enableSearch(false); // disable search until pokemon names are downloaded
        DownloadPokemonNamesAsyncTask downloadPokemonNamesAsyncTask = new DownloadPokemonNamesAsyncTask();
        downloadPokemonNamesAsyncTask.execute();

    }

    private void enableSearch(boolean enable) {
        pokemonSearchEditText.setEnabled(enable);
        pokemonSearchButton.setEnabled(enable);
    }

    public void openPokemonDetails(String pokemonName) {
        enableSearch(false);

        @SuppressLint("StaticFieldLeak")
        DownloadPokemonAsyncTask downloadPokemonAsyncTask = new DownloadPokemonAsyncTask() {
            @Override
            protected void onPostExecute(Pokemon pokemon) {
                super.onPostExecute(pokemon);

                enableSearch(true);

                if (pokemon == null) {
                    return;
                }

                // open details activity
                Intent intent = new Intent(MainActivity.this, PokemonDetailsActivity.class);
                intent.putExtra(KEY_EXTRA_POKEMON, pokemon);
                startActivity(intent);
            }
        };

        downloadPokemonAsyncTask.execute(pokemonName);
    }

    private class DownloadPokemonNamesAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            PokemonNamesRepository.downloadPokemonNames();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pokemonSearchEditText.setHint("Pokemon name or id");
            enableSearch(true);

        }
    }

    private class DownloadPokemonAsyncTask extends AsyncTask<String, Void, Pokemon> {

        @Override
        protected Pokemon doInBackground(String... strings) {
            return PokemonNetworkDAO.getPokemon(strings[0]);
        }

    }




}
