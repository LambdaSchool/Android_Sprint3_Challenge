package com.example.Pokedex;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Pokemon implements Parcelable {

    private ArrayList<String> moves = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private Bitmap image;
    private String name;

    protected Pokemon(Parcel in) {
        moves = in.createStringArrayList();
        types = in.createStringArrayList();
        image = in.readParcelable(Bitmap.class.getClassLoader());
        name = in.readString();
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

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pokemon(String pokeName, JSONArray pokeMoves, JSONArray pokeTypes, String pokeImageUrl) {
        this.name = pokeName;

        //Unwrapping jsonArrays
        for (int i = 0; i < pokeMoves.length(); ++i) {

            try {
                this.moves.add (pokeMoves.getJSONObject(i).getJSONObject("move").getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        this.image = NetworkAdapter.httpImageRequest(pokeImageUrl);

        for (int i = 0; i < pokeTypes.length(); ++i) {

            try {
                this.types.add (pokeTypes.getJSONObject(i).getJSONObject("type").getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(moves);
        dest.writeStringList(types);
        dest.writeParcelable(image, flags);
        dest.writeString(name);
    }
}
