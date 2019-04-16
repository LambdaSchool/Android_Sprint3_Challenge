package com.example.Pokedex;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String POKEMON_NUMBER_EXTRA = "POKEMON_NUMBER";
    Button catchButton;
    EditText editTextSearch;
    Context context;
    LinearLayout linearLayoutPokeNames;
    LinearLayout linearLayoutPokeDex;

    //ArrayList<String> savedPokemon = new ArrayList<>(); //For use with permanence
    ArrayList<String> pokeNames = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutPokeNames = findViewById(R.id.linear_layout_scroll_child_name_list);
        //linearLayoutPokeDex = findViewById(R.id.linear_layout_scroll_child_pokedex_list);
        catchButton = findViewById(R.id.button_catch);
        editTextSearch = findViewById(R.id.edit_text_search);
        context = this;


        new Thread(new Runnable() {
            @Override
            public void run() {
                pokeNames = PokemonNetworkDao.getAllPokemonNames();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        recyclerView = findViewById(R.id.recycler_view_view);

                        // use this setting to improve performance if you know that changes
                        // in content do not change the layout size of the RecyclerView
                        recyclerView.setHasFixedSize(true);

                        // use a linear layout manager
                        layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);

                        // specify an adapter (see also next example)
                        mAdapter = new ListAdapter(pokeNames);
                        recyclerView.setAdapter(mAdapter);


                        /*for (int i = 0; i < pokeNames.size(); ++i) { //populate pokedex scrollview
                            String pokeViewText = (i+1) + " " + pokeNames.get(i); //creates String for each item in pokenames
                            final TextView tv = new TextView(context);
                            tv.setText(pokeViewText);
                            tv.setTransitionName("pokename");
                            final String index = String.valueOf(i+1);
                            tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) { //clicking the newly added view
                                    Intent intent = new Intent(context, DetailView.class);
                                    intent.putExtra(POKEMON_NUMBER_EXTRA, index);
                                    ActivityOptionsCompat options = ActivityOptionsCompat.
                                            makeSceneTransitionAnimation((Activity) view.getContext(), tv, "pokename");
                                    startActivity(intent, options.toBundle());
                                }
                            });
                            tv.setOnLongClickListener(new View.OnLongClickListener() { //add pokemon from pokedex to saved list by long click
                                @Override
                                public boolean onLongClick(View view) {
                                    final TextView savedtv = new TextView(context);
                                    savedtv.setText(tv.getText());
                                    savedtv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) { //clicking the newly added view
                                            Intent intent = new Intent(context, DetailView.class);
                                            intent.putExtra(POKEMON_NUMBER_EXTRA, index);



                                            *//*Bundle options2 = ActivityOptions.makeSceneTransitionAnimation( //animate the name
                                                    (Activity) view.getContext(),
                                                    tv,
                                                    ViewCompat.getTransitionName(tv)
                                            ).toBundle();*//*

                                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                                    makeSceneTransitionAnimation((Activity) view.getContext(), tv, "pokename");

                                            startActivity(intent, options.toBundle());
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
                            });
                            linearLayoutPokeDex.addView(tv);
                        }*/


                        catchButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) { //Creating view for saved pokemon and passing intent
                                Intent intent = new Intent(context, DetailView.class);
                                final String pokeNumber = editTextSearch.getText().toString();
                                int pokeNumberInt = Integer.parseInt(pokeNumber);
                                intent.putExtra(POKEMON_NUMBER_EXTRA, pokeNumber);
                                String pokeViewText = (pokeNumberInt + " " + pokeNames.get(pokeNumberInt-1)); //adds a view for each item searched for
                                final TextView tv = new TextView(context);
                                tv.setText(pokeViewText);
                                tv.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) { //clicking the newly added view
                                        Intent intent = new Intent(context, DetailView.class);
                                        intent.putExtra(POKEMON_NUMBER_EXTRA, pokeNumber);
                                        startActivity(intent);
                                    }
                                });
                                tv.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View view) {
                                        linearLayoutPokeNames.removeView(tv);
                                        return true;
                                    }
                                });
                                linearLayoutPokeNames.addView(tv);
                                //startActivity(intent); //ReEnable to go to detailview of caught pokemon on button press
                            }
                        });
                    }
                });
            }
        }).start();
    }
}
