package com.example.myplanner.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myplanner.R
import com.example.myplanner.Utils
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        task_ivBack.setOnClickListener {
            onBackPressed()
        }

    }
}