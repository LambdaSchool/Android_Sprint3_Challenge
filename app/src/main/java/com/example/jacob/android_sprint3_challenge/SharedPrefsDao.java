/*
package com.example.jacob.android_sprint3_challenge;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class SharedPrefsDao {
    private static final String KEY_IDS       = "key_ids";
    private static final String KEY_ID_PREFIX = "key_id_";
    private static final String NEXT_KEY_ID   = "key_next_id";

    private static String getIdsString() {
        String keyIds = "";
        if (MainActivity.preferences != null) {
            keyIds = MainActivity.preferences.getString(KEY_IDS, "");
        }
        return keyIds;
    }

    private static String[] getAllIds() {
        // keys are stored as CSV string
        final String[] ids = getIdsString().split(",");
        return ids;
    }

    public static ArrayList<Pokemon> getAllPokemons() {
        String[] ids = getAllIds();
        ArrayList<Pokemon> pokemons = new ArrayList<>(ids.length);
        for(String id: ids) {
            if(!id.equals("")) {
                pokemons.add(getPokemon(id));
            }
        }
        return pokemons;
    }

    private static Pokemon getPokemon(String id) {
        Pokemon pokemon = null;
        if (MainActivity.preferences != null) {
            final String pokemonString = MainActivity.preferences.getString(KEY_ID_PREFIX + id, "");
            pokemon = new Pokemon(pokemonString);
        }
        return pokemon;
    }

    private static int getNextId() {
        int currentId = 0;
        if (MainActivity.preferences != null) {
            currentId = MainActivity.preferences.getInt(NEXT_KEY_ID, 0);
            int                      nextId = currentId + 1;
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putInt(NEXT_KEY_ID, nextId);
            editor.apply();
        }
        return currentId;
    }

    public static void setPokemon(Pokemon pokemon) {
        if (pokemon.getId() == Pokemon.NO_ID) {
            pokemon.setId(getNextId());
        }
        String[] ids    = getAllIds();
        boolean  exists = false;
        for (String id : ids) {
            if(!id.equals("")) {
                if (pokemon.getId() == Integer.parseInt(id)) {
                    exists = true;
                    break;
                }
            }
        }

        if (!exists) {
            addId(pokemon.getId());
        }

        addPokemon(pokemon);
    }

    private static void addPokemon(Pokemon pokemon) {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(KEY_ID_PREFIX + pokemon.getId(), pokemon.toCsvString());
        editor.apply();
    }

    private static void addId(int id) {
        String idsString = getIdsString();
        idsString = idsString + "," + id;
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(KEY_IDS, idsString.replace(" ", ""));
        editor.apply();
    }
}
*/
