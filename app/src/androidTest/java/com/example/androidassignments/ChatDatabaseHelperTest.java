package com.example.androidassignments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class ChatDatabaseHelperTest {

    ChatDatabaseHelper chatDatabaseHelper;
    SQLiteDatabase database;

    //Mock Data
    public static final String DATABASE_NAME = "TEST_DATA";
    public static final String MESSAGE = "MESSAGE";



    @Rule
    public ActivityTestRule<ChatWindowActivity> rule = new ActivityTestRule <>(ChatWindowActivity.class);


    @Test
    public void onCreate() {
        chatDatabaseHelper = new ChatDatabaseHelper(rule.getActivity());
        database = chatDatabaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME,null);
        cursor.moveToFirst();
        Assert.assertEquals(ChatDatabaseHelper.KEY_ID_COLUMN, cursor.getColumnName(0));
        Assert.assertEquals(ChatDatabaseHelper.KEY_MESSAGE_COLUMN, cursor.getColumnName(1));
    }

    @Test
    public void onUpgrade() {
        chatDatabaseHelper = new ChatDatabaseHelper(rule.getActivity(), DATABASE_NAME);
        database = chatDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChatDatabaseHelper.KEY_MESSAGE_COLUMN,MESSAGE);
        database.insert(ChatDatabaseHelper.TABLE_NAME,null,contentValues);

        chatDatabaseHelper.onUpgrade(database,1,2);

        Cursor cursor = database.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME,null);
        cursor.moveToFirst();

        Assert.assertEquals(ChatDatabaseHelper.KEY_ID_COLUMN, cursor.getColumnName(0));
        Assert.assertEquals(ChatDatabaseHelper.KEY_MESSAGE_COLUMN, cursor.getColumnName(1));
        Assert.assertEquals(0, cursor.getCount());

        String path = database.getPath();
        File fdelete = new File(path);
        if (fdelete.exists()) {
            fdelete.delete();
        }

    }
    @After
    public void tearDown(){
        String path = database.getPath();
        File fdelete = new File(path);
        if (fdelete.exists()) {
            fdelete.delete();
        }
    }
}
