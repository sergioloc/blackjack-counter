package com.example.sergio.blackjack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class CounterSimple extends AppCompatActivity {

    private TextView textCount, textPlus, textEqual, textMinus;
    private int numPlus,numEqual,numMinus,number,check,count;
    private float total,perPlus,perEqual,perMinus;
    private DecimalFormat df;
    private Context context;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_simple);

        init();

        /**Alert Dialog**/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.string_reset)
                .setTitle(R.string.string_reset_title)
                .setCancelable(true)
                .setNegativeButton(R.string.string_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(R.string.string_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                init();
                            }
                        });
        alert = builder.create();

        /**Buttons**/
        findViewById(R.id.buttonPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionPlus();
            }
        });
        findViewById(R.id.buttonEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionEqual();
            }
        });
        findViewById(R.id.buttonMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionMinus();
            }
        });
        findViewById(R.id.buttonTable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CounterSimple.this, Table.class));
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            }
        });
        findViewById(R.id.buttonMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CounterSimple.this, Counter.class);
                i.putExtra("num", number);
                i.putExtra("check", check);
                startActivity(i);
                overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            }
        });
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alert.show();
            }
        });


    }

    public void init(){

        df = new DecimalFormat("0.00");
        context=getApplicationContext();
        count=0;

        textCount = (TextView) findViewById(R.id.textResult);
        textPlus = (TextView) findViewById(R.id.text1);
        textEqual = (TextView) findViewById(R.id.text2);
        textMinus = (TextView) findViewById(R.id.text3);
        textCount.setText("0");

        // Recuperar Numero
        number = getIntent().getExtras().getInt("num");
        check = getIntent().getExtras().getInt("check");
        numPlus=20*number;
        numEqual=12*number;
        numMinus=20*number;
        total=52*number;

        updatePercents();

    }

    public void actionPlus() {

        if(total>0){
            if(numPlus>0){
                numPlus--;
                total--;
                count++;
                int c;
                if(check==0)
                    c = count/number;
                else
                    c = count;
                textCount.setText(String.valueOf(c));
                updatePercents();
            }else{
                Toast.makeText(context, getResources().getString(R.string.string_plus),
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, getResources().getString(R.string.string_total),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void actionEqual() {

        if(total>0){
            if(numEqual>0){
                numEqual--;
                total--;
                updatePercents();
            }else{
                Toast.makeText(context,getResources().getString(R.string.string_equal),
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context,getResources().getString(R.string.string_total),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void actionMinus() {

        if(total>0){
            if(numMinus>0){
                numMinus--;
                total--;
                count--;
                int c;
                if(check==0)
                    c = count/number;
                else
                    c = count;
                textCount.setText(String.valueOf(c));
                updatePercents();
            }else{
                Toast.makeText(context,getResources().getString(R.string.string_minus),
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context,getResources().getString(R.string.string_total),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePercents(){
        if(total==0){
            textPlus.setText("0.00");
            textEqual.setText("0.00");
            textMinus.setText("0.00");
        }else{
            perPlus=(numPlus*100)/total;
            perEqual=(numEqual*100)/total;
            perMinus=(numMinus*100)/total;

            textPlus.setText(df.format(perPlus));
            textEqual.setText(df.format(perEqual));
            textMinus.setText(df.format(perMinus));
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(CounterSimple.this, Number.class);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }

}