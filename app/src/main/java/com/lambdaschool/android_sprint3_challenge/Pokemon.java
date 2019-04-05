package com.lambdaschool.android_sprint3_challenge;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Pokemon {
    private String name;
    private int id;
    private int height;
    private int weight;
    private String sprite;
    private ArrayList<String> abilities;
    private ArrayList<String> moves;
    private ArrayList<String> types;

    public Pokemon(JSONObject jsonObject) {
        try {
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.height = jsonObject.getInt("height");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.weight = jsonObject.getInt("weight");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.sprite = jsonObject.getJSONObject("sprites").getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < jsonObject.getJSONArray("abilities").length(); i++) {
                try {
                    abilities.add(jsonObject.getJSONArray("abilities").getJSONObject(i).getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < jsonObject.getJSONArray("moves").length(); i++) {
                try {
                    abilities.add(jsonObject.getJSONArray("moves").getJSONObject(i).getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < jsonObject.getJSONArray("types").length(); i++) {
                try {
                    abilities.add(jsonObject.getJSONArray("types").getJSONObject(i).getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }
}
