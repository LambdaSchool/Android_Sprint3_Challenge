package com.thadocizn.sprintchallengethree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDao {
    private static final String All_POKEMON = "https://pokeapi.co/api/v2/pokemon/";
    public static ArrayList<Pokemon>getAllPokemons(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        String result = NetworkAdapter.httpGetRequest(All_POKEMON);
        try {
            JSONObject json = new JSONObject(result);
            JSONArray jsonArray = json.names();
            for (int i = 0; i < jsonArray.length(); i++) {
                String pokemon = jsonArray.getString(i);
                pokemons.add(new Pokemon(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemons;
    }
}
