package com.example.myplanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
    val ampm = Calendar.getInstance().get(Calendar.AM_PM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        var strDateTime = sharedPreference.getString("editDateTime", "")
        if (strDateTime.equals("editDateTime")) {

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

    }

    private fun openCalendar() {

//        val format = SimpleDateFormat("MMM dd,yyyy  hh:mm a")
//        val date = format.format(Date.parse("Your date string"))
        val picker = DatePickerDialog(
            this@AddEventActivity,
            { view, year, monthOfYear, dayOfMonth ->
                val Date = (day.toString() + "-" + (month + 1) + "-" + year)

                // set this date in TextView for Display
                addevent_tvDate.setText(Date)

//                addevent_tvDate.text = "" + dayOfMonth + " " + (monthOfYear+1) + ", " + year
                updateLabel(dayOfMonth, monthOfYear + 1, year)

            }, year, month, day
        )
        picker.show()

    }

    private fun openStartCalendar() {

        val picker = TimePickerDialog(
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
                    val hour = if (hour < 10) "0" + hour else hour
                    val min = if (minute < 10) "0" + minute else minute
                    // display format of time
                    val msg = "$hour : $min $am_pm"
                    addevent_tvStime.text = msg


                }
            }, hour, minute, false
        )

        picker.setTitle("Select Time")
        picker.show()

    }

    private fun openEndCalendar() {

        val picker = TimePickerDialog(
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
                if (addevent_tvEtime != null) {
                    val hour = if (hour < 10) "0" + hour else hour
                    val min = if (minute < 10) "0" + minute else minute
                    // display format of time
                    val msg = "$hour : $min $am_pm"
                    addevent_tvEtime.text = msg


                }


            }, hour, minute, false
        )

        picker.setTitle("Select Time")
        picker.show()


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