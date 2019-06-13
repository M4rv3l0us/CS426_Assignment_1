package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowtimesAdapter extends RecyclerView.Adapter<ShowtimesAdapter.ViewHolder>{
    @NonNull
    ArrayList<Showtimes> showtimes;
    Context context;
    int row_index;
    int selectedItem = 0;

    protected StringBuilder day_n;
    public ShowtimesAdapter(@NonNull ArrayList<Showtimes> showtimes, Context context, StringBuilder day_n){
        this.showtimes = showtimes;
        this.context = context;
        this.day_n = day_n;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.date_row,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d("Huan",day_n.toString());
        viewHolder.btnDate.setText(showtimes.get(i).getDate().toString() + "/" + showtimes.get(i).getMonth().toString());
        viewHolder.btnDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    row_index = i;
                        Button b = ((Button) v);
                        b.setPressed(false);
                        showtimes.get(i).setAvailable(false);
                        notifyDataSetChanged();
                    }
                // Save current item
                selectedItem = i;
                return true;//Return true, so there will be no onClick-event
                }
        });
        if (row_index == i){
            showtimes.get(i).setAvailable(false);

            viewHolder.btnDate.setPressed(true);
        }
        else{
            showtimes.get(i).setAvailable(true);
            viewHolder.btnDate.setPressed(false);
        }
    }

    @Override
    public int getItemCount() {
        return showtimes.size();
    }


    public String getCurrentDay() {
        return showtimes.get(selectedItem).getDate().toString() +"/"+ showtimes.get(selectedItem).getMonth().toString();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{
        Button btnDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDate = (Button)itemView.findViewById(R.id.btnDate);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }

}
