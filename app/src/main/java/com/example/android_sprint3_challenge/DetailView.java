package com.example.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Field;

public class DetailView extends AppCompatActivity {
    Pokemon pokemon;
    LinearLayout linearLayoutPokeMoves;
    LinearLayout linearLayoutPokeTypes;
    TextView textViewPokeName;
    ImageView imageViewPokeImage;
    Context context;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        context = this;
        //Attaching Handles
        linearLayoutPokeMoves = findViewById(R.id.linear_layout_scroll_child_poke_moves);
        linearLayoutPokeTypes = findViewById(R.id.linear_layout_scroll_child_poke_types);
        textViewPokeName = findViewById(R.id.text_view_poke_name);
        imageViewPokeImage = findViewById(R.id.image_view_poke_image);

        //Retrieving Intent
        Intent intent = getIntent();
        final String pokeNumber = intent.getStringExtra(MainActivity.POKEMON_NUMBER_EXTRA);



        //Creating Pokemon TODO: Create asynctask
        new Thread(new Runnable() {
            @Override
            public void run() {
                pokemon = PokemonNetworkDao.getSinglePokemon(pokeNumber);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageViewPokeImage.setImageBitmap(pokemon.getImage());
                        textViewPokeName.setText(pokemon.getName());

                        for (String moveName: pokemon.getMoves()) { //generates views for moves
                            TextView tv = new TextView(context);
                            tv.setText(moveName);
                            linearLayoutPokeMoves.addView(tv);
                        }

                        for (String typeName: pokemon.getTypes()) { //generates views for types
                            TextView tv = new TextView(context);
                            tv.setText(typeName);
                            linearLayoutPokeTypes.addView(tv);
                        }
                        int resId = getResId("cry" + pokeNumber); //calls function to generate ResId
                        try {
                            MediaPlayer mediaPlayer = MediaPlayer.create(context, resId); //plays pokemon cry
                            mediaPlayer.start();
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
            }
        }).start();





    }

    public static int getResId(String resName) { //function for programmatically getting ResId as int

        try {
            Field idField = R.raw.class.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }



/*    class getPokemon extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            progressBar = findViewById(R.id.progress_circular);
            progressBar.setVisibility(View.VISIBLE);
            backgroundCancel = false;
        }

        @Override
        protected void onPostExecute(String s) {
            //generate views here
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String result =  cipher(strings[0], Integer.parseInt(strings[1]), this);
            if (backgroundCancel == true) {
                cancel(true);
            }
            return result;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setMax(100);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            progressBar.setVisibility(View.GONE);
            Log.i("appLog", "asynctask canceled successfully");
        }

        public void doProgress(int value) {
            publishProgress(value);
        }
    }*/

}
