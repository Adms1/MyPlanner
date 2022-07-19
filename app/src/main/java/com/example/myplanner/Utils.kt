package com.example.myplanner

import android.app.Activity
import android.content.Context
import android.content.Intent

class Utils {

    companion object{

        fun openDailyCal(context: Context, activity: Class<*>){

            val intent = Intent(context, activity)
            (context as Activity).startActivity(intent)

        }



    }

}