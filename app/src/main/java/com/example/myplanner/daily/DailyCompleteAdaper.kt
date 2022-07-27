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
import java.text.SimpleDateFormat
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
        val strCurrentDate = listOfDailyPlan[position].plandate
        var format = SimpleDateFormat("yyyy/MM/dd")
        val newDate: Date = format.parse(strCurrentDate)
        format = SimpleDateFormat("dd/MM/yyyy")
        val date: String = format.format(newDate)
        var dateFormat = date
        if (dateFormat == date1) {
            holder.headingView.visibility = View.GONE
            holder.linearLayout.visibility = View.GONE

        } else {
            holder.headingView.visibility = View.VISIBLE
            holder.linearLayout.visibility = View.VISIBLE

            val strCurrentDate = listOfDailyPlan[position].plandate
            var format = SimpleDateFormat("yyyy/MM/dd")
            val newDate: Date = format.parse(strCurrentDate)
            format = SimpleDateFormat("dd/MM/yyyy")
            val date: String = format.format(newDate)
            date1 = date
            holder.headingView.text = date1

            /* holder.headingView.text = listOfDailyPlan[position].date
             date1 = holder.headingView.text.toString()
             Log.d("date", date1.toString())*/
        }

        if (listOfDailyPlan[position].CompanyID.equals(1)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.darkBlue))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.darkBlue))
        } else if (listOfDailyPlan[position].CompanyID.equals(2)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.orange))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.orange))
        } else if (listOfDailyPlan[position].CompanyID.equals(3)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.cyan))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.cyan))
        } else if (listOfDailyPlan[position].CompanyID.equals(4)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.grayorgray))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.grayorgray))
        } else if (listOfDailyPlan[position].CompanyID.equals(5)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.green))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.green))
        } else if (listOfDailyPlan[position].CompanyID.equals(6)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.olive))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.olive))
        } else if (listOfDailyPlan[position].CompanyID.equals(7)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.brown))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.brown))
        }

        holder.txtEvent.text = listOfDailyPlan[position].EventName
        holder.txtTime.text = listOfDailyPlan[position].ToTime.toString()

    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTime: TextView = itemView.findViewById(R.id.txtTime)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linerMain)
        var headingView: TextView = itemView.findViewById(R.id.headingView)

        var event_item_color_bar: ImageView = itemView.findViewById(R.id.event_item_color_bar)
        var event_item_color_bar1: ImageView = itemView.findViewById(R.id.event_item_color_bar1)

    }

}