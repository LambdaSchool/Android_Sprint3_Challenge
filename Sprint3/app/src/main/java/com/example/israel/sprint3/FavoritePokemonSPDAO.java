package com.example.israel.sprint3;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritePokemonSPDAO {

    private static final String NAME_SP_FAVORITE_POKEMONS = "favorite_pokemons";
    private static final String KEY_SP_FAVORITE_POKEMON_SET = "favorite_pokemons";

    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(NAME_SP_FAVORITE_POKEMONS, Context.MODE_PRIVATE);
    }

    public static Set<String> getFavoritePokemons(Context context) {
        return getSP(context).getStringSet(KEY_SP_FAVORITE_POKEMON_SET, new HashSet<String>());
    }

    public static boolean isFavoritePokemon(Context context, String pokemonName) {
        Set<String> favoritePokemonSet = getFavoritePokemons(context);

        for (String favoritePokemonName : favoritePokemonSet) {
            if (favoritePokemonName.equals(pokemonName)) {
                return true;
            }
        }

        return false;
    }

    public static void addFavoritePokemon(Context context, String pokemonName) {
        SharedPreferences sp = getSP(context);
        Set<String> favoritePokemonSet = sp.getStringSet(KEY_SP_FAVORITE_POKEMON_SET, new HashSet<String>());

        favoritePokemonSet.add(pokemonName);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(KEY_SP_FAVORITE_POKEMON_SET);
        editor.apply();
        editor.putStringSet(KEY_SP_FAVORITE_POKEMON_SET, favoritePokemonSet);
        editor.apply();

    }

    public static void removeFavoritePokemon(Context context, String pokemonName) {
        SharedPreferences sp = getSP(context);
        Set<String> favoritePokemonSet = sp.getStringSet(KEY_SP_FAVORITE_POKEMON_SET, new HashSet<String>());

        favoritePokemonSet.remove(pokemonName);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(KEY_SP_FAVORITE_POKEMON_SET);
        editor.apply();
        editor.putStringSet(KEY_SP_FAVORITE_POKEMON_SET, favoritePokemonSet);
        editor.apply();

    }

}
