package com.example.myplanner.week

import android.content.Intent
import android.graphics.Color
import android.graphics.RectF
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alamkanak.weekview.DateTimeInterpreter
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekViewEvent
import com.example.myplanner.DashboardActivity
import com.example.myplanner.R
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import java.text.SimpleDateFormat
import java.util.*


class activity_weekly : AppCompatActivity(),
    com.alamkanak.weekview.WeekView.EventClickListener,
    MonthLoader.MonthChangeListener, com.alamkanak.weekview.WeekView.EventLongPressListener,
    com.alamkanak.weekview.WeekView.EmptyViewLongPressListener {
    private var mWeekViewType = TYPE_THREE_DAY_VIEW
    var weekView: com.alamkanak.weekview.WeekView? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.title = "  Weekly Plan  "

        // Get a reference for the week view in the layout.
        weekView = findViewById(R.id.weekView) as com.alamkanak.weekview.WeekView

        // Show a toast message about the touched event.
        weekView!!.setOnEventClickListener(this)

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView!!.monthChangeListener = this

        // Set long press listener for events.
        weekView!!.eventLongPressListener = this

        // Set long press listener for empty view
        weekView!!.emptyViewLongPressListener = this


        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false)
    }


    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)

        return true
    }

    override fun onBackPressed() {
        return
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        setupDateTimeInterpreter(id == R.id.action_week_view)
        when (id) {

            R.id.action_day_view -> {
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.isChecked = !item.isChecked
                    mWeekViewType = TYPE_DAY_VIEW
                    weekView!!.numberOfVisibleDays = 1

                    // Lets change some dimensions to best fit the view.
                    weekView!!.columnGap =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            8f,
                            resources.displayMetrics
                        ).toInt()
                    weekView!!.textSize =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,
                            12f,
                            resources.displayMetrics
                        ).toInt()
                    weekView!!.eventTextSize =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,
                            12f,
                            resources.displayMetrics
                        ).toInt()
                }
                return true
            }
            R.id.action_three_day_view -> {
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.isChecked = !item.isChecked
                    mWeekViewType = TYPE_THREE_DAY_VIEW
                    weekView!!.numberOfVisibleDays = 3

                    // Lets change some dimensions to best fit the view.
                    weekView!!.columnGap =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            8f,
                            resources.displayMetrics
                        ).toInt()
                    weekView!!.textSize =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,
                            12f,
                            resources.displayMetrics
                        ).toInt()
                    weekView!!.eventTextSize =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,
                            12f,
                            resources.displayMetrics
                        ).toInt()
                }
                return true
            }
            R.id.action_week_view -> {
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.isChecked = !item.isChecked
                    mWeekViewType = TYPE_WEEK_VIEW
                    weekView!!.numberOfVisibleDays = 7

                    // Lets change some dimensions to best fit the view.
                    weekView!!.columnGap =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            2f,
                            resources.displayMetrics
                        ).toInt()
                    weekView!!.textSize =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,
                            10f,
                            resources.displayMetrics
                        ).toInt()
                    weekView!!.eventTextSize =
                        TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,
                            10f,
                            resources.displayMetrics
                        ).toInt()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDateTimeInterpreter(shortDate: Boolean) {
        weekView!!.setDateTimeInterpreter(object : DateTimeInterpreter {
            override fun interpretDate(date: Calendar): String? {
                val weekdayNameFormat = SimpleDateFormat("EEE", Locale.getDefault())
                var weekday = weekdayNameFormat.format(date.time)
                val format = SimpleDateFormat(" d/M", Locale.getDefault())
                if (shortDate) weekday = weekday[0].toString()
                return weekday.uppercase(Locale.getDefault()) + format.format(date.time)
            }

            override fun interpretTime(hour: Int): String {
                var hour = hour
                if (hour == 24) hour = 0
                if (hour == 0) hour = 0
                return "$hour:00"
            }
        })
    }

    protected fun getEventTitle(eventName: String, time: Calendar): String {
        return String.format(
            "$eventName %02d:%02d %s/%d",
            time[Calendar.HOUR_OF_DAY],
            time[Calendar.MINUTE],
            time[Calendar.MONTH],
            time[Calendar.DAY_OF_MONTH]

        )
    }

    override fun onEventClick(event: WeekViewEvent, eventRect: RectF) {
        Toast.makeText(this, "Clicked " + event.name, Toast.LENGTH_SHORT).show()

    }

    override fun onEventLongPress(event: WeekViewEvent, eventRect: RectF) {
        //  Toast.makeText(this, "Long pressed event: " + event.name, Toast.LENGTH_SHORT).show()
    }

    override fun onEmptyViewLongPress(time: Calendar) {
        //  Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT) .show()
    }

    companion object {
        private const val TYPE_DAY_VIEW = 1
        private const val TYPE_THREE_DAY_VIEW = 2
        private const val TYPE_WEEK_VIEW = 3
    }

    override fun onMonthChange(newYear: Int, newMonth: Int): MutableList<WeekViewEvent> {
        val events: MutableList<WeekViewEvent> = ArrayList()

        var startTime = Calendar.getInstance()
        val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        val DayAfterWeek = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 7
        var endTime = startTime.clone() as Calendar
        var event: WeekViewEvent
        val db = DatabaseHandler(applicationContext)

        val weeklyPalnning: List<DailyPlanner> = db.getWeeklyData()


        for (cn in weeklyPalnning) {
            startTime = Calendar.getInstance()
            startTime[Calendar.HOUR_OF_DAY] = cn.starthours
            startTime[Calendar.MINUTE] = cn.startmin
            startTime[Calendar.DAY_OF_MONTH] = cn.day
            startTime[Calendar.MONTH] = cn.month
            startTime[Calendar.YEAR] = newYear
            endTime = startTime.clone() as Calendar
            endTime[Calendar.HOUR_OF_DAY] = cn.endhours
            endTime[Calendar.MINUTE] = cn.endmin
            val eventName = cn.event_name
            event = WeekViewEvent(5, getEventTitle(eventName, startTime), startTime, endTime)
            event.color = getRandomColor()
            events.add(event)

            weekView!!.setMonthChangeListener({ newYear, newMonth -> // Populate the week view with some events.

                // Return only the events that matches newYear and newMonth.
                val matchedEvents: MutableList<WeekViewEvent> = ArrayList()
                for (event in events) {
                    if (eventMatches(event, newYear, newMonth)) {
                        matchedEvents.add(event)
                    }
                }
                matchedEvents
            })
        }

        return events
    }

    private fun eventMatches(event: WeekViewEvent, year: Int, month: Int): Boolean {
        return event.startTime[Calendar.YEAR] == year && event.startTime[Calendar.MONTH] == month - 1 || event.endTime[Calendar.YEAR] == year && event.endTime[Calendar.MONTH] == month - 1
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}


