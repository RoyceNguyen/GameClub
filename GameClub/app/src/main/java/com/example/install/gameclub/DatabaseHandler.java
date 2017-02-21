package com.example.install.gameclub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by web on 2017-02-07.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Keep track of the database version
     */

    private static final int DATABASE_VERSION = 1;

    /**
     * Create the name of the database
     */

    private static final String DATABASE_NAME = "gamingclub";

    /**
     * Create the names of all the tables
     */
    private static final String TABLE_RULES = "rules";
    private static final String TABLE_SCHEDULE = "schedule";

    /**
     * Common column names
     */
    private static final String KEY_ID = "id";

    /**
     * Rules Table column names
     */
    private static final String KEY_TITLE = "title";
    private static final String KEY_RULE = "rule";

    /**
     * SCHEDULE Table column names
     */
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_URL= "URL";


    /**
     * Create statements for all our tables
     */

    private static final String CREATE_RULES_TABLE = "CREATE TABLE "
            + TABLE_RULES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE +
            " TEXT," + KEY_RULE + " TEXT)";

    private static final String CREATE_SCHEDULE_TABLE = "CREATE TABLE " + TABLE_SCHEDULE +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," +
            KEY_DESCRIPTION + " TEXT, " + KEY_URL + " TEXT)";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_RULES_TABLE);
        db.execSQL(CREATE_SCHEDULE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RULES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        onCreate(db);
    }

    /**
     * CRUD OPERATIONS FOR THE DATABASE AND TABLES
     * Create
     * Read
     * Update
     * Delete
     */

    /**
     * CREATE OPERATIONS
     */

    public void addSchedule(ScheduleContentFragment scheduleContentFragment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, scheduleContentFragment.getName());
        values.put(KEY_DESCRIPTION, scheduleContentFragment.getDescription());
        values.put(KEY_URL, scheduleContentFragment.getUrl());
        db.insert(TABLE_SCHEDULE, null, values);
    }



    /**
     * READ OPERATIONS
     */

    public ScheduleContentFragment getSchedule(int id){
        /**
         * Create a readable database
         */
        SQLiteDatabase db = this.getReadableDatabase();
        /**
         * Create a cursor
         * (which is able to move through and access database records)
         * Have it store all the records retrieved from the db.query()
         * cursor starts by pointing at record 0
         * Databases do not have a record 0
         * we use cursor.moveToFirst() to have it at the first record returned
         */
        Cursor cursor = db.query(TABLE_SCHEDULE,
                new String[] {KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_URL},
                "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        /**
         * We create a location object using the cursor record
         */
        ScheduleContentFragment location = new ScheduleContentFragment(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return location;
    }

    public ArrayList<ScheduleContentFragment> getAllSchedule(){
        ArrayList<ScheduleContentFragment> scheduleList = new ArrayList<ScheduleContentFragment>();
        String selectQuery = "SELECT * FROM " + TABLE_SCHEDULE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                ScheduleContentFragment schedule = new ScheduleContentFragment();
                schedule.setId(Integer.parseInt(cursor.getString(0)));
                schedule.setName(cursor.getString(1));
                schedule.setDescription(cursor.getString(2));
                schedule.setUrl(cursor.getString(3));
                scheduleList.add(schedule);

            } while(cursor.moveToNext());
        }
        return  scheduleList;
    }


    /**
     * UPDATE OPERATIONS
     */

    public int updateLocation(ScheduleContentFragment scheduleContentFragment){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, scheduleContentFragment.getName());
        values.put(KEY_DESCRIPTION, scheduleContentFragment.getDescription());
        values.put(KEY_URL, scheduleContentFragment.getUrl());
        return db.update(TABLE_SCHEDULE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(scheduleContentFragment.getId())});
    }
    /**
     * DELETE OPERATIONS
     */

    public void deleteLocation(long location_id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_SCHEDULE, KEY_ID + " = ?",
                new String[]{String.valueOf(location_id)});
    }

    /**
     * Closing the database connection
     */
    public void closeDB(){
        SQLiteDatabase db =this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }


}
