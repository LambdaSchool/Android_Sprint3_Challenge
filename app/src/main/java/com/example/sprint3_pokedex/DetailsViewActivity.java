package com.example.sprint3_pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsViewActivity extends AppCompatActivity {

    ImageView ivImage;
    LinearLayout ll;
    TextView tvType1;
    TextView tvType2;
    TextView tvName;
    TextView tvNum;
    Pokemon pokemon;
    String pokemonName;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        tvName = findViewById(R.id.poke_name);
        tvNum = findViewById(R.id.poke_num);
        tvType1 = findViewById(R.id.poke_type1);
        tvType2 = findViewById(R.id.poke_type2);
        ivImage = findViewById(R.id.poke_image);
        ll = findViewById(R.id.scrollview_ll_details_view);
        pokemonName = "0";
        context = this;

        pokemonName = intent.getStringExtra("poke_key");

        if(pokemonName == "0"){
            onBackPressed();
        }

        pokemon = PokemonFavoriteRepo.getPokemonByName(pokemonName);

        for(int i = 0; i < pokemon.getMoves().length; i++){
            ll.addView(createTextView(pokemon.getMoves()[i]));
        }
        tvName.setText(pokemon.getName());

        tvNum.setText(String.valueOf(pokemon.getNumber()));
        tvType1.setText(pokemon.getElementType()[0]);
        tvType2.setText(pokemon.getElementType()[1]);

       new setImageAsync().execute();
    }


    public TextView createTextView(String text){
        TextView textView = new TextView(context);
        textView.setText(text);
        return textView;
    }

    private class setImageAsync extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TODO:PROGRESS BAR
        }
        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap image = PokemonDao.bitmapFromURL(pokemon.getImageURL());
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            ivImage.setImageBitmap(result);
        }

    }
}
