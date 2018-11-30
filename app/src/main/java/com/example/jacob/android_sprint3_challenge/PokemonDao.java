package com.example.jacob.android_sprint3_challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class PokemonDao {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static Pokemon findPokemon(String searchString) {
        Pokemon pokemon = null;
        String url = BASE_URL + searchString + "/";
        final String result = NetworkAdapter.httpRequest(url, NetworkAdapter.GET);
        ArrayList<String> types = new ArrayList<>();
        ArrayList<String> moves = new ArrayList<>();

        try {
            JSONObject topLevel = new JSONObject(result);
            String id = topLevel.getString("id");
            String name = topLevel.getString("name");
            JSONArray jsonArray = topLevel.getJSONArray("types");
            JSONArray jsonSubArray = new JSONArray();
            for (int i = 0; i < jsonArray.length();++i) {
//                jsonArray(i)
            }

/*            for (int i = 0; i < jsonArray.length(); ++i) {
//                JSONArray jsonSubArray = ((JSONObject) jsonArray.getJSONObject(i)).getJSONArray("type");
//                String move = jsonSubArray.getJSONObject(i).getString("name");
                moves.add(move);
            }*/


//            JSONArray itemNames = topLevel.names();
//            for (int i = 0; i < itemNames.length(); ++i) {
//                final String id = itemNames.getString(i);
//                final String name = topLevel.getJSONObject(id).getString("name");
//
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return pokemon;
    }

    public static ArrayList<String> getAbilities(String id) {

        return null;
    }


}
