package com.example.myplanner.daily

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.R
import com.example.myplanner.pojo.DailyPlanner
import java.util.*

class DailyCompleteAdaper(
    var context: Context,
    private var listOfDailyPlan: ArrayList<DailyPlanner>,
    private var date1: String? = ""

) : RecyclerView.Adapter<DailyCompleteAdaper.Viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Viewholder {

        return Viewholder(

            LayoutInflater.from(context).inflate(R.layout.daily_item_list_complete, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfDailyPlan.size

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        if (listOfDailyPlan[position].date == date1) {
            holder.headingView.visibility = View.GONE
            holder.linearLayout.visibility = View.GONE

        } else {
            holder.headingView.visibility = View.VISIBLE
            holder.linearLayout.visibility = View.VISIBLE

            holder.headingView.text = listOfDailyPlan[position].date
            date1 = holder.headingView.text.toString()
            Log.d("date", date1.toString())
        }
        holder.txtEvent.text = listOfDailyPlan[position].event_name
        holder.txtTime.text = listOfDailyPlan[position].to_time

        holder.lastDate = holder.headingView.text.toString()
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTime: TextView = itemView.findViewById(R.id.txtTime)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linerMain)
        var headingView: TextView = itemView.findViewById(R.id.headingView)
        var lastDate: String? = ""
    }

}