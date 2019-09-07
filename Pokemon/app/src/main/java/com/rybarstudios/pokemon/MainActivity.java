package com.rybarstudios.pokemon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageButton searchButton;
    EditText mEditText;
    LinearLayout recentSearches;
    Context context;
    public static SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mSharedPreferences = getSharedPreferences(Constants.SAVED_NAMES, Context.MODE_PRIVATE);

        recentSearches = findViewById(R.id.recent_pokemon_search);
        mEditText = findViewById(R.id.edit_pokemon_search);

        Map<String, ?> keys = mSharedPreferences.getAll();
        for(Map.Entry<String, ?> entry : keys.entrySet()) {
            recentSearches.addView(generateTextView(entry.getValue().toString()));
        }


        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = mEditText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon pokemon = PokemonDao.getPokemon(id);
                        PokemonDao.saveSearch(pokemon);
                        Intent intent = new Intent(context, DisplayPokemon.class);
                        intent.putExtra(Intent.EXTRA_STREAM, pokemon);
                        startActivity(intent);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recentSearches.addView(generateTextView(
                                        PokemonDao.getRecentSearch(pokemon.getId())));
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private TextView generateTextView(String pokemon) {
        final TextView view = new TextView(context);
        view.setText(pokemon);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                view.setVisibility(View.GONE);
                return false;
            }
        });
        return view;
    }
}
