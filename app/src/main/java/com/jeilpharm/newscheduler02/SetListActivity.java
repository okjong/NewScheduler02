package com.jeilpharm.newscheduler02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.jeilpharm.newscheduler02.databinding.ActivitySetListBinding;

import java.util.ArrayList;

public class SetListActivity extends AppCompatActivity implements NetworkCallback{

    ActivitySetListBinding binding;

    MyAdapter adapter;
    ArrayList<Recycler_item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySetListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarSetList);
        getSupportActionBar().setTitle("거래처");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter=new MyAdapter(this,items);
        
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {

            }
        });
        
        binding.recyclerViewSetList.setAdapter(adapter);

        binding.btnSetList.setOnClickListener(view -> {
            NetworkModule networkModule= new NetworkModule(SetListActivity.this,SetListActivity.this);
            networkModule.runGetList();

        });
    }

    @Override
    public void prevGetList() {
        items.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void endGetList(ArrayList<Recycler_item> arrayList) {
        adapter.setArrayList(arrayList);
    }
}