package com.lambda.android_sprint3_challenge;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pokemon implements Parcelable {
    private String name, spriteUrl, strID, abilities, types;
    private Bitmap bitmap;
    private int index;
    private boolean bSaved;

    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteUrl = in.readString();
        strID = in.readString();
        abilities = in.readString();
        types = in.readString();
        bitmap = in.readParcelable( Bitmap.class.getClassLoader() );
        bSaved = in.readByte() != 0;
    }

    public  Pokemon(String strCSV ){

        readCSV(strCSV);
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

    public boolean isSaved() {
        return bSaved;
    }

    public void setSaved(boolean saved) {
        this.bSaved = saved;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Pokemon(String name, String ID) {
        this.name = name;
        this.strID = ID;
        this.spriteUrl = "";
        this.abilities = "";
        this.types = "";
    }

    public Pokemon(String name, String ID, String abilities, String types, String spriteUrl) {
        this.name = name;
        this.strID = ID;
        this.spriteUrl = spriteUrl;

        this.abilities = abilities;
        this.types = types;

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

    public String getID() {
        return strID;
    }
    public int getIntID() {
        //assumed integer ID in PocketMonsters
        return Integer.parseInt( this.strID)-1;
    }

    public void setID(String ID) {
        this.strID = ID;
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
        dest.writeString( strID );
        dest.writeString( abilities );
        dest.writeString( types );
        dest.writeParcelable( bitmap, flags );
        dest.writeByte( (byte) (bSaved ? 1 : 0) );
    }

    //20190418 Make CSV for preferences
    public String toCSV(){
        String strCSV=name;
        strCSV+=",";
        strCSV+=spriteUrl;
        strCSV+=",";
        strCSV+=strID;
        strCSV+=",";
        strCSV+=abilities;
        strCSV+=",";
        strCSV+=types;
        strCSV+=",";
        strCSV+=Boolean.toString(bSaved);
//        strCSV+=bitmap;
//        strCSV+=",";

        return strCSV;
    }

    public void readCSV(String strCSV){
        String[] strarCSV=strCSV.split(",");
        int len=strarCSV.length;
        if(len==6){
            name=strarCSV[0];
            spriteUrl=strarCSV[1];
            strID=strarCSV[2];
            abilities=strarCSV[3];
            types=strarCSV[4];
            bSaved=Boolean.valueOf(strarCSV[5]);
            //            bitmap=strarCSV[5];
        }else{
            name=strarCSV[0];

            spriteUrl=strarCSV[1];
            strID=strarCSV[2];
            abilities=strarCSV[3];
            for(int i=4;i<len-2;i++){
                abilities+=strarCSV[i];
            }
            types=strarCSV[len-2];
            bSaved=Boolean.valueOf(strarCSV[len-1]);
        }

    }

    public Bitmap getBitmap(){
        if(bitmap==null){
            new Thread( new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap = NetworkAdapter.getBitmapFromUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + strID + ".png");
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            } ).start();


        }
        this.setSpriteUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + strID + ".png");
        this.setBitmap( bitmap );
        return bitmap;

    }
}

