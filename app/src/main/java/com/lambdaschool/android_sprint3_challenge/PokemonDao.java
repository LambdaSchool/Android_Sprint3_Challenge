package com.lambdaschool.android_sprint3_challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class PokemonDao {
    private static final String URL_BASE = "https://pokeapi.co/api/v2/";
    private static final String URL_LIST = "pokemon/?offset=01&limit=1200";
    private static final String URL_INDIVIDUAL = "pokemon/";

    public static ArrayList<Pokemon> getAllPokemons() {
        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
        String returnedJsonAsString = NetworkAdapter.httpRequest(URL_BASE + URL_LIST);
        JSONObject jsonObject = null;

        try {

            jsonObject = new JSONObject(returnedJsonAsString);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = null;

        try {

            jsonArray = jsonObject.getJSONArray("results");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            try {

                pokemonArrayList.add(getAPokemon(jsonArray.getJSONObject(i).getString("name")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return pokemonArrayList;
    }

    public static Pokemon getAPokemon(String nameOrId) {
        String returnedJsonAsString = NetworkAdapter.httpRequest(URL_BASE + URL_INDIVIDUAL + nameOrId);
        Pokemon pokemon = null;
        JSONObject jsonObject;

        try {

            jsonObject = new JSONObject(returnedJsonAsString);
            pokemon = new Pokemon(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemon;
    }
}
