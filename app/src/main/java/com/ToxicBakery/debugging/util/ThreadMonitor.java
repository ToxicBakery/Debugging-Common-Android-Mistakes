package com.ToxicBakery.debugging.util;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadMonitor {

    private final AtomicBoolean running;

    public ThreadMonitor(int threshold) {
        running = new AtomicBoolean(true);
        new Thread(new Runner(running, threshold)).start();
    }

    public void destroy() {
        running.set(false);
    }

    class Runner implements Runnable {

        private final AtomicBoolean running;
        private final int threshold;

        Runner(AtomicBoolean running,
               int threshold) {

            this.running = running;
            this.threshold = threshold;
        }

        @Override
        public void run() {
            while (running.get()) {
                SystemClock.sleep(50);
                int count = Thread.currentThread()
                        .getThreadGroup()
                        .activeCount();

                if (count > threshold) {
                    Log.e("ThreadCount", "Counted: " + count);
                    throw new RuntimeException("Counted: " + count);
                }
            }
        }

    }

}
