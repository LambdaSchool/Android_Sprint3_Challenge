package com.lambdaschool.android_sprint3_challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PokemonDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        Pokemon pokemon = getIntent().getParcelableExtra("pokemon");

        ((TextView) findViewById(R.id.text_view_name)).setText(pokemon.getName());
        ((TextView) findViewById(R.id.text_view_id)).setText("ID: " + pokemon.getId());
        ((TextView) findViewById(R.id.text_view_height)).setText("Height: " + pokemon.getHeight());
        ((TextView) findViewById(R.id.text_view_weight)).setText("Weight: " + pokemon.getWeight());

        for (String ability : pokemon.getAbilities()) {
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(ability);
            ((LinearLayout) findViewById(R.id.linear_layout_abilities)).addView(textView);
        }
        for (String moves : pokemon.getMoves()) {
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(moves);
            ((LinearLayout) findViewById(R.id.linear_layout_moves)).addView(textView);
        }
        for (String types : pokemon.getTypes()) {
            TextView textView = new TextView(getApplicationContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(types);
            ((LinearLayout) findViewById(R.id.linear_layout_types)).addView(textView);
        }
    }
}
