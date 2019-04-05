package com.example.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String POKEMON_NUMBER_EXTRA = "POKEMON_NUMBER";
    Button searchButton;
    EditText editTextSearch;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.search_button);
        editTextSearch = findViewById(R.id.edit_text_search);
        context = this;

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailView.class);
                intent.putExtra(POKEMON_NUMBER_EXTRA, editTextSearch.getText().toString());
                startActivity(intent);
            }
        });
    }
}
