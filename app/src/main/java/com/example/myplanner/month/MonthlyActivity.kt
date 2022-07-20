package com.example.myplanner.month

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.example.myplanner.R
import kotlinx.android.synthetic.main.activity_monthly.*
import java.util.*

class MonthlyActivity : AppCompatActivity()/*, EventsCalendar.Callback*/ {

    /*  override fun onDayLongPressed(selectedDate: Calendar?) {
        Log.e(
            "LONG CLICKED",
            EventsCalendarUtil.getDateString(selectedDate, EventsCalendarUtil.DD_MM_YYYY)
        )
    }

    override fun onMonthChanged(monthStartDate: Calendar?) {
        Log.e("MON", "CHANGED")
    }

    override fun onDaySelected(selectedDate: Calendar?) {
        Log.e(
            "CLICKED", EventsCalendarUtil.getDateString(selectedDate, EventsCalendarUtil.DD_MM_YYYY)
        )
        setList(EventsCalendarUtil.getDateString(selectedDate, EventsCalendarUtil.DD_MM_YYYY))

    }
*/
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly)

        // selected.text = getDateString(eventsCalendar.getCurrentSelectedDate()?.timeInMillis)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.title = "  Monthly Plan  "




        /*  val today = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 2)
        eventsCalendar.setSelectionMode(eventsCalendar.MULTIPLE_SELECTION)
            .setToday(today)
            .setMonthRange(today, end)
            .setWeekStartDay(Calendar.MONDAY, false)
            .setIsBoldTextOnSelectionEnabled(true)

            //  .setDatesTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_REGULAR, this))
            //  .setMonthTitleTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this))
            //   .setWeekHeaderTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this))
            .setCallback(this)
            .build()

        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_MONTH, 2)
        eventsCalendar.addEvent(c)
        c.add(Calendar.DAY_OF_MONTH, 3)
        eventsCalendar.addEvent(c)
        c.add(Calendar.DAY_OF_MONTH, 4)
        eventsCalendar.addEvent(c)
        c.add(Calendar.DAY_OF_MONTH, 7)
        eventsCalendar.addEvent(c)
        c.add(Calendar.MONTH, 1)
        c[Calendar.DAY_OF_MONTH] = 1
        eventsCalendar.addEvent(c)

//        selected.typeface = FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this)

        val dc = Calendar.getInstance()
        dc.add(Calendar.DAY_OF_MONTH, 2)

        val c1 = Calendar.getInstance().time
        println("Current time => $c1")
        val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = df.format(c1)
        var date = (formattedDate)

        setList(date)
        monthly_ivAdd.setOnClickListener {
            val intent = Intent(this@MonthlyActivity, AddEventActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getDateString(time: Long?): String {
        if (time != null) {
            val cal = Calendar.getInstance()
            cal.timeInMillis = time
            val month = when (cal[Calendar.MONTH]) {
                Calendar.JANUARY -> "January"
                Calendar.FEBRUARY -> "February"
                Calendar.MARCH -> "March"
                Calendar.APRIL -> "April"
                Calendar.MAY -> "May"
                Calendar.JUNE -> "June"
                Calendar.JULY -> "July"
                Calendar.AUGUST -> "August"
                Calendar.SEPTEMBER -> "September"
                Calendar.OCTOBER -> "October"
                Calendar.NOVEMBER -> "November"
                Calendar.DECEMBER -> "December"
                else -> ""
            }
            return "$month ${cal[Calendar.DAY_OF_MONTH]}, ${cal[Calendar.YEAR]}"
        } else return ""
    }

    private fun  setList(date: String) {
        val db = DatabaseHandler(applicationContext)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val monthlyPalnning: ArrayList<DailyPlanner> = db.getMonthly("'" + date + "'")
        eventsCalendar.setEventDotColor(R.color.red)
        monthly_rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        monthly_rvList.adapter = MonthlyEventListAdapter(this, monthlyPalnning)
    }


    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@MonthlyActivity, DashboardActivity::class.java)
        startActivity(intent)
        return true
    }

    *//*override fun onBackPressed() {
        return
    }*//*

    private fun addEvent() {

        monthly_ivAdd.setOnClickListener {
            val intent = Intent(this@MonthlyActivity, AddEventActivity::class.java)
            startActivity(intent)
        }
    }
*/
    }

    fun nextWeekAction(view: View) {}
    fun previousWeekAction(view: View) {}
    fun newEventAction(view: View) {}
}
