package com.example.jacob.android_sprint3_challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

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
}
