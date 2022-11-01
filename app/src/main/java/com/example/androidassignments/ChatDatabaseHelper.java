package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String ACTIVITY_NAME = "ChatDatabaseHelper";

    private static final String DATABASE_NAME = "Messages.db";
    private static final int VERSION_NUM = 4;

    public static final String TABLE_NAME = "Table1";
    public static final String KEY_ID_COLUMN = "KEY_ID";
    public static final String KEY_MESSAGE_COLUMN = "KEY_MESSAGE";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + KEY_ID_COLUMN
            + " integer primary key autoincrement, " + KEY_MESSAGE_COLUMN
            + " text not null);";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    //Created for JUnit Testing
    public ChatDatabaseHelper(Context ctx, String databaseName) {
        super(ctx, databaseName, null, VERSION_NUM);
    }

    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i(ACTIVITY_NAME, "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion=" + oldVersion + " new Version=" + newVersion);
        onCreate(db);
    }


}
