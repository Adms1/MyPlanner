package com.example.myplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daily_rvlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        daily_rvlist.adapter = PriorityTaskAdapter(this)

        daily_rvSchedule.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvSchedule.adapter = ScheduleAdapter(this)

    }
}
