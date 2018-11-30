package com.thadocizn.sprintchallengethree;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDao {
    private static final String All_POKEMON = "https://pokeapi.co/api/v2/pokemon/";
    private static final String SINGLE_POKEMON = "%S";
    private static final String FULL_URL = All_POKEMON + SINGLE_POKEMON;

    //Got to change this, I need to grab one at a time, ran out of time.

    public static Pokemon getPokemon() {
        Pokemon pokemon = null;
        String page = NetworkAdapter.httpGetRequest(FULL_URL);
        JSONObject json = null;
        try {
            json = new JSONObject(page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json != null) {
            pokemon = new Pokemon(json);
        }
        return pokemon;
    }
}
