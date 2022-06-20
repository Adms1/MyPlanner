package com.example.myplanner.daily

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplanner.AddEventActivity
import com.example.myplanner.R
import com.example.myplanner.month.MonthlyEventListAdapter
import kotlinx.android.synthetic.main.activity_daily.*
import kotlinx.android.synthetic.main.activity_monthly.*
import kotlinx.android.synthetic.main.activity_task.*

class DailyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        daily_ivBack.setOnClickListener {
            onBackPressed()
        }

        daily_ivAdd.setOnClickListener {

            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("newDateTime","newDateTime")
            editor.commit()

            val intent = Intent(this@DailyActivity, AddEventActivity::class.java)
            startActivity(intent)
        }

        setList()
    }

    private fun setList(){
        daily_rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        daily_rvList.adapter = DailyAdaper(this)
    }
}