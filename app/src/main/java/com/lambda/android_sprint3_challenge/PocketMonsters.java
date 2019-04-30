package com.lambda.android_sprint3_challenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PocketMonsters implements Parcelable {
    private ArrayList<Pokemon> alPokemon;
    private static final String strURL_QUERY = "?offset=0&limit=964";
    private static final String strURL="https://pokeapi.co/api/v2/pokemon/";
    private static final String strURL_READ_ALL = strURL+strURL_QUERY;
    private static SharedPreferences preferences;
    private SharedPreferences.Editor spEditor;
    private static final String strTitle="PocketMonsters";
    private static final String strIndexKey="PM_INDEX";
    private static final String strDataKey="PM_DATA_";

    public PocketMonsters(Pokemon pokemon) {
        alPokemon = new ArrayList<>( 1 );
        alPokemon.add( pokemon );
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public PocketMonsters(Context context) {
        if(this.preferences==null) {
            this.preferences = context.getApplicationContext().getSharedPreferences( strTitle, MODE_PRIVATE );
            spEditor = preferences.edit();
            //Delete preference data
            if(strDataKey.equals( "" )&&strIndexKey.equals( "" )){
                deleteDataInPreference();
            }

            String strRetrieved = preferences.getString( strIndexKey, "" );
            if (strRetrieved.equals( "" )) {//No data in preference
                writeDataInPreference(obtainFromAPI(context));

            } else {
                readDataInPreference(strRetrieved);

            }
        }

    }
    //20190417 writhe data in preference
    public void deleteDataInPreference(){
        spEditor.clear(); //to erase preference
        spEditor.commit();//to erase preference
        return;
    }

    //20190417 writhe data in preference
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void writeDataInPreference(PocketMonsters pm){
        String strIndex = "";
        if(pm.size()==0)return;//No data from API

        strIndex += "0,";
        spEditor.putString( strDataKey + "0", pm.alPokemon.get(0).toCSV() );
        spEditor.apply();
        for (int i = 1; i < size(); i++) {
            if (i == size() - 1) {
                strIndex += Integer.toString( i );
            } else {
                strIndex += Integer.toString( i ) + ",";
            }
            spEditor.putString( strDataKey + Integer.toString( i ), pm.alPokemon.get(i).toCSV() );
            spEditor.apply();
        }

        spEditor.putString( strIndexKey, strIndex ); //Write all the index in a list
        spEditor.apply();
    }


    //20190417 read data in preference
    public PocketMonsters readDataInPreference(String strIndex){
        String[] strarIndex = strIndex.split( "," );
        for (int i = 0; i < strarIndex.length; i++) {
            String strRetrieved = preferences.getString(strDataKey + strarIndex[i], "" );

            if (strRetrieved.equals( "" )) {
            } else {
                if(alPokemon==null)alPokemon=new ArrayList<>(1);
                Pokemon pk=new Pokemon(strRetrieved);
                alPokemon.add(pk);
            }
        }
        return this;
    }

    public void updateFromPreference(String strRetrieved){


    }

    //20190417 obrtain data from API
    public PocketMonsters obtainFromAPI(Context context) {
        alPokemon = new ArrayList<>( 1 );
        if(NetworkAdapter.isInternetConnected( context )){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //  pocketMonsters.obtainEveryoneFromAPI();


                    String strDebug="";
                    final String result = NetworkAdapter.httpRequest( strURL_READ_ALL );
                    String[] strNames=result.split( "," );
                    String name="", id="";
                    for(int i=3;i<strNames.length-1;i+=2){
                        if(i==3){
                            name=strNames[i].split("\""  )[5];
                        }else{
                            name=strNames[i].split("\""  )[3];
                        }
                        id=((strNames[i+1].split("\""  )[3]).replace( strURL,"" )).replace( "/","" );
                        Pokemon pk=new Pokemon(name,id);
                        if(alPokemon==null)                            alPokemon=new ArrayList<Pokemon>(1);
                        alPokemon.add(pk);
                    }
                }
            }).start();
        }
        return this;
    }
    public PocketMonsters(ArrayList<Pokemon> alPokemon) {
        this.alPokemon = alPokemon;
    }

    protected PocketMonsters(Parcel in) {
        alPokemon = in.createTypedArrayList( Pokemon.CREATOR );
    }

    public static final Creator<PocketMonsters> CREATOR = new Creator<PocketMonsters>() {
        @Override
        public PocketMonsters createFromParcel(Parcel in) {
            return new PocketMonsters( in );
        }

        @Override
        public PocketMonsters[] newArray(int size) {
            return new PocketMonsters[size];
        }
    };

    public void add(Pokemon pokemon) {
        alPokemon.add( pokemon );
    }

    public Pokemon findByID(String strID) {
        for(int i=0;i<size();i++){
            if(this.alPokemon.get( i ).getID().equals( strID ))return this.alPokemon.get(i);
        }
        return null;

    }

    public ArrayList<Pokemon> getAlPokemon() {
        return alPokemon;
    }

    public void setAlPokemon(ArrayList<Pokemon> alPokemon) {
        this.alPokemon = alPokemon;
    }

    public Pokemon findByIDandNameString(String strIDandName) {
        String[] strTemp = strIDandName.split( "," );
        Pokemon found;
        found = findByID( strTemp[0] );
        if (found.getName().equals( strTemp[1].replace( "\n", "" ) )) {
            return found;
        } else {
            return null;
        }
    }

    public int size() {
        return alPokemon.size();
    }

    public PocketMonsters update(Pokemon pk) {
        int i=0;
        for(;i<size();i++){
            if(this.alPokemon.get( i ).getID().equals( pk.getID() )&&
                    this.alPokemon.get( i ).getName().equals( pk.getName()))break;
        }

        this.alPokemon.set(i,pk );
        return this;
    }

    public PocketMonsters update(PocketMonsters pm){
        Pokemon pk=null;
        for(int i=0;i<pm.size();i++){
            this.update( pm.getAlPokemon().get( i ) );
        }

        return this;
    }

    public ArrayList<Pokemon> findByPartialName(String strPartialName){
        ArrayList<Pokemon> found=new ArrayList<Pokemon>(  );
        for(int i=0;i<size();i++){
            if(alPokemon.get( i ).getName().contains( strPartialName)){
                found.add( alPokemon.get(i) );
            }
        }
        return found;
    }

    public ArrayList<Pokemon> findSaved(){
        ArrayList<Pokemon>alpk=new ArrayList<>( 1 );
        for(int i=0;i<size();i++){
            if(alPokemon.get( i ).isSaved()==true){
                alpk.add( alPokemon.get(i) );
            }
        }
        return alpk;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList( alPokemon );
    }
}