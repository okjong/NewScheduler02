package com.jeilpharm.newscheduler02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.jeilpharm.newscheduler02.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;

    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnIntroStart.setOnClickListener(view -> {
            savedata();

            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        });

        loadData();


    }//oncreate....

    void loadData(){

        SharedPreferences pref=getSharedPreferences("account", MODE_PRIVATE);

        name=pref.getString("name","");
        binding.etNicname.setText(name);

    }

    void savedata(){
        name = binding.etNicname.getText().toString();

        SharedPreferences pref=getSharedPreferences("account",MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();

        editor.putString("name",name);

        editor.commit();
    }

}