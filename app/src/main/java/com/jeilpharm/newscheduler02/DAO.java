package com.jeilpharm.newscheduler02;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAO {

@Query("SELECT * FROM schedule")
    List<Schedule> getAll();

    @Query("SELECT * FROM schedule WHERE id IN (:scheduled)")
    List<Schedule> loadAllByIds(int[] scheduled);

    @Insert
    void InsertAll(Schedule...schedules);
    @Delete
    void Delete(Schedule...schedule);

}
