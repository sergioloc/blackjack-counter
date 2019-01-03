package com.example.sergio.blackjack;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Table extends AppCompatActivity {

    private TextView titleTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        titleTable = (TextView) findViewById(R.id.titleTable);
        String font_path = "font/street cred.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        titleTable.setTypeface(TF);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }
}
