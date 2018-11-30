package com.example.jacob.android_sprint3_challenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon {
    private long id;
    private String name;
    private ArrayList<String> moves;
    private String spriteUrl;
    private ArrayList<String> types;

    //    ,String name, ArrayList<String> abilities, String spriteUrl, ArrayList<String> types
    public Pokemon(long id, String name, ArrayList<String> moves, String spriteUrl, ArrayList<String> types) {
        this.id = id;
        this.name = name;
        this.moves = moves;
        this.spriteUrl = spriteUrl;
        this.types = types;
    }

    public Pokemon(JSONObject json) {
        try {
            this.id = json.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.name = json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Get types
        try {
            JSONArray jsonArray = json.getJSONArray("types");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject jsonSubObject = object.getJSONObject("type");
                String type = jsonSubObject.getString("name");
                types.add(type);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Get moves
        try {
            JSONArray jsonArray = json.getJSONArray("moves");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject jsonSubObject = object.getJSONObject("move");
                String move = jsonSubObject.getString("name");
                moves.add(move);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Get spriteURL


    }


//    public Pokemon(long id) {
//        Pokemon pokemon = PokemonDao.findPokemon(String.valueOf(id));
//        this.id = pokemon.getId();
//        this.name = pokemon.getName();
//        this.moves = pokemon.getAbilities();
//        this.spriteUrl = pokemon.getSpriteUrl();
//        this.types = pokemon.getTypes();
//    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAbilities() {
        return moves;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public ArrayList<String> getTypes() {
        return types;
    }
}
