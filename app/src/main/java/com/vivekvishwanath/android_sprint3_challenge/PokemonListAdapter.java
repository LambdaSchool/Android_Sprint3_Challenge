package com.vivekvishwanath.android_sprint3_challenge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    ArrayList<Pokemon> searchedPokemon;
    Context context;

    public PokemonListAdapter(ArrayList<Pokemon> searchedPokemon) {
        this.searchedPokemon = searchedPokemon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.search_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Pokemon pokemon = searchedPokemon.get(position);
        holder.pokemonName.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return searchedPokemon.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout pokemonListLayout;
        TextView pokemonName;

        public ViewHolder(View itemView) {
            super(itemView);

            pokemonListLayout = itemView.findViewById(R.id.pokemon_list_layout);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
        }
    }
}
