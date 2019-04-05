package com.lambdaschool.android_sprint3_challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayoutMain;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutMain = findViewById(R.id.linear_layout);
        Button button = findViewById(R.id.button_go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edit_text_search);
                final String textToSearchFor = editText.getText().toString().toLowerCase();

                if (!textToSearchFor.equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            pokemon = PokemonDao.getAPokemon(textToSearchFor);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (pokemon.getName().toLowerCase().equals(textToSearchFor) || Integer.toString(pokemon.getId()).equals(textToSearchFor)) {

                                        TextView textView = new TextView(getApplicationContext());
                                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                        textView.setPadding(5, 5, 5, 0);
                                        textView.setTextSize(18);
                                        textView.setText(pokemon.getId() + ": " + pokemon.getName());
                                        textView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String pokemonListing = ((TextView) v).getText().toString();
                                                final String listingEnd = pokemonListing.substring(pokemonListing.indexOf(": ") + 2);

                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        final Pokemon pokemonClicked = PokemonDao.getAPokemon(listingEnd);
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                Intent intent = new Intent(getApplicationContext(), PokemonDetails.class);
                                                                intent.putExtra("pokemon", pokemonClicked);
                                                                startActivity(intent);
                                                            }
                                                        });
                                                    }
                                                }).start();
                                            }
                                        });
                                        textView.setOnLongClickListener(new View.OnLongClickListener() {
                                            @Override
                                            public boolean onLongClick(View v) {
                                                linearLayoutMain.removeView(v);
                                                return true;
                                            }
                                        });
                                        linearLayoutMain.addView(textView);

                                        Intent intent = new Intent(getApplicationContext(), PokemonDetails.class);
                                        intent.putExtra("pokemon", pokemon);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }
}
