package com.example.myplanner.week

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myplanner.R
import kotlinx.android.synthetic.main.activity_weekly_plan.*

class WeekPlanActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weekly_plan)

        weekly_rvlist.layoutManager = GridLayoutManager(this, 3)
        weekly_rvlist.adapter = WeekPlanAdapter(this)

    }

}