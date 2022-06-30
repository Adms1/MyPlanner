package com.example.myplanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.events.calendar.utils.Events
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
    var strEventName: String? = null
    var strEventDescription: String? = null
    var strDate: String? = null
    var strToTime: String? = null
    var strFromTime: String? = null
    var strNotification: String? = null
    var strRepeat: String? = null
    var strPriority: String? = null
    var strCompany: String? = null


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
            // addevent_tvLocation.setText(strLocation.toString())
            // addevent_tvRepeat.setText(strRepeat.toString())

            addevent_tvEname.isCursorVisible = false
            addevent_tvEdesc.isCursorVisible = false
            addevent_tvNotification.isCursorVisible = false
            //    addevent_tvLocation.isCursorVisible = false
            //   addevent_tvRepeat.isCursorVisible = false

            addevent_tvEname.isFocusable = false
            addevent_tvEdesc.isFocusable = false
            addevent_tvNotification.isFocusable = false
            //     addevent_tvLocation.isFocusable = false
            //     addevent_tvRepeat.isFocusable = false


        }

        addevent_ivstime.setOnClickListener { openStartCalendar() }
        addevent_tvStime.setOnClickListener { openStartCalendar() }
        addevent_ivEtime.setOnClickListener { openEndCalendar() }
        addevent_tvEtime.setOnClickListener { openEndCalendar() }

        addevent_ivCal.setOnClickListener {

            openCalendar()
            val calendar = Calendar.getInstance()
/*
            val intent: Intent = Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .setType("vnd.android.cursor.item/event")
                .putExtra(Events.TITLE, "Tuesdays")
                .putExtra(Events.DESCRIPTION, "Tuesday Specials")
                .putExtra(Events.EVENT_LOCATION, "Lixious Bench")
                .putExtra(Events.RRULE, "FREQ=WEEKLY;BYDAY=Tu;UNTIL=20151231")
                .putExtra(Events.DTSTART, calendar.timeInMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                .putExtra(CalendarContract.Events.HAS_ALARM, 1)
                .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
            startActivity(intent*/)
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

        val spnCompany = resources.getStringArray(R.array.Company)
        if (spinnerCompany != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, spnCompany
            )
            spinnerCompany.adapter = adapter
        }
        spinnerCompany.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@AddEventActivity,
                    getString(R.string.selected_Company) + " " + "" + spnCompany[position],
                    Toast.LENGTH_SHORT
                ).show()
                strCompany = spinnerCompany.getSelectedItem().toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        val spnPriority = resources.getStringArray(R.array.Priority)
        if (spinnerPriority != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, spnPriority
            )
            spinnerPriority.adapter = adapter
        }
        spinnerPriority.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Toast.makeText(
                    this@AddEventActivity,
                    getString(R.string.selected_Priority) + " " +
                            "" + spnPriority[position], Toast.LENGTH_SHORT
                ).show()
                strPriority = spinnerPriority.getSelectedItem().toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }


        val spnRepeat = resources.getStringArray(R.array.Repeat)
        if (spinnerRepeat != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, spnRepeat
            )
            spinnerRepeat.adapter = adapter
        }
        spinnerRepeat.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Toast.makeText(
                    this@AddEventActivity,
                    getString(R.string.selected_repeat) + " " +
                            "" + spnPriority[position], Toast.LENGTH_SHORT
                ).show()
                strRepeat = spinnerRepeat.getSelectedItem().toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }


        btnSave.setOnClickListener({
            strEventName = addevent_tvEname.text.toString()
            strEventDescription = addevent_tvEdesc.text.toString()
            strDate = addevent_tvDate.text.toString()
            strToTime = addevent_tvStime.text.toString()
            strFromTime = addevent_tvEtime.text.toString()
            strNotification = addevent_tvNotification.text.toString()
            //  strLocation = addevent_tvLocation.text.toString()
            //  strRepeat = addevent_tvRepeat.tex.ttoString()

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
                        strCompany,
                        strPriority,
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
                val Date = (day.toString() + " " + (month + 1) + "," + year)
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
                    val min = if (selectedMinute < 10) "0" + selectedMinute else selectedMinute
                    val hour = if (hour < 10) "0" + hour else hour

                    val msg = "$hour : $min $am_pm"
                    addevent_tvEtime.text = msg
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
        } else if (isEmpty(this.strNotification)) {
            Toast.makeText(applicationContext, "Plese Enter Event Location", Toast.LENGTH_SHORT)
                .show()

            isValidData = false
        } else if (isEmpty(this.strToTime)) {
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







