package com.lambda.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PocketMonsters pocketMonsters;


    private static final String BASE_URL       = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964";
    private static final String READ_ALL_URL = BASE_URL ;
    private Context context;

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
        //        TextView tva = findViewById( R.id.text_result );
                Pokemon pokemonFound=pocketMonsters.findByID( et.getText().toString() );
                LinearLayout ll=findViewById( R.id.ll_found );
                ll.addView(addFoundPokemon( pokemonFound) );

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

                           strDebug+=name+","+id+"\n";
                        }


               //    TextView tv = findViewById( R.id.text_debug );
                 //   strDebug="test";
                //    tv.setText(""  );
               //     tv.append(  strDebug );
                }
            }).start();
        }
    }

    private TextView addFoundPokemon(Pokemon pokemonFound){
        TextView tv =new TextView( context);
        if (pokemonFound == null) {
            tv.setText( "Not Found" );
            return tv;
        }else{

            tv.setText(pokemonFound.getID()+","+pokemonFound.getName()+"\n");
            tv.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData( (TextView)v );



                }
            } );
            return tv;
        }

    }

    private void sendData(TextView tv){
        context

        Pokemon found=pocketMonsters.findByIDandNameString( tv.getText().toString() );
        Intent intent = new Intent(context, FullscreenActivity.class);
        intent.putExtra("DATA", found);
        startActivity(intent);
    }


}
