package com.thadocizn.sprintchallengethree.data;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.thadocizn.sprintchallengethree.Constants;
import com.thadocizn.sprintchallengethree.activities.MainActivity;
import com.thadocizn.sprintchallengethree.classes.Pokemon;
import com.thadocizn.sprintchallengethree.classes.PokemonNames;
import com.thadocizn.sprintchallengethree.data.NetworkAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDao {
    private static final String All_POKEMON = "https://pokeapi.co/api/v2/pokemon/";
    private static final String SINGLE_POKEMON = "%s/";
    private static final String FULL_URL = All_POKEMON + SINGLE_POKEMON;

    //Got to change this, I need to grab one at a time, ran out of time.

    public static Pokemon getPokemon(int id) {
        Pokemon pokemon = null;
        Bitmap imagePokemon;
        String strUrl = String.format(FULL_URL, id);
        String page = NetworkAdapter.httpGetRequest(strUrl);
        getNames();

        try {
            JSONObject json = new JSONObject(page);
            pokemon = new Pokemon(json);

            imagePokemon = NetworkAdapter.httpImageRequest(pokemon.getSpriteUrl());
            pokemon.setImagePokemon(imagePokemon);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemon;
    }

    public static void getNames(){
        String result = NetworkAdapter.httpGetRequest(All_POKEMON);
        ArrayList<String> names = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(result);
            JSONArray name = json.getJSONArray("results");

            for (int i = 0; i < name.length(); ++i) {
                PokemonNames pokemon = new PokemonNames(name.getJSONObject(i));
                names.add(pokemon.getName());
                Log.i("charles", "Charles" + " " + pokemon.getName());

            }
            if (MainActivity.preferences != null){
                SharedPreferences.Editor editor = MainActivity.preferences.edit();
                Gson gson = new Gson();
                String jsonNameList = gson.toJson(names);
                editor.putString(Constants.NAME_LIST, jsonNameList);
                editor.apply();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
