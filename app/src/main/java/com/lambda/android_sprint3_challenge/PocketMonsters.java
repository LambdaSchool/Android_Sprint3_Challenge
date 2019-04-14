package com.lambda.android_sprint3_challenge;


import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class PocketMonsters  {
    private ArrayList<Pokemon> alPokemon;
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964";
    private static final String READ_ALL_URL = BASE_URL;

    public PocketMonsters(Pokemon pokemon) {
        alPokemon = new ArrayList<>( 1 );
        alPokemon.add( pokemon );
    }
    public PocketMonsters() {
        alPokemon = new ArrayList<>( 1 );

    }

    public void add(Pokemon pokemon) {
        alPokemon.add( pokemon );
    }

    public Pokemon findByID(String strID) {
        for(int i=0;i<size();i++){
            if(this.alPokemon.get( i ).getID().equals( strID ))return this.alPokemon.get(i);
        }
        return null;

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
    public ArrayList<Pokemon> findByPartialName(String strPartialName){
        ArrayList<Pokemon> found=new ArrayList<Pokemon>(  );
        for(int i=0;i<size();i++){
            if(alPokemon.get( i ).getName().contains( strPartialName)){
                found.add( alPokemon.get(i) );
            }
        }



        return found;
    }

}