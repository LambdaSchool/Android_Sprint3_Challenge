package com.example.joshh.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon implements Parcelable {
    private String name, spriteUrl,id;
    private String [] moves, types;
    private Bitmap bitmap;
    private boolean isFavorite;

    public Pokemon(JSONObject jsonObject){
        this.isFavorite = false;
        try {
            this.name = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.spriteUrl = jsonObject.getString("spriteUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.id = jsonObject.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonSprites = jsonObject.getJSONObject("sprites");
            this.spriteUrl = jsonSprites.getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonMoves = jsonObject.getJSONArray("moves");
            this.moves = new String[jsonMoves.length()];
            for(int i = 0; i < jsonMoves.length(); i++){
                JSONObject moves = jsonMoves.getJSONObject(i);
                JSONObject move = moves.getJSONObject("move");
                String name = move.getString("name");
                this.moves[i] = name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonTypes = jsonObject.getJSONArray("types");
            this.types = new String[jsonTypes.length()];
            for(int i = 0; i < jsonTypes.length(); i++){
                JSONObject types = jsonTypes.getJSONObject(i);
                JSONObject type = types.getJSONObject("type");
                String name = type.getString("name");
                this.types[i] = name + ", ";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteUrl = in.readString();
        id = in.readString();
        moves = in.createStringArray();
        types = in.createStringArray();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public String getId() {
        return id;
    }

    public String[] getMoves() {
        return moves;
    }

    public String[] getTypes() {
        return types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(spriteUrl);
        dest.writeString(id);
        dest.writeStringArray(moves);
        dest.writeStringArray(types);
        dest.writeParcelable(bitmap, flags);
    }
}