/*
package com.example.jacob.android_sprint3_challenge;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class PokemonRepository {

    public MutableLiveData<ArrayList<Pokemon>> getPokemons() {
        MutableLiveData<ArrayList<Pokemon>> liveDataList = new MutableLiveData<>();
        liveDataList.setValue(SharedPrefsDao.getAllPokemons());
        return liveDataList;
    }

    public ArrayList<Pokemon> addPokemon(Pokemon pokemon) {
        SharedPrefsDao.setPokemon(pokemon);
        return SharedPrefsDao.getAllPokemons();
    }
}
*/
