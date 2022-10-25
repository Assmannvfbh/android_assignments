package com.example.androidassignments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatWindowActivity extends AppCompatActivity {
    ListView listView;
    EditText textInput;
    Button sendButton;
    List<String> list = new ArrayList<>();
    ChatAdapter messageAdapter;
    public static final String ACTIVITY_NAME = "ChatWindowActivity";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = findViewById(R.id.chatList);
        textInput = findViewById(R.id.editTextChat);

        sendButton = findViewById(R.id.sendButton);
        messageAdapter = new ChatAdapter( this );
        listView.setAdapter(messageAdapter);

        ChatDatabaseHelper chatDatabaseHelper = new ChatDatabaseHelper(this);
        database = chatDatabaseHelper.getWritableDatabase();
        Cursor cursor = database.query(ChatDatabaseHelper.TABLE_NAME, null, null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString( cursor.getColumnIndexOrThrow( ChatDatabaseHelper.KEY_MESSAGE_COLUMN) ) );
            String message = cursor.getString(cursor.getColumnIndexOrThrow(ChatDatabaseHelper.KEY_MESSAGE_COLUMN));
            list.add(message);
            cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount() );

        for(int i = 0; i < cursor.getColumnCount(); i++){
            Log.i(ACTIVITY_NAME, "Cursor column: " + cursor.getColumnName(i));
        }
    }

    public void onClick(View view) {
        String msg = textInput.getText().toString();
        list.add(msg);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ChatDatabaseHelper.KEY_MESSAGE_COLUMN,msg);
        database.insert(ChatDatabaseHelper.TABLE_NAME,null,contentValues);
        messageAdapter.notifyDataSetChanged();
        textInput.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(@NonNull Context context) {
            super(context, 0);
        }

        public int getCount(){
            return list.size();
        }

        public String getItem(int position){
            return list.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindowActivity.this.getLayoutInflater();

            View result = null;

            if(position%2 == 0) {

                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }
            else {

                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }

    }


}