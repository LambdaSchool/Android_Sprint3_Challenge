package com.example.sprint3_pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PokeFavoritesListActivity extends AppCompatActivity {
    EditText etInput;
    Button searchButton;
    LinearLayout ll;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_favorites_list);
        etInput = findViewById(R.id.search_text);
        searchButton = findViewById(R.id.search_button);
        ll = findViewById(R.id.scrollview_ll_list_view);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PokemonDao.addPokemonToFavorites(PokemonDao.getPokemon(Integer.parseInt(etInput.getText().toString())));
                        updateList();
                    }
                }).start();
            }
        });
    }

    private void updateList() {
        ll.removeAllViews();
        String[] names = PokemonDao.getPokemonNames();
        for(int i = 0; i < names.length; i++){
            ll.addView(createTextView(names[i]));
        }
    }

    public TextView createTextView(String text){
        final TextView textView = new TextView(this);
        textView.setText(text);

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PokemonDao.removePokemon(textView.getText().toString());
                updateList();
                return true;
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsViewActivity.class);
                intent.putExtra(getString(R.string.poke_key), textView.getText().toString());
                startActivity(intent);
                }
        });
        return textView;
    }
}
