package com.example.androidassignments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
    boolean checkFrameLayout;
    FrameLayout frameLayout;
    Cursor cursor;
    ChatWindowActivity chatWindowActivity;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 101) {
            Bundle bundle = data.getExtras();
            int id = bundle.getInt("id");
            deleteMessage(id);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = findViewById(R.id.chatList);
        textInput = findViewById(R.id.editTextChat);
        frameLayout = this.findViewById(R.id.chat_frame_layout);
        chatWindowActivity = this;

        checkFrameLayout();

        sendButton = findViewById(R.id.sendButton);
        messageAdapter = new ChatAdapter( this );
        listView.setAdapter(messageAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!checkFrameLayout){
                    Intent intent = new Intent(ChatWindowActivity.this, MessageDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("message",list.get(position));
                    bundle.putInt("id", Math.toIntExact(id));
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 100);
                }
                else if(checkFrameLayout){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    MessageFragment messageFragment = new MessageFragment(chatWindowActivity);
                    Bundle bundle = new Bundle();
                    bundle.putString("message", list.get(position));
                    bundle.putInt("id", Math.toIntExact(id));
                    messageFragment.setArguments(bundle);
                    ft.replace(R.id.chat_frame_layout, messageFragment).addToBackStack("big screen").commit();
                }
            }
        });


        ChatDatabaseHelper chatDatabaseHelper = new ChatDatabaseHelper(this);
        database = chatDatabaseHelper.getWritableDatabase();
        cursor = database.query(ChatDatabaseHelper.TABLE_NAME, null, null,null,null,null,null);
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

    private void checkFrameLayout() {
        if(frameLayout == null){
            checkFrameLayout = false;
        }
        else {
            checkFrameLayout = true;
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

    public void deleteMessage(int id){
        list.remove(id - 1);
        listView.setAdapter(new ChatAdapter(this));

        database.delete(ChatDatabaseHelper.TABLE_NAME, ChatDatabaseHelper.KEY_ID_COLUMN + " = ?", new String[]{String.valueOf(id)});
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

        @Override
        public long getItemId(int position) {
            cursor.moveToPosition(position);
            return cursor.getInt(0);
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