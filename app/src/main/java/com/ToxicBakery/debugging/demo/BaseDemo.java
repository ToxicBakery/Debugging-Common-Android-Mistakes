package com.ToxicBakery.debugging.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ToxicBakery.debugging.R;


public abstract class BaseDemo extends AppCompatActivity implements View.OnClickListener {

    TextView output;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        output = (TextView) findViewById(R.id.output);

        findViewById(R.id.calculate).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculate:
                calculate();
                break;
            default:
                throw new IllegalArgumentException("Unhandled view: " + v.getId());
        }
    }

    abstract void calculate();

    protected void setOutput(String output) {
        this.output.setText(output);
    }

}
