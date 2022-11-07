package com.example.androidassignments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected final String ACTIVITY_NAME = "MainActivity";
    protected Button mainButton;
    protected Button chatButton;
    protected Button toolbarButton;
    protected Button weatherButton;
    protected Spinner spinner;
    String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "in onCreate()");
        mainButton = findViewById(R.id.buttonMain);

        chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(MainActivity.this, ChatWindowActivity.class);
                startActivity(intent);
            }
        });
        toolbarButton = findViewById(R.id.toolbarButton1);
        toolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(intent);
            }
        });

        weatherButton = findViewById(R.id.main_weather_button);
        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherForecast.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });

        spinner = findViewById(R.id.main_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.canadian_cities));
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city = "Ottawa";
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "in onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "in onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "in onDestroy()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "in onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "in onResume()");
    }

    public void buttonOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
        startActivityForResult(intent,10);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");

        if(resultCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            Toast.makeText(this, getResources().getString(R.string.toastPutExtra) + " " + messagePassed,Toast.LENGTH_LONG).show();
        }
    }

    public void print(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}