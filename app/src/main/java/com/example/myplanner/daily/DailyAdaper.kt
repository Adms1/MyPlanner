package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import java.text.DateFormatSymbols
import java.util.*

class DailyAdaper(
    var context: Context,
    var listOfDailyPlan: ArrayList<DailyPlanner>,
    var date1: String? = ""
) : RecyclerView.Adapter<DailyAdaper.viewholder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewholder {
        return viewholder(
            LayoutInflater.from(context).inflate(R.layout.daily_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfDailyPlan.size

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val strDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val strMonth = getMonthForInt(Calendar.getInstance().get(Calendar.MONTH))
        val strYear = Calendar.getInstance().get(Calendar.YEAR)
        var Date = (strDay.toString() + " " + (strMonth) + ", " + strYear)

        if (holder.oneTimeCheck.equals(true)) {
            holder.oneTimeCheck = false
            if (listOfDailyPlan.get(position).date.equals(date1)) {
                holder.headingView.setVisibility(View.GONE);
            } else {
                holder.headingView.setVisibility(View.VISIBLE);
                holder.headingView.setText(listOfDailyPlan[position].date);
                date1 = holder.headingView.text.toString()
            }
        }



        holder.txtEvent.text = listOfDailyPlan[position].event_name
        holder.txtTime.text = listOfDailyPlan[position].to_time
        holder.imgEditCal.setOnClickListener({
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
            editor.apply()
            val intent = Intent(context, AddEventActivity::class.java)
            context.startActivity(intent)
        })
/*
        holder.imgStatus.setOnClickListener({
            val db = DatabaseHandler(context)
            db.UpdateStatus(listOfDailyPlan[position].id)
            listOfDailyPlan.removeAt(position);
            notifyDataSetChanged();

        })
*/
        holder.lastDate = holder.headingView.text.toString()
    }

    fun getMonthForInt(num: Int): String {
        var month = "wrong"
        val dfs = DateFormatSymbols()
        val months: Array<String> = dfs.getMonths()
        if (num >= 0 && num <= 11) {
            month = months[num]
        }
        return month
    }


    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgEditCal: ImageView = itemView.findViewById(R.id.imgEditCal)
        var txtTime: TextView = itemView.findViewById(R.id.txtTime)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)
        var mainCardView: CardView = itemView.findViewById(R.id.mainCardView)
        var LinearLayout: LinearLayout = itemView.findViewById(R.id.liner2)
     //   var imgStatus: ImageView = itemView.findViewById(R.id.imgStatus)
        var headingView: TextView = itemView.findViewById(R.id.headingView)
        var lastDate: String? = ""
        var oneTimeCheck: Boolean = true

    }

}