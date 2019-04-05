package com.example.israel.sprint3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritePokemonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pokemons);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_favorite_pokemons);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> favoritePokemons = new ArrayList<>(FavoritePokemonSPDAO.getFavoritePokemons(this));
        FavoritePokemonsAdapter favoritePokemonsAdapter = new FavoritePokemonsAdapter(favoritePokemons);
        recyclerView.setAdapter(favoritePokemonsAdapter);
    }
}
