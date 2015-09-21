package com.paulbonenfant.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.paulbonenfant.sqlitedemo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bonenfan on 13/09/2015.
 */
public class StudentDataSource {

    // instance members for accessing the database
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbOpenHelper;

    // table name
    public static final String TABLE_NAME="Students";

    // column information
    public static final String ID_COLUMN = "_id";
    public static final int ID_COLUMN_POSITION = 0;

    public static final String NAME_COLUMN = "name";
    public static final int NAME_COLUMN_POSITION = 1;

    public static final String NUMBER_COLUMN = "number";
    public static final int NUMBER_COLUMN_POSITION = 2;

    public static final String FULL_TIME_COLUMN = "fullTime";
    public static final int FULL_TIME_COLUMN_POSITION = 3;

    // DDL SQL for creating the table careful with adding spaces!!
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COLUMN + " TEXT, " +
                    NUMBER_COLUMN + " TEXT, " +
                    FULL_TIME_COLUMN + " INTEGER)";


    // constructor, must pass in context
    public StudentDataSource(Context context) {
        //instantiate the helper class
        mDbOpenHelper = new StudentDBOpenHelper(context);
    }

    // method for saving a Student to the db.
    public Student saveStudent(Student student) {

        // get writable since inserting record
        mDatabase = mDbOpenHelper.getWritableDatabase();

        // use ContentValues to group field names and values
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, student.getName());
        values.put(NUMBER_COLUMN, student.getNumber());
        values.put(FULL_TIME_COLUMN, student.isFullTime() ? 1: 0);

        long dbId = mDatabase.insert(TABLE_NAME, null, values);

        student.setDbId(dbId);

        mDatabase.close();

        return student; // return the student with the dbId assigned
    }

    /* return a List containing all students */
    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();

        // get readable database since we only want to read
        mDatabase = mDbOpenHelper.getReadableDatabase();

        Cursor cursor = mDatabase.query(TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            String name = cursor.getString(NAME_COLUMN_POSITION);
            String number = cursor.getString(NUMBER_COLUMN_POSITION);
            boolean fullTime = cursor.getInt(FULL_TIME_COLUMN_POSITION) == 1;
            students.add(new Student(name, number, fullTime) );
            cursor.moveToNext();
        }
        // close the cursor
        cursor.close();

        // return the list to the caller
        return students;
    }
}