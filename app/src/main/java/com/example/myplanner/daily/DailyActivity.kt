package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import kotlinx.android.synthetic.main.activity_daily.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class DailyActivity : AppCompatActivity() {
    private var listOfDailyPlan: ArrayList<DailyPlanner> = ArrayList()
    var Date: String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        val strDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val strMonth = getMonthForInt(Calendar.getInstance().get(Calendar.MONTH))
        val strYear = Calendar.getInstance().get(Calendar.YEAR)
        Date = (strDay.toString() + " " + (strMonth) + ", " + strYear)
        var strdate = Date
        Log.d("Date" , strdate.toString())

        daily_tvDate.text = Date

        daily_ivBack.setOnClickListener {
            onBackPressed()
        }

        daily_ivAdd.setOnClickListener {

            val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("editOrNotDateTime", "newDateTime")
            editor.commit()

            val intent = Intent(this@DailyActivity, AddEventActivity::class.java)
            startActivity(intent)
        }

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
        listOfDailyPlan = db.getAllPlan(/*Date.toString()*/);
        Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
        daily_rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan)

    }
}
