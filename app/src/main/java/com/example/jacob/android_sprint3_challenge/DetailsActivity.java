package com.example.jacob.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    Context context;
    LinearLayout parentlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = this;

        final String searchString = getIntent().getStringExtra(MainActivity.SEARCH_DATA);
        new offloadTask().execute(searchString);

    }

    public class offloadTask extends AsyncTask<String, Integer, Pokemon> {

        @Override
        protected void onPostExecute(Pokemon pokemon) {
            super.onPostExecute(pokemon);
            if (pokemon != null) {
                ((TextView) findViewById(R.id.text_name)).setText(pokemon.getName());
                ((TextView) findViewById(R.id.text_number)).setText(String.valueOf(pokemon.getId()));
                for (String item : pokemon.getMoves()) {
                    ((LinearLayout) findViewById(R.id.layout_moves)).addView(getDefaultTextView(item));
                }

            }
        }

        @Override
        protected Pokemon doInBackground(String... strings) {
            final Pokemon pokemon = new Pokemon(PokemonDao.findPokemon(strings[0]));
            return pokemon;
        }
    }


    TextView getDefaultTextView(final String name) {
        TextView view = new TextView(context);
        view.setText(name);
        view.setTextSize(28);
        return view;
    }


}
