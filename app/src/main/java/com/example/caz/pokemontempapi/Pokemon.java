package com.example.caz.pokemontempapi;

import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {

    private String name;
    private String spriteUrl;
    private String id;
    private String abilities;
    private String types;


    public Pokemon(JSONObject json) {
        try {
            this.name = json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.spriteUrl = json.getString("spriteUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.id = json.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.abilities = json.getString("abilities");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.types = json.getString("types");
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

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}