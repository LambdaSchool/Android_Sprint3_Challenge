package com.example.israel.sprint3;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonNetworkDAO {

    public static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static final String POKEMON = "pokemon/";

    @Nullable
    @WorkerThread
    public static Pokemon getPokemon(String nameOrId) {
        String pokemonJsonStr = NetworkAdapter.httpRequestGET(BASE_URL + POKEMON + nameOrId);
        if (pokemonJsonStr == null) {
            return null;
        }

        try {
            JSONObject pokemonJson = new JSONObject(pokemonJsonStr);
            return new Pokemon(pokemonJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> getPokemonNames() {
        // do not return partial list
        try {
            ArrayList<String> outNames = new ArrayList<>();
            String nextUrl = BASE_URL + POKEMON;
            while(true) {
                String pokemonsJsonStr = NetworkAdapter.httpRequestGET(nextUrl);
                if (pokemonsJsonStr == null) {
                    return null;
                }

                JSONObject pokemonsJson = new JSONObject(pokemonsJsonStr);
                JSONArray results = pokemonsJson.getJSONArray("results");
                if (results != null) {
                    for (int i = 0; i < results.length(); ++i) {
                        JSONObject result = results.getJSONObject(i);
                        outNames.add(result.getString("name"));
                    }
                }

                nextUrl = pokemonsJson.getString("next");
                if (nextUrl.equals("null")) { // that was the last batch
                    if (outNames.size() != pokemonsJson.getInt("count")) {
                        return null; // we did not get all the pokemon names
                    }
                    break;
                }
            }
            return outNames; // success
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
