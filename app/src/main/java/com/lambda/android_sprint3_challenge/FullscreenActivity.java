package com.lambda.android_sprint3_challenge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    Pokemon pokemonCurrent;
    Context context;
 //   LinearLayout ll;
    private TextView tv;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        setContentView( R.layout.activity_fullscreen );
        context=getApplicationContext();
        tv=findViewById( R.id.text_deteail);
        img=findViewById( R.id.image_view );
        pokemonCurrent=receiveData();
        initialView();

        new Thread(new Runnable() {
            @Override
            public void run() {


String str=pokemonCurrent.getID();
                final String result = NetworkAdapter.httpRequest( "https://pokeapi.co/api/v2/ability/"+str+"/");
                String strDebug;
                try{
                    JSONObject json =  new JSONObject( result );

                        String JsonChild =json.getString(      "effect_changes" );

                       pokemonCurrent.setAbilities( JsonChild.replace( "\"","" ) );

                    tv.append(pokemonCurrent.getAbilities() );
                    img.setImageBitmap( NetworkAdapter.getBitmapFromUrl(  "http://pokeapi.co/media/sprites/pokemon/"+str+".png"));


                    //        pokemonCurrent.setAbilities(JsonChild.getString( "name" ));


                }catch (JSONException e){
                    e.printStackTrace();

                }



            }
        }).start();


        Button bt=findViewById( R.id.dummy_button );

        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData();
            }
        } );
    }

    void initialView(){

        tv.setText( pokemonCurrent.getID()+"\n"+pokemonCurrent.getName()+"\n"+pokemonCurrent.getAbilities());
   //     LinearLayout ll=findViewById( R.id.ll_detail );
   //     ll.addView( tv );
    }


    private Pokemon receiveData(){
        Pokemon pokemon=(Pokemon) getIntent().getSerializableExtra("DATA");
    //    pokemonCurrent=pokemon;
        return pokemon;

    }
    private void sendData(){

        Pokemon found=pokemonCurrent;
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("DATA", found);
        startActivity(intent);
    }

}
