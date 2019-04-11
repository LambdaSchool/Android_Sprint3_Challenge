package com.example.Pokedex;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonNetworkDao {

    private static final java.lang.String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final java.lang.String ALL_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/?limit=807";

    //example url https://pokeapi.co/api/v2/pokemon/5

    public static Pokemon getSinglePokemon(java.lang.String pokeNumber) {
        java.lang.String result = NetworkAdapter.httpRequest(BASE_URL + pokeNumber);
        try {
            JSONObject jsonTOP = new JSONObject(result);
            java.lang.String pokeName = jsonTOP.getString("name");
            JSONArray pokeMoves = jsonTOP.getJSONArray("moves");
            JSONArray pokeTypes = jsonTOP.getJSONArray("types");
            java.lang.String pokeImageUrl = jsonTOP.getJSONObject("sprites").getString("front_default");
            Pokemon pokemon = new Pokemon(pokeName, pokeMoves, pokeTypes, pokeImageUrl);
            return pokemon;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<java.lang.String> getAllPokemonUrls() {
        ArrayList<java.lang.String> pokeNames = new ArrayList<>();

        java.lang.String result = NetworkAdapter.httpRequest(ALL_POKEMON_URL);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject temp = jsonArray.getJSONObject(i);
                pokeNames.add(temp.getString("url"));
            }
            return pokeNames;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<java.lang.String> getAllPokemonNames() {
        ArrayList<java.lang.String> pokeNames = new ArrayList<>();
        java.lang.String result = NetworkAdapter.httpRequest(ALL_POKEMON_URL);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject temp = jsonArray.getJSONObject(i);
                pokeNames.add(temp.getString("name"));
            }
            return pokeNames;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


}
