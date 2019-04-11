package com.example.Pokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.Pokedex.MainActivity.POKEMON_NUMBER_EXTRA;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> { //NOT IMPLEMENTED
    private ArrayList<String> pokeList;

    public ListAdapter(ArrayList<String> pokeList) {
        this.pokeList = pokeList;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_view_single_element, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.MyViewHolder myViewHolder, int i) {


        java.lang.String pokeViewText = (i + 1) + " " + pokeList.get(i);
        myViewHolder.pokeName.setText(pokeViewText);
        myViewHolder.pokeImage.setImageDrawable(myViewHolder.pokeImage.getContext().getDrawable(R.drawable.pokeball));
        final java.lang.String index = java.lang.String.valueOf(i + 1);
        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            private Context context;
            @Override
            public void onClick(View v) {
                context = myViewHolder.parent.getContext();
                Intent intent = new Intent(context, DetailView.class);
                intent.putExtra(java.lang.String.valueOf(POKEMON_NUMBER_EXTRA), index);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) v.getContext(), myViewHolder.pokeImage, "pokename");
                context.startActivity(intent, options.toBundle());
            }
        });
/*myViewHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                {
                    final TextView savedtv = new TextView(context);
                    savedtv.setText(tv.getText());
                    savedtv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) { //clicking the newly added view
                            Intent intent = new Intent(context, DetailView.class);
                            intent.putExtra(POKEMON_NUMBER_EXTRA, index);
                            startActivity(intent);
                        }
                    });
                    savedtv.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            linearLayoutPokeNames.removeView(savedtv);
                            return true;
                        }
                    });

                    linearLayoutPokeNames.addView(savedtv);
                    return true;
                }

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pokeImage;
        TextView pokeName;
        View parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pokeImage = itemView.findViewById(R.id.recycler_element_image);
            pokeName = itemView.findViewById(R.id.recycler_element_pokename);
            parent = itemView.findViewById(R.id.recycyler_constraint_layout_parent);
        }
    }
}
