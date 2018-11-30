package com.example.joshh.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonApiDao {
    public static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    public static final String POKEMON_URL = BASE_URL + "%s/";


    public static Pokemon getPokemon(String id){
        Pokemon pokemon = null;
        try {
            String url = String.format(POKEMON_URL, id);
            JSONObject jsonObject = new JSONObject(NetworkAdapter.httpRequest(url, NetworkAdapter.GET));
            pokemon = new Pokemon(jsonObject);
            Bitmap imageBitMap = NetworkAdapter.httpImageRequest(pokemon.getSpriteUrl());
            pokemon.setBitmap(imageBitMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemon;
    }
}
