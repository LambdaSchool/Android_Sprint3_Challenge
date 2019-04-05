package com.jakeesveld.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {
    TextView pokemonNameTextView;
    TextView pokemonIdTextView;
    TextView pokemonType1TextView;
    TextView pokemonType2TextView;
    LinearLayout layoutAbilities;
    Context context;
    ImageView spriteImageView;
    Pokemon selectedPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);
        pokemonIdTextView = findViewById(R.id.text_view_id);
        pokemonNameTextView = findViewById(R.id.text_view_name);
        pokemonType1TextView = findViewById(R.id.text_view_type_1);
        pokemonType2TextView = findViewById(R.id.text_view_type_2);
        layoutAbilities = findViewById(R.id.layout_abilities_list);
        context = this;
        spriteImageView = findViewById(R.id.image_view_sprite);
        Intent intent = getIntent();
        try {
            selectedPokemon = (Pokemon) intent.getSerializableExtra(Pokemon.POKEMON_INTENT_KEY);
            pokemonNameTextView.setText(selectedPokemon.getName());
            pokemonIdTextView.setText("No. " + selectedPokemon.getId());
            ArrayList<String> pokemonTypes = selectedPokemon.getTypes();
            pokemonType1TextView.setText(pokemonTypes.get(0));
            try {
                pokemonType2TextView.setText(pokemonTypes.get(1));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            ArrayList<String> selectedPokemonAbilities = selectedPokemon.getAbilities();
            layoutAbilities.removeAllViews();
            for (String ability : selectedPokemonAbilities) {
                layoutAbilities.addView(createTextView(ability));
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Bitmap pokemonBitmap = NetworkAdapter.bitmapFromUrl(selectedPokemon.getSpriteUrl());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            spriteImageView.setImageBitmap(pokemonBitmap);
                        }
                    });
                }
            }).start();
        } catch (NullPointerException e) {
            e.printStackTrace();
            finish();
        }


    }


    public TextView createTextView(String ability) {
        TextView view = new TextView(context);
        view.setText(ability);
        view.setTextSize(18);
        view.setPadding(10, 10, 10, 10);
        return view;
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(Pokemon.POKEMON_INTENT_KEY, selectedPokemon);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
