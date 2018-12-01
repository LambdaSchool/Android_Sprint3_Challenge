package com.thadocizn.sprintchallengethree;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon  implements Parcelable{

        public int id;
        public String name;
        public String spriteUrl;
        public String[] abilities;
        public String[] types ;
        private Bitmap imagePokemon;

    public Pokemon(int id, String name, String[] abilities, String spriteUrl, String[] types) {
        this.id = id;
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.abilities = abilities;
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
            JSONObject jsonSprites = jsonObject.getJSONObject("sprites");
            this.spriteUrl = jsonSprites.getString("front_default");
        }catch (JSONException e){
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
            JSONArray jsonTypes = jsonObject.getJSONArray("types");
            this.types = new String[jsonTypes.length()];
            for (int i = 0; i <jsonTypes.length() ; i++) {
                this.types[1] = jsonTypes.getString(i);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    protected Pokemon(Parcel in) {
        id = in.readInt();
        name = in.readString();
        abilities = in.createStringArray();
        spriteUrl = in.readString();
        types = in.createStringArray();
        imagePokemon = in.readParcelable(Bitmap.class.getClassLoader());

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeArray(abilities);
        dest.writeString(spriteUrl);
        dest.writeArray(types);
        dest.writeParcelable(imagePokemon, flags);
    }
}
