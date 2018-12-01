package com.example.patrickjmartin.android_sprint3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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

        pokeName = findViewById(R.id.tv_pokemon_name);
        pokeType1 = findViewById(R.id.tv_type1);
        pokeType2 = findViewById(R.id.tv_type2);
        pokeNum = findViewById(R.id.tv_pokemon_number);
        pokeMoves = findViewById(R.id.moves_list);

        Intent intent  = getIntent();

        Pokemon pokemonPicked = intent.getParcelableExtra("poke_deets");

        pokeName.setText(pokemonPicked.getName());
        pokeNum.setText(String.valueOf(pokemonPicked.getID()));
        pokeType1.setText(pokemonPicked.getType1());
        pokeType2.setText(pokemonPicked.getType2());

        for (String temp : pokemonPicked.getMoves()) {
            pokeMoves.addView(getDefaultTextView(temp));
        }

        new DownloadImageTask((ImageView) findViewById(R.id.image_pokemon))
                .execute(pokemonPicked.getSpriteURL());

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView pokeImage;

        public DownloadImageTask(ImageView pokeImage) {
            this.pokeImage = pokeImage;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String pokeURL = url[0];
            Bitmap image = null;

            try {
                InputStream in = new URL(pokeURL).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            pokeImage.setImageBitmap(bitmap);
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
        }
    }

    TextView getDefaultTextView(final String name) {
        TextView view = new TextView(context);
        view.setText(name);
        view.setTextSize(24);
        return view;
    }



}
