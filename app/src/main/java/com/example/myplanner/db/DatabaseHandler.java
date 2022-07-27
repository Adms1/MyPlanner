package com.example.myplanner.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myplanner.pojo.DailyPlanner;

import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version 
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myplanner";
    // Table Names
    private static final String PlannerMaster = "plannermaster";
    private static final String PlannerDetails = "plannerdetails";

    // PlannerMaster Feilds
    private static final String planId = "planid";
    private static final String PlanDate = "plandate";
    private static final String EndDate = "enddate";
    private static final String EventName = "eventname";
    private static final String EventDescription = "eventdescription";
    private static final String CompanyID = "companyid";
    private static final String RepeatModeID = "repeatmodeid";
    private static final String ProrityID = "prorityid";
    private static final String IsActive = "isactive";
    private static final String CreatedDate = "createddate";
    private static final String RepeatOrNot = "repeatornot";

    // PlannerDetails Feilds

    private static final String PlanID = "planid";
    private static final String Day = "day";
    private static final String Month = "month";
    private static final String Year = "year";
    private static final String FromTime = "fromtime";
    private static final String ToTime = "totime";
    private static final String StartHours = "starthours";
    private static final String EndHours = "endhours";
    private static final String StartMin = "startmin";
    private static final String EndMin = "endmin";

    // Database helper instance
    private static DatabaseHandler _instance;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHandler getInstance(Context context) {
        if (null == _instance) {
            _instance = new DatabaseHandler(context);
        }
        return _instance;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_PlannerMaster = "CREATE TABLE " + PlannerMaster + "("
                + planId + " INTEGER PRIMARY KEY AUTOINCREMENT," + PlanDate + " TEXT," + EndDate + " TEXT ," + EventName + " TEXT, " +
                EventDescription + " TEXT, " + CompanyID + " INTEGER, " + RepeatModeID + " INTEGER, " +
                ProrityID + " INTEGER ," + IsActive + " TEXT ," + RepeatOrNot + " TEXT ," + CreatedDate + " TEXT)";
        db.execSQL(CREATE_TABLE_PlannerMaster);

        String CREATE_TABLE_PlannerDetails = "CREATE TABLE " + PlannerDetails + "("
                + PlanID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Day + " INTEGER," + Month + " INTEGER ," + Year + " INTEGER, " +
                FromTime + " INTEGER, " + ToTime + " INTEGER, " + StartHours + " INTEGER, " +
                EndHours + " INTEGER ," + StartMin + " INTEGER ," + EndMin + " INTEGER)";
        db.execSQL(CREATE_TABLE_PlannerDetails);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + PlannerMaster);
        db.execSQL("DROP TABLE IF EXISTS " + PlannerDetails);
        // Create tables again
        onCreate(db);
    }

    public void cleanAllTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + PlannerMaster);
        db.execSQL("DELETE FROM " + PlannerDetails);
    }

    //Dailyplanner Table all Opertations Start//
    // Adding new Dailyplan
