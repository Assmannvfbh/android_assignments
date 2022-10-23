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
public class LoginActivityTest {


    @Rule
    public ActivityScenarioRule rule2 = new ActivityScenarioRule<>(LoginActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule <>(LoginActivity.class);

    @Test
    public void onCreate() {
        LoginActivity loginActivity = rule.getActivity();
        View password = loginActivity.findViewById(R.id.editTextPassword);
        View email = loginActivity.findViewById(R.id.editTextEmail);
        View login = loginActivity.findViewById(R.id.loginButton);
        assertThat(password, notNullValue());
        assertThat(password, instanceOf(EditText.class));
        assertThat(email, notNullValue());
        assertThat(email, instanceOf(EditText.class));
        assertThat(login, notNullValue());
        assertThat(login, instanceOf(Button.class));
    }
}

