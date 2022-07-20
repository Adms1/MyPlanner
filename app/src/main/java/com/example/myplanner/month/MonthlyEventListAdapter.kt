package com.example.myplanner.month

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.R
import com.example.myplanner.daily.DailyAdaper
import com.example.myplanner.pojo.DailyPlanner
import java.util.ArrayList

class MonthlyEventListAdapter(val context: Context,
                              private var monthlyPlannig: ArrayList<DailyPlanner>,
) : RecyclerView.Adapter<MonthlyEventListAdapter.Viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Viewholder {

        return Viewholder(

            LayoutInflater.from(context).inflate(R.layout.monthly_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return monthlyPlannig.size

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.txtDate.text = monthlyPlannig[position].day.toString()
        holder.txtEvent.text = monthlyPlannig[position].event_name
        holder.txtTime.text = monthlyPlannig[position].to_time
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtDate: TextView = itemView.findViewById(R.id.mevent_date)
        var txtEvent: TextView = itemView.findViewById(R.id.mevent_event)
        var txtTime: TextView = itemView.findViewById(R.id.mevent_time)


    }

}