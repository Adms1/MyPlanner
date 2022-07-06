package com.example.myplanner.week

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myplanner.R
import kotlinx.android.synthetic.main.activity_weekly_review.*

class WeeklyReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_review)

        wr_ivBack.setOnClickListener {
            onBackPressed()
        }

    }
}