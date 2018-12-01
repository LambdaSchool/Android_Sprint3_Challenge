package com.thadocizn.sprintchallengethree.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thadocizn.sprintchallengethree.data.PokemonDao;
import com.thadocizn.sprintchallengethree.R;

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
