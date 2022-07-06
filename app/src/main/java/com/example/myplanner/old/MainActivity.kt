package com.example.myplanner.old

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplanner.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var currentFragments = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daily_rvlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        daily_rvlist.adapter = PriorityTaskAdapter(this)

        daily_rvSchedule.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvSchedule.adapter = ScheduleAdapter(this)

    }

}
