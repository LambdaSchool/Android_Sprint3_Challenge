package com.thadocizn.sprintchallengethree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDao {
    private static final String All_POKEMON = "https://pokeapi.co/api/v2/pokemon/";

    //Got to change this, I need to grab one at a time, ran out of time.

    public static ArrayList<Pokemon>getAllPokemons(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String page = NetworkAdapter.httpGetRequest(All_POKEMON);
        try {
            JSONObject json = new JSONObject(page);
            JSONArray jsonArray = json.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                pokemons.add(new Pokemon(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemons;
    }
}
