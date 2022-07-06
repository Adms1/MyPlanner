package com.example.myplanner.week

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myplanner.R
import kotlinx.android.synthetic.main.activity_habit_tracker.*
import kotlinx.android.synthetic.main.activity_weekly_review.*
import kotlinx.android.synthetic.main.activity_weekly_review.wr_ivBack

class HabitTrackerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_tracker)

        habit_ivBack.setOnClickListener {
            onBackPressed()
        }

    }
}