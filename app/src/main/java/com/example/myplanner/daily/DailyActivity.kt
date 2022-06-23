package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import kotlinx.android.synthetic.main.activity_daily.*
import java.text.DateFormatSymbols
import java.util.*

class DailyActivity : AppCompatActivity() {
    private var listOfDailyPlan: ArrayList<DailyPlanner> = ArrayList()
    var Date: String? = null
    var yesterday: String? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        assert(
            supportActionBar != null
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val actionBar = supportActionBar
        actionBar!!.title = "  Daily Plan  "


        val strDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val strMonth = getMonthForInt(Calendar.getInstance().get(Calendar.MONTH))
        val strYear = Calendar.getInstance().get(Calendar.YEAR)
        Date = (strDay.toString() + " " + (strMonth) + ", " + strYear)
        var strdate = Date
        Log.d("Date", strdate.toString())

        /* daily_tvDate.text = Date

         daily_ivBack.setOnClickListener {
             onBackPressed()
         }*/

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
        listOfDailyPlan = db.getTodayPlan("'" + Date.toString() + "'");
        Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
        daily_rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
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
                daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan)
                true
            }
            R.id.item2 -> {
                Toast.makeText(applicationContext, "Item 2 Selected", Toast.LENGTH_LONG).show()
                true
            }
            R.id.item3 -> {

                val db = DatabaseHandler(this)
                listOfDailyPlan = db.getAllPlan();
                Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
                daily_rvList.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
