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

public class MainActivity extends AppCompatActivity {
    public static final String POKEMON_NUMBER_EXTRA = "POKEMON_NUMBER";
    Button searchButton;
    EditText editTextSearch;
    Context context;
    LinearLayout linearLayoutPokeNames;

    ArrayList<String> savedPokemon = new ArrayList<>();
    ArrayList<String> pokeNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutPokeNames = findViewById(R.id.linear_layout_scroll_child_name_list);
        searchButton = findViewById(R.id.search_button);
        editTextSearch = findViewById(R.id.edit_text_search);
        context = this;


        new Thread(new Runnable() {
            @Override
            public void run() {
                pokeNames = PokemonNetworkDao.getAllPokemonNames();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < pokeNames.size(); ++i) {

                            //add something to populate pokedex scrollview

                        }
                        searchButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, DetailView.class);
                                final String pokeNumber = editTextSearch.getText().toString();
                                int pokeNumberInt = Integer.parseInt(pokeNumber);
                                intent.putExtra(POKEMON_NUMBER_EXTRA, pokeNumber);
                                String pokeViewText = (pokeNumberInt + " " + pokeNames.get(pokeNumberInt-1)); //adds a view for each item searched for
                                final TextView tv = new TextView(context);
                                tv.setText(pokeViewText);
                                tv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //clicking the newly added view
                                        Intent intent = new Intent(context, DetailView.class);
                                        intent.putExtra(POKEMON_NUMBER_EXTRA, pokeNumber);
                                        startActivity(intent);
                                    }
                                });
                                tv.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View view) {
                                        linearLayoutPokeNames.removeView(tv);
                                        return true;
                                    }
                                });
                                linearLayoutPokeNames.addView(tv);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        }).start();
    }
}
