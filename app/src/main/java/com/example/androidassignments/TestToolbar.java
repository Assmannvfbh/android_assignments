package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {

    FloatingActionButton floatButton;
    Dialog dialog;
    SharedPreferences messagePref;

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
        dialog = new Dialog(this);
        messagePref = this.getSharedPreferences("Message",MODE_PRIVATE);
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
                Snackbar.make(this.floatButton, this.getSharedPreferences("Message",MODE_PRIVATE).getString("Message",getResources().getString(R.string.menu1snackbar)) , Snackbar.LENGTH_LONG).show();
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
                openDialog();
                break;
            case R.id.aboutMenu:
                Log.d("Toolbar", "Option About selected");
                Toast.makeText(this, getResources().getString(R.string.about), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void openDialog(){
        dialog.setContentView(R.layout.dialog);
        Button okButton = dialog.findViewById(R.id.ok1);
        EditText editTest = dialog.findViewById(R.id.newMessageEdit);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTest.getText().toString();
                if( !text.isEmpty()) {
                    messagePref.edit().putString("Message", text).commit();
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(okButton.getContext(), getResources().getString(R.string.noCharacter), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cancelButton = dialog.findViewById(R.id.cancel1);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cancelButton.getContext(), "dismiss", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}