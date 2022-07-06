package com.example.myplanner.month

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.R

class MonthlyEventListAdapter(val context: Context) : RecyclerView.Adapter<MonthlyEventListAdapter.viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {

        return viewholder(

            LayoutInflater.from(context).inflate(R.layout.monthly_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return 2

    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {


    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}