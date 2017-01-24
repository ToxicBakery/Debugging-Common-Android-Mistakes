package com.ToxicBakery.debugging.demo;

import com.ToxicBakery.debugging.math.Fibonacci;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Demo showing a common situation where multiple threads end up modifying the same value indirectly. The callers have
 * to know that fibonacci is not thread safe. In this example we can expect to see the wrong value and sometimes
 * ArrayIndexOutOfBoundsException. The exception is easy to catch, the wrong value is not as it may not always be wrong.
 * <p>
 * Excepted fib value is 55
 * <p>
 * This implementation should work as expected in a typical single core emulator setup but will fail on most real
 * devices which can lead to flaky testing and getting out into the wild without being a caught issue.
 */
public class DemoDebuggerWrongValue extends BaseDemo {

    private static final int CPU_CORES = Runtime.getRuntime().availableProcessors();
    final Executor executor = Executors.newFixedThreadPool(CPU_CORES);

    @Override
    void calculate() {
        Fibonacci fibonacci = new Fibonacci();
        for (int i = 0; i < 10; ++i) {
            Calculator calculator = new Calculator(fibonacci);
            executor.execute(calculator);
        }
    }

    class Calculator implements Runnable {

        private final Fibonacci fibonacci;

        Calculator(Fibonacci fibonacci) {
            this.fibonacci = fibonacci;
        }

        @Override
        public void run() {
            final int fib = fibonacci.to(10);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setOutput(String.valueOf(fib));
                }
            });
        }

    }

}
