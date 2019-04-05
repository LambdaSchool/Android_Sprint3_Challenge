package com.example.android_sprint3_challenge;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonNetworkDao {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    //example url https://pokeapi.co/api/v2/pokemon/5

    public static Pokemon getSinglePokemon(String pokeNumber) {
        String result = NetworkAdapter.httpRequest(BASE_URL + pokeNumber);
        try {
            JSONObject jsonTOP = new JSONObject(result);
            String pokeName = jsonTOP.getString("name");
            JSONArray pokeMoves = jsonTOP.getJSONArray("moves");
            JSONArray pokeTypes = jsonTOP.getJSONArray("types");
            String pokeImageUrl = jsonTOP.getJSONObject("sprites").getString("front_default");
            Pokemon pokemon = new Pokemon(pokeName, pokeMoves, pokeTypes, pokeImageUrl);
            return pokemon;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }




}
