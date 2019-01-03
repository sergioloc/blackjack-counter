package com.example.sergio.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends AppCompatActivity {

    private TextView textCountdown;
    private ImageView card_d;
    private Thread t,t2;
    private int countdown, speed, i, count, number, anterior,y;
    private int[] secRandom;
    private boolean stop,go,first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        init();
        go=true;
        countdown();
    }

    private void init(){
        textCountdown = (TextView) findViewById(R.id.countdown);
        card_d = (ImageView) findViewById(R.id.card_1);
        number = getIntent().getExtras().getInt("number");
        speed = getIntent().getExtras().getInt("speed");
    }

    public void countdown(){
        countdown=3;
        t = new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()&&countdown>0){
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(countdown==0){
                                    textCountdown.setText("");
                                    startCounting();
                                }
                                else textCountdown.setText(String.valueOf(countdown));
                                countdown--;
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    private void startCounting(){
        secRandom=getSecuenciaAleatoria(1,52);
        i=0; count=0; anterior=-1; stop=false; first=true;

        t2 = new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()&&i<secRandom.length&&!stop){
                    try {
                        if(first){
                           speed=1000;
                        }else{
                            speed = getIntent().getExtras().getInt("speed");
                        }
                        first=false;
                        Thread.sleep(speed);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(i==number){
                                    stop=true;
                                    i=-1;
                                    try {
                                        Thread.sleep(speed);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    getOut();
                                    interrupt();
                                }else{
                                    try{
                                        card_d.setVisibility(View.INVISIBLE);
                                        compare();
                                        i++;
                                        card_d.setVisibility(View.VISIBLE);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();

    }

    private void compare(){
        if(secRandom[i]%13==1){
            if(anterior==1){
                card_d.setImageResource(R.drawable.z_card_2_d);
                count=count+1;
                anterior=2;
            }else{
                card_d.setImageResource(R.drawable.z_card_1_d);
                count=count-1;
                anterior=1;
            }
        }else if(secRandom[i]%13==2){
            if(anterior==2){
                card_d.setImageResource(R.drawable.z_card_3_d);
                count=count+1;
                anterior=3;
            }else{
                card_d.setImageResource(R.drawable.z_card_2_d);
                count=count+1;
                anterior=2;
            }
        }else if(secRandom[i]%13==3){
            if(anterior==3){
                card_d.setImageResource(R.drawable.z_card_4_d);
                count=count+1;
                anterior=4;
            }else{
                card_d.setImageResource(R.drawable.z_card_3_d);
                count=count+1;
                anterior=3;
            }
        }else if(secRandom[i]%13==4){
            if(anterior==4){
                card_d.setImageResource(R.drawable.z_card_5_d);
                count=count+1;
                anterior=5;
            }else{
                card_d.setImageResource(R.drawable.z_card_4_d);
                count=count+1;
                anterior=4;
            }
        }else if(secRandom[i]%13==5){
            if(anterior==5){
                card_d.setImageResource(R.drawable.z_card_6_d);
                count=count+1;
                anterior=6;
            }else{
                card_d.setImageResource(R.drawable.z_card_5_d);
                count=count+1;
                anterior=5;
            }
        }else if(secRandom[i]%13==6){
            if(anterior==6){
                card_d.setImageResource(R.drawable.z_card_7_d);
                anterior=7;
            }else{
                card_d.setImageResource(R.drawable.z_card_6_d);
                count=count+1;
                anterior=6;
            }
        }else if(secRandom[i]%13==7){
            if(anterior==7){
                card_d.setImageResource(R.drawable.z_card_8_d);
                anterior=8;
            }else{
                card_d.setImageResource(R.drawable.z_card_7_d);
                anterior=7;
            }
        }else if(secRandom[i]%13==8){
            if(anterior==8){
                card_d.setImageResource(R.drawable.z_card_9_d);
                anterior=9;
            }else{
                card_d.setImageResource(R.drawable.z_card_8_d);
                anterior=8;
            }
        }else if(secRandom[i]%13==9){
            if(anterior==9){
                card_d.setImageResource(R.drawable.z_card_10_d);
                anterior=10;
                count=count-1;
            }else{
                card_d.setImageResource(R.drawable.z_card_9_d);
                anterior=9;
            }
        }else if(secRandom[i]%13==10){
            if(anterior==10){
                card_d.setImageResource(R.drawable.z_card_11_d);
                count=count-1;
                anterior=11;
            }else{
                card_d.setImageResource(R.drawable.z_card_10_d);
                count=count-1;
                anterior=10;
            }
        }else if(secRandom[i]%13==11){
            if(anterior==11){
                card_d.setImageResource(R.drawable.z_card_12_d);
                count=count-1;
                anterior=12;
            }else{
                card_d.setImageResource(R.drawable.z_card_11_d);
                count=count-1;
                anterior=11;
            }
        }else if(secRandom[i]%13==12){
            if(anterior==12){
                card_d.setImageResource(R.drawable.z_card_13_d);
                count=count-1;
                anterior=13;
            }else{
                card_d.setImageResource(R.drawable.z_card_12_d);
                count=count-1;
                anterior=12;
            }
        }else if(secRandom[i]%13==0){
            if(anterior==0){
                card_d.setImageResource(R.drawable.z_card_1_d);
                count=count-1;
                anterior=1;
            }else{
                card_d.setImageResource(R.drawable.z_card_13_d);
                count=count-1;
                anterior=0;
            }
        }else{
            card_d.setImageResource(R.drawable.z_card_6_d);
        }
    }

    private void getOut(){
        if(go){
            Intent i = new Intent(Play.this, Trainer.class);
            i.putExtra("op", 2);
            i.putExtra("solution", count);
            i.putExtra("number", number);
            i.putExtra("speed", speed);
            startActivity(i);
            finish();
        }

    }

    public static int[] getSecuenciaAleatoria(int a, int b){

        java.util.Random rnd = new java.util.Random();
        int n = b - a + 1;
        java.util.ArrayList<Integer> v = new java.util.ArrayList<Integer>();
        for(int i=0; i < n; ++i){
            v.add(a+i);
        }
        int[] s = new int[n];
        int t;

        for(int i=0; i < n; i++){
            t = rnd.nextInt(v.size());
            s[i] = v.get(t);
            v.remove(t);
        }
        return s;
    }

    @Override
    public void onBackPressed() {
        go=false;
        Intent i = new Intent(Play.this, Trainer.class);
        i.putExtra("op",1);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }
}
