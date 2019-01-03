package com.example.sergio.blackjack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Trainer extends AppCompatActivity{

    private TextView textCountdown,textSpeed,textSpeedOp,textNum,textAsk,textCorrect,textMistake,
            textSolution,textPlus,textMinus,textAcept,textStart,textBack,textRepeat;
    private ImageButton buttonStart,buttonPlus,buttonMinus,buttonAcept,buttonRepeat,buttonBack,
            buttonQuest;
    private EditText numCards,countResult;
    private Typeface TF;
    private int number,speed,op,solution;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        init();

        /**View**/
        if(op==2){
            solution = getIntent().getExtras().getInt("solution");
            number = getIntent().getExtras().getInt("number");
            speed = getIntent().getExtras().getInt("speed");
            view2();
        }

        /**Fonts**/
        String font_path = "font/street cred.ttf";
        TF = Typeface.createFromAsset(getAssets(),font_path);
        textSpeed.setTypeface(TF);
        textSpeedOp.setTypeface(TF);
        textNum.setTypeface(TF);
        textAsk.setTypeface(TF);
        textStart.setTypeface(TF);
        textAcept.setTypeface(TF);
        textSolution.setTypeface(TF);
        textCorrect.setTypeface(TF);
        textMistake.setTypeface(TF);
        textRepeat.setTypeface(TF);
        textBack.setTypeface(TF);

        /**Alert Dialog**/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.string_help_des2)
                .setTitle(R.string.string_help)
                .setCancelable(true)
                .setNeutralButton(R.string.string_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alert = builder.create();

        /**Buttons**/
        buttonQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus();
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus();
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startB();
            }
        });
        buttonAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acept();
            }
        });
        buttonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeat();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Trainer.this, Trainer.class);
                i.putExtra("op", 1);
                startActivity(i);
                finish();
            }
        });
    }

    private void init(){
        try{
            op = getIntent().getExtras().getInt("op");
            buttonStart = (ImageButton) findViewById(R.id.buttonStart);
            buttonPlus = (ImageButton) findViewById(R.id.buttonPlus);
            buttonMinus = (ImageButton) findViewById(R.id.buttonMinus);
            buttonAcept = (ImageButton) findViewById(R.id.buttonAcept);
            textSpeedOp = (TextView) findViewById(R.id.textSpeedOp);
            textSpeed = (TextView) findViewById(R.id.textSpeed);
            textNum = (TextView) findViewById(R.id.textNum);
            numCards = (EditText) findViewById(R.id.numberCards);
            countResult = (EditText) findViewById(R.id.countResult);
            textAsk = (TextView) findViewById(R.id.textAsk);
            buttonBack = (ImageButton) findViewById(R.id.buttonBack);
            buttonRepeat = (ImageButton) findViewById(R.id.buttonRepeat);
            buttonQuest = (ImageButton) findViewById(R.id.buttonQuest);
            textCountdown = (TextView) findViewById(R.id.countdown);
            textCorrect = (TextView) findViewById(R.id.textCorrect);
            textMistake = (TextView) findViewById(R.id.textMistake);
            textSolution = (TextView) findViewById(R.id.textSolution);
            textStart = (TextView) findViewById(R.id.textStart);
            textAcept = (TextView) findViewById(R.id.textAcept);
            textPlus = (TextView) findViewById(R.id.textPlus);
            textMinus = (TextView) findViewById(R.id.textMinus);
            textBack = (TextView) findViewById(R.id.textBack);
            textRepeat = (TextView) findViewById(R.id.textRepeat);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void view2(){
        countResult.setVisibility(View.VISIBLE);
        textAsk.setVisibility(View.VISIBLE);
        buttonAcept.setVisibility(View.VISIBLE);
        textAcept.setVisibility(View.VISIBLE);

        buttonStart.setVisibility(View.INVISIBLE);
        buttonPlus.setVisibility(View.INVISIBLE);
        buttonMinus.setVisibility(View.INVISIBLE);
        textStart.setVisibility(View.INVISIBLE);
        textPlus.setVisibility(View.INVISIBLE);
        textMinus.setVisibility(View.INVISIBLE);
        textSpeedOp.setVisibility(View.INVISIBLE);
        textSpeed.setVisibility(View.INVISIBLE);
        textNum.setVisibility(View.INVISIBLE);
        numCards.setVisibility(View.INVISIBLE);
        buttonQuest.setVisibility(View.INVISIBLE);
    }

    private void end(){
        buttonRepeat.setVisibility(View.VISIBLE);
        buttonBack.setVisibility(View.VISIBLE);
        buttonAcept.setVisibility(View.INVISIBLE);
        textAcept.setVisibility(View.INVISIBLE);
        textRepeat.setVisibility(View.VISIBLE);
        textBack.setVisibility(View.VISIBLE);
    }

    private int getSpeed(){
        if(textSpeedOp.getText().toString()==getResources().getString(R.string.string_speed_slow))
            return 3000;
        else if(textSpeedOp.getText().toString()==getResources().getString(R.string.string_speed_fast))
            return 1000;
        else return 2000;
    }

    /** For buttons **/
    public void repeat(){
        Intent i = new Intent(Trainer.this, Play.class);
        i.putExtra("number", number);
        i.putExtra("speed", speed);
        startActivity(i);
        finish();
    }
    public void acept(){
            try{
                if(Integer.parseInt(countResult.getText().toString())==solution){
                    textCorrect.setVisibility(View.VISIBLE);
                    end();
                }else{
                    textMistake.setVisibility(View.VISIBLE);
                    if(solution>0)
                        textSolution.setText(getResources().getString(R.string.string_info_count)
                                +" +"+solution);
                    else
                        textSolution.setText(getResources().getString(R.string.string_info_count)
                                +" "+solution);
                    textSolution.setVisibility(View.VISIBLE);
                    textRepeat.setText(getResources().getString(R.string.string_try_again));
                    end();
                }

            }catch (Exception e){
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.string_value), Toast.LENGTH_SHORT).show();

            }
    }
    public void startB(){
        try{
            if(Integer.parseInt(numCards.getText().toString())<52 &&
                    Integer.parseInt(numCards.getText().toString())>0){
                number = Integer.parseInt(numCards.getText().toString());
                speed = getSpeed();
                Intent i = new Intent(Trainer.this, Play.class);
                i.putExtra("number", number);
                i.putExtra("speed", speed);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), R.string.string_between,
                        Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), R.string.string_value,
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void minus(){
        if (textSpeedOp.getText().toString()
                .equals(getResources().getString(R.string.string_speed_normal))) {
            textSpeedOp.setText(getResources().getString(R.string.string_speed_slow));
        }
        else if (textSpeedOp.getText().toString()
                .equals(getResources().getString(R.string.string_speed_fast))) {
            textSpeedOp.setText(getResources().getString(R.string.string_speed_normal));
        }
    }
    public void plus(){
        if (textSpeedOp.getText().toString()
                .equals(getResources().getString(R.string.string_speed_normal))) {
            textSpeedOp.setText(getResources().getString(R.string.string_speed_fast));
        }
        else if (textSpeedOp.getText().toString()
                .equals(getResources().getString(R.string.string_speed_slow))) {
            textSpeedOp.setText(getResources().getString(R.string.string_speed_normal));
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Trainer.this, Menu.class);
        startActivity(i);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
        finish();
    }
}

