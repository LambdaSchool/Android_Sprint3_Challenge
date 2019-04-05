package com.example.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    public static final String POKEMONTAG = "poke_deets";
    Context context;
    private LinearLayout savedListLayout;
    private Button searchButton;
    private EditText pokeSearch;
    private ArrayList<View> savedList;
    int nextID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        pokeDetailsIntent.putExtra(POKEMONTAG, pokemon);
                        startActivity(pokeDetailsIntent);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final TextView tv = new TextView(context);
                                tv.setId(nextID);
                                tv.setText(pokemon.getName());
                                tv.setTextSize(40);
                                tv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent pokeDetailsIntent =  new Intent(context, ViewPokemonDetails.class);
                                        pokeDetailsIntent.putExtra(POKEMONTAG, pokemon);
                                        startActivity(pokeDetailsIntent);
                                        savedList.remove(tv);
                                    }
                                });
                                savedList.add(tv);
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
