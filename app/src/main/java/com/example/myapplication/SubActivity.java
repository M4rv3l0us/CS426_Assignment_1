package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    ArrayList<String> Genres;
    TextView mname, mrun, mIM, mfm, cname, cdate,chour;
    MovieInfo mif;
    LinearLayout mgen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        Intent intent = getIntent();
        String Title = intent.getStringExtra("Title");
        String Date = intent.getStringExtra("Date");
        String Time = intent.getStringExtra("Time");
        String Cinema = intent.getStringExtra("Cinema");

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
        mrun.setText("- " + mif.getRuntime() + "m");
        mIM = (TextView) findViewById(R.id.IMDB);
        mIM.setText(mif.getIMDB());
        mfm = (TextView) findViewById(R.id.Format);
        mfm.setText(mif.getMovieFormat());
        cname = (TextView) findViewById(R.id.CinemaName);
        cname.setText(Cinema);
        cname.setTextColor(Color.parseColor("#3D3BEE"));
        cname.setTextSize(10);
        cdate = (TextView)findViewById(R.id.CinDate);
        cdate.setText(Date);
        cdate.setTextColor(Color.parseColor("#3D3BEE"));
        cdate.setTextSize(10);
        chour = (TextView)findViewById(R.id.CinTime);
        chour.setText(Time);
        chour.setTextSize(10);
        chour.setTextColor(Color.parseColor("#3D3BEE"));
    }
}
