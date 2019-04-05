package com.vivekvishwanath.android_sprint3_challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Pokemon pokemon = new Pokemon(PokemonDao.getPokemon("bulbasaur"));
                int i = 0;
            }
        }).start();
    }
}
