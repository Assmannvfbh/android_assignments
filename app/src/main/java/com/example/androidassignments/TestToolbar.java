package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {

    FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar3);
        floatButton = findViewById(R.id.floatingActionButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, getResources().getString(R.string.snackbar), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.sunMenu:
                Log.d("Toolbar", "Option Sun selected");
                Snackbar.make(this.floatButton, getResources().getString(R.string.snackbar1), Snackbar.LENGTH_LONG).show();
                break;
            case R.id.campfireMenu:
                Log.d("Toolbar", "Option Campfire selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.alertDialogToolbar);
                // Add the buttons
                builder.setPositiveButton(R.string.dialogYes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.dialogNo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TestToolbar.this, "Do nothing", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.heartsMenu:
                Log.d("Toolbar", "Option Hearts selected");
                break;
            case R.id.aboutMenu:
                Log.d("Toolbar", "Option About selected");
                Toast.makeText(this, getResources().getString(R.string.about), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}