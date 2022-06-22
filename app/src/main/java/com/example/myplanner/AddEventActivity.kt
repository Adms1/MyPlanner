package com.example.myplanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myplanner.db.DatabaseHandler
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.DateFormatSymbols
import java.util.*

class AddEventActivity : AppCompatActivity() {


    val year = Calendar.getInstance().get(Calendar.YEAR)
    val month = Calendar.getInstance().get(Calendar.MONTH)
    val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val minute = Calendar.getInstance().get(Calendar.MINUTE)
    var datesStore: String? = null

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
            val db = DatabaseHandler(this)

            val strEventName = addevent_tvEname.text.toString()
            val strEventDescription = addevent_tvEdesc.text.toString()
            val strDate = addevent_tvDate.text.toString()
            val strToTime = addevent_tvStime.text.toString()
            val strFromTime = addevent_tvEtime.text.toString()
            val strNotification = addevent_tvNotification.text.toString()
            val strLocation = addevent_tvLocation.text.toString()
            val strRepet = addevent_tvRepeat.text.toString()

            if (strDateTime.equals("editDateTime")) {
                id?.let { it1 -> db.updateDateTime(it1, datesStore, strToTime, strFromTime) }

            } else {
                db.addDailyPlan(
                    strEventName,
                    strEventDescription,
                    datesStore,
                    strToTime,
                    strFromTime,
                    strNotification,
                    strLocation,
                    strRepet
                )
            }
        })

    }

    private fun openCalendar() {

//        val format = SimpleDateFormat("MMM dd,yyyy  hh:mm a")
//        val date = format.format(Date.parse("Your date string"))
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

    }

    private fun openStartCalendar() {

        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            this,
            { timePicker, selectedHour, selectedMinute ->
                var selectedHour = hour
                var am_pm = ""
                // AM_PM decider logic
                when {

                    hour == 0 -> {
                        selectedHour += 12
                        am_pm = "AM"
                    }
                    hour == 12 -> am_pm = "PM"
                    hour > 12 -> {
                        selectedHour -= 12
                        am_pm = "PM"
                    }
                    else -> am_pm = "AM"
                }
                if (addevent_tvStime != null) {
                    val min = if (selectedMinute < 10) "0" + selectedMinute else selectedMinute
                    val msg = "$selectedHour : $min $am_pm"
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
            { timePicker, selectedHour, selectedMinute ->
                var selectedHour = hour
                var am_pm = ""
                // AM_PM decider logic
                when {

                    hour == 0 -> {
                        selectedHour += 12
                        am_pm = "AM"
                    }
                    hour == 12 -> am_pm = "PM"
                    hour > 12 -> {
                        selectedHour -= 12
                        am_pm = "PM"
                    }
                    else -> am_pm = "AM"
                }
                if (addevent_tvStime != null) {
                    val min = if (selectedMinute < 10) "0" + selectedMinute else selectedMinute
                    val msg = "$selectedHour : $min $am_pm"
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
}