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
            if (abilityInfoJsonArr != null) {
                for (int i = 0; i < abilityInfoJsonArr.length(); ++i) {
                    JSONObject abilityInfoJson = abilityInfoJsonArr.getJSONObject(i);
                    JSONObject abilityJson = abilityInfoJson.getJSONObject("ability");
                    abilities.add(abilityJson.getString("name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // types
        try {
            JSONArray typeInfoJsonArr = pokemonJson.getJSONArray("types");
            if (typeInfoJsonArr != null) {
                for (int i = 0; i < typeInfoJsonArr.length(); ++i) {
                    JSONObject typeInfoJson = typeInfoJsonArr.getJSONObject(i);
                    JSONObject typeJson = typeInfoJson.getJSONObject("type");
                    types.add(typeJson.getString("name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int id;
    private String name;
    private String spriteUrl;
    private ArrayList<String> abilities = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();

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
