package com.thadocizn.sprintchallengethree.classes;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Pokemon  implements Parcelable{

        private int id;
        private String name;
        private String spriteUrl;
        private String[] abilities;
        private String[] types ;
        private Bitmap imagePokemon;
        private boolean favorite;

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public Pokemon(JSONObject jsonObject) {
        this.favorite = false;

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
            for (int i = 0; i <jsonAbilities.length() ; ++i) {
                JSONObject abilities = jsonAbilities.getJSONObject(i);
                JSONObject ability = abilities.getJSONObject("ability");
                String name = ability.getString("name");
                this.abilities[i] = name;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        try {
            JSONArray jsonTypes = jsonObject.getJSONArray("types");
            this.types = new String[jsonTypes.length()];
            for (int i = 0; i <jsonTypes.length() ; i++) {
                JSONObject types = jsonTypes.getJSONObject(i);
                JSONObject type = types.getJSONObject("type");
                String name = type.getString("name");
                this.types[i] = name;
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

    public String[] getAbilities() {
        return abilities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Bitmap getImagePokemon() {
        return imagePokemon;
    }

    public void setImagePokemon(Bitmap imagePokemon) {
        this.imagePokemon = imagePokemon;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeStringArray(abilities);
        dest.writeString(spriteUrl);
        dest.writeStringArray(types);
        dest.writeParcelable(imagePokemon, flags);
    }
}
