package com.example.myplanner.old

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.R

class ScheduleAdapter(val context: Context) : RecyclerView.Adapter<ScheduleAdapter.viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {

        return viewholder(

            LayoutInflater.from(context).inflate(R.layout.schedule_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return 4

    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {

    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
