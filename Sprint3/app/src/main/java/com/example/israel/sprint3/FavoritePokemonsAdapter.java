package com.example.israel.sprint3;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritePokemonsAdapter extends RecyclerView.Adapter<FavoritePokemonsAdapter.ViewHolder> {

    public FavoritePokemonsAdapter(ArrayList<String> favoritePokemons) {
        this.favoritePokemons = favoritePokemons;
    }

    private ArrayList<String> favoritePokemons;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_search_result_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final String pokemonName = favoritePokemons.get(i);

        final Context context = viewHolder.pokemonNameTextView.getContext();
        viewHolder.pokemonNameTextView.setText(pokemonName);

        viewHolder.pokemonNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokemonDetailsController.openPokemonDetails(v.getContext(), pokemonName);
            }
        });

        viewHolder.pokemonNameTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // add/remove from favorites
                if (FavoritePokemonSPDAO.isFavoritePokemon(context, pokemonName)) {
                    FavoritePokemonSPDAO.removeFavoritePokemon(context, pokemonName);
                } else {
                    FavoritePokemonSPDAO.addFavoritePokemon(context, pokemonName);
                }

                // update recycler view
                favoritePokemons.clear();
                favoritePokemons.addAll(FavoritePokemonSPDAO.getFavoritePokemons(context));
                notifyDataSetChanged();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoritePokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonNameTextView = itemView.findViewById(R.id.text_view_pokemon_name);
        }

        TextView pokemonNameTextView;
    }
}
