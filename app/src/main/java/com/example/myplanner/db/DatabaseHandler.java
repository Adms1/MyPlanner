package com.example.myplanner.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myplanner.pojo.DailyPlanner;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version 
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myplanner";

    // Table Names
    private static final String TABLE_DAILYPLANNER = "dailyplanner";


    // Brand Table Columns names
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String To_TIME = "to_time";
    private static final String FROM_TIME = "from_time";
    private static final String EVENT_NAME = "event_name";
    private static final String EVENT_DESCRIPTION = "event_description";
    private static final String NOTIFICATION_DESCRIPTION = "notification_description";
    private static final String COMPANY = "company";
    private static final String PRIORITY = "priority";
    private static final String REPEAT = "repeat";
    private static final String STATUS = "status";


    private static final String STARTHOURS = "starthours";
    private static final String STARTMINUTE = "startmin";
    private static final String DAY = "day";
    /*  private static final int MONTH = "month";
      private static final int YEAR = "year";*/
    private static final String ENDHOURS = "endhours";
    private static final String ENDMINUTE = "endmin";


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

        String CREATE_TABLE_BRAND = "CREATE TABLE " + TABLE_DAILYPLANNER + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE + " TEXT," + To_TIME + " TEXT ," + FROM_TIME + " TEXT, " +
                EVENT_NAME + " TEXT, " + EVENT_DESCRIPTION + " TEXT, " + NOTIFICATION_DESCRIPTION + " TEXT, " +
                COMPANY + " TEXT ," + PRIORITY + " TEXT ," + REPEAT + " TEXT," + STATUS + " TEXT," +
                STARTHOURS + " INTEGER," + STARTMINUTE + " INTEGER ," + DAY + " INTEGER," + ENDHOURS + " INTEGER," + ENDMINUTE + " INTEGER)";
        db.execSQL(CREATE_TABLE_BRAND);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILYPLANNER);
        // Create tables again
        onCreate(db);
    }

    public void cleanAllTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DAILYPLANNER);
    }

    //Dailyplanner Table all Opertations Start//
    // Adding new Dailyplan
    public boolean addDailyPlan(String strEventName, String strEventDescription, String strDate, String strToTime, String strFromTime, String strNotification, String strCompany, String strPriority, int startHours, int startMin, int day, int endHours, int endMin, String strRepet) {
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

        values.put(String.valueOf(STARTHOURS), startHours);
        values.put(String.valueOf(STARTMINUTE), startMin);
        values.put(String.valueOf(DAY), day);
        values.put(String.valueOf(ENDHOURS), endHours);
        values.put(String.valueOf(ENDMINUTE), endMin);

        values.put(STATUS, 0);

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


    public ArrayList<DailyPlanner> getTodayPlan(String date) {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + DATE + " = " + date + " AND " + STATUS + " = " + 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public ArrayList<DailyPlanner> getEventTitle() {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + STATUS + " = " + 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public ArrayList<DailyPlanner> getWeeklyData(String startDate, String endDate) {
        //   Log.d("Date",date.toString());
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        // String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + DATE + " = " + 0;

        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + DATE + " BETWEEN " + startDate + " AND " + endDate;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15)));
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
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + STATUS + " = " + 0 + " ORDER BY " + DATE + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15)));
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
        ArrayList<DailyPlanner> AllPlan = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT " + ID + "," + DATE + "," + To_TIME + "," + FROM_TIME + "," + EVENT_NAME + "," + EVENT_DESCRIPTION + "," + NOTIFICATION_DESCRIPTION + "," + COMPANY + "," + PRIORITY + "," + REPEAT + "," + STARTHOURS + "," + STARTMINUTE + "," + DAY + "," + ENDHOURS + "," + ENDMINUTE + "," + STATUS + " FROM " + TABLE_DAILYPLANNER + " WHERE " + STATUS + " = " + 1 + " ORDER BY " + DATE + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AllPlan.add(new DailyPlanner(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getString(15)));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels


        return AllPlan;
    }

    public void updateDateTime(int id, String date, String ToTime, String FromTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATE, date);
        cv.put(To_TIME, ToTime);
        cv.put(FROM_TIME, FromTime);

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
    }
}
