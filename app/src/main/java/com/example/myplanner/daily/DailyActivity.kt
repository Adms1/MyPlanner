package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.pojo.DailyPlanner
import kotlinx.android.synthetic.main.activity_daily.*

class DailyActivity : AppCompatActivity() {
    private var listOfDailyPlan: ArrayList<DailyPlanner> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

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

    private fun setList() {
        val db = DatabaseHandler(this)
        listOfDailyPlan = db.getAllPlan();
        Log.d("sizeOfPlan", listOfDailyPlan.size.toString())
        daily_rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvList.adapter = DailyAdaper(this, listOfDailyPlan)
    }
}