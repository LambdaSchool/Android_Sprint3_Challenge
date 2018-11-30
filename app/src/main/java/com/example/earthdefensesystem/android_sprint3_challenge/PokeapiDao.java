package com.example.earthdefensesystem.android_sprint3_challenge;

import java.util.ArrayList;

public class PokeapiDao {
    public static final  String BaseUrl = "https://pokeapi.co/api/v2/pokemon/";



    public static ArrayList<Pokemon> getPokemon(){
        ArrayList<Pokemon> pokemon = new ArrayList<>();
        String url = NetworkAdapter.httpGetRequest(BaseUrl);
    }
}
