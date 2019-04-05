package com.rybarstudios.pokemon;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDao {

    private static final String POKEMON = "https://pokeapi.co/api/v2/pokemon/";
    private static final String URL_END = "%s/";
    private static final String SINGLE_POKEMON = POKEMON + URL_END;

    public static Pokemon getPokemon(String id) {
        Pokemon pokemon = null;
        String pokemonUrl = String.format(SINGLE_POKEMON, id);
        String result = NetworkAdapter.httpGetRequest(pokemonUrl);

        try {
            JSONObject jsonObject = new JSONObject(result);
            pokemon = new Pokemon(jsonObject);
            Bitmap image = NetworkAdapter.httpImageRequest(pokemon.getSpriteUrl());
            pokemon.setImage(image);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    public static void saveSearch(Pokemon pokemon) {
        SharedPreferences.Editor editor = MainActivity.mSharedPreferences.edit();
        editor.putString(Constants.SAVED_NAMES, pokemon.getName());
        editor.apply();
    }

    public static String getRecentSearch() {
        SharedPreferences preferences = MainActivity.mSharedPreferences;
        return preferences.getString(Constants.SAVED_NAMES, "");
    }
}
