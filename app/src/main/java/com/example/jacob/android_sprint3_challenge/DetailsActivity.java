package com.example.jacob.android_sprint3_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.RETURN_DATA_KEY, ((TextView) findViewById(R.id.text_name)).getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

    public class offloadTask extends AsyncTask<String, Integer, Wrapper> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Wrapper wrapper) {
            super.onPostExecute(wrapper);
            if (wrapper != null) {
                try {
                    ((ImageView) findViewById(R.id.image)).setImageBitmap(wrapper.bitmap);
                    Pokemon pokemon = wrapper.pokemon;
                    ((TextView) findViewById(R.id.text_name)).setText(pokemon.getName());
                    ((TextView) findViewById(R.id.text_number)).setText("No " + String.valueOf(pokemon.getId()));

                    for (String item : pokemon.getMoves()) {
                        ((LinearLayout) findViewById(R.id.layout_moves)).addView(getDefaultTextView(item));
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "No Pokemon found", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }

        @Override
        protected Wrapper doInBackground(String... strings) {
            final Pokemon pokemon = new Pokemon(PokemonDao.findPokemon(strings[0]));
            Bitmap bitmap = PokemonDao.getPokemonImage(pokemon.getSpriteUrl());
            Wrapper wrapper = new Wrapper();
            wrapper.pokemon = pokemon;
            wrapper.bitmap = bitmap;
            return wrapper;
        }
    }

    TextView getDefaultTextView(final String name) {
        TextView view = new TextView(context);
        view.setText(name);
        view.setTextSize(28);
        return view;
    }

    private class Wrapper {
        public Pokemon pokemon;
        public Bitmap bitmap;
    }


}


