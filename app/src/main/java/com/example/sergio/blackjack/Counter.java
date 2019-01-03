package com.example.sergio.blackjack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.*;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Counter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private TextView textCount,textAdvanced;
    private int number,check,count,pos,posReal,numHis,last,num1,num2,num3,num4,num5,num6,num7,num8,
            num9,num10,numJ,numQ,numK;
    private float total,per1,per2,per3,per4,per5,per6,per7,per8,per9,per10,perJ,perQ,perK;
    private DecimalFormat df, df2;
    private DrawerLayout drawerLayout;
    private ListView listView, listView2;
    private String[] res, his;
    private List<String> res_list, his_list;
    private Context context;
    private AlertDialog alert;
    private ArrayAdapter<String> arrayAdapter1, arrayAdapter2;
    private SharedPreferences mydata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

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


        /**Font**/
        String font_path = "font/street cred.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        textAdvanced.setTypeface(TF);

        /**Buttons**/
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(1);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(2);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(3);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(4);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(5);
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(6);
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(7);
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(8);
            }
        });
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(9);
            }
        });
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(10);
            }
        });
        findViewById(R.id.buttonJ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(11);
            }
        });
        findViewById(R.id.buttonQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(12);
            }
        });
        findViewById(R.id.buttonK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button(13);
            }
        });
        findViewById(R.id.buttonAdvance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(listView);
                }
            }
        });
        findViewById(R.id.buttonHis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(listView2)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(listView2);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                infoItem(i);
            }

        });
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alert.show();
            }
        });
        findViewById(R.id.buttonSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }


    private void init(){
        df = new DecimalFormat("0.00");
        df2 = new DecimalFormat("0");
        mydata = getSharedPreferences("dataHis", Context.MODE_PRIVATE);
        numHis=mydata.getInt("numHis",12);
        textCount = (TextView) findViewById(R.id.textResult);
        textAdvanced = (TextView) findViewById(R.id.textAdvanced);
        listView = (ListView) findViewById(R.id.list_view);
        listView2 = (ListView) findViewById(R.id.list_view2);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        res = new String[14];
        his = new String[numHis];
        count=0; last=0; pos=2; posReal=1;
        textCount.setText("0");

        number = getIntent().getExtras().getInt("num");
        check = getIntent().getExtras().getInt("check");
        num1=4*number;
        num2=4*number;
        num3=4*number;
        num4=4*number;
        num5=4*number;
        num6=4*number;
        num7=4*number;
        num8=4*number;
        num9=4*number;
        num10=4*number;
        numJ=4*number;
        numQ=4*number;
        numK=4*number;
        total=52*number;

        his[0]=getResources().getString(R.string.string_history);
        his[1]=getResources().getString(R.string.string_info_his1)+"               "
                +getResources().getString(R.string.string_info_his2);
        for(int i=2;i<his.length;i++){
            his[i]="0";
        }
        percents();
        valueStats();
        valueHistorial();
        updateStats();
        updateHistorial();
    }

    public void valueStats(){
        res[0]=getResources().getString(R.string.string_cards);
        res[1]="A          -->          "+df.format(per1)+" %";
        res[2]="2          -->          "+df.format(per2)+" %";
        res[3]="3          -->          "+df.format(per3)+" %";
        res[4]="4          -->          "+df.format(per4)+" %";
        res[5]="5          -->          "+df.format(per5)+" %";
        res[6]="6          -->          "+df.format(per6)+" %";
        res[7]="7          -->          "+df.format(per7)+" %";
        res[8]="8          -->          "+df.format(per8)+" %";
        res[9]="9          -->          "+df.format(per9)+" %";
        res[10]="10       -->          "+df.format(per10)+" %";
        res[11]="J         -->          "+df.format(perJ)+" %";
        res[12]="Q         -->          "+df.format(perQ)+" %";
        res[13]="K         -->          "+df.format(perK)+" %";
    }

    public void valueHistorial(){
        String lastString = String.valueOf(last);
        if(last==1) lastString="A";
        else if(last==11) lastString="J";
        else if(last==12) lastString="Q";
        else if(last==13) lastString="K";
        if(pos==his.length)
            pos=2;
        if(last!=0){
            if(posReal>9){
                his[pos]="#"+posReal+"         -->           "+lastString;
            }else if(posReal>99){
                his[pos]="#"+posReal+"        -->           "+lastString;
            }else{
                his[pos]="#"+posReal+"           -->           "+lastString;
            }
            pos++;
            posReal++;
        }
    }

    public void updateStats(){
        res_list = new ArrayList<String>(Arrays.asList(res));
        arrayAdapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, res_list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                return view;
            }
        };
        listView.setAdapter(arrayAdapter1);
    }

    public void updateHistorial(){
        his_list = new ArrayList<String>(Arrays.asList(his));
        arrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, his_list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                return view;
            }
        };
        listView2.setAdapter(arrayAdapter2);
    }

    public void percents(){
        per1=(num1*100)/total;
        per2=(num2*100)/total;
        per3=(num3*100)/total;
        per4=(num4*100)/total;
        per5=(num5*100)/total;
        per6=(num6*100)/total;
        per7=(num7*100)/total;
        per8=(num8*100)/total;
        per9=(num9*100)/total;
        per10=(num10*100)/total;
        perJ=(numJ*100)/total;
        perQ=(numQ*100)/total;
        perK=(numK*100)/total;
    }

    public void infoItem(int i){
        if(i==1){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num1+" (A) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==2){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num2+" (2) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==3){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num3+" (3) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==4){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num4+" (4) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==5){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num5+" (5) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==6){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num6+" (6) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==7){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num7+" (7) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==8){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num8+" (8) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==9){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num9+" (9) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==10){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+num10+" (10) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==11){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+numJ+" (J) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==12){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+numQ+" (Q) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }else if(i==13){
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.string_there)+" "+numK+" (K) "+
                            getResources().getString(R.string.string_and)+" "+
                            df2.format(total)+" "+getResources().getString(R.string.string_left),
                    Toast.LENGTH_LONG).show();
        }
        drawerLayout.closeDrawers();
    }

    public void button(int s){
        context=getApplicationContext();
        boolean cont = false;

        if(total>0){
            if(s==1){
                if(num1>0){
                    num1=num1-1;
                    count--;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_1),
                            Toast.LENGTH_SHORT).show();

            }else if(s==2){
                if(num2>0){
                    num2=num2-1;
                    count++;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_2),
                            Toast.LENGTH_SHORT).show();

            }else if(s==3){
                if(num3>0){
                    num3=num3-1;
                    count++;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_3),
                            Toast.LENGTH_SHORT).show();

            }else if(s==4){
                if(num4>0){
                    num4=num4-1;
                    count++;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_4),
                            Toast.LENGTH_SHORT).show();
            }else if(s==5){
                if(num5>0){
                    num5=num5-1;
                    count++;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_5),
                            Toast.LENGTH_SHORT).show();
            }else if(s==6){
                if(num6>0){
                    num6=num6-1;
                    count++;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_6),
                            Toast.LENGTH_SHORT).show();
            }else if(s==7){
                if(num7>0){
                    num7=num7-1;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_7),
                            Toast.LENGTH_SHORT).show();
            }else if(s==8){
                if(num8>0){
                    num8=num8-1;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_8),
                            Toast.LENGTH_SHORT).show();
            }else if(s==9){
                if(num9>0){
                    num9=num9-1;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_9),
                            Toast.LENGTH_SHORT).show();
            }else if(s==10){
                if(num10>0){
                    num10=num10-1;
                    count--;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_10),
                            Toast.LENGTH_SHORT).show();
            }else if(s==11){
                if(numJ>0){
                    numJ=numJ-1;
                    count--;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_J),
                            Toast.LENGTH_SHORT).show();
            }else if(s==12){
                if(numQ>0){
                    numQ=numQ-1;
                    count--;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_Q),
                            Toast.LENGTH_SHORT).show();
            }else if(s==13){
                if(numK>0){
                    numK=numK-1;
                    count--;
                    cont=true;
                }
                else
                    Toast.makeText(context,getResources().getString(R.string.string_no_K),
                            Toast.LENGTH_SHORT).show();
            }

            if(cont){
                int n;
                last=s;
                total=total-1;
                if(check==0) n = count/number;
                else n = count;
                textCount.setText(String.valueOf(n));
                if(total==0){
                    finalStats();
                }else{
                    percents();
                    valueStats();
                    updateStats();
                    valueHistorial();
                    updateHistorial();
                }
            }
        }else{
            Toast.makeText(context, getResources().getString(R.string.string_total),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void showDialog(){

        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        final View Viewlayout = inflater.inflate(R.layout.historial_dialog,
                (ViewGroup) findViewById(R.id.layout_dialog));

        final TextView item1 = (TextView)Viewlayout.findViewById(R.id.txtItem1);
        item1.setText(String.valueOf(numHis-2));

        popDialog.setTitle(getResources().getString(R.string.string_num_his));
        popDialog.setView(Viewlayout);

        SeekBar seek1 = (SeekBar) Viewlayout.findViewById(R.id.seekBar1);
        seek1.setMax(50);
        seek1.setKeyProgressIncrement(5);
        seek1.setProgress(numHis-2);
        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                item1.setText(""+progress);
            }
            public void onStartTrackingTouch(SeekBar arg0) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                numHis=seekBar.getProgress()+2;
            }
        });

        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        his = new String[numHis];
                        his[0]=getResources().getString(R.string.string_history);
                        his[1]=getResources().getString(R.string.string_info_his1)+"               "
                                +getResources().getString(R.string.string_info_his2);
                        for(int i=2;i<his.length;i++){
                            his[i]="0";
                        }
                        pos=2;posReal=1;
                        saveData();
                        updateHistorial();
                    }

                });
        popDialog.create();
        popDialog.show();

    }

    public void saveData(){
        SharedPreferences.Editor editor = mydata.edit();
        editor.putInt("numHis",numHis);
        editor.commit();
    }

    public void finalStats(){
        res[0]=getResources().getString(R.string.string_cards);
        res[1]="A          -->          0.00 %";
        res[2]="2          -->          0.00 %";
        res[3]="3          -->          0.00 %";
        res[4]="4          -->          0.00 %";
        res[5]="5          -->          0.00 %";
        res[6]="6          -->          0.00 %";
        res[7]="7          -->          0.00 %";
        res[8]="8          -->          0.00 %";
        res[9]="9          -->          0.00 %";
        res[10]="10       -->          0.00 %";
        res[11]="J         -->          0.00 %";
        res[12]="Q         -->          0.00 %";
        res[13]="K         -->          0.00 %";
        updateStats();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_counter_drawer, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }

}
