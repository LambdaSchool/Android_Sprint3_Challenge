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


    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static String URL_ENDING = "/.json";
    private static String READ_POKEMON_BY_NUM = BASE_URL + "/%d" + URL_ENDING;

    public static ArrayList<Pokemon> getPokemon(int num) {

        String[] elementType = {"", ""};
        String imageURL = "";
        int number = num;
        String name = "";
        String[] moves = null;
        final ArrayList<Pokemon> resultList = new ArrayList<>();

        final String result = NetworkAdapter.httpRequest(String.format(READ_POKEMON_BY_NUM, num));
        try {
            JSONObject jsonObject = new JSONObject(result);

            try {
                name = jsonObject.getJSONObject("forms").getString("name");
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
                    moves[i] = jsonArray.getJSONObject(i).getJSONObject("move").getString("name");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            resultList.add(new Pokemon(elementType, imageURL, number, name, moves));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }


    public Bitmap bitmapFromURL (String imageURl){
        Bitmap image = null;

        try {
            URL url = new URL(imageURl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (IOException e){e.printStackTrace();}
        return image;
    }
}
