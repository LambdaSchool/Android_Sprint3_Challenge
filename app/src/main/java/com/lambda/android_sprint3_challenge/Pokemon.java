package com.lambda.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pokemon implements Parcelable {
    private String name, spriteUrl, ID, abilities, types;
    private Bitmap bitmap;
    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Pokemon(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.spriteUrl = "";
        this.abilities = "";
        this.types = "";
    }

    public Pokemon(String name, String ID, String abilities, String types, String spriteUrl) {
        this.name = name;
        this.ID = ID;
        this.spriteUrl = spriteUrl;

        this.abilities = abilities;
        this.types = types;

    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteUrl = in.readString();
        ID = in.readString();
        abilities = in.readString();
        types = in.readString();
        bitmap = in.readParcelable( Bitmap.class.getClassLoader() );
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon( in );
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

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public String getID() {
        return ID;
    }
    public int getIntID() {
        //assumed integer ID in PocketMonsters
        return Integer.parseInt( this.ID)-1;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( name );
        dest.writeString( spriteUrl );
        dest.writeString( ID );
        dest.writeString( abilities );
        dest.writeString( types );
        dest.writeParcelable( bitmap, flags );
    }
}
