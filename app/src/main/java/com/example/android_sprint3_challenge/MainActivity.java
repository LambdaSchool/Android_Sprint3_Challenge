package com.example.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    public static final String POKEMON_DETAILS = "pokemonDetails";
    Context context;
    private LinearLayout savedListLayout;
    private Button searchButton;
    private EditText pokeSearch;
    private ArrayList<View> savedList;
    int nextID;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("pokemon",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        savedList = new ArrayList<>();
        nextID = 1;
        context = this;
        savedListLayout = findViewById(R.id.poke_saved_list);
        searchButton = findViewById(R.id.search_button);
        pokeSearch = findViewById(R.id.edit_text);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    nextID++;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon pokemon = PokemonDAO.getPokemon(Integer.parseInt(pokeSearch.getText().toString()));
                        Intent pokeDetailsIntent =  new Intent(context, ViewPokemonDetails.class);

                        pokeSearch.setText("");
                        pokeDetailsIntent.putExtra(POKEMON_DETAILS, pokemon);
                        startActivity(pokeDetailsIntent);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final TextView textView = new TextView(context);
                                textView.setId(nextID);
                                textView.setText(pokemon.getName());
                                final String idKey = String.valueOf(nextID);
                                textView.setTextSize(40);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent pokeDetailsIntent =  new Intent(context, ViewPokemonDetails.class);
                                        pokeDetailsIntent.putExtra(POKEMON_DETAILS, pokemon);
                                        startActivity(pokeDetailsIntent);
                                        savedList.remove(textView);
                                        editor.remove(idKey);
                                        editor.apply();
                                    }
                                });
                                savedList.add(textView);
                                editor.putString(idKey,pokemon.name );
                                editor.apply();
                            }
                        });
                    }
                }).start();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(savedListLayout != null){
            savedListLayout.removeAllViews();
        }
        for(int i=0;i < savedList.size();++i){
            savedListLayout.addView(savedList.get(i));
        }
    }
}
