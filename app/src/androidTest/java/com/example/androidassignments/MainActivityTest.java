package com.example.androidassignments;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule rule2 = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule <MainActivity> rule = new ActivityTestRule <>(MainActivity.class);



    @Test
    public void onCreate() {
    MainActivity mainActivity = rule.getActivity();
        View toolbarButton = mainActivity.findViewById(R.id.toolbarButton1);
        View chatButton = mainActivity.findViewById(R.id.chatButton);
        View IAmAButton = mainActivity.findViewById(R.id.buttonMain);
        assertThat(toolbarButton, notNullValue());
        assertThat(toolbarButton, instanceOf(Button.class));
        assertThat(chatButton, notNullValue());
        assertThat(chatButton, instanceOf(Button.class));
        assertThat(IAmAButton, notNullValue());
        assertThat(IAmAButton, instanceOf(Button.class));

    }

    @Test
    public void onStart() {
        ActivityScenario scenario = rule2.getScenario();
        Lifecycle.State state = scenario.getState();
        Log.i ("Eichhornchen" , state.name()) ;

    }
    @Test
    public void onStop() {
    }

    @Test
    public void onDestroy() {
    }

    @Test
    public void onPause() {
    }

    @Test
    public void onResume() {
    }

    @Test
    public void buttonOnClick() {
    }

    @Test
    public void onActivityResult() {
    }

    @Test
    public void print() {
    }
}
