package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import kotlin.text.Regex;

public class LoginActivity extends AppCompatActivity {

    protected final String ACTIVITY_NAME = "LoginActivity";
    protected Button loginButton;
    protected EditText emailField;
    protected EditText passwordField;
    SharedPreferences emailPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "in onCreate()");
        loginButton = findViewById(R.id.loginButton);
        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
        emailPref = this.getPreferences(MODE_PRIVATE);
        emailField.setText(emailPref.getString("DefaultEmail", "email@domain.com"));

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

    public void login(View view) {
        String emailDomain = emailField.getText().toString();
        emailPref.edit().putString("DefaultEmail",emailDomain).commit();
        if(!passwordField.getText().toString().isEmpty() && Pattern.matches("\\w*@\\w*.com", emailDomain)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, R.string.loginError, Toast.LENGTH_LONG).show();
        }

    }
    public void print(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}