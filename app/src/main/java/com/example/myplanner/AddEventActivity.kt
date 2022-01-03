package com.example.myplanner

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_event.*
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

}