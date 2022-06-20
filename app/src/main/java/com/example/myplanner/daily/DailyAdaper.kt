package com.example.myplanner.daily

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.month.MonthlyEventListAdapter
import com.example.myplanner.daily.DailyAdaper
import java.util.*

class DailyAdaper(val context: Context) : RecyclerView.Adapter<DailyAdaper.viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {

        return viewholder(

            LayoutInflater.from(context).inflate(R.layout.daily_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return 3

    }

    override fun onBindViewHolder(holder: viewholder, p1: Int) {

        holder.calEditDateTime.setOnClickListener({

            val sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("editDateTime", "editDateTime")
            editor.commit()
            val intent = Intent(context, AddEventActivity::class.java)
            context.startActivity(intent)
        })
    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var calEditDateTime: ImageView = itemView.findViewById(R.id.calEditDateTime)
    }

}