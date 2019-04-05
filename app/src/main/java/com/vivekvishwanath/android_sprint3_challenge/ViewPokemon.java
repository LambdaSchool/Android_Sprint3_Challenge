package com.vivekvishwanath.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewPokemon extends AppCompatActivity {

    private Context context;
    private TextView pokemonName;
    private TextView pokemonId;
    private ImageView pokemonSprite;
    private TextView pokemonMovesView;
    private TextView pokemonTypesView;
    private TextView pokemonAbilitiesView;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pokemon);
        context = this;

        pokemonName = findViewById(R.id.pokemon_name_view);
        pokemonId = findViewById(R.id.pokemon_id_view);
        pokemonSprite = findViewById(R.id.pokemon_sprite);
        pokemonMovesView = findViewById(R.id.pokemon_moves);
        pokemonTypesView = findViewById(R.id.pokemon_types);
        pokemonAbilitiesView = findViewById(R.id.pokemon_abilities);

        Intent intent = getIntent();
        pokemon = intent.getParcelableExtra("pokemon");
        int i = 0;
    }
}
