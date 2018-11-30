package com.thadocizn.sprintchallengethree;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon  {

        public int id;
        public String name;
        public String[] abilities = null;
        public String[] sprites;
        public String[] types = null;

    public Pokemon(int id, String name, String[] abilities, String[] sprites, String[] types) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
        this.sprites = sprites;
        this.types = types;
    }

    public Pokemon(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonAbilities = jsonObject.getJSONArray("abilities");
            this.abilities = new String[jsonAbilities.length()];
            for (int i = 0; i <jsonAbilities.length() ; i++) {
                this.abilities[1] = jsonAbilities.getString(i);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            JSONArray jsonSprites = jsonObject.getJSONArray("sprites");
            this.abilities = new String[jsonSprites.length()];
            for (int i = 0; i <jsonSprites.length() ; i++) {
                this.abilities[1] = jsonSprites.getString(i);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            JSONArray jsonTypes = jsonObject.getJSONArray("abilities");
            this.abilities = new String[jsonTypes.length()];
            for (int i = 0; i <jsonTypes.length() ; i++) {
                this.abilities[1] = jsonTypes.getString(i);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
