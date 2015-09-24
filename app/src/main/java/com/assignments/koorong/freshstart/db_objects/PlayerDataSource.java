package com.assignments.koorong.freshstart.db_objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.assignments.koorong.freshstart.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koorong on 9/20/2015.
 */
public class PlayerDataSource
{

    // instance members for accessing the database
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbOpenHelper;

    // table name
    public static final String TABLE_NAME="Players";

    // column information
    public static final String ID_COLUMN = "_id";
    public static final int ID_COLUMN_POSITION = 0;

    public static final String NAME_COLUMN = "name";
    public static final int NAME_COLUMN_POSITION = 1;

    public static final String POSITION_COLUMN = "position";
    public static final int PLAYER_POSITION_COLUMN_POSITION = 2;

    public static final String GOALS_COLUMN = "goals";
    public static final int GOALS_COLUMN_POSITION = 3;

    // DDL SQL for creating the table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COLUMN + " TEXT, " +
                    POSITION_COLUMN + " TEXT, " +
                    GOALS_COLUMN + " INTEGER)";


    // constructor, must pass in context
    public PlayerDataSource(Context context) {
        //instantiate the helper class
        mDbOpenHelper = new PlayerDBOpenHelper(context);
    }

    // method for saving a Student to the db.
    public Player savePlayer(Player player) {

        // get writable since inserting record
        mDatabase = mDbOpenHelper.getWritableDatabase();

        // use ContentValues to group field names and values
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, player.getPlayerName());
        values.put(POSITION_COLUMN, player.getPosition());
        values.put(GOALS_COLUMN, player.getGoals());

        long dbId = mDatabase.insert(TABLE_NAME, null, values);

        player.setDbId(dbId);

        mDatabase.close();

        return player; // return the student with the dbId assigned
    }

    /* return a List containing all Players */
    public List<Player> getAllPlayers()
    {
        List<Player> players = new ArrayList<>();

        // get readable database to query for player information
        mDatabase = mDbOpenHelper.getReadableDatabase();

        Cursor cursor = mDatabase.query(TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (cursor.moveToNext())
        {
            String name = cursor.getString(NAME_COLUMN_POSITION);
            String position = cursor.getString(PLAYER_POSITION_COLUMN_POSITION);
            int goals = cursor.getInt(GOALS_COLUMN_POSITION);
            players.add(new Player(name, position, goals));
            cursor.moveToNext();
        }
        // close the cursor
        cursor.close();
        // return the list to the caller
        return players;
    }

    public boolean deleteRecords()
    {
        return true;
    }
}
