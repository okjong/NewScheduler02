package com.jeilpharm.newscheduler02;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Schedule.class},version = 1)
public abstract class DB extends RoomDatabase {

    private static DB INSTANCE=null;
    public abstract DAO dao();

    public static DB getInstance(Context context){
     if (INSTANCE==null){
         INSTANCE= Room.databaseBuilder(context.getApplicationContext(),DB.class,"schedule.db").build();
     }
     return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }



}
