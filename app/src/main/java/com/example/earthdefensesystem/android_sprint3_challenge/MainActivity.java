package com.example.earthdefensesystem.android_sprint3_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_KEY = "search_key";

//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private RecycleViewAdapter viewAdapter;
    private Button button;
    private EditText editText;
//    private List<Pokemon> pokemonList;
    Context context;
    private Activity activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

//        recyclerView = findViewById(R.id.recyclerView);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        viewAdapter = new RecycleViewAdapter(pokemonList, activity);
//        recyclerView.setAdapter(viewAdapter);

//        pokemonList = new ArrayList<>();


        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PokemonDetails.class);
//                System.out.println("Jason "+ editText.getText().toString().toLowerCase());
                intent.putExtra(SEARCH_KEY, editText.getText().toString().toLowerCase());
                startActivity(intent);
            }
        });
    }
}
