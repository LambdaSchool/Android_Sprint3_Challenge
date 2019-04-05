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

    public static Pokemon getPokemonByName(String name){
        name = name.toLowerCase();
        for(int i = 0; i < pokemons.size(); i++){
            if(pokemons.get(i).getName().equals(name)){
               return pokemons.get(i);
            }
        }
        return null;
    }

    public static String[] getPokeNames(){
        String[] names = new String[pokemons.size()];
        for(int i = 0; i < pokemons.size(); i++){
            names[i] = pokemons.get(i).getName();
        }
        return names;
    }

    public static Pokemon getPokemon(int num){
        for(int i = 0; i < pokemons.size(); i++){
            if(pokemons.get(i).getNumber() == num){
                return  pokemons.get(i);
            }
        }
        return null;
    }

    public static void removeFromListByName(String name) {
        name = name.toLowerCase();
        for(int i = 0; i < pokemons.size(); i++){
            if(pokemons.get(i).getName().equals(name)){
                removeFromList(pokemons.get(i).getNumber());
            }
        }
    }
}
