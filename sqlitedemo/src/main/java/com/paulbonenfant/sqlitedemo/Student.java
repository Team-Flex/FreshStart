package com.paulbonenfant.sqlitedemo;

/**
 * Created by bonenfan on 13/09/2015.
 */
public class Student {

    private String mName;
    private String mNumber;
    private boolean mFullTime;
    private long mDbId; // will populate once inserted into db

    public Student(String name, String number, boolean fullTime) {
        mName = name;
        mNumber = number;
        mFullTime = fullTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public boolean isFullTime() {
        return mFullTime;
    }

    public void setFullTime(boolean fullTime) {
        mFullTime = fullTime;
    }

    public long getDbId() {
        return mDbId;
    }

    public void setDbId(long dbId) {
        mDbId = dbId;
    }
}

