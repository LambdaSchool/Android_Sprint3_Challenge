package com.example.android_sprint3_challenge;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDAO {
    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    private static final String SELECTED_POKEMON_URL = BASE_URL + "%d" + "/";

    public static Pokemon getPokemon(int pokeID) {
        String pokeURL = String.format(SELECTED_POKEMON_URL, pokeID);
        Pokemon selectedPokemon = null;
        final String result = NetworkAdapter.httpRequest(pokeURL, NetworkAdapter.GET);
        JSONObject pokeJsonObject;
        try {
            pokeJsonObject = new JSONObject(result);
            selectedPokemon = new Pokemon(pokeJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return selectedPokemon;
    }
}
