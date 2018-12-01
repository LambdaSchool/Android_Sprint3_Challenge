package com.example.patrickjmartin.android_sprint3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPokemonDetails extends AppCompatActivity {

    Context context;
    private ImageView pokeImage;
    private TextView pokeName, pokeType1, pokeType2, pokeNum;
    private LinearLayout pokeMoves;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pokemon_details);
        context = this;

        pokeImage = findViewById(R.id.image_pokemon);
        pokeName = findViewById(R.id.tv_pokemon_name);
        pokeType1 = findViewById(R.id.tv_type1);
        pokeType2 = findViewById(R.id.tv_type2);
        pokeNum = findViewById(R.id.tv_pokemon_number);
        pokeMoves = findViewById(R.id.moves_list);

        Intent intent  = getIntent();

        Pokemon pokemonPicked = intent.getParcelableExtra("poke_deets");

        pokeName.setText(pokemonPicked.getName());





    }



}
