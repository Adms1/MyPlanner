package com.example.myplanner.daily

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.R
import com.example.myplanner.month.MonthlyEventListAdapter

class DailyAdaper(val context: Context) : RecyclerView.Adapter<DailyAdaper.viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {

        return viewholder(

            LayoutInflater.from(context).inflate(R.layout.daily_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return 3

    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {


    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}