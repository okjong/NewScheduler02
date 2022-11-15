package com.jeilpharm.newscheduler02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jeilpharm.newscheduler02.databinding.ActivityEditBinding;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.GregorianCalendar;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;

    int category;
    String[] categoryTitle= new String[]{"웹심포지움","당일심포지움","숙박심포지움","점심식사","저녁식사","조조판촉","간식"};

    BottomSheetDialog bottomSheetDialog;
    String date= "2022년09월30일";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("일정 추가");

        category=getIntent().getIntExtra("category",0);
        binding.tvCategory.setText(categoryTitle[category]);

        binding.tvDate.setOnClickListener(v->clickDate());

    }

    void clickDate(){

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheetdialog_calendar);
        bottomSheetDialog.show();

        CalendarView calendarView=bottomSheetDialog.findViewById(R.id.bsd_calenadar);
        calendarView.setOnDateChangeListener((view,year,month,day)->{

            GregorianCalendar gregorianCalendar= new GregorianCalendar(year,month,day);
            date= new SimpleDateFormat("yyyy년MM월dd일").format(gregorianCalendar.getTime());
            binding.tvDate.setText(date);

            bottomSheetDialog.dismiss();

        });

    }
}
































