package com.thadocizn.sprintchallengethree.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.thadocizn.sprintchallengethree.Constants;
import com.thadocizn.sprintchallengethree.R;
import com.thadocizn.sprintchallengethree.classes.Pokemon;
import com.thadocizn.sprintchallengethree.data.NetworkAdapter;
import com.thadocizn.sprintchallengethree.data.PokemonDao;

public class PokemonDetailActivity extends AppCompatActivity {

    private TextView name;//abilities;
    private ImageView pokemonImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        name =findViewById(R.id.tvPokemonName);
        //abilities= findViewById(R.id.tvAbilities);
        pokemonImage = findViewById(R.id.imageViewPokemon);

        Intent intent = getIntent();
        Pokemon pokemon = intent.getParcelableExtra(Constants.PICKED_POKEMON);
        name.setText(pokemon.getName());
        pokemonImage.setImageBitmap(pokemon.getImagePokemon());
    }
}
