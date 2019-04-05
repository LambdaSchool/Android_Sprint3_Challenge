package com.rybarstudios.pokemon;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon implements Parcelable {
    private String name, spriteUrl;
    private String[] abilities, types;
    private int id;
    private Bitmap image;

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
            this.spriteUrl = jsonObject.getString("sprites");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonSprite = jsonObject.getJSONObject("sprites");
            this.spriteUrl = jsonSprite.getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonAbilities = jsonObject.getJSONArray("abilities");
            this.abilities = new String[jsonAbilities.length()];
            for (int i = 0; i < jsonAbilities.length(); i++) {
                JSONObject abilities = jsonAbilities.getJSONObject(i);
                JSONObject ability = abilities.getJSONObject("ability");
                String name = ability.getString("name");
                this.abilities[i] = name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonTypes = jsonObject.getJSONArray("types");
            this.types = new String[jsonTypes.length()];
            for (int i = 0; i < jsonTypes.length(); i++) {
                JSONObject types = jsonTypes.getJSONObject(i);
                JSONObject type = types.getJSONObject("type");
                String name = type.getString("name");
                this.types[i] = name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteUrl = in.readString();
        abilities = in.createStringArray();
        types = in.createStringArray();
        id = in.readInt();
        image = in.readParcelable(Bitmap.class.getClassLoader());
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

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public String[] getTypes() {
        return types;
    }

    public int getId() {
        return id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(spriteUrl);
        dest.writeStringArray(abilities);
        dest.writeStringArray(types);
        dest.writeInt(id);
        dest.writeParcelable(image, flags);
    }
}
