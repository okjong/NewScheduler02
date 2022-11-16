package com.jeilpharm.newscheduler02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jeilpharm.newscheduler02.databinding.ActivityHospitalBinding;

import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {

    ActivityHospitalBinding binding;

    ArrayList<Recycler_item> items= new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHospitalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter= new MyAdapter(this, items);
        binding.recyclerView.setAdapter(adapter);



    }
}