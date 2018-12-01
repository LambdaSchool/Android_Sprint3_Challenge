/*
package com.example.jacob.android_sprint3_challenge;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class PokemonViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Pokemon>> pokemonList;
    private PokemonRepository repo;

    public LiveData<ArrayList<Pokemon>> getPokemonsList() {
        if(pokemonList == null) {
            loadList();
        }
        return pokemonList;
    }

    private void loadList() {
        repo = new PokemonRepository();
        pokemonList = repo.getPokemons();
    }

    public void addPokemon(Pokemon pokemon) {
        if(pokemonList != null) {
            pokemonList.setValue(repo.addPokemon(pokemon));
        }
    }
}
*/
