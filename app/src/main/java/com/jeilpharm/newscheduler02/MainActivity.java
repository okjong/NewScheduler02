package com.jeilpharm.newscheduler02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.jeilpharm.newscheduler02.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    ArrayList<Fragment> fragments = new ArrayList<>();
    FragmentManager manager;

    int category=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.fab.setOnClickListener(view -> {
            Intent intent= new Intent(this,EditActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);

        });

        fragments.add(new ListFragment());
        fragments.add(null);

        manager=getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container,fragments.get(0)).commit();

        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction=manager.beginTransaction();

                if (fragments.get(0)!=null) transaction.hide(fragments.get(0));
                if (fragments.get(1)!=null) transaction.hide(fragments.get(1));

                switch (item.getItemId()){
                    case R.id.list:
                        transaction.show(fragments.get(0));
                        break;
                    case R.id.month:
                        if (fragments.get(1)==null){
                            fragments.set(1, new MonthFragment());
                            transaction.add(R.id.fragment_container,fragments.get(1));
                }
                        transaction.show(fragments.get(1));
                        break;

            }
                transaction.commit();
                return true;
        }

    });
    }
}