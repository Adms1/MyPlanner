package com.example.myplanner

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.db.dbManager
import com.example.myplanner.old.MainActivity
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class AddEventActivity : AppCompatActivity() {


    val year = Calendar.getInstance().get(Calendar.YEAR)
    val month = Calendar.getInstance().get(Calendar.MONTH)
    val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val minute = Calendar.getInstance().get(Calendar.MINUTE)
    var datesStore: String? = null
    var strEventName: String? = null
    var strEventDescription: String? = null
    var strDate: String? = null
    var strToTime: String? = null
    var strFromTime: String? = null
    var strNotification: String? = null
    var strLocation: String? = null
    var strRepeat: String? = null

    private var minHour = -1
    private var minMinute = -1

    private var maxHour = 25
    private var maxMinute = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var id: Int? = null
        val strDateTime = sharedPreference.getString("editOrNotDateTime", "")
        if (strDateTime.equals("editDateTime")) {
            id = sharedPreference.getInt("id", 0)
            val strDate = sharedPreference.getString("date", "")
            val strToTime = sharedPreference.getString("toTime", "")
            val strFromTime = sharedPreference.getString("fromTime", "")
            val strEventName = sharedPreference.getString("eventName", "")
            val strEventDescription = sharedPreference.getString("eventDescription", "")
            val strNotificationDescription =
                sharedPreference.getString("notificationDescription", "")
            val strLocation = sharedPreference.getString("Location", "")
            val strRepeat = sharedPreference.getString("repeat", "")


            addevent_tvDate.text = strDate
            addevent_tvStime.text = strToTime
            addevent_tvEtime.text = strFromTime
            addevent_tvEname.setText(strEventName.toString())
            addevent_tvEdesc.setText(strEventDescription.toString())
            addevent_tvNotification.setText(strNotificationDescription.toString())
            addevent_tvLocation.setText(strLocation.toString())
            addevent_tvRepeat.setText(strRepeat.toString())

            addevent_tvEname.isCursorVisible = false
            addevent_tvEdesc.isCursorVisible = false
            addevent_tvNotification.isCursorVisible = false
            addevent_tvLocation.isCursorVisible = false
            addevent_tvRepeat.isCursorVisible = false

            addevent_tvEname.isFocusable = false
            addevent_tvEdesc.isFocusable = false
            addevent_tvNotification.isFocusable = false
            addevent_tvLocation.isFocusable = false
            addevent_tvRepeat.isFocusable = false


        } else {

        }

        addevent_ivstime.setOnClickListener { openStartCalendar() }
        addevent_tvStime.setOnClickListener { openStartCalendar() }
        addevent_ivEtime.setOnClickListener { openEndCalendar() }
        addevent_tvEtime.setOnClickListener { openEndCalendar() }

        addevent_ivCal.setOnClickListener {

            openCalendar()

        }

        addevent_ivBack.setOnClickListener {
            onBackPressed()
        }

        addevent_ivColor.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(this)                            // Pass Activity Instance
                .setTitle("Pick Theme")                // Default "Choose Color"
                .setColorShape(ColorShape.CIRCLE)    // Default ColorShape.CIRCLE
                .setColorSwatch(ColorSwatch._300)    // Default ColorSwatch._500
                .setDefaultColor(R.color.black)        // Pass Default Color
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    addevent_ivColor.setBackgroundColor(color)
                }
                .show()
        }

        btnSave.setOnClickListener({
            strEventName = addevent_tvEname.text.toString()
            strEventDescription = addevent_tvEdesc.text.toString()
            strDate = addevent_tvDate.text.toString()
            strToTime = addevent_tvStime.text.toString()
            strFromTime = addevent_tvEtime.text.toString()
            strNotification = addevent_tvNotification.text.toString()
            strLocation = addevent_tvLocation.text.toString()
            strRepeat = addevent_tvRepeat.text.toString()

            if (validateInput()) {
                val db = DatabaseHandler(this)


                if (strDateTime.equals("editDateTime")) {
                    id?.let { it1 -> db.updateDateTime(it1, strDate, strToTime, strFromTime) }

                } else {
                    db.addDailyPlan(
                        strEventName,
                        strEventDescription,
                        strDate,
                        strToTime,
                        strFromTime,
                        strNotification,
                        strLocation,
                        strRepeat
                    )
                }

                val intent = Intent(this@AddEventActivity, DashboardActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun openCalendar() {
        val picker = DatePickerDialog(
            this@AddEventActivity,
            { view, year, monthOfYear, dayOfMonth ->
                val Date = (day.toString() + "-" + (month + 1) + "-" + year)
                datesStore = (day.toString() + " " + (month + 1) + " " + year)
                // set this date in TextView for Display
                addevent_tvDate.setText(Date)

                updateLabel(dayOfMonth, monthOfYear + 1, year)

            }, year, month, day
        )
        picker.show()
        picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); }

    private fun openStartCalendar() {
        val mTimePicker = TimePickerDialog(
            this@AddEventActivity,
            { timePicker, hour, selectedMinute ->
                var hour = hour
                var am_pm = ""
                // AM_PM decider logic
                when {

                    hour == 0 -> {
                        hour += 12
                        am_pm = "AM"
                    }
                    hour == 12 -> am_pm = "PM"
                    hour > 12 -> {
                        hour -= 12
                        am_pm = "PM"
                    }
                    else -> am_pm = "AM"
                }
                if (addevent_tvStime != null) {
                    val min = if (selectedMinute < 10) "0" + selectedMinute else selectedMinute
                    val hour = if (hour < 10) "0" + hour else hour
                    val msg = "$hour : $min $am_pm"
                    addevent_tvStime.text = msg
                    // addevent_tvStime.setText("$selectedHour:$selectedMinute")
                }
            },
            hour,
            minute,
            false
        ) //Yes 24 hour time
        mTimePicker.setTitle("Select Time")

        mTimePicker.show()

    }

    private fun openEndCalendar() {
        val mTimePicker: TimePickerDialog

        mTimePicker = TimePickerDialog(
            this,
            { timePicker, hour, selectedMinute ->
                var hour = hour
                var am_pm = ""
                // AM_PM decider logic
                when {

                    hour == 0 -> {
                        hour += 12
                        am_pm = "AM"
                    }
                    hour == 12 -> am_pm = "PM"
                    hour > 12 -> {
                        hour -= 12
                        am_pm = "PM"
                    }
                    else -> am_pm = "AM"
                }
                /*if ((hour < (Calendar.getInstance().get(Calendar.HOUR_OF_DAY))) &&
                    (minute == (Calendar.getInstance().get(Calendar.MINUTE)))
                ) {
                    Toast.makeText(
                        this, "Wrong hour",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {*/
                if (addevent_tvEtime != null) {
                    val min = if (selectedMinute < 10) "0" + selectedMinute else selectedMinute
                    val hour = if (hour < 10) "0" + hour else hour

                    val msg = "$hour : $min $am_pm"
                    addevent_tvEtime.text = msg
                    strDate?.let { processinsert("test", it, msg) }

                    // addevent_tvStime.setText("$selectedHour:$selectedMinute")
                }


            },
            hour,
            minute,
            false
        ) //Yes 24 hour time

        mTimePicker.setTitle("Select Time")
        mTimePicker.show()

    }

    private fun processinsert(title: String?, date: String, time: String) {

        val result: String = dbManager(this).addreminder(
            title,
            date,
            time
        ) //inserts the title,date,time into sql lite database

        setAlarm(title, date, time) //calls the set alarm method to set alarm
       // mTitledit.setText("")
        Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()


    }

    private fun setAlarm(title: String?, date: Any, time: Any) {
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager //assigning alarm manager object to set alarm

        val intent = Intent(applicationContext, AlarmBroadcast::class.java)
        intent.putExtra("event", title) //sending data to alarm class to create channel and notification
       // intent.putExtra("time", date)
        //intent.putString("date", time)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val dateandtime = "$date $time"
        val formatter: DateFormat = SimpleDateFormat("d-M-yyyy hh:mm")
        try {
            val date1: Date = formatter.parse(dateandtime)
            am[AlarmManager.RTC_WAKEUP, date1.time] = pendingIntent
            Toast.makeText(applicationContext, "Alarm", Toast.LENGTH_SHORT).show()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val intentBack = Intent(
            applicationContext,
            MainActivity::class.java
        ) //this intent will be called once the setting alarm is complete

        intentBack.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intentBack)                                                                     //navigates from adding reminder activity to mainactivity
        //navigates from adding reminder activity to mainactivity
    }
    private fun updateLabel(date: Int, month: Int, year: Int) {
        // val myFormat = "dd/MM/yyyy"
        //  val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val monthName = getMonthForInt(month - 1)
        val Date = (date.toString() + " " + (monthName) + ", " + year)
        addevent_tvDate.text = Date
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

    private fun validateInput(): Boolean {
        // TODO Auto-generated method stub
        var isValidData = true
        if (isEmpty(this.strEventName)) {
            Toast.makeText(applicationContext, "Plese Enter Event Name", Toast.LENGTH_SHORT).show()
            isValidData = false
        } else if (isEmpty(this.strEventDescription)) {
            Toast.makeText(applicationContext, "Plese Enter Event Description", Toast.LENGTH_SHORT)
                .show()
            isValidData = false
        } else if (isEmpty(this.strDate)) {
            Toast.makeText(applicationContext, "Plese Select Date", Toast.LENGTH_SHORT).show()
            isValidData = false
        } else if (isEmpty(this.strToTime)) {
            Toast.makeText(applicationContext, "Plese Select To Time", Toast.LENGTH_SHORT).show()
            isValidData = false
        } else if (isEmpty(this.strFromTime)) {
            Toast.makeText(applicationContext, "Plese Select From Time", Toast.LENGTH_SHORT).show()

            isValidData = false
        } else if (isEmpty(this.strNotification)) {
            Toast.makeText(applicationContext, "Plese Enter Notification", Toast.LENGTH_SHORT)
                .show()

            isValidData = false
        } else if (isEmpty(this.strLocation)) {
            Toast.makeText(applicationContext, "Plese Enter Event Location", Toast.LENGTH_SHORT)
                .show()

            isValidData = false
        } else if (isEmpty(this.strRepeat)) {
            Toast.makeText(
                applicationContext,
                "Plese Enter Event Repeat Or Not",
                Toast.LENGTH_SHORT
            ).show()

            isValidData = false
        }
        return isValidData
    }

}





