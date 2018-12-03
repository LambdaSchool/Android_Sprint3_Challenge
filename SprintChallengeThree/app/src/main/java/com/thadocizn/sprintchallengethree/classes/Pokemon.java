package com.thadocizn.sprintchallengethree.classes;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon  implements Parcelable{

        private int id;
        private String name;
        private String spriteUrl;
        private String[] abilities;
        private String[] types ;
        private Bitmap imagePokemon;

   /* public Pokemon(int id, String name, String[] abilities, String spriteUrl, String[] types) {
        this.id = id;
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.abilities = abilities;
        this.types = types;
    }*/

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
                this.types[i] = jsonTypes.getString(i);
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

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public Bitmap getImagePokemon() {
        return imagePokemon;
    }

    public void setImagePokemon(Bitmap imagePokemon) {
        this.imagePokemon = imagePokemon;
    }

    public static Creator<Pokemon> getCREATOR() {
        return CREATOR;
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
