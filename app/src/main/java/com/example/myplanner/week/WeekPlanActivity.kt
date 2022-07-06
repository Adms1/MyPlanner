package com.example.myplanner.week

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import kotlinx.android.synthetic.main.activity_weekly_goals.*
import kotlinx.android.synthetic.main.activity_weekly_plan.*

class WeekPlanActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weekly_plan)

        val arr: ArrayList<String> = ArrayList()
        arr.add("Top Tasks")
        arr.add("Weekly Goals")
        arr.add("Weekly Review")
        arr.add("Habit Tracker")

        weekly_rvlist.layoutManager = GridLayoutManager(this, 3)
        weekly_rvlist.adapter = WeekPlanAdapter(this, arr)

        weekly_ivBack.setOnClickListener {
            onBackPressed()
        }

        weekly_ivAdd.setOnClickListener {
            val intent = Intent(this@WeekPlanActivity, AddEventActivity::class.java)
            startActivity(intent)
        }

    }

}