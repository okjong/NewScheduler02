package com.jeilpharm.newscheduler02;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MonthFragment extends Fragment {

    private DB db=null;
    private List<Schedule> scheduleList;
    private Context context=null;
    Adapter_schedule adapter_schedule;

    CalendarView calendarView;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_month,container, false);

        recyclerView=view.findViewById(R.id.recycler_month);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                adapter_schedule=new Adapter_schedule(scheduleList);
                recyclerView.setAdapter(adapter_schedule);
            }
        });



        return view;
    }
}
