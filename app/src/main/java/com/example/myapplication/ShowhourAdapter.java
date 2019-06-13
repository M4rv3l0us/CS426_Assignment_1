package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ShowhourAdapter extends RecyclerView.Adapter<ShowhourAdapter.ViewHolder>{
    @NonNull
    ArrayList<Showhour> showhours;
    Context context;
    int row_index;
    int selectedHour = 0;

    public ShowhourAdapter(@NonNull ArrayList<Showhour> showhours, Context context) {
        this.showhours = showhours;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView2 = layoutInflater.inflate(R.layout.hour_row,viewGroup,false);
        return  new ViewHolder(itemView2);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.btnHour.setText(showhours.get(i).getHour().toString() + ":" + showhours.get(i).getMinute().toString());
        viewHolder.btnHour.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN && showhours.get(i).getDisable()==false){
                    row_index = i;
                    Button b = ((Button)v);
                    b.setPressed(true);
                    showhours.get(i).setAvailable(false);
                    notifyDataSetChanged();
                }
                selectedHour = i;
                return true;
            }
        });
        if(showhours.get(i).getDisable() == false && viewHolder.btnHour.isEnabled() == true){
        if (row_index == i){
            viewHolder.btnHour.setPressed(true);
        }
        else{
                showhours.get(i).setAvailable(false);
                viewHolder.btnHour.setPressed(false);
        }
        }
        else {
            viewHolder.btnHour.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return showhours.size();
    }
    public String getCurrentHour(){
        return showhours.get(selectedHour).getHour() +":" +showhours.get(selectedHour).getMinute();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{
        Button btnHour;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnHour =(Button)itemView.findViewById(R.id.btnHour);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }
}
