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
import com.example.myplanner.Company.CompanyAdapter
import com.example.myplanner.Company.CompanyModel
import com.example.myplanner.daily.DailyActivity
import com.example.myplanner.db.DatabaseHandler
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class AddEventActivity : AppCompatActivity() {
    private val year = Calendar.getInstance().get(Calendar.YEAR)
    private var month = Calendar.getInstance().get(Calendar.MONTH)
    private var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    private val minute = Calendar.getInstance().get(Calendar.MINUTE)
    private var strEventName: String? = null
    private var strEventDescription: String? = null
    private var strDate: String? = null
    private var strToTime: String? = null
    private var strFromTime: String? = null
    private var strNotification: String? = null
    var strRepeat: String? = null
    var strYear: String? = null
    var strPriority: String? = null
    var strCompany: String? = null
    private var strDateTime: String? = null
    var startToTime: String? = null
    var startTomin: String? = null
    var endHours: String? = null
    var endMin: String? = null
    var day1: Int = 0
    var month1: String? = null
    var id1: Int? = null

    private var spinnerCompanyList: ArrayList<CompanyModel>? = null


    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.title = "Add Event"

        companyList();
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        strDateTime = sharedPreference.getString("editOrNotDateTime", "")
        if (strDateTime.equals("editDateTime")) {
            id1 = sharedPreference.getInt("id", 0)
            val strDate = sharedPreference.getString("date", "")
            val strToTime = sharedPreference.getString("toTime", "")
            val strFromTime = sharedPreference.getString("fromTime", "")
            val strEventName = sharedPreference.getString("eventName", "")
            val strEventDescription = sharedPreference.getString("eventDescription", "")
            val strNotificationDescription =
                sharedPreference.getString("notificationDescription", "")
            var strCompany1 = sharedPreference.getInt("company", 0)
            var strRepeat1 = sharedPreference.getInt("repeat", 0)
            var strPriority1 = sharedPreference.getInt("Priority", 0)


            val strStartHours = sharedPreference.getInt("StartHours", 0)
            val strStartMin = sharedPreference.getInt("StartMin", 0)
            val strEndHours = sharedPreference.getInt("EndHours", 0)
            val strEndMin = sharedPreference.getInt("EndMin", 0)
            val strDay = sharedPreference.getInt("Day", 0)
            val strMonth = sharedPreference.getString("Month", "")
            var strYear = sharedPreference.getInt("Year", 0)


            startToTime = strStartHours.toString()
            startTomin = strStartMin.toString()
            endHours = strEndHours.toString()
            endMin = strEndMin.toString()
            day1 = Integer.parseInt(strDay.toString())
            month1 = strMonth.toString()
            strYear = Integer.parseInt(strYear.toString())

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

            spinnerRepeat.setSelection(strRepeat1)
            spinnerPriority.setSelection(strPriority1)
            spinnerCompany.setSelection(strCompany1)

            strCompany = strCompany1.toString()
            strPriority = strPriority1.toString()
            strRepeat = strRepeat1.toString()

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
             //   val adapter = spinnerCompanyList?.let { CompanyAdapter(this, it) }
             //   spinnerCompany.adapter = adapter

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
                    strDate = addevent_tvDate.text.toString()
                    strRepeat = spinnerRepeat.selectedItemPosition.toString()
                    /*Toast.makeText(applicationContext, "Cooming Soon", Toast.LENGTH_SHORT)
                        .show()
*/

                    Log.d("repeatEvent :", strRepeat.toString())

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
            id1?.let { it1 -> db.UpdateStatus(it1) }
            val intent = Intent(this@AddEventActivity, DailyActivity::class.java)
            startActivity(intent)

        }
    }


    private fun companyList() {
        spinnerCompanyList = ArrayList()
        spinnerCompanyList!!.add(CompanyModel("Please Select Company"))
        spinnerCompanyList!!.add(CompanyModel("ADM"))
        spinnerCompanyList!!.add(CompanyModel("ASL"))
        spinnerCompanyList!!.add(CompanyModel("SRPL"))
        spinnerCompanyList!!.add(CompanyModel("ULTRA"))
        spinnerCompanyList!!.add(CompanyModel("GALACTIC"))
        spinnerCompanyList!!.add(CompanyModel("PARCOTICS"))
        spinnerCompanyList!!.add(CompanyModel("PERSONAL"))


    }

    private fun openCalendar() {
        val picker = DatePickerDialog(
            this@AddEventActivity,
            { view, year, monthOfYear, dayOfMonth ->
                // set this date in TextView for Displ
                val date = (day.toString() + "/" + (month + 1) + "/" + year)

                addevent_tvDate.text = date

                updateLabel(dayOfMonth, monthOfYear, year)


            }, year, month, day
        )
        picker.show()
        picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); }


    private fun openStartCalendar() {
        val cal = Calendar.getInstance()
        val dialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                Log.e("Time :", "$hourOfDay:$minute")
                if (addevent_tvStime != null) {
                    val min = if (minute < 10) "0$minute" else minute
                    val hour = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                    startToTime = "$hour"
                    startTomin = "$min"
                    addevent_tvStime.text = "$hour : $min"
                }
            },
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ) //here pass true for 24hrs view else false for AM-PM(12hrs view)
        dialog.setTitle("Select Time")
        dialog.show()
    }

    private fun openEndCalendar() {
        val cal = Calendar.getInstance()
        val dialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                Log.e("Time :", "$hourOfDay:$minute")

                if (addevent_tvEtime != null) {
                    val min = if (minute < 10) "0$minute" else minute
                    val hour = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                    endHours = "$hour"
                    endMin = "$min"
                    addevent_tvEtime.text = "$hour : $min"

                }
            },
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ) //here pass true for 24hrs view else false for AM-PM(12hrs view)
        dialog.setTitle("Select Time")
        dialog.show()
    }

    private fun updateLabel(date: Int, month: Int, year: Int) {
        //  val monthName = getMonthForInt(month - 1)
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_MONTH] = date
        calendar[Calendar.MONTH] = month
        calendar[Calendar.YEAR] = year
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString: String = dateFormat.format(calendar.time)
        val dateFormat1 = SimpleDateFormat("MM")
        val dateString1: String = dateFormat1.format(calendar.time)
        month1 = dateString1
        strYear = year.toString()
        day1 = date
        addevent_tvDate.text = dateString.toString()
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
                        id1?.let {
                            db.updateDateTime(
                                it, strDate, strToTime, strFromTime, Integer.parseInt(startToTime),
                                Integer.parseInt(startTomin),
                                day1,
                                Integer.parseInt(endHours),
                                Integer.parseInt(endMin),
                                month1.toString()
                            )
                            // Integer.parseInt(strYear.toString()))
                        }

                    } else {
                        try {
                            val sharedPreference =
                                getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                            val editor = sharedPreference.edit()
                            editor.putInt("spiCompanyId", spinnerCompany.selectedItemPosition)
                            editor.putInt("spiRepeatId", spinnerRepeat.selectedItemPosition)
                            editor.putInt("spiPriorityId", spinnerPriority.selectedItemPosition)
                            editor.apply()

                            when (spinnerRepeat.selectedItemPosition) {
                                2 -> {
                                    var dt = strDate // Start date
                                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                                    val c = Calendar.getInstance()
                                    c.add(Calendar.DATE, 7)
                                    dt = sdf.format(c.time)
                                    val dateFormat1 = SimpleDateFormat("dd")
                                    val dateString1: String = dateFormat1.format(c.time)


                                    db.addDailyPlan(
                                        strEventName,
                                        strEventDescription,
                                        dt,
                                        strToTime,
                                        strFromTime,
                                        strNotification,
                                        strCompany,
                                        strPriority,
                                        Integer.parseInt(startToTime),
                                        Integer.parseInt(startTomin),
                                        Integer.parseInt(dateString1),
                                        Integer.parseInt(endHours),
                                        Integer.parseInt(endMin),
                                        strRepeat,
                                        month1.toString(),
                                        Integer.parseInt(strYear.toString())
                                    )
                                }
                                3 -> {
                                    var dt = strDate // Start date
                                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                                    val c = Calendar.getInstance()
                                    c.add(Calendar.MONTH, 1)
                                    dt = sdf.format(c.time)
                                    val dateFormat1 = SimpleDateFormat("MM")
                                    val dateString1: String = dateFormat1.format(c.time)

                                    Log.d("Monthly", dt)
                                    Log.d("MonthMonthly", dateString1)

                                    db.addDailyPlan(
                                        strEventName,
                                        strEventDescription,
                                        dt,
                                        strToTime,
                                        strFromTime,
                                        strNotification,
                                        strCompany,
                                        strPriority,
                                        Integer.parseInt(startToTime),
                                        Integer.parseInt(startTomin),
                                        day1,
                                        Integer.parseInt(endHours),
                                        Integer.parseInt(endMin),
                                        strRepeat,
                                        dateString1,
                                        Integer.parseInt(strYear.toString())
                                    )

                                }
                                4 -> {
                                    var dt = strDate // Start date
                                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                                    val c = Calendar.getInstance()
                                    c.add(Calendar.YEAR, 1) // number of days to add
                                    dt = sdf.format(c.time)
                                    val dateFormat1 = SimpleDateFormat("yyyy")
                                    val dateString1: String = dateFormat1.format(c.time)

                                    Log.d("Yearly", dt)
                                    db.addDailyPlan(
                                        strEventName,
                                        strEventDescription,
                                        dt,
                                        strToTime,
                                        strFromTime,
                                        strNotification,
                                        strCompany,
                                        strPriority,
                                        Integer.parseInt(startToTime),
                                        Integer.parseInt(startTomin),
                                        day1,
                                        Integer.parseInt(endHours),
                                        Integer.parseInt(endMin),
                                        strRepeat,
                                        month1.toString(),
                                        Integer.parseInt(dateString1)
                                    )

                                }
                            }
                            db.addDailyPlan(
                                strEventName,
                                strEventDescription,
                                strDate,
                                strToTime,
                                strFromTime,
                                strNotification,
                                strCompany,
                                strPriority,
                                Integer.parseInt(startToTime),
                                Integer.parseInt(startTomin),
                                day1,
                                Integer.parseInt(endHours),
                                Integer.parseInt(endMin),
                                strRepeat,
                                month1.toString(),
                                Integer.parseInt(strYear.toString())
                            )

                        } catch (e: Exception) {
                            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    val intent = Intent(this@AddEventActivity, DailyActivity::class.java)
                    startActivity(intent)
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}







