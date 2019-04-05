package com.jakeesveld.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 15;
    EditText editSearch;
    Button buttonSubmit;
    Context context;
    static ArrayList<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editSearch = findViewById(R.id.edit_search);
        buttonSubmit = findViewById(R.id.button_search);
        context = this;
        pokemonList = new ArrayList<>();

        final PokemonDAO dao = new PokemonDAO();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchQuery = editSearch.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon searchPokemon = dao.getPokemonById(searchQuery);

                        Intent intent = new Intent(context, ViewerActivity.class);
                        intent.putExtra(Pokemon.POKEMON_INTENT_KEY, searchPokemon);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                }).start();
            }
        });



    }
}
