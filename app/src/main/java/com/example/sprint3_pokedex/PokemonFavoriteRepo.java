package com.example.sprint3_pokedex;

import java.util.ArrayList;

public class PokemonFavoriteRepo {
    static ArrayList<Pokemon> pokemons = new ArrayList<>();

    public static void addToList(Pokemon pokemon){
        pokemons.add(pokemon);
    }

    public static void removeFromList(int num){
        for(int i = 0; i < pokemons.size(); i++){
            if(pokemons.get(i).getNumber() == num){
                pokemons.remove(num);
            }
        }
    }

    public static Pokemon getPokemon(int num){
        for(int i = 0; i < pokemons.size(); i++){
            if(pokemons.get(i).getNumber() == num){
                return  pokemons.get(i);
            }
        }
        return null;
    }
}
