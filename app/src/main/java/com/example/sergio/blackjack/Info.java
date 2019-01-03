package com.example.sergio.blackjack;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Info extends AppCompatActivity {

    private Uri uri;
    private int r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        uri = Uri.parse("https://www.blackjackapprenticeship.com/resources/how-to-count-cards/");

        findViewById(R.id.moreInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });

        r = getIntent().getExtras().getInt("return");
    }

    @Override
    public void onBackPressed() {
        Intent i;
        if(r==0){
            i = new Intent(Info.this, Menu.class);
        }else{
            i = new Intent(Info.this, Number.class);
        }
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }
}
