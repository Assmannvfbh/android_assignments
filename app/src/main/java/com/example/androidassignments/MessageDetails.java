package com.example.androidassignments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MessageDetails extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        Bundle bundle = getIntent().getExtras();
        MessageFragment mf = new MessageFragment(null);
        mf.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.message_details_frame, mf).addToBackStack("TA1").commit();
    }
}