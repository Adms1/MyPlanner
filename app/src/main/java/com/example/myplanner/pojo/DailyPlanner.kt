package com.example.myplanner.pojo


class DailyPlanner(
    var id: Int,
    var plandate: String,
    var EndDate: String,
    var EventName: String,
    var EventDescription: String,
    var CompanyID: Int,
    var RepeatModeID: Int,
    var ProrityID: Int,
    var IsActive: String,
    var CreatedDate: String,
    var RepeatOrNot: String,

    var planIdDetail: Int,
    var Day: Int,
    var Month: Int,
    var Year: Int,
    var StartHours: Int,
    var EndHours: Int,
    var StartMin: Int,
    var EndMin: Int,
    var FromTime: Int,
    var ToTime: Int,


    )

fun DailyPlanner() {}