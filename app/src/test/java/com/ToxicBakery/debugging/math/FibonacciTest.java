package com.ToxicBakery.debugging.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void to() throws Exception {
        // Cache instance calc
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(0, fibonacci.to(0));
        assertEquals(1, fibonacci.to(1));
        assertEquals(1, fibonacci.to(2));
        assertEquals(2, fibonacci.to(3));
        assertEquals(3, fibonacci.to(4));
        assertEquals(5, fibonacci.to(5));
        assertEquals(8, fibonacci.to(6));
        assertEquals(13, fibonacci.to(7));
        assertEquals(21, fibonacci.to(8));
        assertEquals(34, fibonacci.to(9));

        // No cache instance calc
        assertEquals(34, new Fibonacci().to(9));
    }

}