package com.example.androidassignments;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ListItemsActivityTest {

    @Rule
    public ActivityScenarioRule rule2 = new ActivityScenarioRule<>(ListItemsActivity.class);

    @Rule
    public ActivityTestRule<ListItemsActivity> rule = new ActivityTestRule <>(ListItemsActivity.class);

    @Test
    public void onCreate() {
        ListItemsActivity listItemsActivity = rule.getActivity();
        View checkbox = listItemsActivity.findViewById(R.id.checkbox1);
        View image = listItemsActivity.findViewById(R.id.imageButton);
        View switch1 = listItemsActivity.findViewById(R.id.switch1);
        View radioButton = listItemsActivity.findViewById(R.id.radioButton);
        assertThat(checkbox, notNullValue());
        assertThat(checkbox, instanceOf(CheckBox.class));
        assertThat(image, notNullValue());
        assertThat(image, instanceOf(ImageButton.class));
        assertThat(switch1, notNullValue());
        assertThat(switch1, instanceOf(Switch.class));
        assertThat(radioButton, notNullValue());
        assertThat(radioButton, instanceOf(RadioButton.class));

    }
}