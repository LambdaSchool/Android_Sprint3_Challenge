package com.example.earthdefensesystem.android_sprint3_challenge;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokeapiDao {
    public static final  String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";




//    public static ArrayList<Pokemon> getAllPokemon(){
//        ArrayList<Pokemon> pokemonName = new ArrayList<>();
//        String url = BASE_URL;
//        while(url != null) {
//            String page = NetworkAdapter.httpGetRequest(url);
//            try{
//                JSONObject pageJson = new JSONObject(page);
//                JSONArray resultsArray = pageJson.getJSONArray("results");
//                for (int i = 0; i < resultsArray.length(); i++) {
//                    try{
//                            pokemonName.add(new Pokemon(resultsArray.getJSONObject(i)));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                    }
//                }
//            }catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return pokemonName;
//    }

//    String url = BASE_URL + searchString + "/";

    public static JSONObject getPokemon(String searchString){
        JSONObject pokemon = null;
        String url = BASE_URL + searchString + "/";
        final String search = NetworkAdapter.httpGetRequest(url, NetworkAdapter.GET);
        try {
            pokemon = new JSONObject(search);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemon;
    }

    public static Bitmap getPokemonSprite(String spriteUrl) {
        return  NetworkAdapter.httpImageRequest(spriteUrl);
    }
}
