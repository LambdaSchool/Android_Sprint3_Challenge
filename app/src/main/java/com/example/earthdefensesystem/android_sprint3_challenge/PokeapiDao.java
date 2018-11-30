package com.example.earthdefensesystem.android_sprint3_challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokeapiDao {
    public static final  String BaseUrl = "https://pokeapi.co/api/v2/pokemon/";




    public static ArrayList<Pokemon> getAllPokemon(){
        ArrayList<Pokemon> pokemon = new ArrayList<>();
        String url = BaseUrl;
        while(url != null) {
            String page = NetworkAdapter.httpGetRequest(url);
            try{
                JSONObject pageJson = new JSONObject(page);
                JSONArray resultsArray = pageJson.getJSONArray("results");
                for (int i = 0; i < resultsArray.length(); i++) {
                    try{
                            pokemon.add(new Pokemon(resultsArray.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                    }
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return pokemon;
    }
}
