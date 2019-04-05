package com.example.israel.sprint3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonAbilitiesAdapter extends RecyclerView.Adapter<PokemonAbilitiesAdapter.ViewHolder> {

    public PokemonAbilitiesAdapter(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    private ArrayList<String> abilities;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon_ability_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String ability = abilities.get(i);

        viewHolder.abilityTextView.setText(ability);
    }

    @Override
    public int getItemCount() {
        return abilities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            abilityTextView = itemView.findViewById(R.id.text_view_ability);
        }

        TextView abilityTextView;
    }
}
