package com.thadocizn.sprintchallengethree.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonNames {
    private String name, url;

    public PokemonNames(JSONObject jsonObject){
        try {
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.url = jsonObject.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
