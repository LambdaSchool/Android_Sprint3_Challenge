package com.example.israel.sprint3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonSearchResultsAdapter extends RecyclerView.Adapter<PokemonSearchResultsAdapter.ViewHolder> {

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String pokemonName = pokemonNames.get(i);

        viewHolder.pokemonNameTextView.setText(pokemonName);

        viewHolder.pokemonNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO pokemon details
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
