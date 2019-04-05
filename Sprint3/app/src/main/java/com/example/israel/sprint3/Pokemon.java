package com.example.israel.sprint3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    public Pokemon(JSONObject pokemonJson) {
        // id
        try {
            this.id = pokemonJson.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //name
        try {
            this.name = pokemonJson.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // sprite url
        try {
            JSONObject spritesJson = pokemonJson.getJSONObject("sprites");
            this.spriteUrl = spritesJson.getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // abilities
        try {
            JSONArray abilityInfoJsonArr = pokemonJson.getJSONArray("abilities");
            // TODO
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // types
        try {
            JSONArray typeInfoJsonArr = pokemonJson.getJSONArray("types");
            // TODO
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int id;
    private String name;
    private String spriteUrl;
    private ArrayList<String> abilities;
    private ArrayList<String> types;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

}
