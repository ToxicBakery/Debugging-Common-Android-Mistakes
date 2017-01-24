package com.ToxicBakery.debugging;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import com.ToxicBakery.debugging.demo.DemoDebuggerTooManyThreads;
import com.ToxicBakery.debugging.demo.DemoDebuggerWrongThread;
import com.ToxicBakery.debugging.demo.DemoDebuggerWrongValue;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Pair<Integer, Class<? extends Activity>>> activityClasses = new LinkedList<>();

    public MainActivity() {
        activityClasses.add(createPair(R.string.activity_wrong_value, DemoDebuggerWrongValue.class));
        activityClasses.add(createPair(R.string.activity_wrong_thread, DemoDebuggerWrongThread.class));
        activityClasses.add(createPair(R.string.activity_too_many_threads, DemoDebuggerTooManyThreads.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.Adapter adapter = new MainAdapter(activityClasses);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    Pair<Integer, Class<? extends Activity>> createPair(@StringRes int res,
                                                        Class<? extends Activity> activityClass) {

        return new Pair<Integer, Class<? extends Activity>>(res, activityClass);
    }

}
