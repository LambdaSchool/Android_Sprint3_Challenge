package com.example.israel.sprint3;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class PokemonNamesRepository {

    private static ArrayList<String> pokemonNames = new ArrayList<>();

    public static void downloadPokemonNames() {
        pokemonNames = PokemonNetworkDAO.getPokemonNames();
    }

    public static ArrayList<String> getPokemonNames() {
        return pokemonNames;
    }

    @NonNull
    public static ArrayList<String> searchPokemonByName(String inPokemonName) {
        ArrayList<String> outPokemonNames = new ArrayList<>();
        if (inPokemonName == null) {
            return outPokemonNames;
        }

        if (inPokemonName.length() == 0) {
            return outPokemonNames;
        }

        int inPokemonNameLength = inPokemonName.length();
        for (String pokemonName : pokemonNames) {
            if (pokemonName.length() < inPokemonNameLength) {
                continue;
            }

            boolean match = true;
            for (int i = 0; i < inPokemonName.length(); ++i) {
                if (inPokemonName.charAt(i) != pokemonName.charAt(i)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                outPokemonNames.add(pokemonName);
            }
        }

        return outPokemonNames;
    }

}
