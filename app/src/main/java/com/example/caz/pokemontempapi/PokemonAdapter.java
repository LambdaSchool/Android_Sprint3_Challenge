package com.example.caz.pokemontempapi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>{

    List<Pokemon> pokemonList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemone_layout, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Pokemon pokemonData = pokemonList.get(position);

        viewHolder.nameView.setText(pokemonData.getName());
    //    viewHolder.spriteView.                                    // figure out how to do spriteview
        viewHolder.idView.setText(pokemonData.getId());
        viewHolder.abilitiesView.setText(pokemonData.getAbilities());
        viewHolder.typesView.setText(pokemonData.getTypes());

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

// Inside this class, we’ll have another class for the ViewHolder thus

    static class ViewHolder extends RecyclerView.ViewHolder{


        TextView nameView;
        ImageView spriteView;
        TextView idView;
        TextView abilitiesView;
        TextView typesView;
        LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            linearLayout = itemView.findViewById(R.id.linear_layout);
            nameView = itemView.findViewById(R.id.name_view);
            spriteView = itemView.findViewById(R.id.sprite_view);
            idView = itemView.findViewById(R.id.id_view);
            abilitiesView = itemView.findViewById(R.id.abilities_view);
            typesView = itemView.findViewById(R.id.types_view);


        }
    }

}

