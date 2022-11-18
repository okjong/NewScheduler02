package com.jeilpharm.newscheduler02

import android.widget.CalendarView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jeilpharm.newscheduler02.R

class MonthFragment : Fragment() {

    var calendarView: CalendarView? = null
    var behavior: BottomSheetBehavior<*>? = null
    var bottomsheet: View? = null
    var position = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_month, container, false)
    }
}