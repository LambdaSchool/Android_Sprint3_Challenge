package com.thadocizn.sprintchallengethree.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.thadocizn.sprintchallengethree.Constants;
import com.thadocizn.sprintchallengethree.R;
import com.thadocizn.sprintchallengethree.classes.Pokemon;
import com.thadocizn.sprintchallengethree.data.NetworkAdapter;
import com.thadocizn.sprintchallengethree.data.PokemonDao;

import java.util.Arrays;

public class PokemonDetailActivity extends AppCompatActivity {

    private TextView types;
    private TextView abilities;
    private TextView name;
    private ImageView pokemonImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        types = findViewById(R.id.tvPokemonType);
        name = findViewById(R.id.tvPokemonName);
        abilities= findViewById(R.id.tvPokemonAbilities);
        pokemonImage = findViewById(R.id.imageViewPokemon);

        Intent intent = getIntent();
        Pokemon pokemon = intent.getParcelableExtra(Constants.PICKED_POKEMON);
        name.setText(pokemon.getName());
        pokemonImage.setImageBitmap(pokemon.getImagePokemon());

        String currentAbility = "";
        for (String ability : pokemon.getAbilities()) {
            currentAbility = currentAbility + "\n" + ability;
        }

        abilities.setText(currentAbility);

        String currentType = "";
        for (String type : pokemon.getTypes()) {
            Log.i("fgsfs", "hfhf" + Arrays.toString(pokemon.getTypes()));
            currentType = currentType + "\n" + type;
        }

        types.setText(currentType);
    }
}
