package com.example.Pokedex;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonNetworkDao {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final String ALL_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/?limit=807";

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

    public static ArrayList<String> getAllPokemonUrls() {
        ArrayList<String> pokeNames = new ArrayList<>();

        String result = NetworkAdapter.httpRequest(ALL_POKEMON_URL);
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

    public static ArrayList<String> getAllPokemonNames() {
        ArrayList<String> pokeNames = new ArrayList<>();
        String result = NetworkAdapter.httpRequest(ALL_POKEMON_URL);
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
