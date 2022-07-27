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
import java.text.SimpleDateFormat
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
            }
        }

        if (listOfDailyPlan[position].RepeatOrNot.equals("true")) {
            holder.imgRepeat.visibility = View.VISIBLE

        } else {
            holder.imgRepeat.visibility = View.GONE

        }

        if (listOfDailyPlan[position].ProrityID.equals(1)) {
            holder.txtPriority.setVisibility(View.VISIBLE)
            holder.imgpriority.setVisibility(View.GONE)

        } else if (listOfDailyPlan[position].ProrityID.equals(2)) {
            holder.imgpriority.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_arrow_upward_24))
            holder.txtPriority.setVisibility(View.GONE)

        } else if (listOfDailyPlan[position].ProrityID.equals(3)) {
            holder.imgpriority.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_notes_24))
            holder.txtPriority.setVisibility(View.GONE)

        } else if (listOfDailyPlan[position].ProrityID.equals(4)) {
            holder.imgpriority.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_arrow_downward_24))
            holder.txtPriority.setVisibility(View.GONE)

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

        holder.eventItemHolder.setOnClickListener {
            val sharedPreference =
                context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString("editOrNotDateTime", "editDateTime")
            editor.putInt("id", listOfDailyPlan[position].id)
            val Date = listOfDailyPlan[position].plandate
            var format = SimpleDateFormat("yyyy/MM/dd")
            val newDate: Date = format.parse(Date)
            format = SimpleDateFormat("dd/MM/yyyy")
            val date: String = format.format(newDate)
            editor.putString("date", date)
            editor.putInt("toTime", listOfDailyPlan[position].ToTime)
            editor.putInt("fromTime", listOfDailyPlan[position].FromTime)
            editor.putString("eventName", listOfDailyPlan[position].EventName)
            editor.putString("eventDescription", listOfDailyPlan[position].EventDescription)
            editor.putInt("company", listOfDailyPlan[position].CompanyID)
            editor.putInt("Priority", listOfDailyPlan[position].ProrityID)
            editor.putInt("repeat", listOfDailyPlan[position].RepeatModeID)

            editor.putInt("StartHours", listOfDailyPlan[position].StartHours)
            editor.putInt("StartMin", listOfDailyPlan[position].StartMin)
            editor.putInt("EndHours", listOfDailyPlan[position].EndHours)
            editor.putInt("EndMin", listOfDailyPlan[position].EndMin)
            editor.putInt("Day", listOfDailyPlan[position].Day)
            editor.putInt("Month", listOfDailyPlan[position].Month)
            editor.putInt("Year", listOfDailyPlan[position].Year)
            editor.apply()
            val intent = Intent(context, AddEventActivity::class.java)
            context.startActivity(intent)
        }
        val strCurrentDate = holder.headingView.text.toString()

    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTime: TextView = itemView.findViewById(R.id.txtTime)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)
        var imgRepeat: ImageView = itemView.findViewById(R.id.imgRepeat)
        var imgpriority: ImageView = itemView.findViewById(R.id.imgPriority)
        var txtPriority: TextView = itemView.findViewById(R.id.txtPriority)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linerMain)
        var eventItemHolder: ConstraintLayout = itemView.findViewById(R.id.event_item_holder)
        var headingView: TextView = itemView.findViewById(R.id.headingView)
        var lastDate: String? = ""
        var oneTimeCheck: Boolean = true

        var event_item_color_bar: ImageView = itemView.findViewById(R.id.event_item_color_bar)
        var event_item_color_bar1: ImageView = itemView.findViewById(R.id.event_item_color_bar1)

    }
}