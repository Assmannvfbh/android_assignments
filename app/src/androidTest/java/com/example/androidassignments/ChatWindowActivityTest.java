package com.example.androidassignments;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ChatWindowActivityTest {


    @Rule
    public ActivityScenarioRule rule2 = new ActivityScenarioRule<>(ChatWindowActivity.class);

    @Rule
    public ActivityTestRule<ChatWindowActivity> rule = new ActivityTestRule <>(ChatWindowActivity.class);

    @Test
    public void onCreate() {
        ChatWindowActivity chatWindowActivity = rule.getActivity();
        View button = chatWindowActivity.findViewById(R.id.sendButton);
        View text = chatWindowActivity.findViewById(R.id.editTextChat);
        assertThat(button, notNullValue());
        assertThat(button, instanceOf(Button.class));
        assertThat(text, notNullValue());
        assertThat(text, instanceOf(EditText.class));

    }
}
