package com.ToxicBakery.debugging.math;

import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    private final List<Integer> fibs = new ArrayList<>();

    public Fibonacci() {
        fibs.add(0);
        fibs.add(1);
    }

    public int to(@IntRange(from = 0) int n) {
        if (fibs.size() > n) {
            return fibs.get(n);
        }

        int fib = to(n - 1) + to(n - 2);
        fibs.add(fib);

        // Sleep to simulate hard work
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fib;
    }

}
