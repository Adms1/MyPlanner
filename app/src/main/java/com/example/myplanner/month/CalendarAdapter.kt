package com.example.myplanner.month

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.CalendarUtils
import com.example.myplanner.R
import com.example.myplanner.month.CalendarAdapter.OnItemListener
import com.example.myplanner.month.CalendarViewHolder
import java.time.LocalDate
import java.util.ArrayList

internal class CalendarAdapter(
    private val days: ArrayList<LocalDate>,
    private val onItemListener: OnItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams
        if (days.size > 15) //month view
            layoutParams.height = (parent.height * 0.166666666).toInt() else  // week view
            layoutParams.height = parent.height
        return CalendarViewHolder(view, onItemListener, days)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = days[position]
        if (date == null) holder.dayOfMonth.text = "" else {
            holder.dayOfMonth.text = date.dayOfMonth.toString()
            if (date == CalendarUtils.selectedDate) holder.parentView.setBackgroundColor(Color.LTGRAY)
            if (date == CalendarUtils.selectedDate) holder.dayOfMonth.setTextColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate?)
    }
}