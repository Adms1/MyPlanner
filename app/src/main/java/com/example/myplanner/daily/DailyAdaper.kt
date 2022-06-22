package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.pojo.DailyPlanner
import com.github.dhaval2404.colorpicker.util.setVisibility
import java.text.DateFormatSymbols
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd MM, yyyy")
        val formatted = current.format(formatter)
        val date = Calendar.getInstance().time
        val strDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val strMonth = getMonthForInt(Calendar.getInstance().get(Calendar.MONTH))
        val strYear = Calendar.getInstance().get(Calendar.YEAR)
        val Date = (strDay.toString() + " " + (strMonth) + ", " + strYear)

        if (listOfDailyPlan[position].date.equals(Date)) {
            holder.mainCardView.visibility = View.VISIBLE

        } else {
            holder.mainCardView.visibility = View.VISIBLE


        }



        holder.txtEvent.text = listOfDailyPlan[position].event_name
        holder.txtDate.text = listOfDailyPlan[position].to_time + "     " + listOfDailyPlan[position].from_time
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

        fun getMonthForInt(num: Int): String? {
            var month = "wrong"
            val dfs = DateFormatSymbols()
            val months: Array<String> = dfs.getMonths()
            if (num >= 0 && num <= 11) {
                month = months[num]
            }
            return month
        }


    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var calEditDateTime: ImageView = itemView.findViewById(R.id.calEditDateTime)
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        var txtEvent: TextView = itemView.findViewById(R.id.txtEvent)
        var mainCardView: CardView = itemView.findViewById(R.id.mainCardView);

    }

}