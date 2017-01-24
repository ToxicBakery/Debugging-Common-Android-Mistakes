package com.ToxicBakery.debugging.demo;

import android.support.test.rule.ActivityTestRule;

import com.azimolabs.conditionwatcher.Instruction;

class CalculateInstruction extends Instruction {

    private static final long TEN_SECONDS_IN_MILLISECONDS = 10L * 1000L;

    private final ActivityTestRule<? extends BaseDemo> activityTestRule;
    private final long startTime;

    CalculateInstruction(ActivityTestRule<? extends BaseDemo> activityTestRule) {
        this.activityTestRule = activityTestRule;
        startTime = System.currentTimeMillis();
    }

    @Override
    public String getDescription() {
        return "Awaiting calculations to finish.";
    }

    @Override
    public boolean checkCondition() {
        if (System.currentTimeMillis() - startTime > TEN_SECONDS_IN_MILLISECONDS) {
            return true;
        }

        BaseDemo activity = activityTestRule.getActivity();
        return activity != null
                && !activity.output.getText().toString().isEmpty();
    }

}
