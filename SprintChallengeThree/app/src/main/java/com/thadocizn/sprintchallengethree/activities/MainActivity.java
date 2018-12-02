package com.thadocizn.sprintchallengethree.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thadocizn.sprintchallengethree.Constants;
import com.thadocizn.sprintchallengethree.data.PokemonDao;
import com.thadocizn.sprintchallengethree.R;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        preferences = getSharedPreferences(Constants.NAME_LIST, Context.MODE_PRIVATE);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                PokemonDao.getPokemon(1);
            }
        })).start();
    }
}
