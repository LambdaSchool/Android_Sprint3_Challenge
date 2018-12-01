package com.example.patrickjmartin.android_sprint3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context;
    private LinearLayout savedListLayout;
    private Button searchButton;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        savedListLayout = findViewById(R.id.poke_saved_list);
        searchButton = findViewById(R.id.search_button);
        searchBox = findViewById(R.id.search_bar);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon pokemon = PokemonDAO.getPokemon(Integer.parseInt(searchBox.getText().toString()));
                        Intent pokeDetailsIntent =  new Intent(context, ViewPokemonDetails.class);
                        pokeDetailsIntent.putExtra("poke_deets", pokemon);
                        startActivity(pokeDetailsIntent);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final TextView tv = new TextView(context);
                                tv.setText(pokemon.getName());
                                tv.setTextSize(25);

                                
                                savedListLayout.addView(tv);
                            }
                        });


                    }
                }).start();



            }
        });
    }
}

