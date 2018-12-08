package com.thadocizn.sprintchallengethree.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.sprintchallengethree.Constants;
import com.thadocizn.sprintchallengethree.R;
import com.thadocizn.sprintchallengethree.classes.Pokemon;
import com.thadocizn.sprintchallengethree.data.PokemonDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    private EditText etPokemon;
    private ArrayAdapter adapter;
    private ArrayList names;
    private ImageButton button;
    private Context context;
    private LinearLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(Constants.NAME_LIST, Context.MODE_PRIVATE);
        etPokemon = findViewById(R.id.editTextPokemon);
        context = this;

        parent = findViewById(R.id.parentLayout);
        button = findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strPokemon = etPokemon.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        final Pokemon pokemon = PokemonDao.getPokemon(strPokemon);
                        Intent intent = new Intent(context, PokemonDetailActivity.class);
                        intent.putExtra(Constants.PICKED_POKEMON, pokemon);
                        startActivity(intent);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final TextView textView = new TextView(context);
                                textView.setText(pokemon.getId() + " " + pokemon.getName());
                                textView.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        textView.setVisibility(View.GONE);
                                        return false;
                                    }
                                });
                                parent.addView(textView);
                            }
                        });
                    }
                }).start();
            }
        });

    }
}
