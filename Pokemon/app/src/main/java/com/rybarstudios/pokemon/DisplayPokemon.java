package com.rybarstudios.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayPokemon extends AppCompatActivity {

    ImageView pokemonImage;
    TextView pokemonName, pokemonNumber, pokemonType, pokemonAbilities, pokemonMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pokemon);

        pokemonImage = findViewById(R.id.pokemon_image);
        pokemonName = findViewById(R.id.text_pokemon_name);
        pokemonNumber = findViewById(R.id.text_pokemon_number);
        pokemonType = findViewById(R.id.text_pokemon_type);
        pokemonAbilities = findViewById(R.id.text_pokemon_abilities);
        pokemonMoves = findViewById(R.id.text_pokemon_moves);

        Intent intent = getIntent();
        Pokemon pokemon = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        pokemonName.setText(pokemon.getName());
        pokemonImage.setImageBitmap(pokemon.getImage());
        pokemonNumber.setText("No " + Integer.toString(pokemon.getId()));

        String types = "Types: ";
        for (String type : pokemon.getTypes()) {
            types += type + "\t\t";
        }
        pokemonType.setText(types);

        String abilities = "Abilities:\n";
        for(String ability : pokemon.getAbilities()) {
            abilities += ability + "\n";
        }
        pokemonAbilities.setText(abilities);
    }
}
