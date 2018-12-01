package com.thadocizn.sprintchallengethree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                PokemonDao.getPokemon(1);
            }
        })).start();
    }
}
