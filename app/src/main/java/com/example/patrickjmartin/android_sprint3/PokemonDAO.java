package com.example.patrickjmartin.android_sprint3;

import org.json.JSONObject;

public class PokemonDAO {

    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    private static final String SELECTED_POKEMON_URL = BASE_URL + "%d/";

    public static JSONObject getPokemon(int pokeID) {
        String pokeURL = String.format(SELECTED_POKEMON_URL, pokeID);
        
    }

}
