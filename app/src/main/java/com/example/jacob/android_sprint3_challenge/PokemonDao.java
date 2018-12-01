package com.example.jacob.android_sprint3_challenge;

import android.graphics.Bitmap;
import org.json.JSONException;
import org.json.JSONObject;


public class PokemonDao {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static JSONObject findPokemon(String searchString) {
        JSONObject json = null;
        String url = BASE_URL + searchString + "/";
        final String result = NetworkAdapter.httpRequest(url, NetworkAdapter.GET);
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Bitmap getPokemonImage(String imageUrl) {
        return  NetworkAdapter.httpImageRequest(imageUrl);
    }
}
