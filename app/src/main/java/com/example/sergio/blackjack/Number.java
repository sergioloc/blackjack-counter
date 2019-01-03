package com.example.sergio.blackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Number extends AppCompatActivity {

    private AlertDialog alert;
    private TextView textNumber;
    private ImageButton button1,button2,button3,button4,button5,button6,button7,buttonQuest;
    private Typeface TF;
    private int check;
    private Switch trueCount;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        /**Ads**/
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /**Init**/
        textNumber = (TextView)findViewById(R.id.textNumber);
        button1 = (ImageButton) findViewById(R.id.button1);
        button2 = (ImageButton) findViewById(R.id.button2);
        button3 = (ImageButton) findViewById(R.id.button3);
        button4 = (ImageButton) findViewById(R.id.button4);
        button5 = (ImageButton) findViewById(R.id.button5);
        button6 = (ImageButton) findViewById(R.id.button6);
        button7 = (ImageButton) findViewById(R.id.button7);
        buttonQuest = (ImageButton) findViewById(R.id.buttonQuest);
        trueCount = (Switch) findViewById(R.id.switch1);

        /**Fonts**/
        String font_path = "font/street cred.ttf";
        TF = Typeface.createFromAsset(getAssets(),font_path);
        textNumber.setTypeface(TF);

        /**Alert Dialog**/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.string_help_des)
        .setTitle(R.string.string_help)
                .setCancelable(true)
                .setNegativeButton((R.string.string_what),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Number.this, Info.class);
                                i.putExtra("return",1);
                                startActivity(i);
                                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
                                finish();
                            }
                        })
                .setNeutralButton(R.string.string_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alert = builder.create();

        /**Buttons**/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(6);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(7);
            }
        });
        buttonQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });
    }

    public void nextActivity(int n){
        if(trueCount.isChecked()) check=0;
        else check=1;

        Intent i = new Intent(Number.this, CounterSimple.class);
        i.putExtra("check", check);
        i.putExtra("num", n);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Number.this, Menu.class);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }

}
