package com.jeilpharm.newscheduler02;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

@Entity
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "date")
    public int date;
    @ColumnInfo(name = "memo")
    public String memo;



}
