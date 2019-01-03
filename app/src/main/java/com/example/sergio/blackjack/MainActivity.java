package com.example.sergio.blackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.RuntimeExecutionException;


public class MainActivity extends AppCompatActivity {

    private TextView textBlackjack, textCounter;
    private ImageButton boton;
    public static final String APP_ID = "ca-app-pub-8901700829116269~8267800663";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, APP_ID);

        /**Init**/
        textBlackjack = (TextView)findViewById(R.id.textBlackjack);
        textCounter = (TextView)findViewById(R.id.textCounter);
        boton = (ImageButton)findViewById(R.id.botonGo);

        /**Fonts**/
        String font_path = "font/street cred.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        textBlackjack.setTypeface(TF);
        textCounter.setTypeface(TF);

        /**Button**/
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(MainActivity.this, Menu.class));
                    overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                    finish();
                }catch (RuntimeExecutionException e){
                    e.getMessage();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Salir")
                .setMessage("¿Estás seguro?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                })
                .show();
    }

}
