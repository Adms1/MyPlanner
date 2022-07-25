package com.example.myplanner

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        fun openDailyCal(context: Context, activity: Class<*>) {

            val intent = Intent(context, activity)
            (context as Activity).startActivity(intent)

        }


        fun parseDateToddMMyyyy(time: String?): String? {
            val inputPattern = "yyyy-MM-dd"
            val outputPattern = "yyyy/MM/dd"
            val inputFormat = SimpleDateFormat(inputPattern)
            val outputFormat = SimpleDateFormat(outputPattern)
            var date: Date? = null
            var str: String? = null
            try {
                date = inputFormat.parse(time)
                str = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return str
        }
    }

}