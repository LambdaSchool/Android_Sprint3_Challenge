package com.example.jacob.android_sprint3_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_DATA = "search_data";
    public static final String RETURN_DATA_KEY = "return_key";
    public static final int SAVE_CODE = 89;
    EditText editText;
    Context context;
    LinearLayout parentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        parentLayout = findViewById(R.id.layout_list);


        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Pokemon pokemon = new Pokemon(PokemonDao.findPokemon("727"));
                    }
                }).start();*/

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(SEARCH_DATA, ((EditText) findViewById(R.id.edit_text_search)).getText().toString());
                startActivityForResult(intent, SAVE_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SAVE_CODE) {
                if (data != null) {
                    final String returnedText = data.getStringExtra(RETURN_DATA_KEY);
                    parentLayout.addView(getDefaultTextView(returnedText));
                }
            }
        }
    }

    TextView getDefaultTextView(final String name) {
        TextView view = new TextView(context);
        view.setText(name);
        view.setTextSize(28);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(SEARCH_DATA, ((EditText) findViewById(R.id.edit_text_search)).getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }



}
