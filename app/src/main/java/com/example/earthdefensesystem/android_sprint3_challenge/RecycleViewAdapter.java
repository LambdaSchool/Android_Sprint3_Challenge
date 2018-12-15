package com.example.earthdefensesystem.android_sprint3_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

//public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView pokemonName;
//        ImageView pokemonSprite;
//        ViewGroup parentView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            pokemonName = itemView.findViewById(R.id.pokemonListName);
//            pokemonSprite = itemView.findViewById(R.id.pokemonListSprite);
//            parentView = itemView.findViewById(R.id.recyclerView);
//        }
//    }
//
//    private Context context;
//    private Activity activity;
//    private List<Pokemon> pokemonList;
//
//    public RecycleViewAdapter(List<Pokemon> pokemonList, Activity activity) {
//        this.activity = activity;
//        this.pokemonList = pokemonList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        context = viewGroup.getContext();
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.pokemon_list, viewGroup, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder viewHolder, int i) {
//
//        Pokemon pokemon = pokemonList.get(i);
//
////      viewHolder.pokemonSprite.setImageDrawable(pokemon.getSprites);
//        viewHolder.pokemonName.setText(pokemon.getName());
//
//        viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, PokemonDetails.class);
////                activity.startActivityForResult(intent, );
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return pokemonList.size();
//    }
//}
