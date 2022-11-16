package com.jeilpharm.newscheduler02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
        binding.tvCategory.setOnClickListener(v->bottomsheeetdialogcategory());
        binding.btnComplete.setOnClickListener(v->clickComplete());
        binding.etTitle.setOnClickListener(v->clickTitle());

    }

    void clickTitle(){
        Intent intent= new Intent();
    }

    void clickComplete(){
        SQLiteDatabase db= openOrCreateDatabase("Todo",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS todo(num INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, date TEXT, category INT, memo TEXT)");
        String title=binding.etTitle.getText().toString();
        String memo=binding.etNote.getText().toString();

        db.execSQL("INSERT INTO Todo(title,date,category,memo)VALUES(?,?,?,?) " ,new Object[]{title,date,category,memo});
        onBackPressed();

    }

    void bottomsheeetdialogcategory(){
        bottomSheetDialog=new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheetdialog_category);
        bottomSheetDialog.show();

        bottomSheetDialog.findViewById(R.id.tv_bscategory_websimposium).setOnClickListener(v->clickCategory(0));
        bottomSheetDialog.findViewById(R.id.tv_bscategory_daysimposium).setOnClickListener(v->clickCategory(1));
        bottomSheetDialog.findViewById(R.id.tv_bscategory_staysimposium).setOnClickListener(v->clickCategory(2));
        bottomSheetDialog.findViewById(R.id.tv_bscategory_lunch).setOnClickListener(v->clickCategory(3));
        bottomSheetDialog.findViewById(R.id.tv_bscategory_dinner).setOnClickListener(v->clickCategory(4));
        bottomSheetDialog.findViewById(R.id.tv_bscategory_morning).setOnClickListener(v->clickCategory(5));
        bottomSheetDialog.findViewById(R.id.tv_bscategory_cookie).setOnClickListener(v->clickCategory(6));

    }

    void clickCategory(int category){

        this.category=category;
        binding.tvCategory.setText(categoryTitle[category]);
        bottomSheetDialog.dismiss();

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
































