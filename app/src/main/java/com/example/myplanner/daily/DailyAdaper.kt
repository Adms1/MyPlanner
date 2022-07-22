package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.pojo.DailyPlanner
import com.github.dhaval2404.colorpicker.util.setVisibility
import java.util.*

class DailyAdaper(
    var context: Context,
    private var listOfDailyPlan: ArrayList<DailyPlanner>,
    private var date1: String? = ""
) : RecyclerView.Adapter<DailyAdaper.Viewholder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Viewholder {
        return Viewholder(

            LayoutInflater.from(context).inflate(R.layout.daily_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfDailyPlan.size

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Viewholder, position: Int) {


        if (holder.oneTimeCheck) {
            holder.oneTimeCheck = false
            if (listOfDailyPlan[position].date == date1) {
                holder.headingView.visibility = View.GONE
                holder.linearLayout.visibility = View.GONE
            } else {
                holder.headingView.visibility = View.VISIBLE
                holder.linearLayout.visibility = View.VISIBLE
                holder.headingView.text = listOfDailyPlan[position].date
                date1 = holder.headingView.text.toString()
            }
        }
        if (listOfDailyPlan[position].RepeatOrNot.equals("true")) {
            holder.imgRepeat.visibility = View.VISIBLE

        } else {
            holder.imgRepeat.visibility = View.GONE

        }
        if (listOfDailyPlan[position].company.equals(1)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.darkBlue))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.darkBlue))

        } else if (listOfDailyPlan[position].company.equals(2)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.orange))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.orange))
        } else if (listOfDailyPlan[position].company.equals(3)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.cyan))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.cyan))
        } else if (listOfDailyPlan[position].company.equals(4)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.grayorgray))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.grayorgray))
        } else if (listOfDailyPlan[position].company.equals(5)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.green))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.green))
        } else if (listOfDailyPlan[position].company.equals(6)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.olive))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.olive))
        } else if (listOfDailyPlan[position].company.equals(7)) {
            holder.event_item_color_bar.setBackgroundColor(context.resources.getColor(R.color.brown))
            holder.event_item_color_bar1.setBackgroundColor(context.resources.getColor(R.color.brown))
        }


        holder.txtEvent.text = listOfDailyPlan[position].event_name
        holder.txtTime.text = listOfDailyPlan[position].to_time

        holder.eventItemHolder.setOnClickListener {
            val sharedPreference =
                context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString("editOrNotDateTime", "editDateTime")
            editor.putInt("id", listOfDailyPlan[position].id)
            editor.putString("date", listOfDailyPlan[position].date)
            editor.putString("toTime", listOfDailyPlan[position].to_time)
            editor.putString("fromTime", listOfDailyPlan[position].from_time)
            editor.putString("eventName", listOfDailyPlan[position].event_name)
            editor.putString("eventDescription", listOfDailyPlan[position].event_description)
            editor.putString(
                "notificationDescription",
                listOfDailyPlan[position].notification_description
            )
            editor.putInt("company", listOfDailyPlan[position].company)
            editor.putInt("Priority", listOfDailyPlan[position].priority)

            editor.putInt("repeat", listOfDailyPlan[position].repeat)

            editor.putInt("StartHours", listOfDailyPlan[position].starthours)
            editor.putInt("StartMin", listOfDailyPlan[position].startmin)
            editor.putInt("EndHours", listOfDailyPlan[position].endhours)
            editor.putInt("EndMin", listOfDailyPlan[position].endmin)
            editor.putInt("Day", listOfDailyPlan[position].day)
            editor.putString("Month", listOfDailyPlan[position].month)
            editor.putInt("Year", listOfDailyPlan[position].year)
            editor.apply()
            val intent = Intent(context, AddEventActivity::class.java)
            context.startActivity(intent)
        }
        holder.lastDate = holder.headingView.text.toString()
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTime: TextView = itemView.findViewById(R.id.txtTime)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)
        var imgRepeat: ImageView = itemView.findViewById(R.id.imgRepeat)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linerMain)
        var eventItemHolder: ConstraintLayout = itemView.findViewById(R.id.event_item_holder)
        var headingView: TextView = itemView.findViewById(R.id.headingView)
        var lastDate: String? = ""
        var oneTimeCheck: Boolean = true

        var event_item_color_bar: ImageView = itemView.findViewById(R.id.event_item_color_bar)
        var event_item_color_bar1: ImageView = itemView.findViewById(R.id.event_item_color_bar1)

    }
}