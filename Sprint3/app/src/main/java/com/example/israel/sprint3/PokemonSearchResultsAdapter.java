package com.example.israel.sprint3;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonSearchResultsAdapter extends RecyclerView.Adapter<PokemonSearchResultsAdapter.ViewHolder> {

    public PokemonSearchResultsAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private MainActivity mainActivity;
    private ArrayList<String> pokemonNames = new ArrayList<>();

    public void setPokemonNames(ArrayList<String> pokemonNames) {
        this.pokemonNames = pokemonNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_search_result_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final String pokemonName = pokemonNames.get(i);

        viewHolder.pokemonNameTextView.setText(pokemonName);

        // set bg color if favorite
        if (FavoritePokemonSPDAO.isFavoritePokemon(mainActivity, pokemonName)) {
            viewHolder.pokemonNameTextView.setBackgroundColor(Color.argb(100, 255, 255, 0));
        } else {
            viewHolder.pokemonNameTextView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        }

        viewHolder.pokemonNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.openPokemonDetails(pokemonName);
            }
        });

        viewHolder.pokemonNameTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // add/remove from favorites
                if (FavoritePokemonSPDAO.isFavoritePokemon(mainActivity, pokemonName)) {
                    FavoritePokemonSPDAO.removeFavoritePokemon(mainActivity, pokemonName);
                } else {
                    FavoritePokemonSPDAO.addFavoritePokemon(mainActivity, pokemonName);
                }

                notifyItemChanged(viewHolder.getAdapterPosition());

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemonNameTextView = itemView.findViewById(R.id.text_view_pokemon_name);
        }

        TextView pokemonNameTextView;
    }
}
