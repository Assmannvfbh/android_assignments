package com.example.androidassignments;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TestToolbarTest {

    @Rule
    public ActivityScenarioRule rule2 = new ActivityScenarioRule<>(TestToolbar.class);

    @Rule
    public ActivityTestRule<TestToolbar> rule = new ActivityTestRule <>(TestToolbar.class);

    @Test
    public void onCreate() {
        TestToolbar toolbarActivity = rule.getActivity();
        View campfire = toolbarActivity.findViewById(R.id.campfireMenu);
        View hearts = toolbarActivity.findViewById(R.id.heartsMenu);
        View sun = toolbarActivity.findViewById(R.id.sunMenu);
        assertThat(campfire, notNullValue());
        assertThat(campfire, instanceOf(View.class));
        assertThat(hearts, notNullValue());
        assertThat(hearts, instanceOf(View.class));
        assertThat(sun, notNullValue());
        assertThat(sun, instanceOf(View.class));

    }
}
