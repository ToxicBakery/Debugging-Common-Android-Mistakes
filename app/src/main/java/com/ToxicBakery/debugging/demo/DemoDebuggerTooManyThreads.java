package com.ToxicBakery.debugging.demo;

import com.ToxicBakery.debugging.math.Fibonacci;
import com.ToxicBakery.debugging.util.ThreadMonitor;

/**
 * Demo showing a different threading mistake. The calculator in this instance wants to count
 * down through the fibonacci sequence so it calls back to it's local API method {@link #calculate(int)}. Unfortunately
 * the calculator is unaware that the implementation spawns new threads so each calculate call spawns two threads. From
 * a debugging perspective this can be difficult to track down when using anonymous classes combined with API based
 * threading such as in RxJava.
 */
public class DemoDebuggerTooManyThreads extends BaseDemo {

// Thread monitor will crash the app before too many threads occur, uncomment to observe this.
    /*private ThreadMonitor threadMonitor;

    @Override
    protected void onResume() {
        super.onResume();

        threadMonitor = new ThreadMonitor(10);
    }

    @Override
    protected void onPause() {
        super.onPause();

        threadMonitor.destroy();
    }*/

    @Override
    void calculate() {
        calculate(15);
    }

    void calculate(int n) {
        Fibonacci fibonacci = new Fibonacci();
        Calculator calculator = new Calculator(fibonacci, n);
        new Thread(calculator).start();
    }

    @Override
    protected void setOutput(String output) {
        if (this.output.getText().length() == 0) {
            super.setOutput(output);
        }
    }

    class Calculator implements Runnable {

        private final Fibonacci fibonacci;
        private final int n;

        Calculator(Fibonacci fibonacci,
                   int n) {

            this.fibonacci = fibonacci;
            this.n = n;
        }

        @Override
        public void run() {
            if (n < 0) {
                return;
            }

            final int fib = fibonacci.to(n);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setOutput(String.valueOf(fib));
                }
            });

            if (n > 0) {
                calculate(n - 1);
                calculate(n - 2);
            }
        }

    }

}
