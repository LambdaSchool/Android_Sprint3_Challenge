package com.thadocizn.sprintchallengethree.data;

import android.graphics.Bitmap;
import android.util.Log;

import com.thadocizn.sprintchallengethree.classes.Pokemon;
import com.thadocizn.sprintchallengethree.data.NetworkAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDao {
    private static final String All_POKEMON = "https://pokeapi.co/api/v2/pokemon/";
    private static final String SINGLE_POKEMON = "%s/";
    private static final String FULL_URL = All_POKEMON + SINGLE_POKEMON;

    //Got to change this, I need to grab one at a time, ran out of time.

    public static Pokemon getPokemon(int id) {
        Pokemon pokemon = null;
        Bitmap imagePokemon;
        String strUrl = String.format(FULL_URL, id);
        String page = NetworkAdapter.httpGetRequest(strUrl);
        ArrayList<String> strNames;

        try {
            JSONObject json = new JSONObject(page);
            pokemon = new Pokemon(json);
            strNames = new ArrayList<>();
            for (int i = 0; i < json.length(); i++) {
                pokemon = new Pokemon(json);
                String name = pokemon.getName();
                strNames.add(name);

            }
            Log.i("Charles", "Charles" + strNames.size());
            imagePokemon = NetworkAdapter.httpImageRequest(pokemon.getSpriteUrl());
            pokemon.setImagePokemon(imagePokemon);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemon;
    }
}
