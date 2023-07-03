package com.yd.supermarket.kata.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Utility class for Id generation.
 * 
 * @author Yassine
 *
 */
public class AtomicIdCounter {

    private AtomicIdCounter() {
        // Hidden constructor
    }

    private static AtomicLong counter = new AtomicLong(1);

    public static long nextId() {
        return counter.incrementAndGet();
    }
}
