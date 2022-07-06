package com.example.myplanner.daily

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import com.roomorama.caldroid.CaldroidFragment
import com.roomorama.caldroid.CaldroidListener
import com.simplemobiletools.commons.extensions.applyColorFilter
import com.simplemobiletools.commons.extensions.getProperTextColor
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.top_navigation.*
import kotlinx.android.synthetic.main.top_navigation.view.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class DailyActivity : AppCompatActivity() {
    private var listOfDailyPlan: ArrayList<DailyPlanner> = ArrayList()
    var Date: String? = null
    var date1: String? = ""


    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)


        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val actionBar = supportActionBar
        actionBar!!.title = "  Daily Plan  "

        val dateTime = LocalDateTime.now()   // current date
        val formatter =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)  // date time formatter
        Log.d("Date:", "parssed date ${dateTime.format(formatter)}")

        val strDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val strMonth = getMonthForInt(Calendar.getInstance().get(Calendar.MONTH))
        val strYear = Calendar.getInstance().get(Calendar.YEAR)
        Date = (strDay.toString() + " " + (strMonth) + ", " + strYear)


        var strdate = Date
        daily_tvDate.text = Date.toString()
        Log.d("Date", strdate.toString())

          daily_ivAdd.setOnClickListener {

            val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("editOrNotDateTime", "newDateTime")
            editor.commit()

            val intent = Intent(this@DailyActivity, AddEventActivity::class.java)
            startActivity(intent)
        }


        //   setupButtons()
        setList()

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

    private fun setList() {
        val db = DatabaseHandler(this)

        listOfDailyPlan = db.getTodayPlan("'" + Date.toString() + "'");
        Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
        daily_rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan, date1.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    fun showGoToDateDialog() {
        // currentFragments.last().showGoToDateDialog()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is
        // .
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        return when (id) {
            R.id.item1 -> {
                val db = DatabaseHandler(this)
                listOfDailyPlan = db.getTodayPlan("'" + Date.toString() + "'")
                Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
                daily_rvList.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan, date1.toString())
                true
            }
            R.id.item2 -> {

                val db = DatabaseHandler(this)
                listOfDailyPlan = db.getAllPlan();
                Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
                daily_rvList.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan, date1.toString())
                true
            }

            R.id.item3 -> {

                val db = DatabaseHandler(this)
                listOfDailyPlan = db.getCompletedPlan();
                Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
                daily_rvList.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                daily_rvList.adapter =
                    DailyCompleteAdaper(this, listOfDailyPlan, date1.toString())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
