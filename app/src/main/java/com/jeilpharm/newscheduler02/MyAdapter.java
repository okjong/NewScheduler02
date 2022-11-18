package com.jeilpharm.newscheduler02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Recycler_item> items;

    public MyAdapter(Context context, ArrayList<Recycler_item> items) {
        this.context = context;
        this.items = items;
    }

    public void setArrayList(ArrayList<Recycler_item> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Recycler_item item=items.get(position);
        holder.sigun.setText(item.tvSigun);
        holder.hospital.setText(item.tvHospital);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView sigun;
        TextView hospital;

        public VH(@NonNull View itemView) {
            super(itemView);

            sigun=itemView.findViewById(R.id.recylcer_item_sigun);
            hospital=itemView.findViewById(R.id.recycler_item_hospital);

        }
    }
}
