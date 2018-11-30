package com.example.joshh.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Context context;
    private EditText searchInput;
    private LinearLayout searchHistorylayout;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        searchInput = findViewById(R.id.search_input);
        searchHistorylayout = findViewById(R.id.search_history_layout);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon pokemon = PokemonApiDao.getPokemon(searchInput.getText().toString());
                        Intent detailsIntent = new Intent(context, PokemonDetailsActivity.class);
                        detailsIntent.putExtra("pokemon_details", pokemon);
                        startActivity(detailsIntent);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final TextView tv = new TextView(context);
                                tv.setText(pokemon.getName());
                                tv.setTextSize(25);
                                tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                tv.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        /*if(pokemon.isFavorite()){
                                            pokemon.setFavorite(false);
                                            tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                        }else{
                                            pokemon.setFavorite(true);
                                            tv.setTextColor(Color.YELLOW);
                                        }*/
                                        tv.setVisibility(View.GONE);
                                        return false;
                                    }
                                });
                                searchHistorylayout.addView(tv);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
