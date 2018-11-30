package com.example.patrickjmartin.android_sprint3;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon implements Parcelable {


    private String name, spriteURL, type1, type2;
    private int ID;
    private ArrayList<String> moves;
    private boolean isSaved;

    public Pokemon(String name, String spriteURL, String type1, String type2, int ID, ArrayList<String> moves) {
        this.name = name;
        this.spriteURL = spriteURL;
        this.type1 = type1;
        this.type2 = type2;
        this.ID = ID;
        this.moves = moves;

    }

    public Pokemon(JSONObject caught){
        JSONObject temp;
        JSONArray tempAry;

        try {
            this.name = caught.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            temp = caught.getJSONObject("sprites");
            this.spriteURL = temp.getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            tempAry = caught.getJSONArray("types");
            this.type2 = tempAry.getJSONObject(0)
                    .getJSONObject("type")
                    .getString("name");
            this.type1 = tempAry.getJSONObject(1)
                    .getJSONObject("type")
                    .getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.ID = caught.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            tempAry = caught.getJSONArray("moves");
            for (int i = 0; i < tempAry.length(); i++) {
                moves.add(tempAry.getJSONObject(i).getJSONObject("move").getString("name"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.isSaved = false;

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

    public void setSaved(boolean saved) {
        isSaved = true;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(spriteURL);
        dest.writeString(type1);
        dest.writeString(type2);
        dest.writeInt(ID);
        dest.writeSerializable(moves);
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteURL = in.readString();
        type1 = in.readString();
        type2 = in.readString();
        ID = in.readInt();
        moves = (ArrayList<String>) in.readSerializable();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel source) {
            return new Pokemon(source);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };
}
