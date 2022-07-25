package com.example.myplanner.month

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.Company.CompanyModel
import com.example.myplanner.Company.PriorityAdapter
import com.example.myplanner.Company.RepeatAdapter
import com.example.myplanner.Company.companyAdapter1
import com.example.myplanner.R
import com.example.myplanner.daily.DailyAdaper
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import kotlinx.android.synthetic.main.activity_add_event.*
import java.util.ArrayList

class MonthlyEventListAdapter(
    val context: Context,
    private var monthlyPlannig: ArrayList<DailyPlanner>,
) : RecyclerView.Adapter<MonthlyEventListAdapter.Viewholder>() {

    var spinnerCompanyList: ArrayList<CompanyModel>? = null
    var spinnerPriorityList: ArrayList<CompanyModel>? = null
    private var spinnerRepeatList: ArrayList<CompanyModel>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Viewholder {

        return Viewholder(

            LayoutInflater.from(context).inflate(R.layout.monthly_item_list, p0, false)
        )
    }

    override fun getItemCount(): Int {
        return monthlyPlannig.size

    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        priorityList()
        companyList()
        repeatList()
        holder.linearMain.setOnClickListener({
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialogweekly)
            val db = DatabaseHandler(context)
            //  weeklyData.addAll(db.getWeeklyClickEvent(event.id.toInt()))
            val btnClose: ImageButton = dialog.findViewById(R.id.btnClose) as ImageButton

            val dailyPlanner: DailyPlanner = db.getWeeklyClickEvent(monthlyPlannig[position].id)
            val addevent_tvEname: EditText = dialog.findViewById(R.id.addevent_tvEname) as EditText
            val addevent_tvEdesc: EditText = dialog.findViewById(R.id.addevent_tvEdesc) as EditText
            val addevent_tvDate: TextView = dialog.findViewById(R.id.addevent_tvDate) as TextView
            val addevent_tvStime: TextView = dialog.findViewById(R.id.addevent_tvStime) as TextView
            val addevent_tvEtime: TextView = dialog.findViewById(R.id.addevent_tvEtime) as TextView
            val addevent_tvNotification: TextView =
                dialog.findViewById(R.id.addevent_tvNotification) as TextView
            val spinnerCompany: Spinner = dialog.findViewById(R.id.spinnerCompany) as Spinner
            val spinnerPriority: Spinner = dialog.findViewById(R.id.spinnerPriority) as Spinner
            val spinnerRepeat: Spinner = dialog.findViewById(R.id.spinnerRepeat) as Spinner


            btnClose.setOnClickListener {
                dialog.dismiss()
                addevent_tvEname.setText("")
                addevent_tvEdesc.setText("")
            }
            addevent_tvEname.setText(dailyPlanner.event_name)
            addevent_tvEdesc.setText(dailyPlanner.event_description)
            addevent_tvDate.setText(dailyPlanner.date)
            addevent_tvStime.setText(dailyPlanner.to_time)
            addevent_tvEtime.setText(dailyPlanner.from_time)
            addevent_tvNotification.setText(dailyPlanner.notification_description)

            addevent_tvEname.isCursorVisible = false
            addevent_tvEdesc.isCursorVisible = false
            addevent_tvDate.isCursorVisible = false
            addevent_tvStime.isCursorVisible = false
            addevent_tvEtime.isCursorVisible = false
            addevent_tvNotification.isCursorVisible = false

            addevent_tvDate.isFocusable = false
            addevent_tvEname.isFocusable = false
            addevent_tvEdesc.isFocusable = false
            addevent_tvStime.isFocusable = false
            addevent_tvEtime.isFocusable = false
            addevent_tvNotification.isFocusable = false
            spinnerPriority.isEnabled = false
            spinnerCompany.isEnabled = false
            spinnerRepeat.isEnabled = false

            val spnRepeat = context.resources.getStringArray(R.array.Repeat)
            if (spinnerCompany != null) {
                val adapter = companyAdapter1(context, spinnerCompanyList)
                spinnerCompany.adapter = adapter
            }
            if (spinnerPriority != null) {
                val adapter = PriorityAdapter(
                    context, spinnerPriorityList
                )
                spinnerPriority.adapter = adapter
            }

            if (spinnerRepeat != null) {
                val adapter = RepeatAdapter(context, spinnerRepeatList)
                spinnerRepeat.adapter = adapter
            }

            spinnerRepeat.setSelection(dailyPlanner.repeat)
            spinnerPriority.setSelection(dailyPlanner.priority)
            spinnerCompany.setSelection(dailyPlanner.company)

            dialog.setCanceledOnTouchOutside(true);
            dialog.show()
        })

        holder.txtDate.text = monthlyPlannig[position].day.toString()
        holder.txtEvent.text = monthlyPlannig[position].event_name
        holder.txtTime.text = monthlyPlannig[position].to_time

    }

    private fun priorityList() {
        spinnerPriorityList = ArrayList()
        spinnerPriorityList!!.add(CompanyModel("Please Select Priority"))
        spinnerPriorityList!!.add(CompanyModel("Urgent"))
        spinnerPriorityList!!.add(CompanyModel("High"))
        spinnerPriorityList!!.add(CompanyModel("Medium"))
        spinnerPriorityList!!.add(CompanyModel("Low"))
    }

    private fun companyList() {
        spinnerCompanyList = ArrayList()
        spinnerCompanyList!!.add(CompanyModel("Please Select Company"))
        spinnerCompanyList!!.add(CompanyModel("ADM"))
        spinnerCompanyList!!.add(CompanyModel("ASL"))
        spinnerCompanyList!!.add(CompanyModel("SRPL"))
        spinnerCompanyList!!.add(CompanyModel("ULTRA"))
        spinnerCompanyList!!.add(CompanyModel("GALACTIC"))
        spinnerCompanyList!!.add(CompanyModel("PARCOTICS"))
        spinnerCompanyList!!.add(CompanyModel("PERSONAL"))
    }

    private fun repeatList() {
        spinnerRepeatList = ArrayList()
        spinnerRepeatList!!.add(CompanyModel("Please Select Repeat Or Not"))
        spinnerRepeatList!!.add(CompanyModel("No Repetition"))
        spinnerRepeatList!!.add(CompanyModel("Week"))
        spinnerRepeatList!!.add(CompanyModel("Month"))
        spinnerRepeatList!!.add(CompanyModel("Year"))
    }


    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtDate: TextView = itemView.findViewById(R.id.mevent_date)
        var txtEvent: TextView = itemView.findViewById(R.id.mevent_event)
        var txtTime: TextView = itemView.findViewById(R.id.mevent_time)
        var linearMain: LinearLayout = itemView.findViewById(R.id.linearMain)


    }

}