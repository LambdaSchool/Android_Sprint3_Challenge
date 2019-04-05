package com.lambdaschool.android_sprint3_challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edit_text_search);
                final String textToSearchFor = editText.getText().toString().toLowerCase();

                if (!textToSearchFor.equals("")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            pokemon = PokemonDao.getAPokemon(textToSearchFor);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (pokemon.getName().toLowerCase().equals(textToSearchFor) || Integer.toString(pokemon.getId()).equals(textToSearchFor)) {

                                        TextView textView = new TextView(getApplicationContext());
                                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                        textView.setPadding(5, 5, 5, 0);
                                        textView.setTextSize(18);
                                        textView.setText(pokemon.getId() + ": " + pokemon.getName());
                                        LinearLayout linearLayout = findViewById(R.id.linear_layout);
                                        linearLayout.addView(textView);

                                        Intent intent = new Intent(getApplicationContext(), PokemonDetails.class);
                                        intent.putExtra("pokemon", pokemon);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }
}
