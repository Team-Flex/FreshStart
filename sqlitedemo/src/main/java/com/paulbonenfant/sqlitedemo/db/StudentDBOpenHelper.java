package com.paulbonenfant.sqlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bonenfan on 13/09/2015.
 */
public class StudentDBOpenHelper extends SQLiteOpenHelper {

    // create a constant for the database name
    private static final String DATABASE_NAME = "Students.db";

    // create a constant for the version
    private static final int DATABASE_VERSION = 1;

    public StudentDBOpenHelper(Context context) {
        // must call superclasses constructor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Called by the runtime if the database doesn't exist yet
        // The runtime creates the database for us, but we have to
        // create the structure here
        db.execSQL(StudentDataSource.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // for now we'll just drop the table and call onCreate() not checking for
        // version info.
        // In real app probably migrate from old structure to new one
        db.execSQL("DROP TABLE IF EXISTS " + StudentDataSource.TABLE_NAME);
        onCreate(db);
    }
}
