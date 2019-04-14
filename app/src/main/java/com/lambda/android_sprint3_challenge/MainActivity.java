package com.lambda.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static PocketMonsters pocketMonsters;


    private static final String BASE_URL       = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964";
    private static final String READ_ALL_URL = BASE_URL ;
    private Context context;

    @Override
    protected void onResume() {
        super.onResume();
        Pokemon pk=receiveData();
        if(pk!=null)       {
            pocketMonsters=pocketMonsters.update(pk);
          //  addFoundPokemon( pk );
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ConnectivityManager cManager=(ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ninfo=cManager.getActiveNetworkInfo();
        Button bt=findViewById( R.id.button_to_search );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=findViewById( R.id.et_entry );
                context=getApplicationContext();
                LinearLayout ll=findViewById( R.id.ll_found );
                Pokemon pokemonFound=pocketMonsters.findByID( et.getText().toString() ); //by number
                if(pokemonFound==null){
                    ArrayList<Pokemon> pm=pocketMonsters.findByPartialName( et.getText().toString() );
                    for(int i=0;i<pm.size();i++){
                        ll.addView(addFoundPokemon(pm.get(i) ));
                    }

                }else{
                    ll.addView(addFoundPokemon( pokemonFound) );

                }

            }
        } );




        if(ninfo!=null&&ninfo.isConnected()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                  //  pocketMonsters.obtainEveryoneFromAPI();


                        String strDebug="";
                        final String result = NetworkAdapter.httpRequest( READ_ALL_URL );
                        String[] strNames=result.split( "," );
                        String name="", id="";
                        for(int i=3;i<strNames.length-1;i+=2){
                            if(i==3){
                                name=strNames[i].split("\""  )[5];
                            }else{
                                name=strNames[i].split("\""  )[3];
                            }
                            id=((strNames[i+1].split("\""  )[3]).replace( "https://pokeapi.co/api/v2/pokemon/","" )).replace( "/","" );

                            Pokemon pk=new Pokemon(name,id);

                            if(pocketMonsters==null){
                                pocketMonsters=new PocketMonsters(pk);
                            }else{
                                pocketMonsters.add(pk);
                            }

                        }





                            //    TextView tv = findViewById( R.id.text_debug );
                 //   strDebug="test";
                //    tv.setText(""  );
               //     tv.append(  strDebug );
                }
            }).start();
        }
    }

    private View addFoundPokemon(final Pokemon pokemonFound){
        TextView tv =new TextView( context);
        tv.setTextSize( 30 );

        if (pokemonFound == null) {
            tv.setText( "Not Found" );
            tv.setBackgroundResource( R.drawable.ic_launcher_foreground);
            return tv;
        }else{
            LinearLayout ll=new LinearLayout( context );
            tv.setText( pokemonFound.getID()+","+pokemonFound.getName()+" " );
            ll.addView(tv);
            ll.setTag( pokemonFound.getID());
            if(pokemonFound.getBitmap(  )!=null){
                try {
                    ImageView iv = new ImageView( context );
                    iv.setImageBitmap( pokemonFound.getBitmap() );
                    ll.addView( iv );
                }catch (Exception e){
                    e.getMessage();
                }
            }
            ll.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData( pokemonFound.getID() );
                }
            } );
            return ll;
        }


    }

    private void sendData(String strID){
        Pokemon found=pocketMonsters.findByID( strID );
        if(found==null)return;
        Intent intent = new Intent(context, FullscreenActivity.class);
        intent.putExtra("DATA", found);
        startActivity(intent);
    }
    private Pokemon receiveData(){
        Pokemon pokemon=(Pokemon) getIntent().getParcelableExtra(  "DATA");
        //    pokemonCurrent=pokemon;
        return pokemon;

    }

}


