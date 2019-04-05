package com.lambdaschool.android_sprint3_challenge;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Pokemon implements Parcelable {
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
            this.name = "";
        }
        try {
            this.id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
            this.id = -1;
        }
        try {
            this.height = jsonObject.getInt("height");
        } catch (JSONException e) {
            e.printStackTrace();
            this.height = -1;
        }
        try {
            this.weight = jsonObject.getInt("weight");
        } catch (JSONException e) {
            e.printStackTrace();
            this.weight = -1;
        }
        try {
            this.sprite = jsonObject.getJSONObject("sprites").getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
            this.sprite = "";
        }
        try {
            this.abilities = new ArrayList<>();
            for (int i = 0; i < jsonObject.getJSONArray("abilities").length(); i++) {
                try {
                    this.abilities.add(jsonObject.getJSONArray("abilities").getJSONObject(i).getJSONObject("ability").getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.abilities.add("");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.moves = new ArrayList<>();
            for (int i = 0; i < jsonObject.getJSONArray("moves").length(); i++) {
                try {
                    this.moves.add(jsonObject.getJSONArray("moves").getJSONObject(i).getJSONObject("move").getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.moves.add("");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.types = new ArrayList<>();
            for (int i = 0; i < jsonObject.getJSONArray("types").length(); i++) {
                try {
                    this.types.add(jsonObject.getJSONArray("types").getJSONObject(i).getJSONObject("type").getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.types.add("");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        id = in.readInt();
        height = in.readInt();
        weight = in.readInt();
        sprite = in.readString();
        abilities = in.createStringArrayList();
        moves = in.createStringArrayList();
        types = in.createStringArrayList();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeString(sprite);
        dest.writeStringList(abilities);
        dest.writeStringList(moves);
        dest.writeStringList(types);
    }
}
