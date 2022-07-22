package com.example.myplanner.month

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.CalendarUtils
import com.example.myplanner.DashboardActivity
import com.example.myplanner.R
import com.example.myplanner.Utils
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.month.CalendarAdapter.OnItemListener
import com.example.myplanner.pojo.DailyPlanner
import java.time.LocalDate

class MonthWeekViewActivity : AppCompatActivity(), OnItemListener {
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var eventListView: RecyclerView? = null

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly)
        initWidgets()
        CalendarUtils.selectedDate = LocalDate.now()
        setWeekView()

    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
        eventListView = findViewById(R.id.monthly_rvList)
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setWeekView() {
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.title = "  Monthly Plan  "

        val dailyEvents = CalendarUtils.selectedDate
        val Date = Utils.parseDateToddMMyyyy(dailyEvents.toString())
        val db = DatabaseHandler(applicationContext)
        val monthlyPalnning: ArrayList<DailyPlanner> = db.getMonthly("'" + Date + "'")


        monthYearText!!.setText(CalendarUtils.monthYearFromDate(CalendarUtils.selectedDate))
        val days = CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapter(days, this, monthlyPalnning)
        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        /* layoutManager = object : GridLayoutManager(this, 7) {
             override fun canScrollVertically(): Boolean {
                 return true
             }
         }*/
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
        setEventAdpater()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun previousWeekAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1)
        setWeekView()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun nextWeekAction(view: View?) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)
        setWeekView()
    }


    override fun onResume() {
        super.onResume()
        setEventAdpater()
    }

    private fun setEventAdpater() {
        val dailyEvents = CalendarUtils.selectedDate
        val Date = Utils.parseDateToddMMyyyy(dailyEvents.toString())
        val db = DatabaseHandler(applicationContext)
        val monthlyPalnning: ArrayList<DailyPlanner> = db.getMonthly("'" + Date + "'")
        eventListView!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        eventListView!!.adapter = MonthlyEventListAdapter(this, monthlyPalnning)
    } /*public void newEventAction(View view)
    {
        startActivity(new Intent(this, EventEditActivity.class));
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate?) {
        CalendarUtils.selectedDate = date
        setWeekView()
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        return true
    }

    override fun onBackPressed() {
        finish()
        return
    }
}