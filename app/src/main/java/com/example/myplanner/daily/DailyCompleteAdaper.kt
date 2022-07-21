package com.example.myplanner.daily

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        if (listOfDailyPlan[position].company.equals(2)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.teal_A400))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.teal_A400))

        } else if (listOfDailyPlan[position].company.equals(3)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.green_400))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.green_400))
        } else if (listOfDailyPlan[position].company.equals(4)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.yellow_900))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.yellow_900))
        } else if (listOfDailyPlan[position].company.equals(5)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.black))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.black))
        } else if (listOfDailyPlan[position].company.equals(6)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.darkBlue))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.darkBlue))
        } else if (listOfDailyPlan[position].company.equals(7)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.red))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.red))
        } else if (listOfDailyPlan[position].company.equals(8)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.pista))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.pista))
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

        var event_item_color_bar: ImageView = itemView.findViewById(R.id.event_item_color_bar)
        var event_item_color_bar1: ImageView = itemView.findViewById(R.id.event_item_color_bar1)

    }

}