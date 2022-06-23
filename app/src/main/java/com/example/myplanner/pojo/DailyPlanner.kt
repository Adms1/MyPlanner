package com.example.myplanner.pojo

import java.util.*


class DailyPlanner(
    var id: Int,
    var date: String,
    var to_time: String,
    var from_time: String,
    var event_name: String,
    var event_description: String,
    var notification_description: String,
    var location: String,
    var repeat: String
)