/*
    public boolean addDailyPlan(String strEventName, String strEventDescription, String strDate, String strToTime, String strFromTime, String strNotification, String strCompany, String strPriority, int startHours, int startMin, int day, int endHours, int endMin, String strRepet, String month, int year, String repeatornot) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE, strDate);
        values.put(To_TIME, strToTime);
        values.put(FROM_TIME, strFromTime);
        values.put(EVENT_NAME, strEventName);
        values.put(EVENT_DESCRIPTION, strEventDescription);
        values.put(NOTIFICATION_DESCRIPTION, strNotification);
        values.put(COMPANY, strCompany);
        values.put(PRIORITY, strPriority);
        values.put(REPEAT, strRepet);
        values.put(STARTHOURS, startHours);
        values.put(STARTMINUTE, startMin);
        values.put(DAY, day);
        values.put(ENDHOURS, endHours);
        values.put(ENDMINUTE, endMin);
        values.put(STATUS, 0);
        values.put(MONTH, month);
        values.put(YEAR, year);
        values.put(REPEATORNOT, repeatornot);

        // Inserting Row
        long iResult = db.insert(TABLE_DAILYPLANNER, null, values);
        // db.insert(TABLE_SUPPLIERS, null, values);
        db.close(); // Closing database connection

        boolean blnResult = false;
        if (iResult > 0) {
            blnResult = true;
        }
        return blnResult;
    }
*/

    public boolean addEvent(String strDate, String strEndDate, String strEventName, String strEventDescription, int intCompany, int intRepeat, int intPriority, String createdDate, String repeatOrNot) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlanDate, strDate);
        values.put(EndDate, strEndDate);
        values.put(EventName, strEventName);
        values.put(EventDescription, strEventDescription);
        values.put(CompanyID, intCompany);
        values.put(RepeatModeID, intRepeat);
        values.put(ProrityID, intPriority);
        values.put(IsActive, 0);
        values.put(CreatedDate, createdDate);
        values.put(RepeatOrNot, repeatOrNot);
        // Inserting Row
        long iResult = db.insert(PlannerMaster, null, values);
        db.close(); // Closing database connection

        boolean blnResult = false;
        if (iResult > 0) {
            blnResult = true;
        }
        return blnResult;
    }

    public boolean addEventDetails(int day, int month, int year, String fromTime, String toTime, String startHours, String endHours, String startMin, String endMin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Day, day);
        values.put(Month, month);
        values.put(Year, year);
        values.put(FromTime, fromTime);
        values.put(ToTime, toTime);
        values.put(StartHours, startHours);
        values.put(EndHours, endHours);
        values.put(StartMin, startMin);
        values.put(EndMin, endMin);

        // Inserting Row
        long iResult = db.insert(PlannerDetails, null, values);

        db.close(); // Closing database connection

        boolean blnResult = false;
        if (iResult > 0) {
            blnResult = true;
        }
        return blnResult;
    }


    public ArrayList<DailyPlanner> getTodayPlan(String date) {
        //   Log.d("Date",date.toString());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery1 = "SELECT m." + planId + ",m." + PlanDate + ",m." + EndDate + ",m." + EventName + ",m." + EventDescription + ",m." + CompanyID + ",m." + RepeatModeID + ",m." + ProrityID + ",m." + IsActive + ",m." + CreatedDate + ",m." + RepeatOrNot + ",d." + planId + ",d." + Day + ",d." + Month + ",d." + Year +  ",d." + StartHours + ",d." + EndHours + ",d." + StartMin + ",d." + EndMin +",d." + FromTime + ",d." + ToTime + " FROM " +
                PlannerMaster + " AS m " + " INNER JOIN " + PlannerDetails + " as d ON " + " d." + PlanID + " = m." + PlanID + " WHERE m. " + PlanDate + " = " + date + " AND " + IsActive + " = " + 0 + " AND d." + Year + " = " + year + " ORDER BY m." + PlanDate + " ASC" + ",d." + ToTime + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery1, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                 AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return AllPlan;
    }

    public ArrayList<DailyPlanner> getAllPlan() {
        //   Log.d("Date",date.toString());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT m." + planId + ",m." + PlanDate + ",m." + EndDate + ",m." + EventName + ",m." + EventDescription + ",m." + CompanyID + ",m." + RepeatModeID + ",m." + ProrityID + ",m." + IsActive + ",m." + CreatedDate + ",m." + RepeatOrNot + ",d." + planId + ",d." + Day + ",d." + Month + ",d." + Year + ",d." + ToTime + ",d." + FromTime + ",d." + StartHours + ",d." + StartMin + ",d." + EndHours + ",d." + EndMin + ",m." + RepeatOrNot + " FROM " +
                PlannerMaster + " AS m " + " INNER JOIN " + PlannerDetails + " as d ON " + " d." + PlanID + " = m." + PlanID + " WHERE m. " + IsActive + " = " + 0 + " AND d." + Year + " = " + year + " ORDER BY m." + PlanDate + " ASC" + ",d." + ToTime + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public ArrayList<DailyPlanner> getCompletedPlan() {
        //   Log.d("Date",date.toString());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT m." + planId + ",m." + PlanDate + ",m." + EndDate + ",m." + EventName + ",m." + EventDescription + ",m." + CompanyID + ",m." + RepeatModeID + ",m." + ProrityID + ",m." + IsActive + ",m." + CreatedDate + ",m." + RepeatOrNot + ",d." + planId + ",d." + Day + ",d." + Month + ",d." + Year + ",d." + ToTime + ",d." + FromTime + ",d." + StartHours + ",d." + StartMin + ",d." + EndHours + ",d." + EndMin + ",m." + RepeatOrNot + " FROM " +
                PlannerMaster + " AS m " + " INNER JOIN " + PlannerDetails + " as d ON " + " d." + PlanID + " = m." + PlanID + " WHERE m. " + IsActive + " = " + 1 + " AND d." + Year + " = " + year + " ORDER BY m." + PlanDate + " ASC" + ",d." + ToTime + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public ArrayList<DailyPlanner> getWeeklyData() {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        // String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + DATE + " = " + 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String selectQuery = "SELECT m." + planId + ",m." + PlanDate + ",m." + EndDate + ",m." + EventName + ",m." + EventDescription + ",m." + CompanyID + ",m." + RepeatModeID + ",m." + ProrityID + ",m." + IsActive + ",m." + CreatedDate + ",m." + RepeatOrNot + ",d." + planId + ",d." + Day + ",d." + Month + ",d." + Year + ",d." + ToTime + ",d." + FromTime + ",d." + StartHours + ",d." + StartMin + ",d." + EndHours + ",d." + EndMin + ",m." + RepeatOrNot + " FROM " +
                PlannerMaster + " AS m " + " INNER JOIN " + PlannerDetails + " as d ON " + " d." + PlanID + " = m." + PlanID + " WHERE m. " + IsActive + " = " + 0 + " AND d." + Year + " = " + year + " ORDER BY m." + PlanDate + " ASC" + ",d." + ToTime + " ASC";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels

        return AllPlan;
    }

    public DailyPlanner getWeeklyClickEvent(int id) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        DailyPlanner um = new DailyPlanner(0, "", "", "", "", 0, 0, 0, "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        String selectQuery = "SELECT m." + planId + ",m." + PlanDate + ",m." + EndDate + ",m." + EventName + ",m." + EventDescription + ",m." + CompanyID + ",m." + RepeatModeID + ",m." + ProrityID + ",m." + IsActive + ",m." + CreatedDate + ",m." + RepeatOrNot + ",d." + planId + ",d." + Day + ",d." + Month + ",d." + Year + ",d." + ToTime + ",d." + FromTime + ",d." + StartHours + ",d." + StartMin + ",d." + EndHours + ",d." + EndMin + ",m." + RepeatOrNot + " FROM " +
                PlannerMaster + " AS m " + " INNER JOIN " + PlannerDetails + " as d ON " + " d." + PlanID + " = m." + PlanID + " WHERE m. " + planId + " = " + id + " AND d." + Year + " = " + year + " ORDER BY m." + PlanDate + " ASC" + ",d." + ToTime + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                um = new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20))
                ;

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return um;
    }

    public ArrayList<DailyPlanner> getMonthly(String date) {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String selectQuery = "SELECT m." + planId + ",m." + PlanDate + ",m." + EndDate + ",m." + EventName + ",m." + EventDescription + ",m." + CompanyID + ",m." + RepeatModeID + ",m." + ProrityID + ",m." + IsActive + ",m." + CreatedDate + ",m." + RepeatOrNot + ",d." + planId + ",d." + Day + ",d." + Month + ",d." + Year + ",d." + FromTime + ",d." + ToTime + ",d." + StartHours + ",d." + EndHours + ",d." + StartMin + ",d." + EndMin + ",m." + RepeatOrNot + " FROM " +
                PlannerMaster + " AS m " + " INNER JOIN " + PlannerDetails + " as d ON " + " d." + PlanID + " = m." + PlanID + " WHERE m. " + PlanDate + " = " + date + " AND " + IsActive + " = " + 0 + " AND d." + Year + " = " + year + " ORDER BY m." + PlanDate + " ASC" + ",d." + ToTime + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20)));

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }
 /*


    public ArrayList<DailyPlanner> getWeeklyData(*//*String startDate, String endDate*//*) {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        // String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + DATE + " = " + 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + "," + MONTH + "," + YEAR + "," + REPEATORNOT + " FROM " + TABLE_DAILYPLANNER + " WHERE " + YEAR + " = " + year; *//*+ DATE + " BETWEEN " + startDate + " AND " + endDate*//*
        ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15), cursor.getString(16), cursor.getInt(17), cursor.getString(18)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public ArrayList<DailyPlanner> getMonthly(String date) {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + "," + MONTH + "," + YEAR + "," + REPEATORNOT + " FROM " + TABLE_DAILYPLANNER + " WHERE " + DATE + "=" + date + " AND " + YEAR + " = " + year + " ORDER BY " + To_TIME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15), cursor.getString(16), cursor.getInt(17), cursor.getString(18)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }



    public ArrayList<DailyPlanner> getCompletedPlan() {
        //   Log.d("Date",date.toString());
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + "," + MONTH + "," + YEAR + "," + REPEATORNOT + " FROM " + TABLE_DAILYPLANNER + " WHERE " + STATUS + " = " + 1 + " AND " + YEAR + " = " + year + " ORDER BY " + DATE + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15), cursor.getString(16), cursor.getInt(17), cursor.getString(18)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public void updateDateTime(int id, String date, String ToTime, String FromTime, int startHours, int startMin, int day, int endHours, int endMin, String month) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(To_TIME, ToTime);
        cv.put(FROM_TIME, FromTime);
        cv.put(STARTHOURS, startHours);
        cv.put(STARTMINUTE, startMin);
        cv.put(ENDHOURS, endHours);
        cv.put(ENDMINUTE, endMin);
        cv.put(MONTH, month);
        // cv.put(YEAR, year);
        cv.put(String.valueOf(DAY), day);
        db.update(TABLE_DAILYPLANNER, cv, ID + "=" + id, null);
        db.close();
    }

    public void UpdateStatus(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS, 1);
        //update row
        db.update(TABLE_DAILYPLANNER, values, ID + "=" + id, null);
        db.close();
    }

    public void deleteAllPlan() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DAILYPLANNER, "", new String[]{});
        db.close();
    }*/
}
