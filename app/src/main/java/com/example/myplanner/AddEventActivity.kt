package com.example.myplanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.SimpleDateFormat
import java.util.*

class AddEventActivity : AppCompatActivity() {

    val year = Calendar.getInstance().get(Calendar.YEAR)
    val month = Calendar.getInstance().get(Calendar.MONTH)
    val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val minute = Calendar.getInstance().get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

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

        val picker = DatePickerDialog(this@AddEventActivity,
            { view, year, monthOfYear, dayOfMonth ->

//                addevent_tvDate.text = "" + dayOfMonth + " " + (monthOfYear+1) + ", " + year
//                updateLabel(dayOfMonth, monthOfYear+1, year)

            }, year, month, day)
        picker.show()

    }

    private fun openStartCalendar() {

        val picker = TimePickerDialog(
            this@AddEventActivity,
            { timePicker, selectedHour, selectedMinute ->

                addevent_tvStime.text = "$selectedHour:$selectedMinute"

            }, hour, minute, false
        )

        picker.setTitle("Select Time")
        picker.show()

    }

    private fun openEndCalendar() {

        val picker = TimePickerDialog(
            this@AddEventActivity,
            { timePicker, selectedHour, selectedMinute ->

                addevent_tvEtime.text = "$selectedHour:$selectedMinute"

            }, hour, minute, false
        )

        picker.setTitle("Select Time")
        picker.show()


    }

    private fun updateLabel(date: Int, month: Int, year: Int,) {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        addevent_tvDate.text = dateFormat.format(dateFormat)
    }

}