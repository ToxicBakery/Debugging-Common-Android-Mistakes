package com.ToxicBakery.debugging.demo;

import android.support.test.rule.ActivityTestRule;

import com.azimolabs.conditionwatcher.Instruction;

class CalculateInstruction extends Instruction {

    private final ActivityTestRule<? extends BaseDemo> activityTestRule;

    CalculateInstruction(ActivityTestRule<? extends BaseDemo> activityTestRule) {
        this.activityTestRule = activityTestRule;
    }

    @Override
    public String getDescription() {
        return "Awaiting calculations to finish.";
    }

    @Override
    public boolean checkCondition() {
        BaseDemo activity = activityTestRule.getActivity();
        return activity != null
                && !activity.output.getText().toString().isEmpty();

    }

}
