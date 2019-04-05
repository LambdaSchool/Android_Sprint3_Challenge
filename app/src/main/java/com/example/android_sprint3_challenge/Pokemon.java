package com.example.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Pokemon {

    private ArrayList<String> moves;
    private ArrayList<String> types;
    private Bitmap image;
    private String name;

    public Pokemon(String pokeName, JSONArray pokeMoves, JSONArray pokeTypes, String pokeImageUrl) {
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        this.name = pokeName;

        //Unwrapping jsonArrays
        for (int i = 0; i < pokeMoves.length(); ++i) {

            try {
                this.moves.add (pokeMoves.getJSONObject(i).getJSONObject("move").getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < pokeTypes.length(); ++i) {

            try {
                this.moves.add (pokeMoves.getJSONObject(i).getJSONObject("type").getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        this.image = NetworkAdapter.httpImageRequest(pokeImageUrl);

    }
}
