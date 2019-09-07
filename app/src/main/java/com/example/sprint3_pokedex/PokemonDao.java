package com.example.sprint3_pokedex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PokemonDao {


    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon";
    private static String URL_ENDING = ".json";
    private static String READ_POKEMON_BY_NUM = BASE_URL + "/%s";// + URL_ENDING;

    public static Pokemon getPokemon(int num) {

        String[] elementType = {"", ""};
        String imageURL = "";
        int number = num;
        String name = "";
        ArrayList<String> movesArrList = new ArrayList<>();
        String[] moves = null;
        Pokemon pokemon = null;

        final String result = NetworkAdapter.httpRequest(String.format(READ_POKEMON_BY_NUM, String.valueOf(num)));
        try {
            JSONObject jsonObject = new JSONObject(result);

            try {
                name = jsonObject.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                imageURL = jsonObject.getJSONObject("sprites").getString("front_default");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray = jsonObject.getJSONArray("types");
                for (int i = 0; i < jsonArray.length(); i++) {
                    elementType[i] = jsonArray.getJSONObject(i).getJSONObject("type").getString("name");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray = jsonObject.getJSONArray("moves");
                for (int i = 0; i < jsonArray.length(); i++) {
                    movesArrList.add(jsonArray.getJSONObject(i).getJSONObject("move").getString("name"));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            pokemon = new Pokemon(elementType, imageURL, number, name, movesArrList);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemon;
    }


    public static Bitmap bitmapFromURL(String imageURl) {
        Bitmap image = null;

        try {
            URL url = new URL(imageURl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void addPokemonToFavorites(Pokemon pokemon) {
        PokemonFavoriteRepo.addToList(pokemon);
    }

    public static String[] getPokemonNames() {
        String[] names = PokemonFavoriteRepo.getPokeNames();
        return names;
    }

    public static void removePokemon(String name) {
        PokemonFavoriteRepo.removeFromListByName(name);
    }

    public static boolean checkForExisitingPokemon(int num){
       return PokemonFavoriteRepo.checkForExistingPokemon(num);
    }

    public static void removePokemonByName(String name) {
        PokemonFavoriteRepo.removeFromListByName(name);
    }
}
