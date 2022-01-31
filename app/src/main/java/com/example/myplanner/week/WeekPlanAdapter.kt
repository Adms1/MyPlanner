package com.example.myplanner.week

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.R
import com.example.myplanner.Utils
import com.example.myplanner.task.TaskActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class WeekPlanAdapter(val context: Context, val arr: ArrayList<String>) :
    RecyclerView.Adapter<WeekPlanAdapter.viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {

        return viewholder(

            LayoutInflater.from(context).inflate(R.layout.weekly_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return arr.size

    }

    override fun onBindViewHolder(p0: viewholder, p1: Int) {

        p0.textv.text = arr[p1]

        p0.cv.setOnClickListener {

            when {
                arr[p1] == "Top Tasks" -> {
                    Utils.openDailyCal(
                        context,
                        TaskActivity::class.java
                    )
                }
                arr[p1] == "Weekly Goals" -> {
                    Utils.openDailyCal(
                        context,
                        WeeklyGoalsActivity::class.java
                    )
                }
                arr[p1] == "Weekly Review" -> {
                    Utils.openDailyCal(
                        context,
                        WeeklyReviewActivity::class.java
                    )
                }
                arr[p1] == "Habit Tracker" -> {
                    Utils.openDailyCal(
                        context,
                        HabitTrackerActivity::class.java
                    )
                }
            }

        }

    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val cv: CardView = itemView.findViewById(R.id.week_item_main)
        val textv: TextView = itemView.findViewById(R.id.daily_stime)

    }

}