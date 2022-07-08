package com.example.myplanner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myplanner.daily.DailyActivity
import com.example.myplanner.db.DatabaseHandler
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.DateFormatSymbols
import java.util.*


class AddEventActivity : AppCompatActivity() {
    private val year = Calendar.getInstance().get(Calendar.YEAR)
    private val month = Calendar.getInstance().get(Calendar.MONTH)
    private val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    private val minute = Calendar.getInstance().get(Calendar.MINUTE)
    private var strEventName: String? = null
    private var strEventDescription: String? = null
    private var strDate: String? = null
    private var strToTime: String? = null
    private var strFromTime: String? = null
    private var strNotification: String? = null
    var strRepeat: String? = null
    var strPriority: String? = null
    var strCompany: String? = null
    private var strDateTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.title = "Add Event"

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var id: Int? = null
        strDateTime = sharedPreference.getString("editOrNotDateTime", "")
        if (strDateTime.equals("editDateTime")) {
            id = sharedPreference.getInt("id", 0)
            val strDate = sharedPreference.getString("date", "")
            val strToTime = sharedPreference.getString("toTime", "")
            val strFromTime = sharedPreference.getString("fromTime", "")
            val strEventName = sharedPreference.getString("eventName", "")
            val strEventDescription = sharedPreference.getString("eventDescription", "")
            val strNotificationDescription =
                sharedPreference.getString("notificationDescription", "")
            val strCompany = sharedPreference.getInt("company", 0)
            val strRepeat = sharedPreference.getInt("repeat", 0)
            val strPriority = sharedPreference.getInt("Priority", 0)

            spinnerCompany.isEnabled = false
            spinnerPriority.isEnabled = false
            spinnerRepeat.isEnabled = false

            val spnPriority = resources.getStringArray(R.array.Priority)
            val spnCompany = resources.getStringArray(R.array.Company)
            val spnRepeat = resources.getStringArray(R.array.Repeat)

            if (spinnerCompany != null) {
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spnCompany)
                spinnerCompany.adapter = adapter
            }
            if (spinnerPriority != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, spnPriority
                )
                spinnerPriority.adapter = adapter
            }

            if (spinnerRepeat != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, spnRepeat
                )
                spinnerRepeat.adapter = adapter
            }

            spinnerRepeat.setSelection(strRepeat)
            spinnerPriority.setSelection(strPriority)
            spinnerCompany.setSelection(strCompany)

            addevent_tvNotification.setTextColor(resources.getColor(R.color.darkgrey))
            addevent_tvEdesc.setTextColor(resources.getColor(R.color.darkgrey))
            addevent_tvEname.setTextColor(resources.getColor(R.color.darkgrey))

            addevent_tvDate.text = strDate
            addevent_tvStime.text = strToTime
            addevent_tvEtime.text = strFromTime
            addevent_tvEname.setText(strEventName.toString())
            addevent_tvEdesc.setText(strEventDescription.toString())
            addevent_tvNotification.setText(strNotificationDescription.toString())
            addevent_tvEname.isCursorVisible = false
            addevent_tvEdesc.isCursorVisible = false
            addevent_tvNotification.isCursorVisible = false
            addevent_tvEname.isFocusable = false
            addevent_tvEdesc.isFocusable = false
            addevent_tvNotification.isFocusable = false

            btnTaskComplete.visibility = View.VISIBLE

        } else {
            val spnCompany = resources.getStringArray(R.array.Company)
            if (spinnerCompany != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item, spnCompany
                )
                spinnerCompany.adapter = adapter
            }
            spinnerCompany.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View, position: Int, id: Long
                    ) {

                        strCompany = spinnerCompany.selectedItemPosition.toString()
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

                    strPriority = spinnerPriority.selectedItemPosition.toString()

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

                    strRepeat = spinnerRepeat.selectedItemPosition.toString()
                    Log.d("repeatEvent :", strRepeat.toString())
                    when (spinnerRepeat.selectedItemPosition) {
                        2 -> {
                            val calendar = Calendar.getInstance()
                            calendar.add(Calendar.DAY_OF_MONTH, +7)
                            val newDate = calendar.time
                            Log.d("repeatEventDate :", newDate.toString())
                        }
                        3 -> {

                        }
                        else -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }

            }

        }

        val watcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                addevent_tvNotification.text = addevent_tvEdesc.text

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        addevent_tvEdesc.addTextChangedListener(watcher)
        addevent_ivstime.setOnClickListener { openStartCalendar() }
        addevent_tvStime.setOnClickListener { openStartCalendar() }
        addevent_ivEtime.setOnClickListener { openEndCalendar() }
        addevent_tvEtime.setOnClickListener { openEndCalendar() }

        addevent_ivCal.setOnClickListener {

            openCalendar()
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

        btnTaskComplete.setOnClickListener {
            val db = DatabaseHandler(applicationContext)
            id?.let { it1 -> db.UpdateStatus(it1) }
            val intent = Intent(this@AddEventActivity, DailyActivity::class.java)
            startActivity(intent)

        }
    }

    private fun openCalendar() {
        val picker = DatePickerDialog(
            this@AddEventActivity,
            { view, year, monthOfYear, dayOfMonth ->
                val date = (day.toString() + " " + (month + 1) + "," + year)
                // set this date in TextView for Display
                addevent_tvDate.text = date

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
                    val min = if (selectedMinute < 10) "0$selectedMinute" else selectedMinute
                    val hour = if (hour < 10) "0$hour" else hour
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
                    val min = if (selectedMinute < 10) "0$selectedMinute" else selectedMinute
                    val hour = if (hour < 10) "0$hour" else hour

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
        val monthName = getMonthForInt(month - 1)
        val date = ("$date $monthName, $year")
        addevent_tvDate.text = date
    }

    private fun getMonthForInt(num: Int): String {
        var month = "wrong"
        val dfs = DateFormatSymbols()
        val months: Array<String> = dfs.months
        if (num in (0..11)) {
            month = months[num]
        }
        return month
    }

    private fun validateInput(): Boolean {
        // TODO Auto-generated method stub
        var isValidData = true
        if (isEmpty(this.strEventName)) {
            Toast.makeText(applicationContext, "Please Enter Event Name", Toast.LENGTH_SHORT)
                .show()
            isValidData = false
        } else if (isEmpty(this.strEventDescription)) {
            Toast.makeText(
                applicationContext,
                "Please Enter Event Description",
                Toast.LENGTH_SHORT
            )
                .show()
            isValidData = false
        } else if (isEmpty(this.strDate)) {
            Toast.makeText(applicationContext, "Please Select Date", Toast.LENGTH_SHORT).show()
            isValidData = false
        } else if (isEmpty(this.strToTime)) {
            Toast.makeText(applicationContext, "Please Select To Time", Toast.LENGTH_SHORT)
                .show()
            isValidData = false
        } else if (isEmpty(this.strFromTime)) {
            Toast.makeText(applicationContext, "Please Select From Time", Toast.LENGTH_SHORT)
                .show()

            isValidData = false
        } else if (isEmpty(this.strNotification)) {
            Toast.makeText(applicationContext, "Please Enter Notification", Toast.LENGTH_SHORT)
                .show()

            isValidData = false
        } else if (isEmpty(this.strNotification)) {
            Toast.makeText(applicationContext, "Please Enter Event Location", Toast.LENGTH_SHORT)
                .show()

            isValidData = false
        } else if (isEmpty(this.strCompany) || this.strCompany.equals("0")) {
            Toast.makeText(
                applicationContext,
                "Please Select Any One Company",
                Toast.LENGTH_SHORT
            ).show()

            isValidData = false
        } else if (isEmpty(this.strPriority) || this.strPriority.equals("0")) {
            Toast.makeText(
                applicationContext,
                "Please Select Priority",
                Toast.LENGTH_SHORT
            ).show()

            isValidData = false
        } else if (isEmpty(this.strRepeat) || this.strRepeat.equals("0")) {
            Toast.makeText(
                applicationContext,
                "Please Select Event Repeat Or Not",
                Toast.LENGTH_SHORT
            ).show()

            isValidData = false
        }
        return isValidData
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuforaddevent, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (val id: Int = item.itemId) {

            R.id.imgSave -> {

                strEventName = addevent_tvEname.text.toString()
                strEventDescription = addevent_tvEdesc.text.toString()
                strDate = addevent_tvDate.text.toString()
                strToTime = addevent_tvStime.text.toString()
                strFromTime = addevent_tvEtime.text.toString()
                strNotification = addevent_tvNotification.text.toString()

                if (validateInput()) {
                    val db = DatabaseHandler(this)


                    if (strDateTime.equals("editDateTime")) {
                        id.let { it1 -> db.updateDateTime(it1, strDate, strToTime, strFromTime) }

                    } else {
                        val sharedPreference =
                            getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()
                        editor.putInt("spiCompanyId", spinnerCompany.selectedItemPosition)
                        editor.putInt("spiRepeatId", spinnerRepeat.selectedItemPosition)
                        editor.putInt("spiPriorityId", spinnerPriority.selectedItemPosition)
                        editor.apply()

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
                    val intent = Intent(this@AddEventActivity, DailyActivity::class.java)
                    startActivity(intent)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        return
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this@AddEventActivity, DailyActivity::class.java)
        startActivity(intent)
        return true
    }

}







