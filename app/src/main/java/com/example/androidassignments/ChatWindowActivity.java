package com.example.androidassignments;

import android.content.Context;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        listView = findViewById(R.id.chatList);
        textInput = findViewById(R.id.editTextChat);

        sendButton = findViewById(R.id.sendButton);
        messageAdapter = new ChatAdapter( this );
        listView.setAdapter(messageAdapter);
    }

    public void onClick(View view) {
        String msg = textInput.getText().toString();
        list.add(msg);
        messageAdapter.notifyDataSetChanged();
        textInput.setText("");
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