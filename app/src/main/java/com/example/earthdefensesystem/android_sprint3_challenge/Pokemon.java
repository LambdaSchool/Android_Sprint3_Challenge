package com.example.earthdefensesystem.android_sprint3_challenge;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private long id;
    private String name;
    private Move[] moves;
//    private Sprites sprites;
//    private Type[] types;

public Pokemon(long id){
    this.id = id;
}
public Pokemon(JSONObject json){
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
    try {
        this.moves = json.getMove[]("moves");
    } catch (JSONException e) {
        e.printStackTrace();
    }

}

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
//
//    public Move[] getMoves() { return moves; }
//    public void setMoves(Move[] value) { this.moves = value; }
//
//    public Sprites getSprites() { return sprites; }
//    public void setSprites(Sprites value) { this.sprites = value; }
//
//    public Type[] getTypes() { return types; }
//    public void setTypes(Type[] value) { this.types = value; }
}
