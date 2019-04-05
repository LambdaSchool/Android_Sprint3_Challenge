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

    public ArrayList<Pokemon> getAllPokemons() {
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

                String pokemonName = jsonArray.getJSONObject(i).getString("name");
                returnedJsonAsString = NetworkAdapter.httpRequest(URL_BASE + URL_INDIVIDUAL + pokemonName);
                jsonObject = new JSONObject(returnedJsonAsString);
                Pokemon pokemon = new Pokemon(jsonObject);
                pokemonArrayList.add(pokemon);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return pokemonArrayList;
    }

}
