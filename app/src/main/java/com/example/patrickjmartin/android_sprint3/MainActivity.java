package com.example.patrickjmartin.android_sprint3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
                        Pokemon pokemon = PokemonDAO.getPokemon(30);
                        Intent pokeDetailsIntent =  new Intent(context, ViewPokemonDetails.class);

                        pokeDetailsIntent.putExtra("poke_deets", pokemon);

                        startActivity(pokeDetailsIntent);

                    }
                }).start();



            }
        });
    }
}

