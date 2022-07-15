package com.example.myplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myplanner.Utils.Companion.openDailyCal
import com.example.myplanner.daily.DailyActivity
import com.example.myplanner.db.DatabaseHandler
import com.example.myplanner.month.MonthlyActivity
import com.example.myplanner.week.activity_weekly
import kotlinx.android.synthetic.main.activity_dashboard.*


class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        var db= DatabaseHandler(getApplicationContext())
      //  db.deleteAllPlan()
        db_btnDaily.setOnClickListener { openDailyCal(this@DashboardActivity, DailyActivity::class.java) }
        db_btnWeek.setOnClickListener { openDailyCal(this@DashboardActivity, activity_weekly::class.java) }
        db_btnMonth.setOnClickListener { openDailyCal(this@DashboardActivity, MonthlyActivity::class.java) }
    }


}