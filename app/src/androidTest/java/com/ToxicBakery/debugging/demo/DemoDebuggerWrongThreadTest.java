package com.ToxicBakery.debugging.demo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ToxicBakery.debugging.R;
import com.azimolabs.conditionwatcher.ConditionWatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DemoDebuggerWrongThreadTest {

    @Rule
    public ActivityTestRule<DemoDebuggerWrongThread> activityTestRule
            = new ActivityTestRule<>(DemoDebuggerWrongThread.class);

    @Test
    public void calculate() throws Exception {
        onView(withId(R.id.calculate))
                .perform(click());

        ConditionWatcher.waitForCondition(new CalculateInstruction(activityTestRule));

        onView(withId(R.id.output))
                .check(matches(withText("55")));
    }

}