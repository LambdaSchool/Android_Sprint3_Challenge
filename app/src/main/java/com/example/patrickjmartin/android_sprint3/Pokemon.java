package com.example.patrickjmartin.android_sprint3;

import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon {


    private String name, spriteURL, type1, type2;
    private int ID;
    private ArrayList<String> moves;

    public Pokemon(String name, String spriteURL, String type1, String type2, int ID, ArrayList<String> moves) {
        this.name = name;
        this.spriteURL = spriteURL;
        this.type1 = type1;
        this.type2 = type2;
        this.ID = ID;
        this.moves = moves;
    }

    public Pokemon(JSONObject caught){

        //TO DO
    }

    public String getName() {
        return name;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }
}
