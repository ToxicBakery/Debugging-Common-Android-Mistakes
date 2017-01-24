package com.ToxicBakery.debugging.demo;

import com.ToxicBakery.debugging.math.Fibonacci;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Demo showing a common situation when multiple threads modify the same value indirectly. The callers have to know that
 * the fibonacci implementation is not thread safe. In this example we can expect to typically see the wrong value on
 * multicore CPUs. Single core CPUs should typically produce the correct result.
 * <p>
 * Excepted fib value is 55
 * <p>
 * The reason for the flaky test result is that internally the fibonacci implementation uses a list to cache results.
 * While this optimization improves nth call performance it is inherently not thread safe.
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
