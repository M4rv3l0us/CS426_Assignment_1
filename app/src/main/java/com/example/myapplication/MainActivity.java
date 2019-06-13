package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    boolean isSelected = false;
    ArrayList<String> Genres;
    TextView mname, mrun, mIM, mdes, mfm;
    MovieInfo mif;
    LinearLayout mgen;
    ArrayList<Showtimes> sht;
    ArrayList<Showhour> shh;
    Button cgv,galaxy,lotte;
    String rap, gio, tl;
    StringBuilder ngay = new StringBuilder();
    ImageView play;
    ItemClickListener itemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        Genres = new ArrayList<>();
        Genres.add("Action");
        Genres.add("Adventure");
        Genres.add("Science Fiction");
        //display movieinfo
        mif = (MovieInfo) new MovieInfo("Avenger: Endgame","IMDB: 8.8", 182, "IMAX 4DX", "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",Genres);
        mname = (TextView) findViewById(R.id.MovieTitle);
        mname.setText(mif.getMovieTitle());
        TextView DisplayGenres = new TextView(this);
        DisplayGenres.setTextSize(8);
        mgen = (LinearLayout) findViewById(R.id.Genres);
        mgen.addView(DisplayGenres);
        for (int i=0; i<Genres.size();i++){
            DisplayGenres.append(Genres.get(i));
            if(i<=1)
                DisplayGenres.append("|");
        }

        mrun = (TextView) findViewById(R.id.Runtime);
        tl = mif.getRuntime().toString();
        mrun.setText("- " + mif.getRuntime() + "m");
        mIM = (TextView) findViewById(R.id.IMDB);
        mIM.setText(mif.getIMDB());
        mdes = (TextView) findViewById(R.id.Description);
        mdes.setText(mif.getDescription());
        mfm = (TextView) findViewById(R.id.Format);
        mfm.setText(mif.getMovieFormat());
        //Showtimes
        Showtimes tmp = new Showtimes(26,5,true);
        sht = new ArrayList<>();
        for (int i = 0; i < 13; i++){
            if (tmp.getDate() == 31){
                tmp.setMonth(6);
                tmp.setDate(0);
            }
            tmp.setDate(tmp.getDate() + 1);
            tmp.setAvailable(true);
            Log.d("HUANTRAN",tmp.getDate() + " " + tmp.getMonth());
            sht.add(new Showtimes(tmp.getDate(), tmp.getMonth(),tmp.getAvailable()));
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_date);
        recyclerView.setHasFixedSize(false);

        //generate layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //generate Adapter
        final ShowtimesAdapter showtimesAdapter = new ShowtimesAdapter(sht,getApplicationContext(), ngay);
        Toast.makeText(this, ngay, Toast.LENGTH_SHORT).show();
        //Log.d("Huan",ngay.toString()+"");
        Log.d("CURRENT DAY--------",showtimesAdapter.getCurrentDay());
        recyclerView.setAdapter(showtimesAdapter);
        //Showhour
        Showhour tmp2 = new Showhour(7,0,true,false);
        shh = new ArrayList<>();
        for(int i = 0; i<13;i++) {
            if(i%3 == 0){
                tmp2.setDisable(true);
            }
            else tmp2.setDisable(false);
            if(tmp2.getMinute()== 0)
            {
                shh.add(new Showhour(tmp2.getHour(),tmp2.getMinute()+30,true,tmp2.getDisable()));
                tmp2.setMinute(30);
            }
            else {
                tmp2.setHour(tmp2.getHour() + 1);
                tmp2.setMinute(0);
                shh.add(new Showhour(tmp2.getHour(), tmp2.getMinute(), true,tmp2.getDisable()));
            }
        }
        for (int i = 0; i<13; i++){
            if(shh.get(i).getDisable()==false && shh.get(i).getAvailable()==false){
                gio = shh.get(i).getHour() + ":" +shh.get(i).getMinute();
            }
        }
        RecyclerView recyclerView1 =(RecyclerView)findViewById(R.id.recycler_hour);
        recyclerView1.setHasFixedSize(false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(layoutManager1);
        final ShowhourAdapter showhourAdapter = new ShowhourAdapter(shh,getApplicationContext());
        recyclerView1.setAdapter(showhourAdapter);
        play = (ImageView)findViewById(R.id.Play);
        cgv = (Button)findViewById(R.id.cgv);
        galaxy=(Button)findViewById(R.id.galaxy);
        lotte = (Button)findViewById(R.id.lotte);
        cgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == true){
                    galaxy.setBackgroundResource(R.drawable.galaxy);
                    lotte.setBackgroundResource(R.drawable.lotte);
                }
                    cgv.setBackgroundResource(R.drawable.cdark);
                rap = "CGV";
                isSelected = true;
            }
        });
        lotte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == true) {
                    galaxy.setBackgroundResource(R.drawable.galaxy);
                    cgv.setBackgroundResource(R.drawable.cgv);
                }
                    lotte.setBackgroundResource(R.drawable.ldark);
                rap = "Lotte";
                isSelected = true;
            }
        });
        galaxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected == true) {
                    cgv.setBackgroundResource(R.drawable.cgv);
                    lotte.setBackgroundResource(R.drawable.lotte);
                }
                galaxy.setBackgroundResource(R.drawable.gdark);
                rap =  "Galaxy";
                isSelected = true;
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("Title",mif.getMovieTitle());
                // this work !!!
                Log.d("Huan",showhourAdapter.getCurrentHour());
                intent.putExtra("Date",showtimesAdapter.getCurrentDay());
                intent.putExtra("Time",showhourAdapter.getCurrentHour());
                intent.putExtra("Duration",tl);
                intent.putExtra("Cinema",rap);
                startActivity(intent);
            }
        });
         }

        }
