package com.example.sergio.blackjack;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Menu extends AppCompatActivity {

    private ImageButton button1,button2,button3;
    private TextView buttonText1,buttonText2,buttonText3;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /**Ads**/
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        /**Init**/
        button1 = (ImageButton) findViewById(R.id.button1);
        button2 = (ImageButton) findViewById(R.id.button2);
        button3= (ImageButton) findViewById(R.id.button3);
        buttonText1 = (TextView) findViewById(R.id.buttonText1);
        buttonText2 = (TextView) findViewById(R.id.buttonText2);
        buttonText3= (TextView) findViewById(R.id.buttonText3);

        /**Fonts**/
        String font_path = "font/street cred.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        buttonText1.setTypeface(TF);
        buttonText2.setTypeface(TF);
        buttonText3.setTypeface(TF);

        /**Buttons**/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Number.class));
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Trainer.class);
                i.putExtra("op", 1);
                startActivity(i);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Info.class);
                i.putExtra("return",0);
                startActivity(i);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Menu.this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }
}
