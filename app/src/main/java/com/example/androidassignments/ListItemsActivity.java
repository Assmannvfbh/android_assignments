package com.example.androidassignments;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    private final int requestCode = 10;
    protected final String ACTIVITY_NAME = "ListItemsActivity";
    ImageButton imageButton;
    Switch aSwitch;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "in onCreate()");


        //builder setter methods



        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, requestCode);
            }
        });


        aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(buttonView.getContext() ,R.string.switchON , Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(buttonView.getContext() ,R.string.switchOFF , Toast.LENGTH_LONG).show();
                }
            }
        });


        checkBox = findViewById(R.id.checkbox1);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                builder.setTitle(R.string.dialogBoxTitle)
                        .setMessage(R.string.dialogBoxMessage)
                        .setPositiveButton(R.string.dialogYes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", getResources().getString(R.string.myResponse));
                                setResult(Activity.RESULT_OK, resultIntent);

                                finish();
                                print("onFinish()");
                            }
                        })
                        .setNegativeButton(R.string.dialogNo, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(buttonView.getContext(),"Nothing happend", Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageButton.setImageBitmap(photo);
        }
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

    public void print(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}