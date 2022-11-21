package com.jeilpharm.newscheduler02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_schedule extends RecyclerView.Adapter<Adapter_schedule.VH> {

    private List<Schedule> scheduleList;
    public Adapter_schedule(List<Schedule> list){
        scheduleList=list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shcedule,parent,false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Schedule item=scheduleList.get(position);
        holder.titleSchedule.setText(item.title);
        holder.categorySchedule.setText(item.category);
        holder.dateSchedule.setText(Integer.valueOf(item.date));
        holder.memoSchedule.setText(item.memo);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class VH extends RecyclerView.ViewHolder{

        TextView titleSchedule;
        TextView categorySchedule;
        TextView dateSchedule;
        TextView memoSchedule;

        public VH(@NonNull View itemView) {
            super(itemView);

            titleSchedule=itemView.findViewById(R.id.title_schedule);
            categorySchedule=itemView.findViewById(R.id.category_schedule);
            dateSchedule=itemView.findViewById(R.id.date_schedule);
            memoSchedule=itemView.findViewById(R.id.memo_schedule);
        }
    }
}
