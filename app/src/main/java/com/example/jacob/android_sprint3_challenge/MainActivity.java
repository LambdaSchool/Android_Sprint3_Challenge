package com.example.jacob.android_sprint3_challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_DATA = "search_data";
    public static final String RETURN_DATA_KEY = "return_key";
    public static final int SAVE_CODE = 89;
    EditText editText;
    Context context;
    LinearLayout parentLayout;
    public static SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        preferences = this.getPreferences(Context.MODE_PRIVATE);
        parentLayout = findViewById(R.id.layout_list);


        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    SharedPreferences.Editor editor = MainActivity.preferences.edit();
                    editor.putString(returnedText,"");
                    editor.apply();
                    refreshViews();
                }
            }
        }
    }

    private void refreshViews() {
        parentLayout.removeAllViews();
        Map<String, ?> allEntries = MainActivity.preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            parentLayout.addView(getDefaultTextView(entry.getKey()));
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
