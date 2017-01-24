package com.ToxicBakery.debugging.demo;

import com.ToxicBakery.debugging.math.Fibonacci;

/**
 * Simple thread mistake posting UI updates on the wrong thread. Unfortunately the {@link #setOutput(String)} method
 * catches exceptions so when viewed and tested, the correct result happens and the issue is quietly logged.
 * <p>
 * Excepted fib value is 55
 */
public class DemoDebuggerWrongThread extends BaseDemo {

    @Override
    void calculate() {
        Fibonacci fibonacci = new Fibonacci();
        Calculator calculator = new Calculator(fibonacci);
        new Thread(calculator).start();
    }

    @Override
    protected void setOutput(String output) {
        try {
            super.setOutput(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Calculator implements Runnable {

        private final Fibonacci fibonacci;

        Calculator(Fibonacci fibonacci) {
            this.fibonacci = fibonacci;
        }

        @Override
        public void run() {
            int fib = fibonacci.to(10);
            setOutput(String.valueOf(fib));
        }

    }

}
