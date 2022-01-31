package com.example.myplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myplanner.Utils.Companion.openDailyCal
import com.example.myplanner.daily.DailyActivity
import com.example.myplanner.month.MonthlyActivity
import com.example.myplanner.task.TaskActivity
import com.example.myplanner.week.WeekPlanActivity
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        db_btnDaily.setOnClickListener { openDailyCal(this@DashboardActivity, DailyActivity::class.java) }
        db_btnWeek.setOnClickListener { openDailyCal(this@DashboardActivity, WeekPlanActivity::class.java) }
        db_btnMonth.setOnClickListener { openDailyCal(this@DashboardActivity, MonthlyActivity::class.java) }

    }

}