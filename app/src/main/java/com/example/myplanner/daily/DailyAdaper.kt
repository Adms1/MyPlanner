package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.pojo.DailyPlanner
import java.util.*

class DailyAdaper(
    var context: Context,
    var listOfDailyPlan: ArrayList<DailyPlanner>
) : RecyclerView.Adapter<DailyAdaper.viewholder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {

        return viewholder(

            LayoutInflater.from(context).inflate(R.layout.daily_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfDailyPlan.size

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.txtEvent.text = listOfDailyPlan[position].event_name
        holder.txtDate.text =
            listOfDailyPlan[position].to_time + "     " + listOfDailyPlan[position].from_time


        holder.calEditDateTime.setOnClickListener({
            val sharedPreference =
                context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("editOrNotDateTime", "editDateTime")
            editor.putInt("id", listOfDailyPlan[position].id)
            editor.putString("date", listOfDailyPlan[position].date)
            editor.putString("date", listOfDailyPlan[position].date)
            editor.putString("toTime", listOfDailyPlan[position].to_time)
            editor.putString("fromTime", listOfDailyPlan[position].from_time)
            editor.putString("eventName", listOfDailyPlan[position].event_name)
            editor.putString("eventDescription", listOfDailyPlan[position].event_description)
            editor.putString(
                "notificationDescription",
                listOfDailyPlan[position].notification_description
            )
            editor.putString("Location", listOfDailyPlan[position].location)
            editor.putString("repeat", listOfDailyPlan[position].repeat)
            editor.commit()
            val intent = Intent(context, AddEventActivity::class.java)
            context.startActivity(intent)
        })
    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var calEditDateTime: ImageView = itemView.findViewById(R.id.calEditDateTime)
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)

    }

}