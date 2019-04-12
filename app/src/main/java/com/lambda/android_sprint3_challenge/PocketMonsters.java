package com.lambda.android_sprint3_challenge;


import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class PocketMonsters implements Serializable {
    ArrayList<Pokemon> alPokemon;
    private static final String BASE_URL       = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964";
    private static final String READ_ALL_URL = BASE_URL ;
    public PocketMonsters(Pokemon pokemon){
        alPokemon=new ArrayList<>( 1 );
        alPokemon.add( pokemon );
    }
    public void add(Pokemon pokemon){
        alPokemon.add( pokemon );
    }

    public Pokemon findByID(String strID){
        int iID=0;

        try{
                iID=Integer.parseInt( strID );
                if(iID<1)return null;
        }catch (NumberFormatException nFE) {
                return null;
        }

       return this.alPokemon.get( iID-1);

    }

    public void obtainEveryoneFromAPI(){
        final String result = NetworkAdapter.httpRequest( READ_ALL_URL );
        String[] strNames=result.split( "," );
        String name="", id="";
        for(int i=0;i<strNames.length;i++){

            Pokemon pk=new Pokemon(name,id);
            alPokemon.add( pk );
        }

    }

    public Pokemon findByIDandNameString(String strIDandName){
        String[] strTemp=strIDandName.split( "," );
        Pokemon found;
        found=findByID(strTemp[0]);
        if(strTemp[1].equals( found.getName() )){
                return found;
        }else{
            return null;
        }


    }

}
